package tn.esprit.b2.esprit1718b2eventmanagement.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CompanyRepServiceRemote extends IGenericDAO<CompanyRep> {
public int countNumberOfEmployee(CompanyRep companyRep);
public List<CompanyRep> findEmployeesForACompanyOwner(CompanyRep companyRep);
public List<CompanyRep> findEmployeesForACompanyOwnerByAString(String searching ,CompanyRep companyRep);
}
