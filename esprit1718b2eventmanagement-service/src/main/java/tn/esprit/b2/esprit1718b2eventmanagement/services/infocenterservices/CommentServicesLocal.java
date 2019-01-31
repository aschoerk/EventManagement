package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Comment;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface CommentServicesLocal extends IGenericDAO<Comment> {
	void assignCommentToPost(Comment comment, Post post);
	public Post findPostById(int id);
	public void saveCommentOrUpdate(Comment comment);
	public List<Comment> findCommentsByPostId(int idPost);
	public int numberOfCommentByPost(int idpost);
	public void incrementCommentByPost(int idpost);
	public void decrementCommentByPost(int idpost);

}
