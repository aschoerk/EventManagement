package tn.esprit.b2.esprit1718b2eventmanagement.services;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService extends GenericDAO<User> implements UserServiceRemote, UserServiceLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private CompanyRepServiceLocal companyRepServiceRemote;
	@EJB
	private CustomerServiceLocal customerServiceRemote;

	public UserService() {
		super(User.class);
	}

	@Override
	public User login(String login, String password) {
		User companyRep = new User();
		try {

			companyRep = entityManager
					.createQuery("SELECT u FROM User AS u WHERE u.login=:l AND u.password=:p", User.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
 
		} catch (Exception e) {
		}

		return companyRep;
	}

	@Override
	public int checkRole(User user) {

		CompanyRep companyRep = new CompanyRep();
		Customer customer = new Customer();
		
		customer = customerServiceRemote.find(user.getId());
		companyRep = companyRepServiceRemote.find(user.getId());
		
		if (customer != null) {
			return -1;
		} else if(companyRep != null) {
			return companyRep.getAccessType();
		}
		return 2;
	}
	
	@Override
	public Boolean checkLogin(String login) {
		try {
			User user1 = new User();
			user1 = entityManager.createQuery("SELECT u FROM User AS u WHERE u.login=:l", User.class)
					.setParameter("l", login).getSingleResult();
			if(user1.getLogin().equals(login)) {
				return false;
			}
			
		} catch (Exception e) {
		}
			return true;
		
	}

	@Override
	public boolean isFirstDayofMonth(Calendar calender) {
		if(calender == null)
	        return false;

	    int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
	    return (dayOfMonth == 1);
	}

	@Override
	public CompanyRep getCompanyRepFromUser(User user) {
	return	companyRepServiceRemote.find(user.getId());
	}
	
	
	
}
