package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BankAccount
 *
 */
@Entity

public class BankAccount implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "account_sending", cascade = CascadeType.ALL)
	private List<Transfer> sending_transfers;
	@OneToMany(mappedBy = "account_reciving", cascade = CascadeType.ALL)
	private List<Transfer> reciving_transfers;
	@OneToOne(mappedBy="bankAccounts",cascade=CascadeType.ALL)
	private User owner;
	@ManyToOne(cascade = CascadeType.ALL)
	private Bank bank;
	
	private String RIB;
	private String Passwd;
	private float credit;
	
	public BankAccount(User owner, String rIB, String passwd) {
		super();
		this.owner = owner;
		RIB = rIB;
		Passwd = passwd;
	}
	public List<Transfer> getSending_transfers() {
		return sending_transfers;
	}
	public void setSending_transfers(List<Transfer> sending_transfers) {
		this.sending_transfers = sending_transfers;
	}
	public List<Transfer> getReciving_transfers() {
		return reciving_transfers;
	}
	public void setReciving_transfers(List<Transfer> reciving_transfers) {
		this.reciving_transfers = reciving_transfers;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public String getRIB() {
		return RIB;
	}
	public void setRIB(String rIB) {
		RIB = rIB;
	}
	public BankAccount() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getPasswd() {
		return Passwd;
	}
	public void setPasswd(String passwd) {
		Passwd = passwd;
	}
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", owner=" + owner.getName() + ", bank=" + bank + ", RIB=" + RIB + ", Passwd=" + Passwd
				+ "]";
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
   
}
