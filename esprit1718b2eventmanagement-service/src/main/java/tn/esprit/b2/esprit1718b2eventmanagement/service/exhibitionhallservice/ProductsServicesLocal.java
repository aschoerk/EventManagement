package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import javax.ejb.Local;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Products;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Local
public interface ProductsServicesLocal  extends IGenericDAO<Products>{

}
