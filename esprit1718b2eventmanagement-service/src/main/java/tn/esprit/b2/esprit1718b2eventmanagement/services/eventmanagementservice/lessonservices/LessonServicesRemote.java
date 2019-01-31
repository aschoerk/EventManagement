package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices;

import javax.ejb.Remote;


import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface LessonServicesRemote  extends IGenericDAO<Lesson>{

}
