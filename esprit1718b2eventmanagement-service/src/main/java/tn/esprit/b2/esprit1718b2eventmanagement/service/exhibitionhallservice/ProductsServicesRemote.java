package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Products;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface ProductsServicesRemote extends IGenericDAO<Products> {

}
