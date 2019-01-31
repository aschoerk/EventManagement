package tn.esprit.b2.esprit1718b2eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CustomerServiceRemote extends IGenericDAO<Customer> {

}
