package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices.EventServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

@ManagedBean
@ViewScoped
public class EventPageBean {
	@ManagedProperty("#{identity}")
	private Identity identity;
	@EJB
	private EventManagementServicesLocal eventManagementServicesLocal;
	@EJB
	private EventServicesLocal eventServicesLocal;
	@EJB
	private SpecialEventServicesLocal specialEventServicesLocal;
	@EJB
	private CompanyServiceLocal companyServiceLocal;
	@EJB
	private CompanyRepServiceLocal companyRepServiceLocal;
	@EJB
	UserServiceLocal userServiceLocal;

	private SpecialEvent specialEvent;
	private User logged;
	private List<SpecialEvent> otherSp;
	private Company company;

	@PostConstruct
	protected void init() {
		logged = userServiceLocal.find(identity.getUser().getId());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String id = request.getParameter("id");
		specialEvent = specialEventServicesLocal.find(Integer.parseInt(id));
		User eventu = userServiceLocal.find(specialEvent.getCompanyRep().getId());
		if (logged.getId() != eventu.getId()) {
			specialEvent.setVisits(specialEvent.getVisits() + 1);
			specialEvent = specialEventServicesLocal.update(specialEvent);
		}
		CompanyRep c = companyRepServiceLocal.find(specialEvent.getCompanyRep().getId());
		company = companyServiceLocal.find(c.getCompany().getId());
		otherSp = eventManagementServicesLocal.findAllOwnerEvents(specialEvent.getCompanyRep());
		otherSp.remove(specialEvent);
	}

	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<SpecialEvent> getOtherSp() {
		return otherSp;
	}

	public void setOtherSp(List<SpecialEvent> otherSp) {
		this.otherSp = otherSp;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public User getLogged() {
		return logged;
	}

	public void setLogged(User logged) {
		this.logged = logged;
	}

}
