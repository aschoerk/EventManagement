package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class PostServices
 */
@Stateless
@LocalBean
public class PostServices extends GenericDAO<Post> implements PostServicesRemote, PostServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private PostServicesLocal postServicesLocal;
    
    public PostServices() {
        super(Post.class);
    }

	@Override
	public List<Post> findPostsByBlog(int id) {
		List<Post> posts;
		posts = entityManager.createQuery("SELECT u FROM Post u WHERE u.blog_id=:idp",
				Post.class).setParameter("idp", id).getResultList();
		return posts;
	}

}
