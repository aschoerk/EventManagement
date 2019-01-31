package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CourseServices
 */
@Stateless
public class CourseServices extends GenericDAO<Course> implements CourseServicesRemote, CourseServicesLocal {

    /**
     * Default constructor. 
     */
    public CourseServices() {
        // TODO Auto-generated constructor stub
    	super(Course.class);
    }

}
