package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Bank
 *
 */
@Entity

public class Bank implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany( mappedBy = "bank", cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
	private List<Transfer> transfers;
	@OneToMany( mappedBy = "bank", cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
	private List<BankAccount> accounts;
	private static final long serialVersionUID = 1L;

	private int Key_1;
	private int Key_2;
	private int Key_3;
	private int Key_4;
	private float EUR_Bal;
	private float USD_Bal;
	private float AUD_Bal;
	private float TND_Bal;

	public Bank(int key_1, int key_2, int key_3, int key_4) {
		super();

		Key_1 = key_1;
		Key_2 = key_2;
		Key_3 = key_3;
		Key_4 = key_4;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", Key_1=" + Key_1 + ", Key_2=" + Key_2 + ", Key_3=" + Key_3 + ", Key_4=" + Key_4
				+ "]";
	}

	
	public float getEUR_Bal() {
		return EUR_Bal;
	}

	public void setEUR_Bal(float eUR_Bal) {
		EUR_Bal = eUR_Bal;
	}

	public float getUSD_Bal() {
		return USD_Bal;
	}

	public void setUSD_Bal(float uSD_Bal) {
		USD_Bal = uSD_Bal;
	}

	public float getAUD_Bal() {
		return AUD_Bal;
	}

	public void setAUD_Bal(float aUD_Bal) {
		AUD_Bal = aUD_Bal;
	}

	public float getTND_Bal() {
		return TND_Bal;
	}

	public void setTND_Bal(float tND_Bal) {
		TND_Bal = tND_Bal;
	}

	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public int getKey_1() {
		return Key_1;
	}

	public void setKey_1(int key_1) {
		Key_1 = key_1;
	}

	public int getKey_2() {
		return Key_2;
	}

	public void setKey_2(int key_2) {
		Key_2 = key_2;
	}

	public int getKey_3() {
		return Key_3;
	}

	public void setKey_3(int key_3) {
		Key_3 = key_3;
	}

	public int getKey_4() {
		return Key_4;
	}

	public void setKey_4(int key_4) {
		Key_4 = key_4;
	}

	public Bank() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void linkBankAccountToBank(List<BankAccount> accounts) {
		this.accounts = accounts;
		for (BankAccount a : accounts) {
			a.setBank(this);
		}
	}
}