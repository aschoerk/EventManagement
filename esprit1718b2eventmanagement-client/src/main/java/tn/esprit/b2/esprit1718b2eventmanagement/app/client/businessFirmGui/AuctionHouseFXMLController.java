/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Auction;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Bid;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BusinessFirm;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BusinessFirmServicesRemote;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class AuctionHouseFXMLController implements Initializable {
	
	@FXML
	private TableColumn<Bid, Integer> Bid_Product;
	@FXML
	private TableColumn<Bid, Float> Bid_start;
	@FXML
	private TableColumn<Bid, Float> Bid_buyout;
	@FXML
	private TableColumn<Bid, Float> Bid_seller;
	@FXML
	private TableColumn<Bid, Date> Bid_timeleft;
	@FXML
	private TableColumn<Bid, Integer> Bid_id;
	@FXML
	private TableView<Bid> Bid_table;
	@FXML
	private JFXButton Bid_Buyout;

	@FXML
	private JFXButton Bid_search;

	@FXML
	private JFXButton Go_Back;
	@FXML
	private JFXButton Bid_reset;

	@FXML
	private JFXTextField Bid_find_seller;

	@FXML
	private JFXButton Bid_bid;

	@FXML
	private JFXTextField Bid_find_name;
	@FXML
	private JFXTextField Bid_find_price;

	@FXML
	private JFXTextField Bid_yourbid;
	@FXML
	private Text MinuitsTimer;
	@FXML
	private Text SecondsTimer;
	@FXML
	private Text HoursTimer;
	@FXML
	private AnchorPane CounterDown;
	@FXML
	private ImageView ImageRed;
	@FXML
	private ImageView ImageOr;
	@FXML
	private ImageView ImageGreen;
	private double xOffset =0;
	private double yOffset =0;
	@FXML
	private AnchorPane parent;
	public void makeStageDrageable() {

		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Projet.stage.setX(event.getScreenX() - xOffset);
				Projet.stage.setY(event.getScreenY() - yOffset);
				Projet.stage.setOpacity(0.7f);
			}
		});
		parent.setOnDragDone((e) -> {
			Projet.stage.setOpacity(1.0f);
		});
		parent.setOnMouseReleased((e) -> {
			Projet.stage.setOpacity(1.0f);
		});
	}

	/**
	 * Initializes the controller class.
	 */

	//
	int userid = 1;
	private Map<Integer, String> TimerMap;
	Integer currSeconds;
	Thread thrd;

	Integer hmsToSecons(Integer h, Integer m, Integer s) {
		Integer hToSeconds = h * 3600;
		Integer mToSeconds = m * 60;
		Integer total = hToSeconds + mToSeconds + s;
		return total;

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

	void StartCountdown() {
		thrd = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						// here c down
						if (currSeconds >= 0) {
							SetOutPut();
							if (currSeconds > 3600) {
								ImageGreen.setVisible(true);
								ImageOr.setVisible(false);
								ImageRed.setVisible(false);
							}
							if ((currSeconds > 600 && currSeconds < 3600)) {
								ImageGreen.setVisible(false);
								ImageOr.setVisible(true);
								ImageRed.setVisible(false);
							}
							if (currSeconds < 600) {
								ImageGreen.setVisible(false);
								ImageOr.setVisible(false);
								ImageRed.setVisible(true);
							}
							if (currSeconds < 60) {
								ImageGreen.setVisible(false);
								ImageOr.setVisible(false);
								ImageRed.setVisible(true);
								RotateTransition transition1 = new RotateTransition(Duration.seconds(1), ImageRed);
								RotateTransition transition2 = new RotateTransition(Duration.seconds(1), ImageRed);

								transition1.setByAngle(360);
								transition2.setByAngle(36000);
								transition1.play();
							}

						}
						if (currSeconds <= 0) {
							CounterDown.setVisible(false);
						}

						Thread.sleep(1000);

						if (currSeconds < 0) {
							thrd.stop();

						}
						currSeconds -= 1;
					}
				} catch (Exception e) {

				}
				// TODO Auto-generated method stub

			}
		});
		thrd.start();
	}

	void SetOutPut() {

		LinkedList<Integer> currHms = secondsTohms(currSeconds);
		HoursTimer.setText(TimerMap.get(currHms.get(0)));
		MinuitsTimer.setText(TimerMap.get(currHms.get(1)));
		SecondsTimer.setText(TimerMap.get(currHms.get(2)));

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		CounterDown.setVisible(false);
		makeStageDrageable();
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

	}

	public void showBid(List<Bid> list) throws NamingException {
		Context context = new InitialContext();
		BiddingServicesRemote biddingServicesRemote = (BiddingServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BiddingServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote");
		AuctionServicesRemote auctionServicesRemote = (AuctionServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");
		List<Bid> myResult2 = new ArrayList<>() ;
		Date date= auctionServicesRemote.GetServerDatetypeDate();
		List<Bid> myResult = biddingServicesRemote.findAll();
		for(Bid b:myResult)
		{	if(b.getEnd_date().compareTo(date)>0)
		{
			if(!Bid_find_name.getText().isEmpty())
			{
				if(b.getProduct().getId()==Integer.parseInt(Bid_find_name.getText()))
				{
				    myResult2.add(b);
				}
			}
			else if(!Bid_find_price.getText().isEmpty())
			{
				if(b.getBuyout()==Float.parseFloat(Bid_find_price.getText()))
						{
					         myResult2.add(b);
						}
			
			}
			else if(!Bid_find_seller.getText().isEmpty())
			{
			          if(b.getCompanyRep().equals(Bid_find_seller.getText()))
			          {
			        	   myResult2.add(b);
			          }
			}
			else
			{
				myResult2.add(b);
			}
		
				
			}
		
		}

		ObservableList<?> data = FXCollections.observableArrayList(myResult2);
		Bid_Product.setCellValueFactory(new PropertyValueFactory<>("product"));

		Bid_start.setCellValueFactory(new PropertyValueFactory<>("Bid_Start"));
		Bid_buyout.setCellValueFactory(new PropertyValueFactory<>("Buyout"));

		Bid_seller.setCellValueFactory(new PropertyValueFactory<>("CompanyRep"));
		Bid_timeleft.setCellValueFactory(new PropertyValueFactory<>("end_date"));
		Bid_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		Bid_table.setItems((ObservableList<Bid>) data);
	}

	@FXML
	void BidSearch(ActionEvent event) throws NamingException {
		List<Bid> listForm = new ArrayList<>();
		Context context = new InitialContext();
		BiddingServicesRemote biddingServicesRemote = (BiddingServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BiddingServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BiddingServicesRemote");

		listForm = biddingServicesRemote.findAll();
		System.out.println("woooor" + biddingServicesRemote.findAll());
		showBid(listForm);
	}

	@FXML
	void MakeBid(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		BusinessFirmServicesRemote businessFirmServicesRemote = (BusinessFirmServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BusinessFirmServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BusinessFirmServicesRemote");

		UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/UserService!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");
		AuctionServicesRemote auctionServicesRemote = (AuctionServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");

		// float a=Float.parseFloat(Bid_yourbid.getText());
		// System.out.println(a);
		Bid thebid = Bid_table.getSelectionModel().getSelectedItem();
		Date date;
		date = auctionServicesRemote.GetServerDatetypeDate();
	
		
		currSeconds = hmsToSecons(thebid.getEnd_date().getHours(), thebid.getEnd_date().getMinutes(), thebid.getEnd_date().getSeconds())-hmsToSecons(date.getHours(), date.getMinutes(), date.getSeconds());
		if ((thebid.getEnd_date().getYear()==date.getYear()) && (thebid.getEnd_date().getMonth()==date.getMonth())
				&& (thebid.getEnd_date().getDate() == date.getDate())) {
			if (currSeconds > 3600) {
				ImageGreen.setVisible(true);
				ImageOr.setVisible(false);
				ImageRed.setVisible(false);
				Bid_bid.setDisable(true);
			}
			if ((currSeconds > 600 && currSeconds < 3600)) {
				ImageGreen.setVisible(false);
				ImageOr.setVisible(true);
				ImageRed.setVisible(false);
				Bid_bid.setDisable(true);
			}
			if (currSeconds < 600) {
				ImageGreen.setVisible(false);
				ImageOr.setVisible(false);
				ImageRed.setVisible(true);
				Bid_bid.setDisable(true);
			}
			if (currSeconds < 60) {
				ImageGreen.setVisible(false);
				ImageOr.setVisible(false);
				ImageRed.setVisible(true);
				RotateTransition transition1 = new RotateTransition(Duration.seconds(1), ImageRed);
				RotateTransition transition2 = new RotateTransition(Duration.seconds(1), ImageRed);

				transition1.setByAngle(40);
				transition2.setByAngle(36000);
				transition1.play();
				Bid_bid.setDisable(true);
			}

		}

		CounterDown.setVisible(true);
		System.out.println(thebid.toString());

		User user = LoginController.getLoggedUser();

		BusinessFirm bf = businessFirmServicesRemote.find(1);
		System.out.println(bf.toString());
		 if(!Bid_yourbid.getText().isEmpty())
		{	
			try 
			{
				float f=Float.parseFloat(Bid_yourbid.getText()) ;
		Auction auc = new Auction(user.getId(), thebid.getProduct().getId(),Float.parseFloat(Bid_yourbid.getText()), user, thebid, bf);
		System.out.println(user.toString());

		 auctionServicesRemote.update(auc);
			}
			catch(NumberFormatException e) {
				MyAlert.ErrorBox("You Must enter a float !!!!");
			}
			}
		else {
			MyAlert.WarningBox(" Please enter a value");
		}
		/*
		 * currSeconds =
		 * hmsToSecons(auctionServicesRemote.GetServerDatetypeDate().getHours(),
		 * auctionServicesRemote.GetServerDatetypeDate().getMinutes(),
		 * auctionServicesRemote.GetServerDatetypeDate().getSeconds());
		 */

		StartCountdown();
		if (currSeconds <= 0) {
			CounterDown.setVisible(false);
		}

	}

	
	@FXML
	void Bid_Buyout(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		  BankServicesRemote bankServicesRemote=
		    		(BankServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote");
	
	//	  bankServicesRemote.TransfertFromAccounts(LoginController.getLoggedUser().getBankAccounts(), Bid_table.getSelectionModel().getSelectedItem().getCompanyRep().getBankAccounts(),Bid_table.getSelectionModel().getSelectedItem().getBuyout() );
	  //    bankServicesRemote.TransfertToAccount(Bid_table.getSelectionModel().getSelectedItem().getCompanyRep().getBankAccounts(), Bid_table.getSelectionModel().getSelectedItem().getBuyout());
	System.out.println(Bid_table.getSelectionModel().getSelectedItem().getBuyout());
	System.out.println(Bid_table.getSelectionModel().getSelectedItem().getCompanyRep());
	}

	@FXML
	void BidNow(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		BusinessFirmServicesRemote businessFirmServicesRemote = (BusinessFirmServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BusinessFirmServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BusinessFirmServicesRemote");

		UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/UserService!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");
		AuctionServicesRemote auctionServicesRemote = (AuctionServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");

		User user = LoginController.getLoggedUser();

		BusinessFirm bf = businessFirmServicesRemote.find(1);
		Bid thebid = Bid_table.getSelectionModel().getSelectedItem();
		Date date;
		date = auctionServicesRemote.GetServerDatetypeDate();
	
		System.out.println(bf.toString());
		if(!Bid_yourbid.getText().isEmpty())
		{	
			try 
			{
				float f=Float.parseFloat(Bid_yourbid.getText()) ;
		Auction auc = new Auction(user.getId(), thebid.getProduct().getId(),Float.parseFloat(Bid_yourbid.getText()), user, thebid, bf);
		System.out.println(user.toString());

		 auctionServicesRemote.update(auc);
			}
			catch(NumberFormatException e) {
				MyAlert.ErrorBox("You Must enter a float !!!!");
			}
			}
		else {
			MyAlert.WarningBox(" Please enter a value");
		}
		
	}
	  @FXML
	    void Go_out(ActionEvent event) {
	System.exit(0);
	    }
	  @FXML
	    void Go_Back(ActionEvent event) throws IOException {
		  Parent root;
			Stage stage = (Stage) Go_Back.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../gui/hr/CustomerHome.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
}