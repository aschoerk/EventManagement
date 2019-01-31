package tn.esprit.b2.esprit1718b2eventmanagement.mBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesLocal;

@ManagedBean
@SessionScoped
public class BankaccountBean {

	
	@EJB
	private BankaccountServicesLocal BankaccountServicesLocal;
	private BankAccount account;
	
	
	@PostConstruct
	public void init() {
		account = new BankAccount();
		
	}


	public BankAccount getAccount() {
		return account;
	}


	public void setAccount(BankAccount account) {
		this.account = account;
	}


	
}
