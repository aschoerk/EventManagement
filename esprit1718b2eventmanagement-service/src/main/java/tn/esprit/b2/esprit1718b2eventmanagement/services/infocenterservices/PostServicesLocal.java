package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface PostServicesLocal extends IGenericDAO<Post> {
	public List<Post> findPostsByBlog(int id);
}
