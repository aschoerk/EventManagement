package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ClubService
 */
@Stateless
@LocalBean
public class ClubService extends GenericDAO<Club> implements ClubServiceRemote, ClubServiceLocal {
	@PersistenceContext
	   private EntityManager entityManager;
	  
    /**
     * Default constructor. 
     */
    public ClubService() {
        super(Club.class);
    }

	@Override
	public Club findClubByid(int id) {
		
			Club club=new Club();
			club = entityManager
					.createQuery("SELECT u FROM Booth AS u WHERE u.id=:id", Club.class)
					.setParameter("id",id).getSingleResult();
			return club;
		

	}
    
}
