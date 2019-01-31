package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;

import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface CourseServicesLocal extends IGenericDAO<Course>{

}
