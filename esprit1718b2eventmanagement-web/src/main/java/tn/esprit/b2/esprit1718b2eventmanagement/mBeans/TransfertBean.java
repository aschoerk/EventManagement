package tn.esprit.b2.esprit1718b2eventmanagement.mBeans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesLocal;

@ManagedBean
@ViewScoped
public class TransfertBean {
	@ManagedProperty (value = "#{identity}") // this references the @ManagedBean named user
    private Identity identity;
	@EJB
	private TransfertServivesLocal transfertServivesLocal;
	private List<Transfer> transfers;
	private List<Transfer> Rtransfers;
	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@EJB
	private BankServicesLocal bankServicesLocal ;
	@EJB
	private AuctionServicesLocal auctionServicesLocal;
	private Transfer transfer;
	private BankAccount account_reciving;
	private float amount;

	@PostConstruct
	public void init() {
		account_reciving= new BankAccount();
		transfer = new Transfer();
		transfers= transfertServivesLocal.findAll();
		Rtransfers= findalltransferts();
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	public void doaddTransfert() {
		// setAmount(Float.parseFloat(Arrays.toString(FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap().get("from-value"))));
		
		System.out.println("okkkkkkkkkkkkkkkk");
      	transfer.setAccount_reciving(bankServicesLocal.findByRIB(account_reciving.getRIB()));
		transfer.setAccount_sending(identity.getUser().getBankAccounts());
		transfer.setBank(identity.getUser().getBankAccounts().getBank());
		transfer.setDate_transfert(auctionServicesLocal.GetServerDatetypeDate());
		//transfer.setAmount(500f);
		bankServicesLocal.TransfertforAccount(transfer.getAccount_sending(), transfer.getAmount());
		bankServicesLocal.TransfertFromAccounts(transfer.getAccount_sending(), transfer.getAccount_reciving(), transfer.getAmount());
		
		
	}

	public BankAccount getAccount_reciving() {
		return account_reciving;
	}

	public void setAccount_reciving(BankAccount account_reciving) {
		this.account_reciving = account_reciving;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}
public List<Transfer> findalltransferts()
    {
	User user=identity.getUser();
	transfers=transfertServivesLocal.findAll();
	for(int i=0;i<transfers.size();i++)
	{
		if (transfers.get(i).getAccount_sending().getOwner()==user)
		{
			Rtransfers.add(transfers.get(i));
		}
	
	}
	
	return Rtransfers;
	
	}

public List<Transfer> getRtransfers() {
	return Rtransfers;
}

public void setRtransfers(List<Transfer> rtransfers) {
	Rtransfers = rtransfers;
}
}
