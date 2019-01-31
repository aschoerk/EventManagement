package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Products
 *
 */
@Entity

public class Products implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String Description;
	private float price;
	private File pic;
	private static final long serialVersionUID = 1L;
    @ManyToOne
    User user;
    
    public Products(String name, String description, float price, File pic, Booth booth) {
		super();
		
		this.name = name;
		Description = description;
		this.price = price;
		this.pic = pic;
		this.booth = booth;
	}
	@ManyToOne
    Booth booth;
	public Products() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	
   
}
