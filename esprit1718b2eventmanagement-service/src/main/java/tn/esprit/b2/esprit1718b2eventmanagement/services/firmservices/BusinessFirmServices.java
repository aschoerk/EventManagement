package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BusinessFirm;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class BusinessFirmServices
 */
@Stateless

public class BusinessFirmServices extends GenericDAO<BusinessFirm> implements BusinessFirmServicesRemote, BusinessFirmServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private AuctionServicesLocal auctionServicesLocal;
    /**
     * Default constructor. 
     */
    public BusinessFirmServices() {
        // TODO Auto-generated constructor stub
    	super(BusinessFirm.class);
    }

	@Override
	public void AssignAuctionToBusinessFirm(BusinessFirm businessFirm, Auction auction) {
		List<Auction> OldAuction = businessFirm.getAuctions();
		if (OldAuction != null) {
			OldAuction.add(auction);
			businessFirm.linkAuctionToBuisnessFirm(OldAuction);
		} else {
			List<Auction> newOne = new ArrayList<>();
			newOne.add(auction);
			businessFirm.linkAuctionToBuisnessFirm(newOne);
		}

		auctionServicesLocal.update(auction); 
		
	}

}
