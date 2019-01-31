package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ExbHallServicesRemote;

public class test {
	public static void main(String[] args) throws NamingException {
		// 
		Context context = new InitialContext();
		ExbHallServicesRemote exbHallServicesRemote = (ExbHallServicesRemote) context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ExbHallServices!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ExbHallServicesRemote");
				
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		ClubServiceRemote clubServiceRemote = (ClubServiceRemote) context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ClubService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote");
		System.out.println("i'm working kinda");

	 Booth booth1=  boothServiceRemote.find(0);
	 System.out.println("7aja"+booth1.getName());
	 Booth booth2=  boothServiceRemote.find(1);
	 Booth booth3=  boothServiceRemote.find(2);
	 
	 
	 Club club1=clubServiceRemote.find(0);
	 exbHallServicesRemote.assignBoothClub(booth1, club1);
	 exbHallServicesRemote.assignBoothClub(booth2, club1);
	 exbHallServicesRemote.findClubidbyname("c1");
	 System.out.println( exbHallServicesRemote.findClubidbyname("c1").getId());
	 
	 
	 
	
	
	
	
	
	
	}
}
