package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface LessonServicesLocal  extends IGenericDAO<Lesson>{

}
