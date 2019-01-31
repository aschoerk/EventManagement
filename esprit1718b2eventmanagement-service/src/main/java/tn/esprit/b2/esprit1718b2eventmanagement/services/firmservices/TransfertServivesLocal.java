package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface TransfertServivesLocal extends IGenericDAO<Transfer> {
	public String intToTextMonth(int Month);
}
