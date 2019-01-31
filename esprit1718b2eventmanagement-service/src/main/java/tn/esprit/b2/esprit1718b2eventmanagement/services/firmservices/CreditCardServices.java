package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;

import java.util.List;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.swing.JSpinner.ListEditor;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.CreditCard;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class CreditCardServices
 */
@Stateless
public class CreditCardServices extends GenericDAO<CreditCard>
		implements CreditCardServicesRemote, CreditCardServicesLocal {
	@EJB
	private CreditCardServicesLocal cardServicesLocal;
	/**
	 * Default constructor.
	 */
	public CreditCardServices() {
		super(CreditCard.class);
		// TODO Auto-generated constructor stub
	}



	@Override
	public CreditCard Returncard(String allDegits, List<CreditCard> cards) {
		 CreditCard resultat=null;
	        String rep;
			
			for (CreditCard c : cards )
			{
				rep=c.toString();
			
				if  (rep.equals(allDegits))
				{  
					System.out.println("ok");
					resultat= c;
				}
				
			}
			return resultat;
	}



	@Override
	public Boolean cardExists(String allDegits ,List<CreditCard> cards) {
	     
		  Boolean resultat=false;
	        String rep;
			
			for (CreditCard c : cards )
			{
				rep=c.toString();
			
				if  (rep.equals(allDegits))
				{  
					System.out.println("ok");
					resultat= true;
				}
				
			}
			return resultat;
	}




	


}
