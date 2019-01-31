package tn.esprit.b2.esprit1718b2eventmanagement.services.infocenterservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Blog;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface BlogServicesLocal extends IGenericDAO<Blog> {
	public void assignBlogToCompany(Blog blog, Company company);
	public Company findbyid(int id);
	public void saveorupdate(Blog b);
}
