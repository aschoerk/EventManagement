package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;

public class OwnerEventPageController implements Initializable {

	@FXML
	private AnchorPane parent;
	@FXML
	private AnchorPane par;
	@FXML
	private AnchorPane fortheripplermr;
	@FXML
	private AnchorPane fortheripplermv;
	@FXML
	private AnchorPane fortheripplerms;
	@FXML
	private VBox booth;


    @FXML
    private AnchorPane eventshow;
	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	private Label mrated;

	@FXML
	private Label mviewed;

	@FXML
	private Label name1;

	@FXML
	private Label type1;

	@FXML
	private Label link1;

	@FXML
	private Label sector1;

	@FXML
	private Label sdate1;

	@FXML
	private Label edate1;

	@FXML
	private Label rate1;

	@FXML
	private Label visits1;

	@FXML
	private Label address1;

	@FXML
	private Label phone1;

	@FXML
	private Label name3;

	@FXML
	private Label type3;

	@FXML
	private Label link3;

	@FXML
	private Label sector3;

	@FXML
	private Label sdate3;

	@FXML
	private Label edate3;

	@FXML
	private Label rate3;

	@FXML
	private Label visits3;

	@FXML
	private Label address3;

	@FXML
	private Label phone3;

	@FXML
	private Label name2;

	@FXML
	private Label type2;

	@FXML
	private Label link2;

	@FXML
	private Label sector2;

	@FXML
	private Label sdate2;

	@FXML
	private Label edate2;

	@FXML
	private Label rate2;

	@FXML
	private Label visits2;

	@FXML
	private Label address2;

	@FXML
	private Label phone2;

	@FXML
	private AnchorPane mreatedAnchor;
	@FXML
	private AnchorPane mviewAnchor;
	@FXML
	private AnchorPane msubsAnchor;

	@FXML
	private AnchorPane anchor1;

	@FXML
	private ImageView imgv1;

	@FXML
	private AnchorPane anchor3;

	@FXML
	private ImageView imgv3;
	@FXML
	private AnchorPane anchor2;

	@FXML
	private ImageView imgv2;
	@FXML
	private JFXButton prev;

	@FXML
	private JFXButton next;
	@FXML
	private Label msubs;
	private int it = 0;

	@FXML
	void Go_auction_Owner(KeyEvent event) {

	}

	@FXML
	void addevent(ActionEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) sdate1.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("AddSpecialEvent.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void back(MouseEvent event) {

	}

