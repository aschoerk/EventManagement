package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface QuestionServicesRemote extends IGenericDAO<Question>{

}
