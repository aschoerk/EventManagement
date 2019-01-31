package tn.esprit.b2.esprit1718b2eventmanagement.mBeans;


import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesLocal;



@ManagedBean
@ViewScoped
public class AuctionBean {
	@EJB
private AuctionServicesLocal auctionServicesLocal;	
	@EJB
	private BiddingServicesLocal biddingServicesLocal;
	private Auction auction;
	private List<Bid> bids; 
	private Bid bid;
	
	@PostConstruct
	public void init() {
	setBids(biddingServicesLocal.findAll());
		
	}
	
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public Bid getBid() {
		return bid;
	}
	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public void productDesc(ActionEvent evt) throws IOException {
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		context.redirect(context.getRequestContextPath() + "/Productdesc.jsf?id=" + bid.getProduct().getId());
	}
}
