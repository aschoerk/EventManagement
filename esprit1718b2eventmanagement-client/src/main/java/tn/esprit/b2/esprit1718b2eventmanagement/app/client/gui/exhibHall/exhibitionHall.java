package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM.EventManagment;

public class exhibitionHall extends Application {
	
		public static Stage stage = null;

		@Override
		public void start(Stage stage) throws Exception {
			
			Parent root = FXMLLoader.load(getClass().getResource("BoothAddFXML.fxml"));

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
