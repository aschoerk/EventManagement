package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: SpectialEvent
 *
 */
@Entity

public class SpecialEvent extends Event implements Serializable {

	private int privacy;
	private File eventBanner;
	private String externalLink;
	@OneToMany(mappedBy = "specialEvent")
	private List<Task> schedule;

	private String address;

	private long repPhone;

	private float price;

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public File getEventBanner() {
		return eventBanner;
	}

	public void setEventBanner(File eventBanner) {
		this.eventBanner = eventBanner;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public List<Task> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Task> schedule) {
		this.schedule = schedule;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getRepPhone() {
		return repPhone;
	}

	public void setRepPhone(long repPhone) {
		this.repPhone = repPhone;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	private static final long serialVersionUID = 1L;

	public SpecialEvent() {
		super();
	}

}
