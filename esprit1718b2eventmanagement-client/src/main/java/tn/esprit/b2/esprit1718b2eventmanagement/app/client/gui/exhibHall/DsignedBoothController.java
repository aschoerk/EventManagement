package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM.EventManagment;

public class DsignedBoothController implements Initializable {
	@FXML
	private AnchorPane parent;
	private double xOffset = 0;
	private double yOffset = 0;
	private static final int DEFAULT_STARTING_X_POSITION = 0;
	private static final int DEFAULT_ENDING_X_POSITION = -120;
	@FXML
	private TextArea messages;
	@FXML
	private TextField input;

	@FXML
	private Pane background;
	private boolean isServer = false;
	private NetworkConnection connection=isServer?createServer():createClient();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();
		try {
			connection.startConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     input.setOnAction(event->{
    	 String message=isServer?"Server ":"Client ";
    	 message+=input.getText();
    	 input.clear();
    	 messages.appendText(message+"\n");
    	 try {
			connection.send(message);
		} catch (Exception e) {
			messages.appendText("failed to send \n");
		}
     }
    		 );
	}
 
 public void init()throws Exception , IOException{
	 connection.startConnection();
 }
	private Server createServer() {
		  
		return new Server(55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + "\n");

			});

		});
	}

	private Client createClient() {
	
		return new Client("127.0.0.1", 55555, data -> {
			Platform.runLater(() -> {
				messages.appendText(data.toString() + " \n");

			});

		});
	}
	/*public void stop()throws Exception{
		connection.closeConnection();
	}*/
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
				EventManagment.stage.setX(event.getScreenX() - xOffset);
				EventManagment.stage.setY(event.getScreenY() - yOffset);
				EventManagment.stage.setOpacity(0.7f);
			}
		});
		parent.setOnDragDone((e) -> {
			EventManagment.stage.setOpacity(1.0f);
		});
		parent.setOnMouseReleased((e) -> {
			EventManagment.stage.setOpacity(1.0f);
		});
	}
	

	/*
	 * @FXML void clos() { ColorAdjust adjust = new ColorAdjust();
	 * adjust.setBrightness(-1);
	 * 
	 * closeButton.setEffect(adjust); System.exit(0); } /*
	 * 
	 * 
	 * @Override public void initialize(URL arg0, ResourceBundle arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * } /* int i=0;
	 * 
	 * public int getI() { return i; }
	 * 
	 * 
	 * 
	 * @FXML void showchat() { TextField input=new TextField(); TextArea
	 * messages=new TextArea(); VBox chatbox=new VBox(20,messages,input);
	 * chatbox.setPrefSize(300,300); chatbox.setEffect(new DropShadow(30,
	 * Color.WHITE)); background.getChildren().add(chatbox);
	 * background.setVisible(true);
	 * 
	 * }
	 */

}
