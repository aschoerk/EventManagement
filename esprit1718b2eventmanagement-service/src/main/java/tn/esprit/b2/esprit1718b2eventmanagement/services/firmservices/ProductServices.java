package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Product;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ProductServices
 */
@Stateless
public class ProductServices extends GenericDAO<Product> implements ProductServicesRemote, ProductServicesLocal {

    /**
     * Default constructor. 
     */
    public ProductServices() {
    	super(Product.class);
        // TODO Auto-generated constructor stub
    }

}
