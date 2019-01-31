package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Invitation
 *
 */
@Entity

public class Participation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String message;
	private int status;
	@ManyToOne
	private Event event;
	@ManyToOne
	private User user;
	private float rate;
	private static final long serialVersionUID = 1L;

	public Participation() {
		super();
	}
	
	public Participation(int status, Event event, User user, float rate) {
		super();
		this.status = status;
		this.event = event;
		this.user = user;
		this.rate = rate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Participation [id=" + id + ", message=" + message + ", status=" + status + ", event=" + event
				+ ", user=" + user + ", rate=" + rate + "]";
	}

}
