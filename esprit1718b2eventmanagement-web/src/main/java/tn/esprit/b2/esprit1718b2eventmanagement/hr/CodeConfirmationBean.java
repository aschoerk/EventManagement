package tn.esprit.b2.esprit1718b2eventmanagement.hr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class CodeConfirmationBean {

	private boolean isLogged = false;
	private String theCode;
	@ManagedProperty(value="#{user}")
	private Identity identity;
	
	public Identity getIdentity() {
		return identity;
	}
	
	public User getCurrent() {
		return current;
	}

	public void setCurrent(User current) {
		this.current = current;
	}

	private User current ;
	@PostConstruct
    public void init(){
        current = identity.getUser();
    }
	@EJB
	private UserServiceLocal userServiceLocal;

	
	public String getTheCode() {
		return theCode;
	}

	public void setTheCode(String theCode) {
		this.theCode = theCode;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

}
