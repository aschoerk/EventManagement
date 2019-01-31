package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminHomeController implements Initializable {
	   @FXML
	    private Label userName;

	    @FXML
	    private ImageView close;

	    @FXML
	    void back(MouseEvent event) {

	    }

	    @FXML
	    void closeWindow(MouseEvent event) {
	    	System.exit(0);
	    }

	    @FXML
	    void logout(MouseEvent event) {

	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			userName.setText(LoginController.getLoggedUser().getName());
		}

}
