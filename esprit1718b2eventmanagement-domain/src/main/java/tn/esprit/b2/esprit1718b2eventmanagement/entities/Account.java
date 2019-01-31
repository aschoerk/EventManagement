package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity

public class Account implements Serializable {

	   
	@Id
	private int id;
	private static final long serialVersionUID = 1L;

	public Account() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
