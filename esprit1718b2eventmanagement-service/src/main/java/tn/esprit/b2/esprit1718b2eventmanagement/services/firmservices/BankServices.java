package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bank;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Post;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class BankServices
 */
@Stateless
public class BankServices extends GenericDAO<Bank> implements BankServicesRemote, BankServicesLocal {

	@EJB
	private BankaccountServicesLocal bankaccountServicesLocal;

	@EJB
	private TransfertServivesLocal transfertServivesLocal;
	@EJB
	private BankServicesLocal bankServicesLocal;
	@EJB
	private AuctionServicesLocal auctionServicesLocal;
	@EJB
	private UserServiceLocal userServiceLocal;
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * Default constructor.
	 */
	public BankServices() {
		// TODO Auto-generated constructor stub
		super(Bank.class);
	}

	@Override
	public void GiveRIBToAccount(Bank bank, BankAccount account) {
		String rIB = "";
		int a = (bank.getKey_1()) + account.getId();
		int b = (bank.getKey_2() % 10) + account.getId() + 1177;
		int c = (bank.getKey_3() % 100) + account.getId() + 177;
		int d = (bank.getKey_4() % 1000) + account.getId() + 77;
		rIB += GetDegit(a);
		rIB += GetDegit(b);
		rIB += GetDegit(c);
		rIB += GetDegit(d);
		account.setRIB(rIB);

		 bankaccountServicesLocal.update(account);
	}

	@Override
	public String GetDegit(int a) {
		String rIB = "";
		if ((a / 1000) != 0) {
			rIB += a;
		}
		if ((a / 100) != 0) {
			rIB += "0" + a;
		}
		if ((a / 10) != 0) {
			rIB += "00" + a;
		} else {
			rIB += "000" + a;
		}
		return rIB;
	}

	@Override
	public void AssignAccountToBank(Bank bank, BankAccount bankaccount) {
		List<BankAccount> OldBankAccount = bank.getAccounts();
		if (OldBankAccount != null) {
			OldBankAccount.add(bankaccount);
			bank.linkBankAccountToBank(OldBankAccount);
		} else {
			List<BankAccount> newOne = new ArrayList<>();
			newOne.add(bankaccount);
			bank.linkBankAccountToBank(newOne);
		}

		bankaccountServicesLocal.update(bankaccount);

	}

	@Override
	public void TransfertFromAccounts(BankAccount account_sending, BankAccount account_reciving, float amount) {

		if (account_sending.getCredit() > amount) {
			Transfer transfer = new Transfer(account_sending, account_reciving, amount);
			
			

			transfer.setBank(bankServicesLocal.find(account_sending.getBank().getId()));
			transfer.setDate_transfert(auctionServicesLocal.GetServerDatetypeDate());
			transfer.setAmount(amount);

			transfertServivesLocal.save(transfer);
			
			

		} else 
		{

			System.out.println("not enough money");
		}

	}

	@Override
	public void TransfertToAccount(BankAccount account_reciving, float amount) {
		float max =account_reciving.getCredit() + amount;
		account_reciving.setCredit(max);
		bankaccountServicesLocal.update(account_reciving);
		
	}

	@Override
	public void givebankaccounttouser(User user,String passwd,Bank bank) {
    String PAss=bankaccountServicesLocal.CryptPasswd(passwd);
    
   
   
 
    BankAccount account= new BankAccount();
    account.setBank(bank);
    account.setPasswd(PAss);
     account.setOwner(user);
   String rIB = "";
	int a=(bank.getKey_1())+account.getId();
	int b=(bank.getKey_2()%10)+account.getId()+1177;
	int c=(bank.getKey_3()%100)+account.getId()+177;
	int d=(bank.getKey_4()%1000)+account.getId()+77;
	rIB+=GetDegit(a);
	rIB+=GetDegit(b);
	rIB+=GetDegit(c);
	rIB+=GetDegit(d);
	account.setRIB(rIB);
    user.setBankAccounts(account);
    bankaccountServicesLocal.update(account);
    userServiceLocal.update(user);
   
	}

	@Override
	public void TransfertforAccount(BankAccount account_sending, float amount) {
		float max =account_sending.getCredit() - amount;
		account_sending.setCredit(max);
		bankaccountServicesLocal.update(account_sending);
		
	}
	@Override
	public BankAccount findByRIB(String RIB) {
		BankAccount account;
		
		account = entityManager.createQuery("SELECT u FROM BankAccount u WHERE u.RIB=:rib",
				BankAccount.class).setParameter("rib", RIB).getSingleResult();
		return account;
	}
}
