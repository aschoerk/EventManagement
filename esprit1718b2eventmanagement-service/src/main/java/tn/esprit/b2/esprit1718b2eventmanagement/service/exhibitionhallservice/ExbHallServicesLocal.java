package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;

@Local
public interface ExbHallServicesLocal {
	public void assignBoothClub(Booth booth, Club club);
}
