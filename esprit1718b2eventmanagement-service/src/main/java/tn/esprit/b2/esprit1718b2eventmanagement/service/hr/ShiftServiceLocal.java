package tn.esprit.b2.esprit1718b2eventmanagement.service.hr;

import java.util.ArrayList;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Shift;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface ShiftServiceLocal extends IGenericDAO<Shift> {
	 public ArrayList<CompanyRep> getListCompanyReps(int id);
	 public ArrayList<Booth> getListBoothes(int id);
}
