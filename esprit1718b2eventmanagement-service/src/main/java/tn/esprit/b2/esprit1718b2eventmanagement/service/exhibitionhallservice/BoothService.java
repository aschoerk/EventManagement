package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class Booth
 */
@Stateless
public class BoothService extends GenericDAO<Booth> implements BoothServiceRemote, BoothServiceLocal {
	 @PersistenceContext
	   private EntityManager entityManager;
	  
    /**
     * Default constructor. 
     */
    public BoothService() {
        super(Booth.class);
    }
    @Override
	public Booth findBoothidbyname(String name) {
		Booth booth=new Booth();
		booth = entityManager
				.createQuery("SELECT u FROM Booth AS u WHERE u.name=:name", Booth.class)
				.setParameter("name",name).getSingleResult();
		return booth;
	}

	@Override
	public List<Booth> findBoothbytype(String type) {
		
		List<Booth> booths;
		booths = entityManager
				.createQuery("SELECT u FROM Booth AS u WHERE u.type=:type", Booth.class)
				.setParameter("type",type).getResultList();
		return booths;
	}
	@Override
	public List<Booth> findBoothbyClub(int id) {
		List<Booth> booths;
		booths = entityManager
				.createQuery("SELECT u FROM Booth AS u WHERE u.club.id=:club", Booth.class)
				.setParameter("club",id).getResultList();
		return booths;
	}
	@Override
	public List<Booth> findBoothbytypeandclub(int id,String type) {
		List<Booth> booths;
		

			booths = entityManager
					.createQuery("SELECT u FROM Booth AS u WHERE u.club.id=:club AND u.type=:type", Booth.class)
					.setParameter("club",id).setParameter("type",type).getResultList();
 
		

		return booths;
	}
	@Override
	public List<SpecialEvent> findassociatedevents(Booth b) {
		List<SpecialEvent> event;
		

		event = entityManager
				.createQuery("SELECT u FROM SpecialEvent AS u WHERE u.companyRep.id=:b.companyRep.id", SpecialEvent.class)
				.setParameter("b",b).getResultList();

	

	return event;
	}
	@Override
	public List<Booth> filterbooths(String letter) {
		List<Booth> filtbooths;
		
		filtbooths = entityManager
				.createQuery("SELECT u FROM Booth AS u WHERE u.name LIKE :letter ", Booth.class)
				.setParameter("letter","%"+letter+"%").getResultList();
		return filtbooths;
	}
	@Override
	public List<Booth> filtervisits() {
List<Booth> filtbooths;
		
		filtbooths = entityManager
				.createQuery("SELECT u FROM Booth AS u ORDER BY u DESC ", Booth.class)
				.getResultList();
		return filtbooths;
	}
	

}
