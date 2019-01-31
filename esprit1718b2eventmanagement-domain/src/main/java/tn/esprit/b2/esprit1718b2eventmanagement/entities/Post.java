package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Article
 *
 */
@Entity

public class Post implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String photo;
	private String description;
	private Date created_at;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@ManyToOne
	private Blog blog;
	private static final long serialVersionUID = 1L;

	public Post() {
		super();
		created_at = new Date();
	}   
	
	
	public Post(String title,String description, String photo) {
		super();
		this.title = title;
		this.description = description;
		this.photo = photo;
		created_at = new Date();
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Blog getBlog() {
		return blog;
	}


	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public void linkCommentsToThisPost(List<Comment> comments){
		this.comments = comments;
		for(Comment c : comments){
			c.setPost(this);
		}
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", photo=" + photo + ", description=" + description
				+ ", created_at=" + created_at + ", comments=" + comments + ", blog=" + blog + "]";
	}

	
	
}
