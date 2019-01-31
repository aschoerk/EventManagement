package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Comment;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CommentServicesRemote extends IGenericDAO<Comment> {
	void assignCommentToPost(Comment comment, Post post);
}
