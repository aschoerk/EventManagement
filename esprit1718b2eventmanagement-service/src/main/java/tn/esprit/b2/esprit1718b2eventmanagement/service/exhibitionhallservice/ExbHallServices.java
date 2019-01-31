package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;

/**
 * Session Bean implementation class ExbHallServices
 */
@Stateless
@LocalBean
public class ExbHallServices implements ExbHallServicesRemote, ExbHallServicesLocal {

   @PersistenceContext
   private EntityManager entityManager;
   
   @EJB
   private ClubServiceLocal clubServiceLocal;
   @EJB
   private BoothServiceLocal boothServiceLocal;
   
    public ExbHallServices() {
    }

	@Override
	public void assignBoothClub(Booth booth, Club club) {
		booth.setClub(club);
		boothServiceLocal.update(booth);
		
	}

	@Override
	public Club findClubidbyname(String name) {
		Club club=new Club();
		club = entityManager
				.createQuery("SELECT u FROM Club AS u WHERE u.name=:name", Club.class)
				.setParameter("name",name).getSingleResult();
		return club;
	}


	
	

}
