package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Shift;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.ShiftServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

public class AddAShiftController implements Initializable {

	@FXML
	private AnchorPane parent;

	@FXML
	private JFXTimePicker startTim;

	@FXML
	private JFXTimePicker endTim;

	@FXML
	private JFXDatePicker startDte;

	@FXML
	private JFXDatePicker endDte;

	@FXML
	private JFXButton add;

	@FXML
	private JFXComboBox<String> comboBothReps;

	@FXML
	private JFXComboBox<String> comboBooth;

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	void Go_auction_Owner(MouseEvent event) {

	}

	@FXML
	void back(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) add.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("BoothManagementComapnyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) add.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			comboBothReps.getItems().addAll(fillcomboCompanyRep());
			comboBooth.getItems().addAll(fillcomboBooth());
		} catch (NamingException e) {
		}
	}

	@FXML
	void addAShift(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context
				.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Booth"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		ShiftServiceRemote shiftServiceRemote = (ShiftServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Shift"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.ShiftServiceRemote");

		Shift shift = new Shift();
		shift.setStartDate(Date.valueOf(startDte.getValue()));
		shift.setEndDate(Date.valueOf(endDte.getValue()));
		shift.setStartTime(Time.valueOf(startTim.getValue()));
		shift.setEndTime(Time.valueOf(endTim.getValue()));
		String s = comboBooth.getValue();
		int c = s.indexOf(":");
		String s1 = comboBothReps.getValue();
		int c1 = s1.indexOf(":");

		shift.setCompanyRep(companyRepServiceRemote.find(Integer.parseInt((String) s1.subSequence(0, c1))));
		shiftServiceRemote.update(shift);
		ArrayList<Shift> shifts = new ArrayList<>();
		shifts = (ArrayList<Shift>) shiftServiceRemote.findAll();
		Shift last = shifts.get(shifts.size() - 1);

		Booth booth = boothServiceRemote.find(Integer.parseInt((String) s.subSequence(0, c)));
		last.setBooth(booth);
		shiftServiceRemote.update(last);
		shifts = (ArrayList<Shift>) shiftServiceRemote.findAll();
		Shift last2 = shifts.get(shifts.size() - 2);
		System.out.println(last2.getId());
		shiftServiceRemote.update(last2);
		Parent root;
		Stage stage = (Stage) add.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("CompanyOwnerHomePage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static ObservableList<String> fillcomboCompanyRep() throws NamingException {

		LoginController loginController = new LoginController();
		Context context = new InitialContext();
		ShiftServiceRemote shiftServiceRemote = (ShiftServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Shift"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.ShiftServiceRemote");

		ArrayList<CompanyRep> companyReps = new ArrayList<>();

		companyReps = shiftServiceRemote.getListCompanyReps(loginController.getLoggedUser().getId());
		ObservableList<String> items = FXCollections.observableArrayList();
		for (CompanyRep companyRep1 : companyReps) {
			items.add(companyRep1.getId() + ":" + companyRep1.getName());
		}

		return items;
	}

	public static ObservableList<String> fillcomboBooth() throws NamingException {

		Context context = new InitialContext();

		LoginController loginController = new LoginController();

		ShiftServiceRemote shiftServiceRemote = (ShiftServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Shift"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.ShiftServiceRemote");

		ArrayList<Booth> booths = new ArrayList<>();
		User user = new User();
		user = loginController.getLoggedUser();
		CompanyRep companyRep = new CompanyRep();

		booths = shiftServiceRemote.getListBoothes(user.getId());

		ObservableList<String> items = FXCollections.observableArrayList();
		for (Booth booth : booths) {
			items.add(booth.getId() + ":" + booth.getName());
		}

		return items;
	}

}
