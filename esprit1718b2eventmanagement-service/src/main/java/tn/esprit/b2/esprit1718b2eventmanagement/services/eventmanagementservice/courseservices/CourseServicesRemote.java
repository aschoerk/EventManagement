package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;

import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CourseServicesRemote extends IGenericDAO<Course>{

}
