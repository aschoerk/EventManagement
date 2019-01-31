package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Task;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class TaskService
 */
@Stateless
public class TaskService extends GenericDAO<Task> implements TaskServiceRemote, TaskServiceLocal {

    /**
     * Default constructor. 
     */
    public TaskService() {
        // TODO Auto-generated constructor stub
    	super(Task.class);
    }

}
