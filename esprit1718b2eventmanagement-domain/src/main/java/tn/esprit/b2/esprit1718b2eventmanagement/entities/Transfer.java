package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Transfer
 *
 */
@Entity

public class Transfer implements Serializable {

	   


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private BankAccount account_sending;
	@ManyToOne
	private BankAccount account_reciving;
	@ManyToOne
	private Bank bank;
	private float amount;
	private Date date_transfert;
	
	public BankAccount getAccount_sending() {
		return account_sending;
	}

	public BankAccount getAccount_reciving() {
		return account_reciving;
	}

	public void setAccount_sending(BankAccount account_sending) {
		this.account_sending = account_sending;
	}

	public void setAccount_reciving(BankAccount account_reciving) {
		this.account_reciving = account_reciving;
	}
	public Transfer() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	@Override
	public String toString() {
		return "Transfer [id=" + id + ", account_sending=" + account_sending.getRIB() + ", account_reciving=" + account_reciving.getRIB()
				+ ", bank=" + bank + "]";
	}

	
	public Transfer(BankAccount account_sending, BankAccount account_reciving, float amount) {
		super();
		this.account_sending = account_sending;
		this.account_reciving = account_reciving;
		this.amount = amount;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDate_transfert() {
		return date_transfert;
	}
	public void setDate_transfert(Date date_transfert) {
		this.date_transfert = date_transfert;
	}
	
}
