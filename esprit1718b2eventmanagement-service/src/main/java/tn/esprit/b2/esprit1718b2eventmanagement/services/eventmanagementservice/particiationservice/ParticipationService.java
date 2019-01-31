package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ParticipationService
 */
@Stateless
public class ParticipationService extends GenericDAO<Participation> implements ParticipationServiceRemote, ParticipationServiceLocal {

    /**
     * Default constructor. 
     */
    public ParticipationService() {
        // TODO Auto-generated constructor stub
    	super(Participation.class);
    }
    

}
