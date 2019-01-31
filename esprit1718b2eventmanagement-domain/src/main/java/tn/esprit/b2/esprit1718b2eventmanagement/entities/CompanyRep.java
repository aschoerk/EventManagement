package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: CompanyRep
 *
 */
@Entity

public class CompanyRep extends User implements Serializable {

	private String department;
	private double hoursSpent;
	private int accessType;
	private long workPhone;
	private float hourPrice;
	private int workingMonth;
	@OneToMany(mappedBy = "companyRep", cascade = { CascadeType.ALL })
	private List<Shift> shifts;
	@OneToMany(mappedBy = "companyRep" , fetch = FetchType.LAZY)
	private List<Event> events ;
	@OneToMany(mappedBy = "companyRep" , fetch = FetchType.LAZY)
	private List<Bid> bids ;
	@ManyToOne
	private Company company;
	
	@OneToMany(mappedBy = "companyRep", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Booth> booths;
	private static final long serialVersionUID = 1L;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public CompanyRep() {
		super();
	}

	public List<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	public List<Booth> getBooths() {
		return booths;
	}

	public void setBooths(List<Booth> booths) {
		this.booths = booths;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	

	public int getAccessType() {
		return this.accessType;
	}

	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}

	public long getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(long workPhone) {
		this.workPhone = workPhone;
	}

	public CompanyRep(int id, String name, String login, String password, String email, String country, String state,
			String zipCode, String street, long phone, Date birthdate, File profilePic, String description,
			String jobtitle, String department, int accessType, long workPhone) {
		super(id, name, login, password, email, country, state, zipCode, street, phone, birthdate, profilePic,
				description, jobtitle);
		this.department = department;
	
		this.accessType = accessType;
		this.workPhone = workPhone;
	}

	public CompanyRep(String name, String login, String password, String email, int accessType) {
		super(name, login, password, email);
		this.accessType = accessType;
	}

	



	public double getHoursSpent() {
		return hoursSpent;
	}

	public void setHoursSpent(double d) {
		this.hoursSpent = d;
	}

	public float getHourPrice() {
		return hourPrice;
	}

	public void setHourPrice(float hourPrice) {
		this.hourPrice = hourPrice;
	}

	public int getWorkingMonth() {
		return workingMonth;
	}

	public void setWorkingMonth(int workingMonth) {
		this.workingMonth = workingMonth;
	}

}
