package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.File;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_user")
@Inheritance(strategy = JOINED)
public class User implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID")
	private int id;
	@Column(name = "USR_NAME")
	private String name;
	@Column(name = "USR_LOGIN")
	private String login;
	@Column(name = "USR_PWD")
	private String password;
	@Column(name = "USR_EMAIL")
	private String email;
	@Column(name = "USR_COUNTRY")
	private String country;
	@Column(name = "USR_STATE")
	private String state;
	@Column(name = "USR_ZIPCODE")
	private String zipCode;
	@Column(name = "USR_STREET")
	private String street;
	@Column(name = "USR_PHONE")
	private long phone;
	@Column(name = "USR_BIRTHDATE")
	private Date birthdate;
	@Column(name = "USR_PROFILEPIC")
	private File profilePic;
	@Column(name = "USR_DESC")
	private String description;
	@Column(name = "USR_CODE")
	private String code;
	@Column(name = "USR_TITLE")
	private String jobtitle;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER) 
	private List<Participation> participations;
	@OneToMany(mappedBy = "user" ,cascade=CascadeType.ALL )
	private List<Auction> auctions;

	@OneToMany(mappedBy="user")
	private List<UserScore> userScores;
	@OneToMany(mappedBy="user")
	private List<UserGiveaway> userGiveaways;
	@OneToMany( mappedBy="user") 
	private List<Products> products;

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@OneToOne(cascade=CascadeType.ALL)
	private BankAccount bankAccounts;


	public int getId() {
		return id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public File getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User(int id, String name, String login, String password, String email, String country, String state,
			String zipCode, String street, long phone, Date birthdate, File profilePic, String description,
			String jobtitle) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
		this.country = country;
		this.state = state;
		this.zipCode = zipCode;
		this.street = street;
		this.phone = phone;
		this.birthdate = birthdate;
		this.profilePic = profilePic;
		this.description = description;
		this.jobtitle = jobtitle;
	}

	public User() {
		super();
	}

	public User(int id, String name, String login, String password, String email, String country, String state,
			String zipCode, String street, long phone, Date birthdate, File profilePic, String description, String code,
			String jobtitle, List<Participation> participations, List<UserScore> userScores,
			List<UserGiveaway> userGiveaways, BankAccount bankAccounts) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
		this.country = country;
		this.state = state;
		this.zipCode = zipCode;
		this.street = street;
		this.phone = phone;
		this.birthdate = birthdate;
		this.profilePic = profilePic;
		this.description = description;
		this.code = code;
		this.jobtitle = jobtitle;
		this.participations = participations;
		this.userScores = userScores;
		this.userGiveaways = userGiveaways;
		this.bankAccounts = bankAccounts;
	}

	public User(String name, String login, String password, String email) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + 
				"]";
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public BankAccount getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(BankAccount bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
 

}
