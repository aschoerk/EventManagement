package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CompanyProfilUserProfileController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	
	
	
	@FXML
	void back(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyOwnerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void goToCompanyProfile(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyProfil.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToUserProfile(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyRepProfile.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) userName.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
