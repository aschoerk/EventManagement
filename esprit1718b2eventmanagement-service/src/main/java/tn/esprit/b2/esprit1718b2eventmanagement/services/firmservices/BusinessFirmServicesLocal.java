package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BusinessFirm;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface BusinessFirmServicesLocal extends IGenericDAO<BusinessFirm> {

	void AssignAuctionToBusinessFirm(BusinessFirm businessFirm,Auction auction);

	


}
