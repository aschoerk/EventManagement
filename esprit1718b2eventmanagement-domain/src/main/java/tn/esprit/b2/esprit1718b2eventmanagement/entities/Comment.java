package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String commentaire;
	private int nbOfComment;
	private Date createdAt;
	
	@ManyToOne
	private Post post;
	
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
		createdAt = new Date();
		nbOfComment = nbOfComment + 1;
	}  
	
	
	public Comment(String commentaire) {
		super();
		this.commentaire = commentaire;
		createdAt = new Date();
		nbOfComment = nbOfComment + 1;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getNbOfComment() {
		return nbOfComment;
	}
	public void setNbOfComment(int nbOfComment) {
		this.nbOfComment = nbOfComment;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}


	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentaire=" + commentaire + ", nbOfComment=" + nbOfComment + ", createdAt="
				+ createdAt + ", post=" + post + "]";
	} 
	
	
	
}
