package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Bank extends Application {
    public static Stage stage = null;
  @Override
  public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("OwnerAuctionHouseFXML.fxml"));
      
      Scene scene = new Scene(root);
      scene.setFill(Color.TRANSPARENT);
      stage.initStyle(StageStyle.TRANSPARENT);
      stage.setScene(scene);
              this.stage = stage;

      stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }
  
}
