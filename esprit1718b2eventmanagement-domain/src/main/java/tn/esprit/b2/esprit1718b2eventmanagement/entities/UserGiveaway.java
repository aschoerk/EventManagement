package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserGiveaways
 *
 */
@Entity

public class UserGiveaway implements Serializable {

	
	@Id
	private int id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Giveaway giveaway;
	
	private static final long serialVersionUID = 1L;

	public UserGiveaway() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Giveaway getGiveaway() {
		return giveaway;
	}

	public void setGiveaway(Giveaway giveaway) {
		this.giveaway = giveaway;
	}
   
}
