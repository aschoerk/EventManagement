package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class testofytLive extends Application {
    @Override
    public void start(Stage primaryStage) {
     
            StackPane root = new StackPane();
            Scene scene = new Scene(root, 640, 400);
            primaryStage.setScene(scene);
            WebView embeddedWV = new WebView();
            embeddedWV.getEngine().load(
           "https://www.youtube.com/watch?v=EyXCBnxbLZo"
                );
                embeddedWV.setPrefSize(640, 400);
                root.getChildren().add(embeddedWV);

                primaryStage.setScene(scene);
                primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
