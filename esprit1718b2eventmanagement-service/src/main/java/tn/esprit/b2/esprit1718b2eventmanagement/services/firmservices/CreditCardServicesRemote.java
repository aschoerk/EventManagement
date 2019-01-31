package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CreditCard;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.IGenericDAO;

@Remote
public interface CreditCardServicesRemote extends IGenericDAO<CreditCard> {
	Boolean cardExists(String allDegits , List<CreditCard> cards) ;
	CreditCard Returncard(String allDegits , List<CreditCard> cards);
}
