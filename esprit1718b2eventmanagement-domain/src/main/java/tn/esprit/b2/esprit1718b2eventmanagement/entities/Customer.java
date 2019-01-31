package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity

public class Customer extends User implements Serializable {

	private String expertises;
	private String interests;
	private File CV;
	private String fbLink;
	private String linkeninLink;
	private String twitterLink;
	private String externalLink;
	private String skypeName;
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}



	public Customer(int id, String name, String login, String password, String email, String country, String state,
			String zipCode, String street, long phone, Date birthdate, File profilePic, String description,
			String jobtitle, String expertises, String interests, File cV, String fbLink, String linkeninLink,
			String twitterLink, String externalLink, String skypeName) {
		super(id, name, login, password, email, country, state, zipCode, street, phone, birthdate, profilePic,
				description, jobtitle);
		this.expertises = expertises;
		this.interests = interests;
		CV = cV;
		this.fbLink = fbLink;
		this.linkeninLink = linkeninLink;
		this.twitterLink = twitterLink;
		this.externalLink = externalLink;
		this.skypeName = skypeName;
	}

	public Customer(String name, String login, String password, String email) {
		super(name, login, password, email);
		// TODO Auto-generated constructor stub
	}

	public String getExpertises() {
		return this.expertises;
	}

	public void setExpertises(String expertises) {
		this.expertises = expertises;
	}

	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public File getCV() {
		return this.CV;
	}

	public void setCV(File CV) {
		this.CV = CV;
	}

	public String getFbLink() {
		return this.fbLink;
	}

	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}

	public String getLinkeninLink() {
		return this.linkeninLink;
	}

	public void setLinkeninLink(String linkeninLink) {
		this.linkeninLink = linkeninLink;
	}

	public String getTwitterLink() {
		return this.twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public String getExternalLink() {
		return this.externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public String getSkypeName() {
		return this.skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

}
