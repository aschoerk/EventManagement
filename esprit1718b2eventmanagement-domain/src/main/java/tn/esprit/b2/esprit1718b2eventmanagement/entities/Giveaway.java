package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Giveaway
 *
 */
@Entity

public class Giveaway implements Serializable {

	   
	@Id
	private int id;
	@ManyToOne
	private Event event;
	private String entryLink;
	private int entryScore;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="giveaway")
	private List<UserGiveaway> userGiveaways;
	public Giveaway() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getEntryLink() {
		return entryLink;
	}
	public void setEntryLink(String entryLink) {
		this.entryLink = entryLink;
	}
	public int getEntryScore() {
		return entryScore;
	}
	public void setEntryScore(int entryScore) {
		this.entryScore = entryScore;
	}
	public List<UserGiveaway> getUserGiveaways() {
		return userGiveaways;
	}
	public void setUserGiveaways(List<UserGiveaway> userGiveaways) {
		this.userGiveaways = userGiveaways;
	}
   
}
