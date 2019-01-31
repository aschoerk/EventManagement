package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BusinessFirm
 *
 */
@Entity

public class BusinessFirm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUSF_ID")
	private int id;
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "businessFirm" ,cascade = CascadeType.MERGE)
	private  List <Auction> auctions;
	private String content ;

	public BusinessFirm() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public void linkAuctionToBuisnessFirm(List<Auction> auctions) {
		this.auctions= auctions;
		for (Auction a : auctions) {
			a.setBusinessFirm(this);
		}
	}

	@Override
	public String toString() {
		return "BusinessFirm [id=" + id + ", content=" + content + "]";
	}
	
}
