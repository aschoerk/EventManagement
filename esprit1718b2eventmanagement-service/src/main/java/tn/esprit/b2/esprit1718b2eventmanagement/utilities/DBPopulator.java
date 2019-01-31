package tn.esprit.b2.esprit1718b2eventmanagement.utilities;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private CompanyRepServiceLocal companyRepLocal;
	@EJB
	private CustomerServiceRemote customerServiceRemote;
	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
		User user = new User("user", "u", "u", "user@bitbox.tn");
		CompanyRep companyRep = new CompanyRep("owner", "owner", "owner", "owner@bitbox.tn" , 0);
		CompanyRep companyRep1 = new CompanyRep("companyrep", "companyrep", "companyrep", "companyrep@bitbox.tn" , 1);
		Customer customer = new Customer("customer", "c", "c", "customer@bitbox.tn");
		//userServiceLocal.update(user);
		//companyRepLocal.update(companyRep);
		//companyRepLocal.update(companyRep1);
		//customerServiceRemote.update(customer);
	}
}
