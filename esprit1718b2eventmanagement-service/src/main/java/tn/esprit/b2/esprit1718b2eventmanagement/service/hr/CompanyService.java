package tn.esprit.b2.esprit1718b2eventmanagement.service.hr;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CompanyService
 */
@Stateless
@LocalBean
public class CompanyService extends GenericDAO<Company> implements CompanyServiceRemote, CompanyServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public CompanyService() {
		super(Company.class);
	}

	@Override
	public Company findCompanyByUsert(CompanyRep companyRep) {
		Company company = new Company();
		if (companyRep.getCompany().equals(Company.getSerialversionuid())) {
			return companyRep.getCompany();
		} else {
			try {
				company = entityManager
						.createQuery("SELECT u FROM User AS u WHERE u.login=:l AND u.password=:p", Company.class).getSingleResult();

			} catch (Exception e) {
			}
			return company;
		}
	}

}
