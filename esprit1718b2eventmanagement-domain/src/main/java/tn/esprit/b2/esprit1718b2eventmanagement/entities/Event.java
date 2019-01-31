package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity
@Inheritance(strategy = JOINED)

public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String category;
	private Date startDate;
	private Date endDate;
	private String eventSector;
	private Time startTime;
	private Time endTime;
	private float rating;
	private int visits;
	@OneToMany(mappedBy = "event", cascade = { CascadeType.ALL })
	private List<Participation> participations;
	@ManyToOne
	private CompanyRep companyRep;

	public Event(String name, String description, String category, Date startDate, Date endDate, String eventSector,
			Time startTime, Time endTime) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventSector = eventSector;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String type) {
		this.category = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEventSector() {
		return eventSector;
	}

	public void setEventSector(String eventSector) {
		this.eventSector = eventSector;
	}

	private static final long serialVersionUID = 1L;

	public Event() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public CompanyRep getCompanyRep() {
		return companyRep;
	}

	public void setCompanyRep(CompanyRep companyRep) {
		this.companyRep = companyRep;
	}

}
