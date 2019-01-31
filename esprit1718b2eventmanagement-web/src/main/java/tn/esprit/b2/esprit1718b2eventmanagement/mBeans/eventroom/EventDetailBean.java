package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.io.IOException;
import java.sql.Time;
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
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DualListModel;

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
public class EventDetailBean {
	@ManagedProperty("#{identity}")
	private Identity identity;
	@ManagedProperty("#{usw}")
	private UserSubWrapper wrapper;
	@EJB
	CompanyRepServiceLocal companyRepServiceLocal;
	@EJB
	EventManagementServicesLocal emlocal;
	@EJB
	SpecialEventServicesLocal splocal;
	@EJB
	UserServiceLocal userServiceLocal;
	@EJB
	ParticipationServiceLocal participationServiceLocal;
	@EJB
	EventServicesLocal ev;
	private List<User> subs;
	private List<User> notsubs;
	private List<Participation> participations;
	private Date sdate = new Date();
	private Date edate = new Date();
	private Date ssdate = new Date();
	private Date eedate = new Date();
	private User logged;
	private SpecialEvent specialEvent;
	private DualListModel<User> dissub;
	private String stramlab ="";
	private boolean toggleStream = false;
	public List<User> esuub;
	@PostConstruct
	protected void init() {
		stramlab = "Stream";
		toggleStream= false;
		logged = userServiceLocal.find(identity.getUser().getId());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		notsubs = new ArrayList<User>();
		String id = request.getParameter("id");
		specialEvent = splocal.find(Integer.parseInt(id));
		participations = new ArrayList<>();
		participations = emlocal.findEventParticipations(specialEvent);
		subs = new ArrayList<User>();
		for (Participation pt : participations) {

			User u = userServiceLocal.find(pt.getUser().getId());
			subs.add(u);

		}

		notsubs = wrapper.usersLeft(specialEvent);
		if(notsubs.size()!=0) {
			for (User user : subs) {
			System.out.println(user);	
				
			}
			System.out.println("//////////////////////////////////////////");
			for (User user : notsubs) {
				System.out.println(user);
			}
			System.out.println("//////////////////////////////////////////");
		notsubs.removeAll(subs);
		
		for (User user : notsubs) {
			System.out.println(user);
		}
		}

		// Themes
		List<User> themesSource = new ArrayList<>();
		List<User> themesTarget = new ArrayList<User>();
		themesSource = notsubs;
		dissub = new DualListModel<User>(themesSource, themesTarget);

	}

	public void doInvite() throws IOException {

		System.out.println(dissub.getTarget());
		for (int i = 0; i < dissub.getTarget().size(); i++) {
			System.out.println(dissub.getTarget().get(i));
			String s = dissub.getTarget().get(i) + "";
			int index = s.indexOf("id=") + 3;
			int ind = s.indexOf(",");
			if (index != -1 && ind != -1) {

				String substrin = s.substring(index, ind);
				int id = Integer.parseInt(substrin);
				Participation pp =new Participation(1, specialEvent, userServiceLocal.find(id), 0);
				pp.setMessage(specialEvent.getName());
				participationServiceLocal.update(pp);
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

				context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id=" + specialEvent.getId());
			}
		}

	}
	public void doGotoUpdate() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/updateEvent.jsf?id="+specialEvent.getId());
		
	}

	public void doUpdateEvent() throws IOException {
		
		Time t = new Time(ssdate.getTime());
		Time tt = new Time(eedate.getTime());
		
		specialEvent.setStartTime(t);
		specialEvent.setEndTime(tt);
		specialEvent.setStartDate(new java.sql.Date(sdate.getTime()));
		specialEvent.setEndDate(new java.sql.Date(edate.getTime()));
		specialEvent.setCompanyRep(companyRepServiceLocal.find(identity.getUser().getId()));
		specialEvent.setCategory("Live event");
		specialEvent = splocal.update(specialEvent);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventBack.jsf?id="+specialEvent.getId());
	}
	
	public void doDeleteEvent() throws IOException {
		
		List <Participation> par = emlocal.findEventParticipations(specialEvent);
		for(Participation p : par) {
			participationServiceLocal.delete(p);
		}

		splocal.delete(specialEvent);
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/eventDashboard.jsf");
	
	}
	
	
	public User getLogged() {
		return logged;
	}

	public void setLogged(User logged) {
		this.logged = logged;
	}

	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public List<User> getSubs() {
		return subs;
	}

	public void setSubs(List<User> subs) {
		this.subs = subs;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public UserSubWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(UserSubWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public List<User> getNotsubs() {
		return notsubs;
	}

	public void setNotsubs(List<User> notsubs) {
		this.notsubs = notsubs;
	}

	public DualListModel<User> getDissub() {
		return dissub;
	}

	public void setDissub(DualListModel<User> dissub) {
		this.dissub = dissub;
	}

	public String getStramlab() {
		return stramlab;
	}

	public void setStramlab(String stramlab) {
		this.stramlab = stramlab;
	}

	public boolean isToggleStream() {
		return toggleStream;
	}

	public void setToggleStream(boolean toggleStream) {
		this.toggleStream = toggleStream;
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

}
