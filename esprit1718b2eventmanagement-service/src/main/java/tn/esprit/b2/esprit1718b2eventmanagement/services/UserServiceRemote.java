package tn.esprit.b2.esprit1718b2eventmanagement.services;

import java.util.Calendar;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface UserServiceRemote extends IGenericDAO<User> {
	public User login(String login, String password) ;
	public int checkRole(User user);
	public Boolean checkLogin(String login);
	public CompanyRep getCompanyRepFromUser(User user);
	public boolean isFirstDayofMonth(Calendar calender);
}