	@FXML
	void boothManagement(MouseEvent event) {

	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void goToCompanyOwnerHome(KeyEvent event) {

	}

	@FXML
	void goToEvents(MouseEvent event) {

	}

	@FXML
	void goToProfils(MouseEvent event) {

	}

	@FXML
	void logout(MouseEvent event) {

	}

	@FXML
	void onNext(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();

		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

		it = it + 3;
		List<SpecialEvent> specialEvents = eventManagementServicesRemote
				.findAllOwnerEvents(LoginController.getLoggedUser());
		for (int i = it; i < it + 3; i++) {
			if (i <= specialEvents.size()) {
				prev.setDisable(false);
				anchor2.setVisible(true);
				anchor3.setVisible(true);
				next.setDisable(false);
				if ((i - it) == 0) {
					populate1(specialEvents.get(i));
				}
				if ((i - it) == 1) {
					populate2(specialEvents.get(i));
				}
				if ((i - it) == 2) {
					populate3(specialEvents.get(i));
				}
			} else {
				if ((i - it) == 1) {
					anchor2.setVisible(false);
				}
				if ((i - it) == 2) {
					anchor3.setVisible(false);
				}
				prev.setDisable(false);
				next.setDisable(true);
			}
		}

	}

	@FXML
	void onPrev(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();

		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

		it = it - 3;
		List<SpecialEvent> specialEvents = eventManagementServicesRemote
				.findAllOwnerEvents(LoginController.getLoggedUser());
		for (int i = it; i < it + 3; i++) {
			if (i >= 0) {
				prev.setDisable(false);
				next.setDisable(false);
				anchor2.setVisible(true);
				anchor3.setVisible(true);
				if ((i - it) == 0) {
					populate1(specialEvents.get(i));
				}
				if ((i - it) == 1) {
					populate2(specialEvents.get(i));
				}
				if ((i - it) == 2) {
					populate3(specialEvents.get(i));
				}
			} else {
				prev.setDisable(true);
				next.setDisable(false);
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Context context;
		userName.setText(LoginController.getLoggedUser().getName());
		JFXRippler mratedRippler = new JFXRippler(mreatedAnchor);
		fortheripplermr.getChildren().add(mratedRippler);
		JFXRippler mviewRippler = new JFXRippler(mviewAnchor);
		fortheripplermv.getChildren().add(mviewRippler);
		JFXRippler msubsRippler = new JFXRippler(msubsAnchor);
		fortheripplerms.getChildren().add(msubsRippler);
		try {
			context = new InitialContext();

			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
			SpecialEvent mostRated = eventManagementServicesRemote.findMostRatedEvent(LoginController.getLoggedUser());
			mrated.setText(mostRated.getName());
			mreatedAnchor.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				AllEventsController.setEventTovisit(mostRated);
				Stage stage = (Stage) mrated.getScene().getWindow();
				Parent root;
				try {
					EventPageController.setPrevstage(stage);
					root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			SpecialEvent mostSubs = eventManagementServicesRemote
					.findMostSubscribedEvent(LoginController.getLoggedUser());
			if(mostSubs!= null) {
			msubs.setText(mostSubs.getName());
			msubsAnchor.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				AllEventsController.setEventTovisit(mostSubs);
				Stage stage = (Stage) msubs.getScene().getWindow();
				Parent root;
				try {
					EventPageController.setPrevstage(stage);
					root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});}
			SpecialEvent mostView = eventManagementServicesRemote.findMostVisitedEvent(LoginController.getLoggedUser());
			if(mostView!=null) {
			mviewed.setText(mostView.getName());
			mviewAnchor.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				AllEventsController.setEventTovisit(mostView);
				Stage stage = (Stage) msubs.getScene().getWindow();
				Parent root;
				try {
					EventPageController.setPrevstage(stage);
					root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});}
           
			List<SpecialEvent> specialEvents = eventManagementServicesRemote
					.findAllOwnerEvents(LoginController.getLoggedUser());
			
			if(!specialEvents.isEmpty()) {
			for (int i = it; i < it + 3; i++) {
				if(i <specialEvents.size()) {
				if ((i - it) == 0) {
					populate1(specialEvents.get(i));
				}
				if ((i - it) == 1) {
					populate2(specialEvents.get(i));
				}
				if ((i - it) == 2) {
					populate3(specialEvents.get(i));
				}
			}else {
				if ((i - it) == 1) {
					anchor2.setVisible(false);
				}
				if ((i - it) == 2) {
					anchor3.setVisible(false);
				}}}
			prev.setDisable(true);}else {
				
				
				
				
				
				eventshow.setVisible(false);
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void populate1(SpecialEvent e) throws IOException {
		File initialFile = e.getEventBanner();
		InputStream is = new FileInputStream(initialFile);
		BufferedImage imBuff = ImageIO.read(is);
		WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
		imgv1.setImage(image);
		sector1.setText(e.getEventSector());
		address1.setText(e.getAddress());
		name1.setText(e.getName());
		sdate1.setText(e.getStartDate() + " at " + e.getStartTime());
		edate1.setText(e.getEndDate() + " at " + e.getEndTime());
		phone1.setText(e.getRepPhone() + "");
		rate1.setText(e.getRating() + "");
		type1.setText(e.getCategory());
		visits1.setText(e.getVisits() + "");
		link1.setText(e.getExternalLink());

	}

	public void populate2(SpecialEvent e) throws IOException {
		File initialFile = e.getEventBanner();
		InputStream is = new FileInputStream(initialFile);
		BufferedImage imBuff = ImageIO.read(is);
		WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
		imgv2.setImage(image);
		sector2.setText(e.getEventSector());
		address2.setText(e.getAddress());
		name2.setText(e.getName());
		sdate2.setText(e.getStartDate() + " at " + e.getStartTime());
		edate2.setText(e.getEndDate() + " at " + e.getEndTime());
		phone2.setText(e.getRepPhone() + "");
		rate2.setText(e.getRating() + "");
		type2.setText(e.getCategory());
		visits2.setText(e.getVisits() + "");
		link2.setText(e.getExternalLink());

	}

	public void populate3(SpecialEvent e) throws IOException {
		File initialFile = e.getEventBanner();
		InputStream is = new FileInputStream(initialFile);
		BufferedImage imBuff = ImageIO.read(is);
		WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
		imgv3.setImage(image);
		sector3.setText(e.getEventSector());
		address3.setText(e.getAddress());
		name3.setText(e.getName());
		sdate3.setText(e.getStartDate() + " at " + e.getStartTime());
		edate3.setText(e.getEndDate() + " at " + e.getEndTime());
		phone3.setText(e.getRepPhone() + "");
		rate3.setText(e.getRating() + "");
		type3.setText(e.getCategory());
		visits3.setText(e.getVisits() + "");
		link3.setText(e.getExternalLink());

	}
}
