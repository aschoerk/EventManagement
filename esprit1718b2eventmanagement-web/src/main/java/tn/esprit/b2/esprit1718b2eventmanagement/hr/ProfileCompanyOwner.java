package tn.esprit.b2.esprit1718b2eventmanagement.hr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

@ManagedBean
@ViewScoped
public class ProfileCompanyOwner {

	private UploadedFile file;
	private User user;
	private CompanyRep companyRep;
	@EJB
	UserServiceLocal userServiceLocal;
	@EJB
	CompanyRepServiceLocal companyRepServiceLocal;
	@ManagedProperty("#{identity}")
	private Identity identity;

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	@PostConstruct
	protected void init() {
		identity.isHeLogged();
		user = userServiceLocal.find(identity.getUser().getId());
		companyRep = companyRepServiceLocal.find(identity.getUser().getId());
	}

	public void update() {
		companyRepServiceLocal.update(companyRep);

		identity.setUser(userServiceLocal.find(companyRep.getId()));
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CompanyRep getCompanyRep() {
		return companyRep;
	}

	public void setCompanyRep(CompanyRep companyRep) {
		this.companyRep = companyRep;
	}

}
