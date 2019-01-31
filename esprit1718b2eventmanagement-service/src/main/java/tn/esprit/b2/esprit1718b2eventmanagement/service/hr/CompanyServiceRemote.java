package tn.esprit.b2.esprit1718b2eventmanagement.service.hr;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CompanyServiceRemote extends IGenericDAO<Company> {
	public Company findCompanyByUsert(CompanyRep companyRep);
}
