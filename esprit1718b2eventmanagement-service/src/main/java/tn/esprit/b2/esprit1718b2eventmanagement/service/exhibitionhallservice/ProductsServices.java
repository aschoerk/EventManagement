package tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice;

import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Products;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductsServices
 */
@Stateless
public class ProductsServices extends GenericDAO<Products> implements ProductsServicesRemote, ProductsServicesLocal {

    /**
     * Default constructor. 
     */
    public ProductsServices() {
       super(Products.class);
    }

}
