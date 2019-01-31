package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;

public class infoBoothController implements Initializable {
	@FXML
	private AnchorPane parent;
	@FXML
	private ScrollPane scroll;
	@FXML
	private Label compane;

	@FXML
	private Label comdesc;
	@FXML
	private Pane eventsp;
	@FXML
	private Label comdesc1, namel;
	@FXML
	private JFXButton ypbutton;
	@FXML
	BorderPane imageholder;
	@FXML
	private ImageView iv;
	@FXML
	private TableView<ObservableList<SpecialEvent>> tableev;

	@FXML
	private TableColumn<SpecialEvent, String> namecol;

	@FXML
	private TableColumn<SpecialEvent, String> descol;
	@FXML
	private Pane rating, contentpane;
	private Rating rate;
	@FXML
    private Label note;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		rate = new Rating();
		rate.setPartialRating(true);
		rate.setOrientation(Orientation.VERTICAL);
		rating.getChildren().add(rate);
		rate.setPrefSize(rating.getWidth(), rating.getHeight());
		rate.setRating(4);
		note.setText(""+rate.getRating());

	}

	void settext(int a) throws IOException {
		Context context;
		try {
			context = new InitialContext();

			BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
					"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
			Booth booths = boothServiceRemote.find(a);
			note.setText(""+rate.getRating());
			note.setText(""+booths.getRating());

			namel.setText(booths.getName());
			ImageView iv = new ImageView();
			InputStream is = new FileInputStream(booths.getBanner());

			BufferedImage imBuff = ImageIO.read(is);
			WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
			iv.setImage(image);
			imageholder.getChildren().add(iv);
			iv.fitWidthProperty().bind(imageholder.widthProperty());
			iv.fitHeightProperty().bind(imageholder.heightProperty());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int a;

	public int getidBooth(int s) throws NamingException {
		int a = s;
		try {
			settext(a);
			populatetable(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@FXML
	private void goup() throws NamingException {

		if (iv.getRotate() == -90) {
			TranslateTransition closeNav = new TranslateTransition(new Duration(350), contentpane);
			System.out.println("teeeeeeeeeeeeeeeeeest");
			closeNav.setToY(-280);
			closeNav.play();

			iv.setRotate(90);
		} else if (iv.getRotate() == 90) {
			godown();
			iv.setRotate(-90);
		}

	}

	private void godown() {

		TranslateTransition closeNav = new TranslateTransition(new Duration(350), contentpane);
		System.out.println("teeeeeeeeeeeeeeeeeest");
		closeNav.setToY(0);
		closeNav.play();

		iv.setRotate(90);

	}

	public void populatetable(int r) throws NamingException {
		Context context = new InitialContext();

		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/User"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		EventManagementServicesRemote eventManagementServicesRemote2;
		eventManagementServicesRemote2 = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

		Booth booths = boothServiceRemote.find(r);
		System.out.println(booths.getName());
		int i = booths.getCompanyRep().getId();
		User trp =  userServiceRemote.find(i);
		System.out.println(trp.getId() + " eeeeeeeeeee");
		
		List<SpecialEvent> eve = eventManagementServicesRemote2.findAllOwnerEvents(trp);
	
		ObservableList<SpecialEvent> data = FXCollections.observableArrayList(eve);
		namecol.setCellValueFactory(new PropertyValueFactory<SpecialEvent,String>("name"));

		descol.setCellValueFactory(new PropertyValueFactory<SpecialEvent,String>("description"));
		tableev.getColumns();
		tableev.getItems().add(data);

		

	}
	 @FXML
	    void returnb(ActionEvent event) throws IOException {
	    	Stage stage = (Stage) rating.getScene().getWindow();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("boothdisplay.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

}
