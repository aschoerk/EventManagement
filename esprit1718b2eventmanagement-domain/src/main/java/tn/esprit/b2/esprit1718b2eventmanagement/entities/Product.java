package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String Description;
	private String image;
	private float price;
	@OneToOne( mappedBy = "product" ,cascade=CascadeType.ALL )
	private Bid bid;
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}






	@Override
	public String toString() {
		return "Product [id=" + id +  "]";
	}


	
	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	

	

}
