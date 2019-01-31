package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface TransfertServivesRemote extends IGenericDAO<Transfer> {

	public String intToTextMonth(int Month);
}
