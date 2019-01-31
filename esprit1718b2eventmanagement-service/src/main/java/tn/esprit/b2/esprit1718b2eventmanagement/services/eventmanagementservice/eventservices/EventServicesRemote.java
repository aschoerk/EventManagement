package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;

import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface EventServicesRemote extends IGenericDAO<Event>{

}
