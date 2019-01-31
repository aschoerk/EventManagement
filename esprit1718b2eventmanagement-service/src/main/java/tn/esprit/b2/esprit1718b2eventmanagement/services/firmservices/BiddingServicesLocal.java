package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface BiddingServicesLocal extends IGenericDAO<Bid>{

}
