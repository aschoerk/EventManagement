package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bid
 *
 */
@Entity

public class Bid implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	private float Bid_Start;
	@OneToMany(mappedBy = "bid" ,cascade=CascadeType.MERGE)
	private List<Auction> auctions;
	private float Buyout;
	private Date end_date;
	@ManyToOne
	private CompanyRep companyRep;

	private static final long serialVersionUID = 1L;

	public Bid( Product product, float bid_Start, float buyout, Date end_date, CompanyRep companyRep) {
		super();
		
		this.product = product;
		Bid_Start = bid_Start;
		Buyout = buyout;
		this.end_date = end_date;
		this.companyRep = companyRep;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bid() {
		super();
	}

	public int getId() {
		return this.id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public float getBid_Start() {
		return Bid_Start;
	}

	public void setBid_Start(float bid_Start) {
		Bid_Start = bid_Start;
	}

	public float getBuyout() {
		return Buyout;
	}

	public void setBuyout(float buyout) {
		Buyout = buyout;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	

	public Bid(Product product, float bid_Start, float buyout) {
		super();
		this.product = product;
		Bid_Start = bid_Start;
		Buyout = buyout;
		
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public String getCompanyRep() {
		return companyRep.getName();
	}

	public void setCompanyRep(CompanyRep companyRep) {
		this.companyRep = companyRep;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id +", product=" + product.getId() + ", Bid_Start=" + Bid_Start + ", Buyout=" + Buyout
				+ ", end_date=" + end_date + "]";
	}



}
