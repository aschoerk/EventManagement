/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class LoginController implements Initializable {

	@FXML
	private JFXButton signin;

	@FXML
	private JFXTextField login;

    @FXML
    private Label message;
    @FXML
    private AnchorPane parent;
  

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton logg;
	   @FXML
	    private JFXButton x;
	   private double xOffset = 0;
		private double yOffset = 0;
	    @FXML
	    void close(ActionEvent event) {
	    	System.exit(0);
	    }
	private static User loggedUser;
	private static long starterTime;

	public static long getStarterTime() {
		return starterTime;
	}
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
	public static void setStarterTime(long starterTime) {
		LoginController.starterTime = starterTime;
	}

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		LoginController.loggedUser = loggedUser;
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		makeStageDrageable();
	}

	private Stage stage;

	@FXML
	void logingIn(ActionEvent event) throws NamingException {

		Context context = new InitialContext();

		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/User"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

		User user = new User();
		String l = login.getText();
		String pwd = password.getText();

		user = userServiceRemote.login(l, pwd);
		setLoggedUser(user);

		if (user.getPhone()!=0) {

			int test = userServiceRemote.checkRole(user);
			System.out.println(test+"aaaaaaaaaaaaaaaaaaaaaaa");
			stage = (Stage) logg.getScene().getWindow();
			if (user.getCode().equals("0")) {
				if (test == -1) {
 
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					} catch (IOException e) {
					}

				} else if (test == 0) {
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("CompanyOwnerHome.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					} catch (IOException e) {
					}
				}
				if (test == 1) {
					setStarterTime(System.nanoTime());
					Calendar calendar = Calendar.getInstance();
					calendar.getTime();
					CompanyRep companyRep = new CompanyRep();
					companyRep = userServiceRemote.getCompanyRepFromUser(getLoggedUser());
					if (calendar.get(Calendar.MONTH) != companyRep.getWorkingMonth()) {

						companyRep.setWorkingMonth(calendar.get(Calendar.MONTH));
						companyRep.setHoursSpent(0);
						userServiceRemote.update(companyRep);
					}
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("CompanyRepHome.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					} catch (IOException e) {
					}
				}else if (test == 2)
				{
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					} catch (IOException e) {
					}
				}
			} else {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("CodeConfirmation.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
				}

			}
		}else
			message.setText("Password/Username incorrect");
	}

	@FXML
	void signIn(ActionEvent event) throws IOException {
		stage = (Stage) logg.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
