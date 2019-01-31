package tn.esprit.b2.esprit1718b2eventmanagement.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CompanyRepService
 */
@Stateless
@LocalBean
public class CompanyRepService extends GenericDAO<CompanyRep>
		implements CompanyRepServiceRemote, CompanyRepServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public CompanyRepService() {
		super(CompanyRep.class);
	}

	@Override
	public int countNumberOfEmployee(CompanyRep companyRep) {
		List<CompanyRep> companyReps = entityManager
				.createQuery("SELECT u FROM CompanyRep AS u WHERE u.accessType=:l AND u.company=:p", CompanyRep.class)
				.setParameter("l", 1).setParameter("p", companyRep.getCompany()).getResultList();

		return companyReps.size();
	}

	@Override
	public List<CompanyRep> findEmployeesForACompanyOwner(CompanyRep companyRep) {
		List<CompanyRep> companyReps = new ArrayList<>();
		companyReps = entityManager
				.createQuery("SELECT u FROM CompanyRep AS u WHERE u.accessType=:l AND u.company=:p", CompanyRep.class)
				.setParameter("l", 1).setParameter("p", companyRep.getCompany()).getResultList();

		return companyReps;
	}

	@Override
	public List<CompanyRep> findEmployeesForACompanyOwnerByAString(String searching, CompanyRep companyRep) {
		List<CompanyRep> companyReps = new ArrayList<>();
		companyReps = entityManager.createQuery(
				"SELECT u FROM CompanyRep AS u WHERE u.accessType=:l AND u.company=:p AND (u.hoursSpent=:d OR u.accessType=:i OR u.workPhone=:lo OR u.hourPrice=:f OR u.workingMonth=:i) ",
				CompanyRep.class).setParameter("l", 1).setParameter("p", companyRep.getCompany())
				.setParameter("d", Double.parseDouble(searching)).setParameter("i",Integer.parseInt(searching) )
				.setParameter("lo", Long.parseLong(searching)).setParameter("f",Float.parseFloat(searching) ).getResultList();
		for (CompanyRep companyRep2 : companyReps) {
			System.out.println(companyRep2.getName());
		}

		return companyReps;
	}

}
