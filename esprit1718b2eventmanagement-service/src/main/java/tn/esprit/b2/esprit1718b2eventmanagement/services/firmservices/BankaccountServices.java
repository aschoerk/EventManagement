package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class BankaccountServices
 */
@Stateless

public class BankaccountServices extends GenericDAO<BankAccount>
		implements BankaccountServicesRemote, BankaccountServicesLocal {

	/**
	 * Default constructor.
	 */
	public BankaccountServices() {
		// TODO Auto-generated constructor stub
		super(BankAccount.class);
	}

	@Override
	public String CryptPasswd(String passwd) {
		String Crypt = "";
		for (int i = 0; i < passwd.length(); i++) {
			int c = passwd.charAt(i) ^ 48;
			Crypt += (char) c;
		}
		return Crypt;
	}

	@Override
	public String DecryptPasswd(String passwd) {
		String DCrypt = "";
		for (int i = 0; i < passwd.length(); i++) {
			int c = passwd.charAt(i) ^ 48;
			DCrypt += (char) c;
		}
		return DCrypt;
	}

	@Override
	public BankAccount FindbyRIB(String RIB, List<BankAccount> accounts) {
		BankAccount result=null;
		for(BankAccount b: accounts)
		{
			if(b.getRIB().equals(RIB))
			{
				result=b;
			}
		}

		return result;
	}

}
