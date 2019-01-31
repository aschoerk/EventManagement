package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;

public class AuctionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int productId;

	public int hashCode() {
		return (int) (userId + productId);
	}

	public boolean equals(Object object) {
		if (object instanceof AuctionId) {
			AuctionId otherId = (AuctionId) object;
			return (otherId.userId == this.userId) && (otherId.productId == this.productId);
		}
		return false;
	}



}
