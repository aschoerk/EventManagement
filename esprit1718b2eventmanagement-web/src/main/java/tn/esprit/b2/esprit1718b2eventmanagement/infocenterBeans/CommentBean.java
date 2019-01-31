package tn.esprit.b2.esprit1718b2eventmanagement.infocenterBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Comment;
import tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices.CommentServicesLocal;

@ManagedBean
@SessionScoped
public class CommentBean {
	
	@EJB
	private CommentServicesLocal commentServicesLocal;
	/* //@ManagedProperty
	
	private Comment comment1;
	private List<Comment> comments1;
	
	
	@PostConstruct
	private void init(){
		comment = new Comment();
		comments = commentServicesLocal.findAll();
		System.out.print(comment.getPost().getTitle());
		
	};
	
	public void doAddComment(){
		System.out.print(comment.getPost().getTitle());
		commentServicesLocal.update(comment);
	}

	public void doDeleteComment(){
		commentServicesLocal.delete(comment);
		comments = commentServicesLocal.findAll();
	} */

	
		
}
