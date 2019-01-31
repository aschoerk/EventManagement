package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CreditCard
 *
 */
@Entity

public class CreditCard implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Degit1;
	private String Degit2;
	private String Degit3;
	private String Degit4;
	private String ExpDate;
	private int cvv;
	private String type;
	private static final long serialVersionUID = 1L;

	public CreditCard() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getDegit1() {
		return this.Degit1;
	}

	public void setDegit1(String Degit1) {
		this.Degit1 = Degit1;
	}   
	public String getDegit2() {
		return this.Degit2;
	}

	public void setDegit2(String Degit2) {
		this.Degit2 = Degit2;
	}   
	public String getDegit3() {
		return this.Degit3;
	}

	public void setDegit3(String Degit3) {
		this.Degit3 = Degit3;
	}   
	public String getDegit4() {
		return this.Degit4;
	}

	public void setDegit4(String Degit4) {
		this.Degit4 = Degit4;
	}   
	public String getExpDate() {
		return this.ExpDate;
	}

	public void setExpDate(String ExpDate) {
		this.ExpDate = ExpDate;
	}   
	public int getCvv() {
		return this.cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return (Degit1+Degit2+Degit3+Degit4
				+cvv);
	}

	
}

