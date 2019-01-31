package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class LessonServices
 */
@Stateless
public class LessonServices  extends GenericDAO<Lesson>implements LessonServicesRemote, LessonServicesLocal {

    /**
     * Default constructor. 
     */
    public LessonServices() {
        // TODO Auto-generated constructor stub
    	super(Lesson.class);
    }

}
