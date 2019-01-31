package tn.esprit.b2.esprit1718b2eventmanagement.mBeans;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@ManagedBean(name = "identity")
@SessionScoped
public class Identity {

	private long starterTime;
	private boolean isLogged = false;
	private User user = new User();
	private String theCode;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	CompanyRepServiceLocal companyRepServiceLocal;
	private boolean undeleteable;

	public String logout() {
		isLogged = false;

		if (userServiceLocal.checkRole(user) == 1) {
			long startTime = getStarterTime();
			long endTime = System.nanoTime();

			CompanyRep companyRep = companyRepServiceLocal.find(getUser().getId());

			companyRep.setHoursSpent((endTime - startTime) * (2.778) * 0.00000000000001);
			companyRepServiceLocal.update(companyRep);
		}

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/loginer?faces-redirect=true";
	}

	public String doConfirmation() throws IOException {

		String navigateTo = "";
		if (user.getCode().equals(this.getTheCode())) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			user.setCode("0");
			userServiceLocal.update(user);
			setIsLogged(true);
			int test = userServiceLocal.checkRole(user);
			System.out.println(test);
			if (test == -1) {
				System.out.println("Client");
				context.redirect(context.getRequestContextPath() + "/homeClient.jsf");
			} else if (test == 0) {
				context.redirect(context.getRequestContextPath() + "/homeCompanyOwner.jsf");
				System.out.println("Company owner");
			} else if (test == 1) {
				setStarterTime(System.nanoTime());
				Calendar calendar = Calendar.getInstance();
				calendar.getTime();
				CompanyRep companyRep = new CompanyRep();
				companyRep = userServiceLocal.getCompanyRepFromUser(user);
				if (calendar.get(Calendar.MONTH) != companyRep.getWorkingMonth()) {

					companyRep.setWorkingMonth(calendar.get(Calendar.MONTH));
					companyRep.setHoursSpent(0);
					userServiceLocal.update(companyRep);
				}
				context.redirect(context.getRequestContextPath() + "/homeCompanyRep.jsf");
				System.out.println("company rep");
			} else if (test == 2) {
				context.redirect(context.getRequestContextPath() + "/homeAdmin.jsf");
				System.out.println("admin");
			}
		}
		return navigateTo;

		// ****************************************

	}

	public void goSignUp() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "/SignerUp.jsf");
	}

	public String doLogin() throws IOException {
		String navigateTo = "";
		User userLoggedIn = userServiceLocal.login(user.getLogin(), user.getPassword());
		if (userLoggedIn.getPhone() != 0) {
			isLogged = true;
			user = userLoggedIn;
			int test = userServiceLocal.checkRole(user);
			System.out.println(test);
			// ****************************************
			if (user.getCode().equals("0")) {
				if (test == -1) {
					System.out.println("Client");

					navigateTo = "/homeClient?faces-redirect=true";
				} else if (test == 0) {
					System.out.println("Company owner");
					navigateTo = "/homeCompanyOwner?faces-redirect=true";
				} else if (test == 1) {
					System.out.println("company rep");
					navigateTo = "/homeCompanyRep?faces-redirect=true";
				} else if (test == 2) {
					System.out.println("admin");
					navigateTo = "/homeAdmin?faces-redirect=true";
				}
			} else
				navigateTo = "/CodeConfirmation?faces-redirect=true";

			// ****************************************

		} else {
			this.setUndeleteable(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Veuillez insérer un login et un mot de passe valide", ""));
			return "/loginer?faces-redirect=true";

		}
		return navigateTo;

	}

	public void isHeLogged() {
		
			
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public boolean getUndeleteable() {
		return undeleteable;
	}

	public void setUndeleteable(boolean undeleteable) {
		this.undeleteable = undeleteable;
	}

	public String getTheCode() {
		return theCode;
	}

	public void setTheCode(String theCode) {
		this.theCode = theCode;
	}

	public long getStarterTime() {
		return starterTime;
	}

	public void setStarterTime(long starterTime) {
		this.starterTime = starterTime;
	}

}
