/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Transfer;
import tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesRemote;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class BankTransfertsFXMLController implements Initializable {
    
	@FXML
    private AnchorPane account;
	@FXML
	private JFXButton Butt;
    @FXML
    private AnchorPane transferts;
    @FXML
    private TableColumn<Transfer, Date> DateTransfert;
    @FXML
    private TableColumn<Transfer, Integer> TrasfertFrom;
    @FXML
    private TableColumn<Transfer, Integer> TransfertTo;
    @FXML
    private TableColumn<Transfer, Float> TransfertAmount;
    @FXML
    private TableView<Transfer> Transfert_table;
    private double xOffset =0;
	private double yOffset =0;
	@FXML
	private AnchorPane parent;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	makeStageDrageable();
        
    	
    }    
    @FXML
	void Go_back(MouseEvent event) throws IOException {
		
		Parent root;
		Stage stage = (Stage) Butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("../gui/hr/CustomerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void GoAccount(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		
	}

	 @FXML
	    void GoTransferts(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankTransfertsFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
		
	
	
	@FXML
    void GoWallet(MouseEvent event) throws IOException {
		 Parent root;
			Stage stage =  (Stage) Butt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("BankWalletFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    }
    
    @FXML
    void searchTransfet(ActionEvent event) throws NamingException {
    	Context context = new InitialContext();
		TransfertServivesRemote transfertServivesRemote=
				(TransfertServivesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/TransfertServives!tn.esprit.b2.esprit1718b2eventmanagement.services.firmservices.TransfertServivesRemote");
		List<Transfer> myResult = transfertServivesRemote.findAll();
//System.out.println(myResult.toString());
		ObservableList<Transfer> data = FXCollections.observableArrayList(myResult);
		DateTransfert.setCellValueFactory(new PropertyValueFactory<Transfer,Date>("date_transfert"));

		TrasfertFrom.setCellValueFactory(new PropertyValueFactory<Transfer,Integer>("account_sending"));
		TransfertTo.setCellValueFactory(new PropertyValueFactory<Transfer,Integer>("account_reciving"));
		TransfertAmount.setCellValueFactory(new PropertyValueFactory<Transfer,Float>("amount"));
	//	System.out.println(data.toString());	
		Transfert_table.setItems((ObservableList<Transfer>) data);

    }
    @FXML
    void Go_out(ActionEvent event) {
    	System.exit(0);

    }

}
