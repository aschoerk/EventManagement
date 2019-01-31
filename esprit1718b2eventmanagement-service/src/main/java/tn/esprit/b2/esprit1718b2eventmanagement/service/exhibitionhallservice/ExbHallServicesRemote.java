package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;

@Remote
public interface ExbHallServicesRemote {
	public void assignBoothClub(Booth booth, Club club);
	public Club findClubidbyname(String name);
	
}
