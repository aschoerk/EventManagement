package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Products;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ProductsServicesRemote;

public class ProductsAddController implements Initializable {
	  @FXML
	    private ImageView iv;
	   @FXML
	    private BorderPane borderhaja;
	    @FXML
	    private JFXTextField pname;

	    @FXML
	    private JFXTextField pricep;

	    @FXML
	    private JFXTextArea pdesc;

	    @FXML
	    private JFXComboBox clubscombo;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			clubscombo.getItems().addAll(populatecombo());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ObservableList<String> populatecombo() throws NamingException {
		//
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		String type="ShowCase";
		List<Booth> booths = boothServiceRemote.findBoothbytype(type);
		ObservableList<String> items = FXCollections.observableArrayList();
		CompanyRep rep = (CompanyRep) LoginController.getLoggedUser();
		for (Booth b : booths) {
			
			 items.add(b.getName());
		}

		return items;

	}
	@FXML
	public void add() throws NamingException
	{
		Context context = new InitialContext();
		
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
	
		ProductsServicesRemote   psr= (ProductsServicesRemote) context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ProductsServices!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ProductsServicesRemote");
		
		Booth b=boothServiceRemote.findBoothidbyname((String)clubscombo.getValue());
		
		Products p=new Products(pname.getText(),pdesc.getText(),Float.parseFloat(pricep.getText()),file,b );
		psr.save(p);
	}
	private File file;	
	@FXML
	void chooseBanner() {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		Window stage = null;
		file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			openFile(file);}
			

	}
	private Desktop desktop = Desktop.getDesktop();

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
	}
	private void openFile(File file) {

		Image image = new Image(file.toURI().toString(), 671, // requested width
				143, // requested height
				true, // preserve ratio
				false, true // load in background
		);
		// banner.setVisible(true);
   ImageView iv=new ImageView();
		iv.setImage(image);
		borderhaja.setCenter(iv);
		iv.fitWidthProperty().bind(borderhaja.widthProperty());
	       iv.fitHeightProperty().bind(borderhaja.heightProperty());

	}
	@FXML
	void clos(MouseEvent event) {

		ColorAdjust adjust = new ColorAdjust();
		adjust.setBrightness(-1);

		
		System.exit(0);

	}
	@FXML
    void returnb() throws IOException {
		Stage stage = (Stage) clubscombo.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../hr/BoothManagementComapnyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
}
