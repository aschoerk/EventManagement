package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;




/**
 * Session Bean implementation class QuestionServices
 */
@Stateless
public class QuestionServices  extends GenericDAO<Question>implements QuestionServicesRemote, QuestionServicesLocal {

    /**
     * Default constructor. 
     */
    public QuestionServices() {
        // TODO Auto-generated constructor stub
    	super(Question.class);
    }

}
