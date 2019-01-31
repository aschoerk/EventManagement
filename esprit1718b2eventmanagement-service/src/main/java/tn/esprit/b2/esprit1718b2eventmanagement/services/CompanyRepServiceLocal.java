package tn.esprit.b2.esprit1718b2eventmanagement.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface CompanyRepServiceLocal extends IGenericDAO<CompanyRep> {
	public int countNumberOfEmployee(CompanyRep companyRep);
	public List<CompanyRep> findEmployeesForACompanyOwner(CompanyRep companyRep);
	public List<CompanyRep> findEmployeesForACompanyOwnerByAString(String searching ,CompanyRep companyRep);

}
