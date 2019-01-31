/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class CompanyRepHomeController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
	 @FXML
	    private JFXButton logout;
	
	@FXML
	void logOut(ActionEvent event) throws NamingException, IOException {
		long startTime = LoginController.getStarterTime();
		long endTime = System.nanoTime();
		Context context = new InitialContext();
		
		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		CompanyRep companyRep =companyRepServiceRemote.find(LoginController.getLoggedUser().getId());
		
		companyRep.setHoursSpent((endTime-startTime)*(2.778) * 0.00000000000001);
		companyRepServiceRemote.update(companyRep);
		Stage stage = (Stage) logout.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyRepHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
