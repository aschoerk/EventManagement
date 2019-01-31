package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Comment;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CommentServices
 */
@Stateless
@LocalBean
public class CommentServices extends GenericDAO<Comment> implements CommentServicesRemote, CommentServicesLocal {

    @PersistenceContext
	private EntityManager entityManager;
    @EJB
    private CommentServicesLocal commentServicesLocal;
    Comment comment;
    
    public CommentServices() {
        super(Comment.class);
    }

	@Override
	public void assignCommentToPost(Comment comment, Post post) {
		comment.setPost(post);
		commentServicesLocal.update(comment);
		
	}

	@Override
	public Post findPostById(int id) {
		return entityManager.find(Post.class, id);
	}

	@Override
	public void saveCommentOrUpdate(Comment comment) {
		entityManager.merge(comment);
	}

	@Override
	public List<Comment> findCommentsByPostId(int idPost) {
		List<Comment> comments;
		TypedQuery<Comment> query = entityManager.createQuery(
				"SELECT i FROM Comment i JOIN FETCH  i.post p WHERE p.id = :idpost", Comment.class);
			query.setParameter("idpost", idPost);
			query.setFirstResult(0);
			query.setMaxResults(5);
			comments = query.getResultList();
		return comments;
/*public List<Booth> findBoothbytype(String type) {
		SELECT p FROM ShoppingBag b JOIN b.product p JOIN b.member m WHERE m.id = :memberId
		List<Item> items = em.createQuery("SELECT i FROM Item i JOIN FETCH i.order", Item.class)
		.getResultList();

		List<Booth> booths;
		booths = entityManager
				.createQuery("SELECT u FROM Booth AS u WHERE u.type=:type", Booth.class)
				.setParameter("type",type).getResultList();
		return booths;*/
	}

	@Override
	public int numberOfCommentByPost(int idpost) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementCommentByPost(int idpost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementCommentByPost(int idpost) {
		// TODO Auto-generated method stub
		
	}

	
}
