package tn.esprit.b2.esprit1718b2eventmanagement.service.hr;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Shift;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ShiftService
 */
@Stateless
@LocalBean
public class ShiftService extends GenericDAO<Shift> implements ShiftServiceRemote, ShiftServiceLocal {
	@EJB
	CompanyRepServiceLocal com;
	@EJB
	BoothServiceLocal boothServiceLocal;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ShiftService() {
		super(Shift.class);
	}

	@Override
	public ArrayList<CompanyRep> getListCompanyReps(int id) {

		ArrayList<CompanyRep> companyReps = new ArrayList<>();

		CompanyRep companyRep = com.find(id);

		companyReps = (ArrayList<CompanyRep>) entityManager
				.createQuery("SELECT c FROM CompanyRep AS c WHERE c.company=:l AND c.accessType=:x", CompanyRep.class)
				.setParameter("l", companyRep.getCompany()).setParameter("x", 1).getResultList();

		return companyReps;
	}

	@Override
	public ArrayList<Booth> getListBoothes(int id) {

		ArrayList<Booth> booths = new ArrayList<>();

		booths = (ArrayList<Booth>) entityManager
				.createQuery("SELECT b FROM Booth AS b WHERE b.companyRep=:l ", Booth.class).setParameter("l",com.find(id))
				.getResultList();

		return booths;
	}

}
