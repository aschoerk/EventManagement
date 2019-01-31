package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface EventServicesLocal extends IGenericDAO<Event>{

}
