package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.AuctionId;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Product;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesRemote;

public class OwnerAuctionHouseFXMLController implements Initializable {
	@FXML
	private AnchorPane CounterDown;

	@FXML
	private ImageView ImageOr;

	@FXML
	private Text SecondsTimer;

	@FXML
	private Text HoursTimer;

	@FXML
	private DatePicker dateend_input;

	@FXML
	private JFXTextField Product_name;

	@FXML
	private JFXButton createabid;

	@FXML
	private JFXTextField BidStart_input;

	@FXML
	private Text MinuitsTimer;

	@FXML
	private JFXTextField buyout_input;

	@FXML
	private VBox booth;
	private Map<Integer, String> TimerMap;
	Integer currSeconds;
	Thread thrd;

	@FXML
	void goToProfils(ActionEvent event) {

	}

	@FXML
	void boothManagement(ActionEvent event) {

	}

	@FXML
	void Go_auction_Owner(ActionEvent event) {

	}
	@FXML
    void createabid(ActionEvent event) throws NamingException, ParseException {
		Context context = new InitialContext();
		 BiddingServicesRemote biddingServicesRemote=
		   		 (BiddingServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BiddingServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote");
		 ProductServicesRemote productServicesRemote=
		    		(ProductServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.ProductServicesRemote");
		 int a=Integer.parseInt(Product_name.getText());
		 float b=Float.parseFloat(BidStart_input.getText());
		 float c=Float.parseFloat(buyout_input.getText());
		 LocalDate date = dateend_input.getValue();
		 System.out.println(date);
		 Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 System.out.println(date1);
		 Product p=productServicesRemote.find(a);
		 CompanyRepServiceRemote customerServiceRemote=
					(CompanyRepServiceRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CompanyRepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		 Bid bid=new Bid(p, b, c, date1, customerServiceRemote.find(3));
		 
		 biddingServicesRemote.update(bid);
		 System.out.println(bid.toString());
    }

	@FXML
	void back(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) createabid.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../gui/hr/CompanyOwnerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Exit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void ConfirmBidNow(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		  BiddingServicesRemote biddingServicesRemote=
			   		 (BiddingServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BiddingServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote");
		System.out.println(currSeconds+"");
		 AuctionServicesRemote auctionServicesRemote=
	         		(AuctionServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");
	List<Bid> bids=biddingServicesRemote.findAll();
	Date date=auctionServicesRemote.GetServerDatetypeDate();
	for(Bid bid:bids)
	{ 
		if(bid.getEnd_date().before(date))
		{
			List<Auction> auctions=auctionServicesRemote.findAll();
			float max_bid = 0;
			
			Auction auci=new Auction();
			for(Auction auc:auctions)
			{
			if(auc.getBid_amount()>max_bid)
			{
				max_bid=auc.getBid_amount();
			   auci=auc;
			
				
			}
			}
			
			
			auci.setStatus(1);
			auctionServicesRemote.update(auci);
		//	biddingServicesRemote.delete(bid);
		}
	}
		
	}

	@Override
		public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Integer> Hourslist = FXCollections.observableArrayList();
		ObservableList<Integer> MinuitSecondlist = FXCollections.observableArrayList();

		for (Integer i = 0; i <= 60; i++) {
			if (0 <= i && i <= 24) {
				Hourslist.add(new Integer(i));
			}
			MinuitSecondlist.add(new Integer(i));
		}

		TimerMap = new TreeMap<Integer, String>();
		for (Integer i = 0; i <= 60; i++) {
			if (0 <= i && i <= 9) {
				TimerMap.put(i, "0" + i.toString());
			}

			else {
				TimerMap.put(i, i.toString());
			}
		}

		
		try {
			currSeconds=initialisecrrusec();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			StartCountplus();
			
			
		}

	Integer hmsToSecons(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		Integer total = hToSeconds + mToSeconds + s;
		return total;

	}

	void SetOutPut() {

		LinkedList<Integer> currHms = secondsTohms(currSeconds);
		HoursTimer.setText(TimerMap.get(currHms.get(0)));
		MinuitsTimer.setText(TimerMap.get(currHms.get(1)));
		SecondsTimer.setText(TimerMap.get(currHms.get(2)));

	}

	LinkedList<Integer> secondsTohms(Integer currSecond) {
		Integer Hours = currSecond / 3600;
		currSecond = currSecond % 3600;
		Integer Minuites = currSecond / 60;
		currSecond = currSecond % 60;
		Integer Seconds = currSecond;
		LinkedList<Integer> answer = new LinkedList<>();
		answer.add(Hours + 0);
		answer.add(Minuites + 0);
		answer.add(Seconds + 0);
		return answer;

	}

	void StartCountplus() {
		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {

						// here c down
						if (currSeconds >= 0) {
							SetOutPut();

						}
						if (currSeconds <= 0) {
							CounterDown.setVisible(false);
						}

						Thread.sleep(1000);

						if (currSeconds < 0) {
							thrd.stop();

						}
						currSeconds += 1;
					}
				} catch (Exception e) {

				}
				// TODO Auto-generated method stub

			}
		});
		thrd.start();
	}
	int initialisecrrusec() throws NamingException
	{
	
		Context context = new InitialContext();			
		AuctionServicesRemote auctionServicesRemote = (AuctionServicesRemote) context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");
		int Currsec=hmsToSecons(auctionServicesRemote.GetServerDatetypeDate().getHours(), auctionServicesRemote.GetServerDatetypeDate().getMinutes(), auctionServicesRemote.GetServerDatetypeDate().getSeconds());
		return Currsec;
		
	}
}
