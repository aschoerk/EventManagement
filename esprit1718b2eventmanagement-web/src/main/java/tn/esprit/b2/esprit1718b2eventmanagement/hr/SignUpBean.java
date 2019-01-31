package tn.esprit.b2.esprit1718b2eventmanagement.hr;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@ManagedBean
@RequestScoped
public class SignUpBean {
	private User user;
	private String psw;
	private String role;
	private boolean pwdchek = false;
	private boolean logincheck = false;
	@EJB
	CompanyRepServiceLocal companyRepServiceLocal;
	@EJB
	CustomerServiceLocal customerServiceLocal;
	@EJB
	CompanyServiceLocal companyServiceLocal;
	@EJB
	UserServiceLocal userServiceLocal;
	@ManagedProperty("#{identity}")
	private Identity identity;

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	public String goSignUp() {
		return "/SignUp";
	}

	@PostConstruct
	public void init() {
		identity.isHeLogged();
		user = new User();
	}

	public void doSignUp() throws MessagingException, IOException {

		String code;
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		code = saltStr;
		Boolean loginner = userServiceLocal.checkLogin(user.getLogin());
		user.setCode(code);
		if (loginner != false) {
			if (user.getPassword().equals(psw)) {
				if (role.equals("Customer")) {
					String USER_NAME = "pdev.esprit.2018@gmail.com"; // GMail user name (just the part before
																		// "@gmail.com")
					String PASSWORD = "pdevesprit2018"; // GMail password
					String from = USER_NAME;
					String pass = PASSWORD;
					String[] to = new String[] { user.getEmail() }; // list of recipient email addresses

					String subject = "email verification";
					String body = code;

					sendFromGMail(from, pass, to, subject, body);
					Customer customer = new Customer();
					// *transform user to customer
					customer.setName(user.getName());
					customer.setLogin(user.getLogin());
					customer.setPassword(user.getPassword());
					customer.setEmail(user.getEmail());
					customer.setPhone(user.getPhone());
					customer.setCode(user.getCode());
					// ****************
					customerServiceLocal.update(customer);
					System.out.println("done customer");
					ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
					context.redirect(context.getRequestContextPath() + "/loginer.jsf");
				} else {
					// *********************************************
					String USER_NAME = "pdev.esprit.2018@gmail.com"; // GMail user name (just the part before
																		// "@gmail.com")
					String PASSWORD = "pdevesprit2018"; // GMail password
					String from = USER_NAME;
					String pass = PASSWORD;
					String[] to = new String[] { user.getEmail() }; // list of recipient email addresses

					String subject = "email verification";
					String body = code;

					sendFromGMail(from, pass, to, subject, body);
					Company company = new Company("your Company Name");
					companyServiceLocal.save(company);
					CompanyRep companyRep = new CompanyRep();
					companyRep.setCompany(company);
					// *transform user to customer
					companyRep.setName(user.getName());
					companyRep.setLogin(user.getLogin());
					companyRep.setPassword(user.getPassword());
					companyRep.setEmail(user.getEmail());
					companyRep.setPhone(user.getPhone());
					companyRep.setCode(user.getCode());
					companyRep.setAccessType(0);
					// ****************
					companyRepServiceLocal.update(companyRep);

					ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
					context.redirect(context.getRequestContextPath() + "/loginer.jsf");
					// *******************************************************************************

					System.out.println("done Company Rep");
				}
			} else {
				this.pwdchek = true;
			}
		} else {
			this.logincheck = true;
		}

	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {

			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AddressException ae) {

			System.out.println("fuuuuuuuuck");
		} catch (MessagingException me) {
			System.out.println("youuuuuuuuuuuuu");
		}
	}

	public boolean isPwdchek() {
		return pwdchek;
	}

	public void setPwdchek(boolean pwdchek) {
		this.pwdchek = pwdchek;
	}

	public boolean isLogincheck() {
		return logincheck;
	}

	public void setLogincheck(boolean logincheck) {
		this.logincheck = logincheck;
	}
	public void goBack() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "/loginer.jsf");

	}
}
