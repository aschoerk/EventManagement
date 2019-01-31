package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;


@Local
public interface BankaccountServicesLocal extends IGenericDAO<BankAccount>{

	String CryptPasswd(String passwd);
	String DecryptPasswd(String passwd);
	BankAccount FindbyRIB(String RIB,List<BankAccount> accounts);
	
}
