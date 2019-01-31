package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity

public class Company implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private File logo;
	private String websiteLink;
	private int phoneNumber;
	private String facebookLink;
	private String linkedinLink;
	private String twitterLink;
	private String status;
	private int yearOfEstablishment;
	private String about;
	private String keyWordSector;
	private int numberOfEmployes;
	private int rating;
	private int numberOfVisitors;
	private int discounts;
	
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	private List<CompanyRep> companyReps;
	
	@OneToOne(mappedBy = "company1", cascade = CascadeType.ALL)
	private Blog blog;
	
	private static final long serialVersionUID = 1L;

	public Company() {
		super();
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
	public File getLogo() {
		return logo;
	}
	public void setLogo(File logo) {
		this.logo = logo;
	}
	public String getWebsiteLink() {
		return websiteLink;
	}
	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFacebookLink() {
		return facebookLink;
	}
	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}
	public String getLinkedinLink() {
		return linkedinLink;
	}
	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}
	public String getTwitterLink() {
		return twitterLink;
	}
	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getYearOfEstablishment() {
		return yearOfEstablishment;
	}
	public void setYearOfEstablishment(int yearOfEstablishment) {
		this.yearOfEstablishment = yearOfEstablishment;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getKeyWordSector() {
		return keyWordSector;
	}
	public void setKeyWordSector(String keyWordSector) {
		this.keyWordSector = keyWordSector;
	}
	public int getNumberOfEmployes() {
		return numberOfEmployes;
	}
	public void setNumberOfEmployes(int numberOfEmployes) {
		this.numberOfEmployes = numberOfEmployes;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNumberOfVisitors() {
		return numberOfVisitors;
	}
	public void setNumberOfVisitors(int numberOfVisitors) {
		this.numberOfVisitors = numberOfVisitors;
	}
	public int getDiscounts() {
		return discounts;
	}
	public void setDiscounts(int discounts) {
		this.discounts = discounts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public Company(String name) {
		super();
		this.name = name;
	}
	public Company(int id, String name, File logo, String websiteLink, int phoneNumber, String facebookLink,
			String linkedinLink, String twitterLink, String status, int yearOfEstablishment, String about,
			String keyWordSector, int numberOfEmployes, int rating, int numberOfVisitors, int discounts) {
		super();
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.websiteLink = websiteLink;
		this.phoneNumber = phoneNumber;
		this.facebookLink = facebookLink;
		this.linkedinLink = linkedinLink;
		this.twitterLink = twitterLink;
		this.status = status;
		this.yearOfEstablishment = yearOfEstablishment;
		this.about = about;
		this.keyWordSector = keyWordSector;
		this.numberOfEmployes = numberOfEmployes;
		this.rating = rating;
		this.numberOfVisitors = numberOfVisitors;
		this.discounts = discounts;
	}

   
	
}
