/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.BankAccount;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CreditCard;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.CreditCardServicesRemote;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class BankWalletController implements Initializable {
	@FXML
	private AnchorPane account;
	@FXML
	private JFXButton Butt;
	@FXML
	private AnchorPane transferts;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private JFXTextField EXP_Year_input;
	@FXML
	private JFXTextField your_Rib;
	@FXML
	private JFXButton Make_paye;

	@FXML
	private JFXTextField degit1_input;

	@FXML
	private JFXTextField P_Amount_input;

	@FXML
	private CheckBox Visa;

	@FXML
	private AnchorPane Payment_pane;

	@FXML
	private CheckBox Discover;

	@FXML
	private CheckBox MasterCard;

	@FXML
	private JFXTextField EXP_month_input;

	@FXML
	private JFXTextField degit4_input;

	@FXML
	private JFXTextField T_Amount;

	@FXML
	private JFXTextField PassCode;

	@FXML
	private JFXTextField degit3_input;

	@FXML
	private JFXTextField cvv_input;

	@FXML
	private JFXTextField degit2_input;

	@FXML
	private CheckBox AmericanExpress;

	@FXML
	private JFXTextField dest_RIB;
	@FXML
	private JFXTextField my_amount;

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
		Payment_pane.setVisible(false);
		User user = LoginController.getLoggedUser();

		your_Rib.setText(user.getBankAccounts().getRIB());
		your_Rib.setDisable(true);
		my_amount.setText(user.getBankAccounts().getCredit() + "");

	}

	@FXML
	private void GoAccount(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) Butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("BankFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void GoTransferts(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) Butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("BankTransfertsFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void GoWallet(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) Butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("BankWalletFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Go_out(ActionEvent event) {
		System.exit(0);
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
	void MakeTransfert(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		BankServicesRemote bankServicesRemote = (BankServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankServicesRemote");
		BankaccountServicesRemote bankaccountServicesRemote = (BankaccountServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankaccountServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesRemote");
		List<BankAccount> accounts = bankaccountServicesRemote.findAll();

		BankAccount account_sending = bankaccountServicesRemote.FindbyRIB(your_Rib.getText(), accounts);
		BankAccount account_reciving = bankaccountServicesRemote.FindbyRIB(dest_RIB.getText(), accounts);
		if (!PassCode.getText().isEmpty()) {
			if (!dest_RIB.getText().isEmpty()) {
				List<BankAccount> baccounts = bankaccountServicesRemote.findAll();
				Boolean response = false;
				for (BankAccount bac : baccounts)
					if (bac.getRIB().equals(dest_RIB.getText())) {
						response = true;
					}
				if (response) {
					try {
						float f = Float.parseFloat(T_Amount.getText());
						if (PassCode.getText()
								.equals(bankaccountServicesRemote.DecryptPasswd(account_sending.getPasswd()))) {
							System.out.println(" sucessss");
							bankServicesRemote.TransfertFromAccounts(account_sending, account_reciving,
									Float.parseFloat(T_Amount.getText()));
							bankServicesRemote.TransfertToAccount(account_reciving,
									Float.parseFloat(T_Amount.getText()));
							bankServicesRemote.TransfertforAccount(account_sending,
									Float.parseFloat(T_Amount.getText()));

						} else {
							MyAlert.WarningBox("Sorry , The password doesn't match , try again !");
							;
						}
					} catch (NumberFormatException e) {
						MyAlert.ErrorBox("You Must enter a float !!!!");
					}
				} else {
					MyAlert.infoBox("The Destination RIB does not exist , try again", "Sorry :)");
				}
			} else {
				MyAlert.ErrorBox("you need to enter destination RIB");
			}
		} else {
			MyAlert.ErrorBox("you need to enter your password");
		}
		System.out.println(account_reciving);
		System.out.println(account_sending);
		System.out.println(Float.parseFloat(T_Amount.getText()) + "");
	}

	@FXML
	void FillWallet(ActionEvent event) {
		Payment_pane.setVisible(true);

	}

	@FXML
	void Make_paye(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		BankaccountServicesRemote bankaccountServicesRemote = (BankaccountServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BankaccountServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.BankaccountServicesRemote");
		CustomerServiceRemote customerServiceRemote = (CustomerServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CustomerService!tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote");
		CreditCardServicesRemote cardServicesRemote=
				(CreditCardServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CreditCardServices!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.CreditCardServicesRemote");
		CreditCard card = new CreditCard();
		card.setId(1);
		card.setDegit1(degit1_input.getText());
		card.setDegit2(degit2_input.getText());
		card.setDegit3(degit3_input.getText());
		card.setDegit4(degit4_input.getText());
		card.setCvv(Integer.parseInt(cvv_input.getText()));
		card.setExpDate(EXP_month_input.getText() + "/" + EXP_Year_input.getText());
		if (Visa.isSelected()) {
			card.setType("Visa");
		} else if (MasterCard.isSelected()) {
			card.setType("MasterCard");
		} else if (AmericanExpress.isSelected()) {
			card.setType("AmericanExpress");
		} else if (Discover.isSelected()) {
			card.setType("Discover");
		}
		User user = LoginController.getLoggedUser();
		float i = user.getBankAccounts().getCredit();
		user.getBankAccounts().setCredit(i + Float.parseFloat(P_Amount_input.getText()));
		bankaccountServicesRemote.update(user.getBankAccounts());
		CreditCard card2=cardServicesRemote.find(1);
		if(card2.getDegit1().equals(card.getDegit1()) )
		{
		MyAlert.infoBox("Your Withdraw was succesful !", "Sucess");
		System.out.println(card.toString());
		System.out.println(card.getType());
		Payment_pane.setVisible(false);
		}
		else
		{
			MyAlert.ErrorBox("Card is Unvalid Try Again");
		}
	}

}
