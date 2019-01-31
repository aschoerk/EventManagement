package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface ParticipationServiceLocal extends IGenericDAO<Participation> {

}
