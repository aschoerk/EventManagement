package tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices;


import javax.ejb.Stateless;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class TransfertServives
 */
@Stateless
public class TransfertServives extends GenericDAO<Transfer> implements TransfertServivesRemote, TransfertServivesLocal {

    /**
     * Default constructor. 
     */
    public TransfertServives() {
    super(Transfer.class);
        // TODO Auto-generated constructor stub
    }

	@Override
	public String intToTextMonth(int Month) {
		String Res=null;
		if(Month==0)
		{Res="JAN";}
		else if (Month==1) {
			Res="FEB";
		}
		else if (Month==2) {
			Res="MAR";
		}
		else if (Month==3) {
			Res="APR";
		}
		else if (Month==4) {
			Res="MAY";
		}
		else if (Month==5) {
			Res="JUN";
		}
		else if (Month==6) {
			Res="JUL";
		}
		else if (Month==7) {
			Res="AOU";
		}
		else if (Month==8) {
			Res="SEP";
		}
		else if (Month==9) {
			Res="OCT";
		}
		else if (Month==10) {
			Res="NOV";
		}
		else if (Month==11) {
			Res="DEC";
		}
		
			
		return Res;
	}

}
