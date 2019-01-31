package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface BoothServiceRemote extends IGenericDAO<Booth> {
	public Booth findBoothidbyname(String name);
	public List<Booth> findBoothbytype(String type);
	public List<Booth> findBoothbyClub(int type);
	public List<Booth> findBoothbytypeandclub(int id,String type);
	public List<SpecialEvent> findassociatedevents(Booth b);
}
