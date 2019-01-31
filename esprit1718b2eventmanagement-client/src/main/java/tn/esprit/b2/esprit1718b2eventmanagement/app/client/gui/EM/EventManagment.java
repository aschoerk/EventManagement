/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author esprit
 */
public class EventManagment extends Application {
	public static Stage stage = null;

	@Override
	public void start(Stage stage) throws Exception {
		 //Parent root = FXMLLoader.load(getClass().getResource("AllEventsFXMLDocument.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("AddSpecialEvent.fxml"));

		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setScene(scene);
		EventManagment.stage = stage;

		stage.show();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
