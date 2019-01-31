package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

@ManagedBean
@ViewScoped
public class UserSubBean {
	@ManagedProperty("#{identity}")
	private Identity identity;
	@EJB
	EventManagementServicesLocal eventManagementServicesLocal;
	@EJB
	ParticipationServiceLocal participationServiceLocal;
	@EJB
	SpecialEventServicesLocal specialEventServicesLocal;
	@EJB
	UserServiceLocal userServiceLocal;
	private User logged;
	private boolean showForm;
	private Participation participation;
	private List<Participation> participations;
	private int pn=0;
	@PostConstruct 
	protected void init() {
		logged = userServiceLocal.find(identity.getUser().getId());
		showForm = false;
		participation = new Participation();
		participations = eventManagementServicesLocal.UserNotification(logged);
		
		pn = participations.size();
	}
	public void doSelect() {
		showForm = true;
	}
	public void doAccept() {
		
		participation.setStatus(0);
		participationServiceLocal.update(participation);
	participations =  eventManagementServicesLocal.UserNotification(logged);
		
		pn = participations.size();
		showForm = false;
	}
	public void doDelete() {
		participationServiceLocal.delete(participation);
		participations =  eventManagementServicesLocal.UserNotification(logged);
		
		pn = participations.size();
		showForm = false;
	}
	public boolean isShowForm() {
		return showForm;
	}
	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	public Participation getParticipation() {
		return participation;
	}
	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	public List<Participation> getParticipations() {
		return participations;
	}
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
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
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	
}
