package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class EventServices
 */
@Stateless
public class EventServices extends GenericDAO<Event> implements EventServicesRemote, EventServicesLocal {

    /**
     * Default constructor. 
     */

	public EventServices() {
		super(Event.class);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
