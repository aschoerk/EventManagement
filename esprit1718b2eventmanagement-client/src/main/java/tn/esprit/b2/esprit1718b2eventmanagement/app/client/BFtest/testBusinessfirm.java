package tn.esprit.b2.esprit1718b2eventmanagement.app.client.BFtest;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bank;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CreditCard;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Product;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.CreditCardServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.CreditCardServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesRemote;

public class testBusinessfirm {
	

	public static  String GetDegit(int a) {
		String rIB="";
		if ((a/1000)!=0)
		{
			rIB+=a;			
		}
		else if((a/100)!=0)
		{
			rIB+="0"+a;
		}
		else if ((a/10)!=0)
		{
			rIB+="00"+a;			
		}
		else
		{
			rIB+="000"+a;			
		}
		return rIB;
	}
	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		Context context = new InitialContext();
		BankaccountServicesRemote bankaccountServicesRemote=
				(BankaccountServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankaccountServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesRemote");

		 AuctionServicesRemote auctionServicesRemote=
         		(AuctionServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");
   
	    BiddingServicesRemote biddingServicesRemote=
	   		 (BiddingServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BiddingServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote");
	    ProductServicesRemote productServicesRemote=
	    		(ProductServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesRemote");
	    BankServicesRemote bankServicesRemote=
	    		(BankServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote");
	    CreditCardServicesRemote cardServicesRemote=
	    		(CreditCardServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CreditCardServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.CreditCardServicesRemote");
	    //esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote
	    //esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesRemote
		//BusinessFirmServicesRemote businessFirmServicesRemote=
			//	(BusinessFirmServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BusinessFirmServicesRemote");
	CustomerServiceRemote customerServiceRemote=
			(CustomerServiceRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CustomerService!tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote");
UserServiceRemote userServiceRemote=
(UserServiceRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/UserService!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");
	User cust=new User();
	cust=userServiceRemote.find(1);
	
		//bankaccountServicesRemote.update(new BankAccount(cust,"1234","passwd")); // works

	
	BankAccount account=   bankaccountServicesRemote.find(1); 
       // bankServicesRemote.update(new Bank(1234,123,12,1));
	   Bank bank=bankServicesRemote.find(1);
      //  bankServicesRemote.AssignAccountToBank(bank, b);
	 
			String rIB = "";
			int a=(bank.getKey_1())+account.getId();
			int b=(bank.getKey_2()%10)+account.getId()+1177;
			int c=(bank.getKey_3()%100)+account.getId()+177;
			int d=(bank.getKey_4()%1000)+account.getId()+77;
			rIB+=GetDegit(a);
			rIB+=GetDegit(b);
			rIB+=GetDegit(c);
			rIB+=GetDegit(d);
			account.setRIB(rIB);
	  
      //  System.out.println(account.getRIB()+"");
        //System.out.println( auctionServicesRemote.GetServerDatetypeDate().getSeconds()+"");
			//System.out.println(cardServicesRemote.findAll().toString());
			List<CreditCard> cards;
			cards=cardServicesRemote.findAll();
		
        //System.out.println(cardExists("0258365012001200336", cards));
        //System.out.println(cardServicesRemote.cardExists("0258365012001200336", cards));
        
       // System.out.println(cardServicesRemote.Returncard("0258365012001200336", cards).getCvv()+"");
			List<BankAccount> accounts;
			accounts =bankaccountServicesRemote.findAll();
			String RIB="1234567891123456";
       // System.out.println(bankaccountServicesRemote.FindbyRIB(RIB, accounts));
      //  bankaccountServicesRemote.update(account);
       //System.out.println( b.getRIB());
	/*String a=bankaccountServicesRemote.CryptPasswd("azerty"); // works !
	System.out.println(a); 
	String b2=bankaccountServicesRemote.DecryptPasswd(a); // works !
	System.out.println(b2);
	/*Product p=new Product();
	
	productServicesRemote.update(p);

	
	Bid bid=new Bid();
	bid.setProduct(p);
	bid.setBid_Start(0.2f);
	bid.setBuyout(0f);
System.out.println(bid.toString());
	biddingServicesRemote.update(bid);

	*/
User user= userServiceRemote.find(3);
String passwd="123";
//Bank bank2=bankServicesRemote.find(1);
//bankServicesRemote.givebankaccounttouser(user, passwd,bank2);
System.out.println(bankaccountServicesRemote.CryptPasswd("passwd"));
	}
	
	
}
