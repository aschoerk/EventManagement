package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface BiddingServicesRemote extends IGenericDAO<Bid> {

}
