package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface  BoothServiceLocal extends IGenericDAO<Booth> {
	public Booth findBoothidbyname(String name);

	List<Booth> findBoothbytype(String type);
	List<Booth> filterbooths(String letter);
	List<Booth> filtervisits();
}
