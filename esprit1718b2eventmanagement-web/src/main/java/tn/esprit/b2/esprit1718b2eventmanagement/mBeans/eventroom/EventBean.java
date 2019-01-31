package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices.EventServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

@ManagedBean
@ViewScoped
public class EventBean {

	@EJB
	private EventManagementServicesLocal eventManagementServicesLocal;
	@EJB
	private EventServicesLocal eventServicesLocal;
	@EJB
	private SpecialEventServicesLocal specialEventServicesLocal;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private ParticipationServiceLocal participationServiceLocal;
	@EJB
	private CompanyRepServiceLocal companyRepServiceLocal;
	private SpecialEvent specialEvent;

	private List<SpecialEvent> specialEvents;
	@ManagedProperty("#{identity}")
	private Identity identity;

	private boolean isSub = false;

	private int i;
	private Date sdate = new Date();
	private Date edate = new Date();
	private Date ssdate = new Date();
	private Date eedate = new Date();
	private SpecialEvent newSp;
	private List<SpecialEvent> sp;
	private User logged;
	private boolean skip;
	private Date currentDate = new Date();

	public Date getCurrentDate() {
		return currentDate;
	}

	@PostConstruct
	private void init() {
		specialEvent = new SpecialEvent();
		newSp = new SpecialEvent();
		specialEvents = new ArrayList<>();
		List<Event> events = eventManagementServicesLocal.findPublicEvents();

		for (Event e : events) {
			specialEvents.add(specialEventServicesLocal.find(e.getId()));
		}
		i = 0;
		if (identity.getIsLogged()) {
			logged = userServiceLocal.find(identity.getUser().getId());

		}
	}

	public void doGotoAddEvent() throws IOException {
		System.out.println("gggggggggggggggggggg");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/addEvent.jsf");
	}

	public void doAddEvent() throws IOException {
		Time t = new Time(ssdate.getTime());
		Time tt = new Time(eedate.getTime());
		
		newSp.setStartTime(t);
		newSp.setEndTime(tt);
		newSp.setStartDate(new java.sql.Date(sdate.getTime()));
		newSp.setEndDate(new java.sql.Date(edate.getTime()));
		newSp.setCompanyRep(companyRepServiceLocal.find(identity.getUser().getId()));
		newSp.setCategory("Live event");
		newSp = specialEventServicesLocal.update(newSp);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id="+newSp.getId());
	}

	public void doSubscribe(SpecialEvent s) {
		if (identity.getIsLogged()) {
			specialEvent = specialEventServicesLocal.find(s.getId());

			Participation p = new Participation(0, specialEvent, logged, 0);
			p.setMessage(specialEvent.getName());
			participationServiceLocal.update(p);
			isSub = true;
		}
	}

	public void doShowEvent() throws IOException {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int idevent = Integer.parseInt(params.get("idevent"));
		this.setSpecialEvent(specialEventServicesLocal.find(idevent));

		System.out.println(idevent);
		Date date = new Date();
		if (specialEvent.getStartDate().after(date)) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

			context.redirect(context.getRequestContextPath() + "/eventCS.jsf?id=" + idevent);
		} else {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

			context.redirect(context.getRequestContextPath() + "/eventPage.jsf?id=" + idevent);
		}
	}

	public void doShowLowPrice() {
		List<Event> events = eventManagementServicesLocal.findByPrice(0);
		specialEvents = new ArrayList<>();
		for (Event e : events) {
			specialEvents.add(specialEventServicesLocal.find(e.getId()));
		}
	}

	public void doShowALL() {
		List<Event> events = eventManagementServicesLocal.findPublicEvents();
		specialEvents = new ArrayList<>();
		for (Event e : events) {
			specialEvents.add(specialEventServicesLocal.find(e.getId()));
		}
	}

	public void doShowFree() {

		specialEvents = new ArrayList<>();
		specialEvents = eventManagementServicesLocal.findFree();
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}

	public List<SpecialEvent> getSpecialEvents() {
		return specialEvents;
	}

	public void setSpecialEvents(List<SpecialEvent> specialEvents) {
		this.specialEvents = specialEvents;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public boolean isSub() {
		return isSub;
	}

	public void setSub(boolean isSub) {
		this.isSub = isSub;
	}

	public SpecialEvent getNewSp() {
		return newSp;
	}

	public void setNewSp(SpecialEvent newSp) {
		this.newSp = newSp;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getSsdate() {
		return ssdate;
	}

	public void setSsdate(Date ssdate) {
		this.ssdate = ssdate;
	}

	public Date getEedate() {
		return eedate;
	}

	public void setEedate(Date eedate) {
		this.eedate = eedate;
	}

	public User getLogged() {
		return logged;
	}

	public void setLogged(User logged) {
		this.logged = logged;
	}



}
