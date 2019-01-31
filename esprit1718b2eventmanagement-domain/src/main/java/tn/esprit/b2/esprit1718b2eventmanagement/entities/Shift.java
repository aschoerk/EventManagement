package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Shift
 *
 */
@Entity

public class Shift implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	@ManyToOne
	private Booth booth;
	@ManyToOne
	private CompanyRep companyRep;
	private static final long serialVersionUID = 1L;

	public Shift() {
		super();
	}

	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	public CompanyRep getCompanyRep() {
		return companyRep;
	}

	public void setCompanyRep(CompanyRep companyRep) {
		this.companyRep = companyRep;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Shift(Date startDate, Date endDate, Time startTime, Time endTime) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
