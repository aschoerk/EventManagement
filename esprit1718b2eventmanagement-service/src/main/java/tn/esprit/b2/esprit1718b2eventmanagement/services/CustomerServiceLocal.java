package tn.esprit.b2.esprit1718b2eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface CustomerServiceLocal extends IGenericDAO<Customer> {

}
