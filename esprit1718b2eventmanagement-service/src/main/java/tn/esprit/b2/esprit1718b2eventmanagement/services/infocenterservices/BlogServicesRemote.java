package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface BlogServicesRemote extends IGenericDAO<Blog> {
	public void assignBlogToCompany(Blog blog, Company company);
}
