package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class SpecialEventServices
 */
@Stateless
public class SpecialEventServices extends GenericDAO<SpecialEvent>
		implements SpecialEventServicesRemote, SpecialEventServicesLocal {

	/**
	 * Default constructor.
	 */
	public SpecialEventServices() {
		// TODO Auto-generated constructor stub
		super(SpecialEvent.class);
	}

}
