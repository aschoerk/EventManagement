package tn.esprit.b2.esprit1718b2eventmanagement.infocenterBeans;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Comment;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices.CommentServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices.PostServicesLocal;

@ManagedBean
@SessionScoped
public class PostBean {
	@EJB
	private PostServicesLocal postServicesLocal;
	@EJB
	private CommentServicesLocal commentServicesLocal;
	
	private Post post;
	private List<Post> posts;
	private boolean showForm;
	
	private Comment comment = new Comment();;
	private List<Comment> comments;
	int idSelectedPost;
	List<Comment> commentByPost;
	
	@PostConstruct
	private void init(){
		post = new Post();
		posts = postServicesLocal.findAll();
		showForm = false;
		//idSelectedPost = post.getId();
		//commentByPost = commentServicesLocal.findCommentsByPostId(idSelectedPost);
	}
	
	public void doSelect() {
		showForm = true;
		comments = commentServicesLocal.findAll();
	}

	public void doCancel() {
		showForm = false;
	}
	
	public void doAddPost(){
		postServicesLocal.update(post);
		showForm = false;
	}
	
	public void doDeletePost(){
		postServicesLocal.delete(post);
		posts = postServicesLocal.findAll();
		showForm = false;
	}
	
	public void doAddComment(){
		idSelectedPost = post.getId();
		comment.setPost(commentServicesLocal.findPostById(idSelectedPost));
		comment.setNbOfComment(comment.getNbOfComment()+1);
		commentServicesLocal.saveCommentOrUpdate(comment);
	}
	
	public void dodisplayCommentsByPost(){
		idSelectedPost = post.getId();
		commentByPost = commentServicesLocal.findCommentsByPostId(idSelectedPost);
		
	}


	public void uploadFile(FileUploadEvent event){
		FacesMessage message = new FacesMessage("successfull",event.getFile().getFileName()+" is uploaded");
		FacesContext.getCurrentInstance().addMessage(null, message);
		try {
			Path folder = Paths.get("");
			System.out.println(folder);
			String name = event.getFile().getFileName();
			System.out.println(name);
			String filename = name.replace(" ", "_");
			InputStream input = (InputStream) event.getFile().getInputstream();
			Path file = Files.createTempFile(folder, filename, "" + "");
			System.out.println(event.getFile().getFileName());
			Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
			this.post.setPhoto(event.getFile().getFileName());
			postServicesLocal.update(post);
			showForm = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*public void uploadFile(FileUploadEvent event)
	{
		FacesMessage message = new FacesMessage("Successfull", event.getFile().getFileName()+" is uploaded");
		FacesContext.getCurrentInstance().addMessage(null, message);
		try {
			Path folder = Paths.get("/esprit1718b2eventmanagement/esprit1718b2eventmanagement-web/src/main/webapp/infocenter/mesimages");
			System.out.println(folder);
			String x = event.getFile().getFileName() ; 
			System.out.println(x);
//			String filenameOriginal = FilenameUtils.getBaseName();
			String filename = x.replaceAll(" ", "_");

		//	String extension = FilenameUtils.getExtension(event.getFile().getFileName());
		
			InputStream input = (InputStream) event.getFile().getInputstream();
			Path file = Files.createTempFile(folder, filename, "." + "png");

			System.out.println(event.getFile().getFileName());		
			
			Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
			
			this.post.setPhoto(file.getFileName().toString().replaceAll(" ", "_"));
			postServicesLocal.update(post);
			showForm = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public int getIdSelectedPost() {
		return idSelectedPost;
	}

	public void setIdSelectedPost(int idSelectedPost) {
		this.idSelectedPost = idSelectedPost;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getCommentByPost() {
		return commentByPost;
	}

	public void setCommentByPost(List<Comment> commentByPost) {
		this.commentByPost = commentByPost;
	}
	
	
}
