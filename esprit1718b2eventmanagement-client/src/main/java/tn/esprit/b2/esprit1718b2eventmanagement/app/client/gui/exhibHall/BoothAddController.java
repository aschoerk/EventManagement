package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;
import javax.imageio.ImageIO;

import java.awt.Button;
import java.awt.Desktop;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM.EventManagment;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ExbHallServicesRemote;

public class BoothAddController implements Initializable {
	@FXML
	private AnchorPane parent;
	private double xOffset = 0;
	private double yOffset = 0;
	private static final int DEFAULT_STARTING_X_POSITION = 0;
	private static final int DEFAULT_ENDING_X_POSITION = -120;
	 

	    @FXML
	    private ImageView closeButton;

	    @FXML
	    private JFXTextField bname;

	    @FXML
	    private JFXTextField logopath;

	    @FXML
	    private ImageView bvideo;

	    @FXML
	    private ImageView vidpreview;

	    @FXML
	    private JFXTextField bname1;

	    @FXML
	    private JFXComboBox clubscombo;

	    @FXML
	    private Label chooseclub;

	    @FXML
	    private RadioButton checkyes;

	    @FXML
	    private ToggleGroup yes;

	    @FXML
	    private RadioButton checkno;

	    @FXML
	    private BorderPane boothstyle1;

	    @FXML
	    private ImageView ivbground;

	    @FXML
	    private Pane outerpane;

	    @FXML
	    private BorderPane borderhaja;

	    @FXML
	    private JFXRadioButton rinfo;

	    @FXML
	    private ToggleGroup boothty;

	    @FXML
	    private JFXRadioButton rshow;

	    @FXML
	    private JFXRadioButton roffers;
	@FXML
	void clos(MouseEvent event) {

		ColorAdjust adjust = new ColorAdjust();
		adjust.setBrightness(-1);

		closeButton.setEffect(adjust);
		System.exit(0);

	}

	@FXML
	void initialize() {

	}
	

	public static ObservableList<String> populatecombo() throws NamingException {
		//
		Context context = new InitialContext();
		ClubServiceRemote clubServiceRemote = (ClubServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ClubService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote");

		List<Club> c = clubServiceRemote.findAll();
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Club c1 : c) {
			items.add(c1.getName());
		}

		return items;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showvalues();
		try {
			System.out.println(populatecombo());
			clubscombo.getItems().addAll(populatecombo());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void showvalues() {
		if (checkyes.isSelected()) {
			chooseclub.setVisible(true);
			clubscombo.setVisible(true);

			System.out.println("test");
		}
		if (checkno.isSelected()) {
			chooseclub.setVisible(false);
			clubscombo.setVisible(false);

		}
		
	}

	@FXML
	void testadd() throws NamingException, IOException {
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		
		String type=new String();
		if(rinfo.isSelected())
		{
			type="informational";
		}
		if(roffers.isSelected())
		{
			type="Job Offers";
		}
		if(rshow.isSelected())
		{
			type="ShowCase";
		}
		
		
		Booth booth= new Booth(bname.getText(),file, type, bname1.getText(),null);
		
	    
	     boothServiceRemote.update(booth);
		ExbHallServicesRemote exbHallServicesRemote = (ExbHallServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ExbHallServices!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ExbHallServicesRemote");
		List<Booth> booths = boothServiceRemote.findAll();
		Booth b = booths.get(booths.size() - 1);
		if (checkyes.isSelected()) {
			if (!clubscombo.getValue().equals("")) {
				Club club = exbHallServicesRemote.findClubidbyname((String) clubscombo.getValue());
				System.out.println(club.getId());
				exbHallServicesRemote.assignBoothClub(b, club);
			}
		}
		
		
		
 boothstyle1.setVisible(true);
 outerpane.setVisible(true);
 
 String toastMsg = "Booth successfully added";
 int toastMsgTime = 3500; //3.5 seconds
 int fadeInTime = 500; //0.5 seconds
 int fadeOutTime= 500; //0.5 seconds


	
	
	Stage mddle=new Stage();

 Toast.makeText(mddle, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
 
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
	
	@FXML
    void breturn() throws IOException {
		Stage stage = (Stage) bname.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../hr/BoothManagementComapnyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

	

}
