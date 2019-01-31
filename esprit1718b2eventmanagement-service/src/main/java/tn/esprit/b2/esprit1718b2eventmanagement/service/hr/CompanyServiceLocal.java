package tn.esprit.b2.esprit1718b2eventmanagement.service.hr;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface CompanyServiceLocal extends IGenericDAO<Company> {

}
