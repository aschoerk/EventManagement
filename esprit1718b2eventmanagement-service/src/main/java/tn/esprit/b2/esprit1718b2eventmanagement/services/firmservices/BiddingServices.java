package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Product;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class BiddingServices
 */
@Stateless
public class BiddingServices extends GenericDAO<Bid> implements BiddingServicesRemote, BiddingServicesLocal {

	@EJB
	private BiddingServicesLocal biddingServicesLocal;
	/**
	 * Default constructor.
	 */
	public BiddingServices() {
		// TODO Auto-generated constructor stub
		super(Bid.class);
	}
	public void assignProductToBid(Product product,float buyout,float bid_start,Date end_date)
	{
		Bid bid=new Bid();
		bid.setProduct(product);
		bid.setBuyout(buyout);
		bid.setBid_Start(bid_start);
		bid.setEnd_date(end_date);
		biddingServicesLocal.update(bid);
	}

}
