package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Task;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface TaskServiceRemote extends IGenericDAO<Task>{

}
