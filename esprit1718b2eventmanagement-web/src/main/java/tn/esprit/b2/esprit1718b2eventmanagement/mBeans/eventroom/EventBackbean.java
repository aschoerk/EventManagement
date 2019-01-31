package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

@ManagedBean
@ViewScoped
public class EventBackbean {
	@ManagedProperty("#{identity}")
	private Identity identity;
	@EJB
	EventManagementServicesLocal emlocal;
	@EJB
	SpecialEventServicesLocal splocal;
	@EJB
	UserServiceLocal userServiceLocal;
	private User logged;
	private List<SpecialEvent> specialevents;
	private ScheduleModel lazyEventModel;
	private SpecialEvent specialEvent;
	private SpecialEvent mostRated;
	private SpecialEvent mostVisited;
	private SpecialEvent mostSubs;
	private int ms=0;
	private int eNum=0;
	private ScheduleEvent event = new DefaultScheduleEvent();

	@PostConstruct
	protected void init() {
		logged = userServiceLocal.find(identity.getUser().getId());
		System.out.println("ghghgh" + logged.getId());
		specialEvent = new SpecialEvent();
		specialevents = new ArrayList<>();
		specialevents.addAll(emlocal.findAllOwnerEvents(logged));
		eNum= specialevents.size();
		lazyEventModel = new LazyScheduleModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1469971451051130887L;

			@Override
			public void loadEvents(Date start, Date end) {
				for (SpecialEvent sp : specialevents) {
					addEvent(new DefaultScheduleEvent(sp.getId() + "~" + sp.getName(), sp.getStartDate(),
							sp.getEndDate()));

				}
			}
		};
		mostRated = new SpecialEvent();
		mostVisited = new SpecialEvent();
		mostSubs = new SpecialEvent();
		mostRated = emlocal.findMostRatedEvent(logged);
		mostVisited = emlocal.findMostVisitedEvent(logged);
		mostSubs = emlocal.findMostSubscribedEvent(logged);
		ms = emlocal.findEventParticipations(mostSubs).size();
	}
	public void doGoToMR() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id=" + mostRated.getId());
	}
	public void doGoToMV() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id=" + mostVisited.getId());
	}
	public void doGoToMS() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id=" + mostSubs.getId());
	}
	public void onDateSelect(SelectEvent selectEvent) {
		setEvent(new DefaultScheduleEvent((String) selectEvent.getObject(), (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject()));
		System.out.println("yeeeeeeeeeeeeejj");
	}

	public void onEventSelect(SelectEvent selectEvent) throws IOException {
		event = (ScheduleEvent) selectEvent.getObject();
		String s = event.getTitle();
		System.out.println("desc:" + s);
		int index = s.indexOf("~");
		if (index != -1) {
			String substrin = s.substring(0, index);
			specialEvent = splocal.find(Integer.parseInt(substrin));
			System.out.println(specialEvent.getName());
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

			context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id=" + specialEvent.getId());

		}
		System.out.println("yeeeeeeeeeeeeejj");
	}

	public List<SpecialEvent> getSpecialevents() {
		return specialevents;
	}

	public void setSpecialevents(List<SpecialEvent> specialevents) {
		this.specialevents = specialevents;
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

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}

	public SpecialEvent getMostRated() {
		return mostRated;
	}

	public void setMostRated(SpecialEvent mostRated) {
		this.mostRated = mostRated;
	}

	public SpecialEvent getMostVisited() {
		return mostVisited;
	}

	public void setMostVisited(SpecialEvent mostVisited) {
		this.mostVisited = mostVisited;
	}

	public SpecialEvent getMostSubs() {
		return mostSubs;
	}

	public void setMostSubs(SpecialEvent mostSubs) {
		this.mostSubs = mostSubs;
	}
	public int getMs() {
		return ms;
	}
	public void setMs(int ms) {
		this.ms = ms;
	}
	public int geteNum() {
		return eNum;
	}
	public void seteNum(int eNum) {
		this.eNum = eNum;
	}

}
