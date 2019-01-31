package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bank;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface BankServicesRemote extends IGenericDAO<Bank> {
	void GiveRIBToAccount(Bank bank, BankAccount account);

	void AssignAccountToBank(Bank bank, BankAccount bankaccount);

	String GetDegit(int a);
	void  TransfertFromAccounts(BankAccount account_sending,BankAccount account_reciving, float amount) ;
	void TransfertToAccount(BankAccount account_reciving, float amount);
	void givebankaccounttouser(User user,String passwd, Bank bank);
	void TransfertforAccount(BankAccount account_sending, float amount);
	BankAccount findByRIB(String RIB);
}
