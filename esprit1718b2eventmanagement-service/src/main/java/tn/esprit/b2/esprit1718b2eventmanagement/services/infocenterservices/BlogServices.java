package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class BlogServices
 */
@Stateless
@LocalBean
public class BlogServices extends GenericDAO<Blog> implements BlogServicesRemote, BlogServicesLocal {

    @PersistenceContext
	EntityManager entityManager;
	
	@EJB
	BlogServicesLocal blogServicesLocal;
	
    public BlogServices() {
        super(Blog.class);
    }

	@Override
	public void assignBlogToCompany(Blog blog, Company company) {
		blog.setCompany1(company);
		blogServicesLocal.update(blog);
		
	}

	@Override
	public Company findbyid(int id) {
		return entityManager.find(Company.class, id);
	}

	@Override
	public void saveorupdate(Blog b) {
		entityManager.merge(b);
		
	}

}
