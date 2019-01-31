package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices;

import javax.ejb.Local;


import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface QuestionServicesLocal  extends IGenericDAO<Question>{

}
