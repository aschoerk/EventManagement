package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

/**
 * Entity implementation class for Entity: Club
 *
 */
@Entity

public class Club implements Serializable {
    @Id
	@Column(name = "CLUB_ID")
	private int id;
	@Column(name = "CLUB_NAME")
	private String name;
	@Column(name = "CLUB_SECTOR")
	private String sector;
	private static final long serialVersionUID = 1L;
	
	@OneToMany( mappedBy="club",cascade = CascadeType.MERGE,fetch = FetchType.EAGER) 
	private List<Booth> booths;

	public Club(int id, String name, String sector) {
		super();
		this.id = id;
		this.name = name;
		this.sector = sector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Club() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Booth> getBooths() {
		return booths;
	}

	public void setBooths(List<Booth> booths) {
		this.booths = booths;
	}
	public void linkBoothsToThisClub(List<Booth> booths) {
		this.booths = booths;
		for (Booth b : booths) {
			b.setClub(this);
		}
}
}
