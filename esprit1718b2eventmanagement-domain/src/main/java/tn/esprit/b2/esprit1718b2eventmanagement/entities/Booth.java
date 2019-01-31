package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Booth
 *
 */
@Entity

public class Booth implements Serializable {

	public int getVisits() {
		return visits;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOTH_ID")
	private int id;
	private static final long serialVersionUID = 1L;
	@Column(name = "BOOTH_NAME")
	private String name;
	@Column(name = "BOOTH_BANNER")
	private File banner;
	@Column(name = "BOOTH_TYPE")
	private String type;
	@Column(name = "BOOTH_VIDEO")
	private String video;
	@Column(name = "BOOTH_EXLINK")
	private String extLink;
	@Column
	private int rating;
	@Column
	private int visits;
	@Column
	private String piclink;

	@ManyToOne
	private Club club;
	@OneToMany(mappedBy = "booth")
	private List<Shift> shifts;
	@ManyToOne
	private CompanyRep companyRep;
	@OneToMany( mappedBy="booth")
	private List<Products> products;

	

	public Booth() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public File getBanner() {
		return banner;
	}

	public void setBanner(File banner) {
		this.banner = banner;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getExtLink() {
		return extLink;
	}

	public void setExtLink(String extLink) {
		this.extLink = extLink;
	}



	



	public Booth(String name, File banner, String type, String extLink, CompanyRep companyRep) {
		super();
		this.name = name;
		this.banner = banner;
		this.type = type;
		this.extLink = extLink;
		this.companyRep = companyRep;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	public String getPiclink() {
		return piclink;
	}

	public void setPiclink(String piclink) {
		this.piclink = piclink;
	}

}
