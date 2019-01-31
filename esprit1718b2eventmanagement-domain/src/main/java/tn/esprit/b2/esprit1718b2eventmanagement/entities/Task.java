package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity

public class Task implements Serializable {

	   
	@Id
	private int id;
	private LocalTime startTime;
	private int duration;
	private String description;
	@ManyToOne 
	private SpecialEvent specialEvent;
	private static final long serialVersionUID = 1L;

	public Task() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}
	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}
	
   
}
