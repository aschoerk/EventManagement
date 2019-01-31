/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class CustomerHomeController implements Initializable {

	@FXML
	private JFXButton eventsButt;
	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	private JFXButton exhibition;

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void goToExhibitionHalll(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) eventsButt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../exhibHall/boothdisplay.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToInfoCenter(ActionEvent event) {

	}

	@FXML
	void goToProfile(ActionEvent event) {

	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root;
		stage = (Stage) eventsButt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

	}

	private Stage stage;

	@FXML
	void goToEvents(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) eventsButt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../EM/AllEventsFXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Go_Auctionhouse(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) eventsButt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../../businessFirmGui/AuctionHouseFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Go_bank(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) eventsButt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../../businessFirmGui/BankFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
