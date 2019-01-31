package tn.esprit.b2.esprit1718b2eventmanagement.hr;

import static java.util.Calendar.MONTH;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Shift;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.ShiftServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class CompanyRepBean {

	@EJB
	UserServiceLocal userServiceLocal;
	@EJB
	CompanyRepServiceLocal companyRepServiceLocal;
	@EJB
	ShiftServiceLocal shiftServiceLocal;
	@EJB
	BoothServiceLocal boothServiceLocal;

	private List<Booth> booths;
	private List<Float> moneey;
	private List<CompanyRep> reps;
	private List<CompanyRep> reps1;
	private int ider;
	private int namer;
	private Shift shift;
	private Date sDU = new Date();
	private Date eDU = new Date();
	private Date st = new Date();
	private Date et = new Date();
	private boolean undeleatable = false;
	private CompanyRep companyRep = new CompanyRep();
	private boolean youDidIt = false;
	private User user;

	@ManagedProperty("#{identity}")
	private Identity identity;

	@PostConstruct
	protected void init() {
		moneey = new ArrayList<>();
		shift = new Shift();
		companyRep = new CompanyRep();
		setUser(userServiceLocal.find(identity.getUser().getId()));
		booths = shiftServiceLocal.getListBoothes(user.getId());
		setReps(shiftServiceLocal.getListCompanyReps(user.getId()));
		CompanyRep companyRep1=new CompanyRep();
		companyRep1 = companyRepServiceLocal.find(identity.getUser().getId());
		reps1 = companyRepServiceLocal.findEmployeesForACompanyOwner(companyRep1);
		for (CompanyRep booth : reps1) {
			Float f = (float) (booth.getHourPrice() * booth.getHoursSpent());
			moneey.add(f);
		}
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public CompanyRep getCompanyRep() {
		return companyRep;
	}

	public void setCompanyRep(CompanyRep companyRep) {
		this.companyRep = companyRep;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void doShifter() {

		Time sTS = new Time(st.getTime());
		Time eTS = new Time(et.getTime());

		java.sql.Date sDS = new java.sql.Date(sDU.getTime());
		shift.setStartDate(sDS);

		java.sql.Date eDS = new java.sql.Date(eDU.getTime());
		shift.setEndDate(eDS);

		shift.setEndTime(eTS);
		shift.setStartTime(sTS);

		Booth booth = boothServiceLocal.find(ider);
		CompanyRep companyRep = companyRepServiceLocal.find(namer);

		System.out.println("booth id = " + ider);
		System.out.println("Company REp id =" + namer);

		shift.setBooth(booth);
		shift.setCompanyRep(companyRep);

		shiftServiceLocal.save(shift);
	}

	public void doAdder() {
		CompanyRep companyRep1 = new CompanyRep();
		companyRep1 = companyRepServiceLocal.find(user.getId());
		companyRep.setCompany(companyRep1.getCompany());
		Boolean loginner = userServiceLocal.checkLogin(user.getLogin());
		if (!loginner) {
			String USER_NAME = "pdev.esprit.2018@gmail.com"; // GMail user name (just the part before
			// "@gmail.com")
			String PASSWORD = "pdevesprit2018"; // GMail password
			String from = USER_NAME;
			String pass = PASSWORD;
			String[] to = new String[] { user.getEmail() }; // list of recipient email addresses

			String subject = "email verification";
			String body = "Your Username Is: " + companyRep.getLogin() + "\n Your Password is"
					+ companyRep.getPassword();

			sendFromGMail(from, pass, to, subject, body);
			Calendar calendar = Calendar.getInstance();
			calendar.getTime();
			int dayOfMonth = calendar.get(MONTH);
			companyRep.setWorkingMonth(dayOfMonth + 1);
			companyRep.setAccessType(1);
			companyRep.setCode("0");
			companyRepServiceLocal.update(companyRep);
			companyRep = new CompanyRep();
			setYouDidIt(true);
		} else {
			undeleatable = true;

		}

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

	public boolean isUndeleatable() {
		return undeleatable;
	}

	public void setUndeleatable(boolean undeleatable) {
		this.undeleatable = undeleatable;
	}

	public boolean isYouDidIt() {
		return youDidIt;
	}

	public void setYouDidIt(boolean youDidIt) {
		this.youDidIt = youDidIt;
	}

	public List<Booth> getBooths() {
		return booths;
	}

	public void setBooths(List<Booth> booths) {
		this.booths = booths;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public List<CompanyRep> getReps() {
		return reps;
	}

	public void setReps(List<CompanyRep> reps) {
		this.reps = reps;
	}

	public int getIder() {
		return ider;
	}

	public void setIder(int ider) {
		this.ider = ider;
	}

	public int getNamer() {
		return namer;
	}

	public void setNamer(int namer) {
		this.namer = namer;
	}

	public Date getsDU() {
		return sDU;
	}

	public void setsDU(Date sDU) {
		this.sDU = sDU;
	}

	public Date geteDU() {
		return eDU;
	}

	public void seteDU(Date eDU) {
		this.eDU = eDU;
	}

	public Date getSt() {
		return st;
	}

	public void setSt(Date st) {
		this.st = st;
	}

	public Date getEt() {
		return et;
	}

	public void setEt(Date et) {
		this.et = et;
	}

	public List<CompanyRep> getReps1() {
		return reps1;
	}

	public void setReps1(List<CompanyRep> reps1) {
		this.reps1 = reps1;
	}

	public List<Float> getMoneey() {
		return moneey;
	}

	public void setMoneey(List<Float> moneey) {
		this.moneey = moneey;
	}

}
