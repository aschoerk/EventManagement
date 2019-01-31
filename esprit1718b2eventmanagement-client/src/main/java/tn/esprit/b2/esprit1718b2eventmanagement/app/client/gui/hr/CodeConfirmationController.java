package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;

public class CodeConfirmationController implements Initializable {

	private LoginController loginController;
	@FXML
	private JFXTextField codeField;

	@FXML
	private JFXButton confirmation;
	@FXML
    private JFXButton x;

	@FXML
	private Label message;

	@FXML
	void checkConfirmation(ActionEvent event) throws NamingException {
		User u = LoginController.getLoggedUser();
		if (!u.getCode().equals(codeField.getText())) {
			message.setText("wrong Code!!");
		} else {

			Context context = new InitialContext();

			UserServiceRemote userServiceRemote = (UserServiceRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/User"
							+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

			u.setCode("0");
			userServiceRemote.update(u);
			int test = userServiceRemote.checkRole(u);

			Stage stage = (Stage) codeField.getScene().getWindow();
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
			} else if (test == 1) {
				try {
					Parent root;
					root = FXMLLoader.load(getClass().getResource("CompanyRepHome.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
				}
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	 @FXML
	    void close(ActionEvent event) {
		 System.exit(0);
	    }

}
