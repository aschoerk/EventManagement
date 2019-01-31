package tn.esprit.b2.esprit1718b2eventmanagement.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
@LocalBean
public class CustomerService extends GenericDAO<Customer> implements CustomerServiceRemote, CustomerServiceLocal {

    /**
     * Default constructor. 
     */
    public CustomerService() {
        super(Customer.class);
    }

}
