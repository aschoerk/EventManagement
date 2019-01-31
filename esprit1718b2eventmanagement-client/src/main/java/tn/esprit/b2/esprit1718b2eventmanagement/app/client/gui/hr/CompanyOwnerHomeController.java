/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class CompanyOwnerHomeController implements Initializable {
	@FXML
	private VBox booth;

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	private JFXTextField shearcher;

	@FXML
	private TableView<CompanyRep> tb;

	@FXML
	private TableColumn<CompanyRep, String> nomtb;

	@FXML
	private TableColumn<Long, Long> moneytb;

	@FXML
	private TableColumn<CompanyRep, Integer> phonetb;

	@FXML
	private TableColumn<CompanyRep, String> mailtb;

	@FXML
	void back(MouseEvent event) {

	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void goToProfils(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyProfilUserProfile.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		userName.setText(LoginController.getLoggedUser().getName());
		try {
			List<CompanyRep> listForm = new ArrayList<>();
			Context context;
			context = new InitialContext();
			CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
							+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

			listForm = companyRepServiceRemote.findEmployeesForACompanyOwner(
					companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));

			resetTableData(listForm);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void boothManagement(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("BoothManagementComapnyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// ***********************
	/*
	 * @FXML void goAddABoothManager(ActionEvent event) throws IOException { Stage
	 * stage = (Stage) logout1.getScene().getWindow(); Parent root; root =
	 * FXMLLoader.load(getClass().getResource("AddABoothManager.fxml")); Scene scene
	 * = new Scene(root); stage.setScene(scene); stage.show(); }
	 * 
	 * @FXML void goToAdd(ActionEvent event) throws IOException { Stage stage =
	 * (Stage) shifter.getScene().getWindow(); Parent root; root =
	 * FXMLLoader.load(getClass().getResource("AddAShift.fxml")); Scene scene = new
	 * Scene(root); stage.setScene(scene); stage.show(); }
	 */
	@FXML
	void Go_auction_Owner(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../../businessFirmGui/OwnerAuctionHouseFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToEvents(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../EM/OwnerEventPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToCompanyOwnerHome(MouseEvent event) {

	}

	public void resetTableData(List<CompanyRep> list) throws NamingException {
		tb.refresh();
		Context context = new InitialContext();

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		List<CompanyRep> myResult = companyRepServiceRemote
				.findEmployeesForACompanyOwner(companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));
		for (CompanyRep companyRep : myResult) {
			Double double1 = companyRep.getHourPrice() * companyRep.getHoursSpent();
			companyRep.setHoursSpent(double1);
		}
		ObservableList<CompanyRep> data = FXCollections.observableArrayList(myResult);
		nomtb.setCellValueFactory(new PropertyValueFactory<>("name"));

		moneytb.setCellValueFactory(new PropertyValueFactory<>("hoursSpent"));

		phonetb.setCellValueFactory(new PropertyValueFactory<>("phone"));

		mailtb.setCellValueFactory(new PropertyValueFactory<>("email"));

		tb.setItems(data);

	}

	public void resetTableData1(List<CompanyRep> list) throws NamingException {
		tb.refresh();
		tb.getItems().removeAll();
		tb.refresh();
		Context context = new InitialContext();

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		List<CompanyRep> myResult = companyRepServiceRemote.findEmployeesForACompanyOwnerByAString(shearcher.getText(),
				companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));
		for (CompanyRep companyRep : myResult) {
			Double double1 = companyRep.getHourPrice() * companyRep.getHoursSpent();
			companyRep.setHoursSpent(double1);
		}
		ObservableList<CompanyRep> data = FXCollections.observableArrayList(myResult);
		nomtb.setCellValueFactory(new PropertyValueFactory<>("name"));

		moneytb.setCellValueFactory(new PropertyValueFactory<>("hoursSpent"));

		phonetb.setCellValueFactory(new PropertyValueFactory<>("phone"));

		mailtb.setCellValueFactory(new PropertyValueFactory<>("email"));

		tb.setItems(data);

	}

	@FXML
	void shearchDisplay(KeyEvent event) {

		tb.getItems().clear();
		try {
			List<CompanyRep> listForm = new ArrayList<>();
			Context context;
			context = new InitialContext();
			CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
							+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

			if (shearcher.getText().isEmpty()) {
				listForm = companyRepServiceRemote.findEmployeesForACompanyOwner(
						companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));

				resetTableData(listForm);
			} else {
				listForm = companyRepServiceRemote.findEmployeesForACompanyOwnerByAString(shearcher.getText(),
						companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));
				resetTableData1(listForm);
			}

		} catch (NamingException e) {
		}
	
	
	}
}
