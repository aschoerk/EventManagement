package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auction
 *
 */
@Entity
@Table(name = "USR_AUC")
@IdClass(AuctionId.class)
public class Auction implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	private int userId;
	@Id
	private int productId;
	@Column(name = "BID_AMOUNT")
	private float bid_amount;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")

	private User user;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "BID_ID", referencedColumnName = "ID")

	private Bid bid;
	@ManyToOne
	@JoinColumn(name="BUSF_ID", nullable=false)
    private BusinessFirm businessFirm;
	private int Status;

	public Auction() {
		super();
	}

	

	public Auction(float bid_amount, int status, User user, Bid bid, BusinessFirm businessFirm) {
		super();
		this.bid_amount = bid_amount;
		this.Status=status;
		this.user = user;
		this.bid = bid;
		this.businessFirm = businessFirm;
	}

	public Auction(int userId, int productId, float bid_amount, User user, Bid bid, BusinessFirm businessFirm) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.bid_amount = bid_amount;
		this.user = user;
		this.bid = bid;
		this.businessFirm = businessFirm;
	}



	public int getUserId() {
		return userId;
	}

	public float getBid_amount() {
		return bid_amount;
	}



	public void setBid_amount(float bid_amount) {
		this.bid_amount = bid_amount;
	}



	public Bid getBid() {
		return bid;
	}



	public void setBid(Bid bid) {
		this.bid = bid;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public BusinessFirm getBusinessFirm() {
		return businessFirm;
	}

	public void setBusinessFirm(BusinessFirm businessFirm) {
		this.businessFirm = businessFirm;
	}



	public int getStatus() {
		return Status;
	}



	public void setStatus(int status) {
		Status = status;
	}

}
