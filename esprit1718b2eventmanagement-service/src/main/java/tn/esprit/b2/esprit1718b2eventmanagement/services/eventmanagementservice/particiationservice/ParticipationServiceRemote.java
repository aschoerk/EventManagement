package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface ParticipationServiceRemote extends IGenericDAO<Participation>{

}
