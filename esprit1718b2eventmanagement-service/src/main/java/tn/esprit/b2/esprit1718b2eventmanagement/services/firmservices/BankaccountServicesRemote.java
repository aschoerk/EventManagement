package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface BankaccountServicesRemote extends IGenericDAO<BankAccount> {

	String CryptPasswd(String passwd);
	String DecryptPasswd(String passwd);
	BankAccount FindbyRIB(String RIB,List<BankAccount> accounts);
}
