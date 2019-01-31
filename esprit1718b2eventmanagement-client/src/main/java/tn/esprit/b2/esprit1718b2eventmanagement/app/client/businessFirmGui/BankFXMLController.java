/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesRemote;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class BankFXMLController implements Initializable {
	
	@FXML
    private Text DateSecondOperation;

   
	@FXML
	private JFXButton Butt;

    @FXML
    private Text SecondOperation;

    @FXML
    private Text AUDValue;

    @FXML
    private Text DateFirstOperation;

    @FXML
    private Text EuroValue;

    @FXML
    private Text ThirdOperation;

    @FXML
    private Text USDValue;

    @FXML
    private Text DateThirdOperation;

    @FXML
    private Text FirstOperation;

    @FXML
    private Text TNDValue;

    @FXML
    private AnchorPane account;

	@FXML
	private GridPane Operation3p;
	@FXML
	private GridPane Operation2p;

	@FXML
	private GridPane Operation1P;

	@FXML
	private AreaChart<?, ?> Operationchart;
	@FXML
	private AnchorPane transferts;
	private double xOffset = 0;
	private double yOffset = 0;
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
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		makeStageDrageable();
		XYChart.Series series = new XYChart.Series();
		try {
			series = getchart();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Operationchart.getData().add(series);
		Operation1P.setVisible(false);
		Operation2p.setVisible(false);
		Operation3p.setVisible(false);
		try {
			operationSys();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tn.esprit.b2.esprit1718b2eventmanagement.entities.Bank bank;
	 bank=LoginController.getLoggedUser().getBankAccounts().getBank();
	EuroValue.setText(bank.getEUR_Bal()+"");
	AUDValue.setText(bank.getAUD_Bal()+"");
	TNDValue.setText(bank.getTND_Bal()+"");
	USDValue.setText(bank.getUSD_Bal()+"");
	}

	private XYChart.Series getchart() throws NamingException {
		User user = LoginController.getLoggedUser();
		Context context = new InitialContext();
		TransfertServivesRemote transfertServivesRemote = (TransfertServivesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/TransfertServives!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesRemote");
		List<Transfer> transferts = transfertServivesRemote.findAll();
		XYChart.Series series = new XYChart.Series();

		for (Transfer t : transferts)

		{
			
				if (user.getBankAccounts().getRIB().equals(t.getAccount_sending().getRIB())) {
					series.getData().add(new XYChart.Data(t.getDate_transfert().toString(), t.getAmount()));
				}

			

		}

		return series;

	}
	private void operationSys() throws NamingException
	{
		User user = LoginController.getLoggedUser();
		Context context = new InitialContext();
		TransfertServivesRemote transfertServivesRemote = (TransfertServivesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/TransfertServives!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesRemote");
		 AuctionServicesRemote auctionServicesRemote=
	         		(AuctionServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/AuctionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.AuctionServicesRemote");
	   
		List<Transfer> transferts = transfertServivesRemote.findAll();
	

		for (Transfer t : transferts)

		{
		
				if (user.getBankAccounts().getRIB().equals(t.getAccount_reciving().getRIB())) {
					if(Operation1P.isVisible()!=true)
					{
						Operation1P.setVisible(true);
						DateFirstOperation.setText(transfertServivesRemote.intToTextMonth(t.getDate_transfert().getMonth()));
						FirstOperation.setText("From :"+t.getAccount_sending().getRIB()+" amount= "+t.getAmount());
					}
					else if(Operation1P.isVisible()&&(Operation2p.isVisible()!=true))
					{
						Operation2p.setVisible(true);
						DateSecondOperation.setText(transfertServivesRemote.intToTextMonth(t.getDate_transfert().getMonth()));
						SecondOperation.setText("From :"+t.getAccount_sending().getRIB()+" amount= "+t.getAmount());
					}
					else if(Operation2p.isVisible()&&(Operation3p.isVisible()!=true))
					{
						Operation3p.setVisible(true);
						DateThirdOperation.setText(transfertServivesRemote.intToTextMonth(t.getDate_transfert().getMonth()));
						ThirdOperation.setText("From :"+t.getAccount_sending().getRIB()+" Credit: "+t.getAmount());
					}
					
					
				}

			}

		}

	

	@FXML
	void Go_back(MouseEvent event) throws IOException {
		
		Parent root;
		Stage stage = (Stage) Butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../gui/hr/CustomerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

	@FXML
	private void GoAccount(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		
	}

	 @FXML
	    void GoTransferts(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankTransfertsFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
		
	
	
	@FXML
    void GoWallet(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankWalletFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    }
	@FXML
	void Go_out(ActionEvent event) {
		System.exit(0);
	}
}
