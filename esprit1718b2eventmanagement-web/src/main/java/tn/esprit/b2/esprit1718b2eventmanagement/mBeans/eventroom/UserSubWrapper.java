package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

@ManagedBean(name="usw")
@ApplicationScoped
public class UserSubWrapper {

	@EJB
	EventManagementServicesLocal emlocal;
	@EJB
	SpecialEventServicesLocal splocal;
	@EJB
	UserServiceLocal userServiceLocal;
	@EJB 
	ParticipationServiceLocal participationServiceLocal;
	
	private User user;
	private Participation participation;
	
	public List<User> usersLeft(SpecialEvent s){
		List<User> users = new ArrayList<>();
		users = userServiceLocal.findAll();
		List<User> us = new ArrayList<>();
		List<Participation> participations = new ArrayList<>();
		participations= emlocal.findEventParticipations(s);
		if(participations.size()!=0) {
		for(Participation p : participations) {
			for(User u: users){
				if(u.getId()!= p.getUser().getId()) {
					us.add(u);
				}else if (us.contains(u)) {
					us.remove(u);
					
				}
			}
			
			
		}
		Set<User> hs = new HashSet<>();
		hs.addAll(us);
		us.clear();
		us.addAll(hs);
		}
		else {
			return users;
		}
		return  us;
		
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Participation getParticipation() {
		return participation;
	}
	public void setParticipation(Participation participation) {
		this.participation = participation;
	} 
	
	
	
}
