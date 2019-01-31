package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Products;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ProductsServicesRemote;

public class showcaseboothController implements Initializable {
	@FXML
	private JFXMasonryPane masonrypane;
	   @FXML
	    private ImageView iv;
	   @FXML
	    private Label boothid;

	    @FXML
	    private Label nametag;

	    @FXML
	    private Label pricetag;

	    @FXML
	    private Label desctag;

	    @FXML
	    private JFXButton bookmark;

	    @FXML
	    private Label boothname;
	private static  int id;

	public  int getId() {
		return id;
	}

	public  void setId(int id) {
		showcaseboothController.id = id;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			panepopulize();
			System.out.println(id+"  here");
		} catch (MalformedURLException | FileNotFoundException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
  
	public int getidBooth(int s) throws MalformedURLException, FileNotFoundException, NamingException {
		int a = s;
		
		setId(a);
		Context context = new InitialContext();

		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		Booth booths = boothServiceRemote.find(a);
		boothname.setText(booths.getName());
		return a;
		
	}

	private void panepopulize() throws MalformedURLException, NamingException, FileNotFoundException {

		Context context = new InitialContext();

		ProductsServicesRemote psr = (ProductsServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductsServices!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ProductsServicesRemote");
		List<Products> products = psr.findAll();
		
		for (int i = 0; i < products.size(); i++) {
			VBox vbox = new VBox(3);
			for (Products p : products) {
				
					
					Button btn=new Button("Consult");
					ImageView iv1 = new ImageView();
					InputStream is = new FileInputStream(p.getPic());

					Image hhhhhhhhh = new Image(is);

					iv1.setImage(hhhhhhhhh);
					iv1.setFitHeight(200);
					iv1.setFitWidth(150);
					vbox.setPrefSize(170, 250);
					vbox.getChildren().addAll(iv1,btn);
					 btn.setOnAction(new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent e) {
	                           nametag.setText(p.getName());
	                           pricetag.setText(""+p.getPrice());
	                           desctag.setText(p.getDescription());
	                           iv.setImage(hhhhhhhhh);
	                           boothid.setText(String.valueOf(p.getId()));
	                           try {
								panepopulize();
							} catch (MalformedURLException | FileNotFoundException | NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                        }
	                    }
	                    );
					
					
				
			
			}
			masonrypane.getChildren().add(vbox);
			
			
		}
		
	}
	@FXML
	void bookmark() throws NamingException
	{
		int p=Integer.parseInt(boothid.getText()) ;
		User u=LoginController.getLoggedUser();
		Context context = new InitialContext();

		ProductsServicesRemote psr = (ProductsServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductsServices!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ProductsServicesRemote");
	
		Products prod=psr.find(p);
		prod.setUser(u);
		psr.update(prod);
	}

    @FXML
    void returnb(ActionEvent event) throws IOException {
    	Stage stage = (Stage) nametag.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("boothdisplay.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
