package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote;

public class EventPageController implements Initializable {

	@FXML
	private Text eventDesc;

	@FXML
	private ImageView eventBanner;

	@FXML
	private WebView eventWV;

	@FXML
	private Rating eventRating;

	@FXML
	private Text eventName;

	@FXML
	private Label ratingLab;

	@FXML
	private Label ratel;

	@FXML
	private AnchorPane NotSubscribed;

	@FXML
	private AnchorPane cdAnchor;

	@FXML
	private JFXButton participatebutt;

	@FXML
	private Label eventAdress;

	@FXML
	private ImageView companyImg;

	@FXML
	private Label CompanyName;

	@FXML
	private Label eventSector;

	@FXML
	private Label eventPhone;

	@FXML
	private Hyperlink Externallink;

	@FXML
	private ImageView adressWV;

	@FXML
	private AnchorPane durationAnchor;
	@FXML
	private AnchorPane addpane;
	@FXML
	private AnchorPane parent;
	private double xOffset = 0;
	private double yOffset = 0;
	private Event event = AllEventsController.getEventTovisit();
	static Stage prevstage;
	static Parent roott;

	public static Parent getRoott() {
		return roott;
	}

	public static void setRoott(Parent roott) {
		EventPageController.roott = roott;
	}

	public static Stage getPrevstage() {
		return prevstage;
	}

	public static void setPrevstage(Stage prevstage) {
		EventPageController.prevstage = prevstage;
	}

	@FXML
	void goBack(ActionEvent event) throws IOException, NamingException {
		Parent root;
		Context context = new InitialContext();
		Stage stage = (Stage) eventAdress.getScene().getWindow();
		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/User"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");
		int check = userServiceRemote.checkRole(LoginController.getLoggedUser());
		if (check == -1) {

			root = FXMLLoader.load(getClass().getResource("AllEventsFXMLDocument.fxml"));
		} else {
			root = FXMLLoader.load(getClass().getResource("OwnerEventPage.fxml"));
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();
		eventName.setText(event.getName());
		eventDesc.setText(event.getDescription());
		eventRating.setRating(event.getRating());
		try {
			Context context = new InitialContext();
			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
			Participation participation = eventManagementServicesRemote
					.UserParticipation(LoginController.getLoggedUser(), event);

			if (participation.getEvent() == null || participation.getStatus() == 1) {
				System.out.println("naaah");
				checkEvent(cdAnchor);
				if (participation.getStatus() == 1) {
					System.out.println("inv");
					participatebutt.setText("Accept invitation");
				}

				NotSubscribed.setVisible(true);
			} else {
				if (participation.getStatus() == 0) {
					System.out.println("sub");
					checkEvent(durationAnchor);
					ratingLab.setText("Your rating : " + participation.getRate());
					NotSubscribed.setVisible(false);
				}
				if (participation.getStatus() == 2) {
					System.out.println("owner");
					checkEvent(durationAnchor);
					ratingLab.setText(" -- ");
					NotSubscribed.setVisible(false);
				}

			}

			if (event instanceof SpecialEvent) {

				try {

					SpecialEventServicesRemote specialEventServicesRemote = (SpecialEventServicesRemote) context
							.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/"
									+ "SpecialEventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote");

					SpecialEvent specialEvent = specialEventServicesRemote.find(event.getId());

					specialEvent.setVisits(specialEvent.getVisits() + 1);
					specialEventServicesRemote.update(specialEvent);
					File initialFile = specialEvent.getEventBanner();
					InputStream is = new FileInputStream(initialFile);
					BufferedImage imBuff = ImageIO.read(is);
					WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
					eventBanner.setPreserveRatio(false);
					eventBanner.setImage(image);
					eventBanner.toBack();
					ratel.setText(specialEvent.getRating() + "/5");
					eventWV.getEngine().load(specialEvent.getExternalLink());
					if (specialEvent.getAddress() != null) {
						adressWV.setImage(new Image(("http://maps.googleapis.com/maps/api/staticmap?center="
								+ specialEvent.getAddress()
								+ "&size=640x400&style=element:labels|visibility:on&style=element:geometry.stroke|visibility:off&style=feature:landscape|element:geometry|saturation:0&style=feature:water|saturation:-100|invert_lightness:true&key=AIzaSyC8rzeQPxgxM-3MVB6qHXZmEZ-XqU38sVI")));
						// adressWV.getEngine().load();

						eventAdress.setText(specialEvent.getAddress());

						addpane.setVisible(false);

						Externallink.setText("Visit us :" + specialEvent.getExternalLink());
						Externallink.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								try {
									Desktop.getDesktop().browse(new URI(specialEvent.getExternalLink()));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (URISyntaxException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						});
					} else {
						addpane.setVisible(false);
						adressWV.setVisible(false);
					}

					eventPhone.setText(specialEvent.getRepPhone() + "");
					eventSector.setText(specialEvent.getEventSector());
					CompanyServiceRemote companyServiceRemote = (CompanyServiceRemote) context.lookup(
							"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CompanyService!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote");
					CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context.lookup(
							"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CompanyRepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
					Company company = companyServiceRemote.find(specialEvent.getCompanyRep().getCompany().getId());

					CompanyName.setText(company.getName());
					File initialFilee = company.getLogo();
					if (initialFilee != null) {
						InputStream iss = new FileInputStream(initialFilee);
						BufferedImage imBufff = ImageIO.read(iss);
						WritableImage imagee = SwingFXUtils.toFXImage(imBufff, null);
						companyImg.setImage(imagee);
					}
				} catch (NamingException | FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	void rateAction(ActionEvent evt) throws NamingException {
		Context context = new InitialContext();
		EventManagementServicesRemote eventManagementServicesRemote2;
		eventManagementServicesRemote2 = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
		float r = (float) eventRating.getRating();

		Participation p = eventManagementServicesRemote2.UserParticipation(LoginController.getLoggedUser(), event);
		p.setRate(r);
		participationServiceRemote.update(p);
		Event e = eventManagementServicesRemote2.UpdateRating(event);
		ratel.setText(e.getRating() + "/5");
	}

	public void checkEvent(AnchorPane Anchor) {
		VBox vb = new VBox();
		long durationStart = ((2 * 3600000 + event.getStartDate().getTime() + event.getStartTime().getTime()
				- System.currentTimeMillis()));
		long durationEnd = ((2 * 3600000 + event.getEndDate().getTime() + event.getEndTime().getTime()
				- System.currentTimeMillis()));
		if (durationStart <= 0) {
			if (durationEnd <= 0) {
				Label l = new Label();
				l.setText("Event Ended");
				participatebutt.setVisible(false);
				l.setStyle("-fx-text-fill:#b4dcbc; -fx-font-size: 25;");
				l.alignmentProperty().set(Pos.CENTER_RIGHT);
				Anchor.getChildren().add(l);
			} else {
				Anchor.getChildren().clear();
				Label l = new Label();
				l.setText("Still until the event's end");
				Countdown countdown = new Countdown();
				l.setStyle("-fx-text-fill:#b4dcbc; -fx-font-size: 25;");
				l.alignmentProperty().set(Pos.CENTER_RIGHT);
				vb.getChildren().addAll(l, countdown.initCD(durationEnd));

				countdown.startCD();
				Anchor.getChildren().add(vb);
			}

		} else {

			Anchor.getChildren().clear();
			Label l = new Label();
			l.setText("Still until the event's start");
			l.setStyle("-fx-text-fill:#b4dcbc; -fx-font-size: 24;");
			l.alignmentProperty().set(Pos.CENTER_RIGHT);

			Countdown countdown = new Countdown();
			vb.getChildren().addAll(l, countdown.initCD(durationStart));

			countdown.startCD();
			Anchor.getChildren().add(vb);
		}

	}

	@FXML
	void participate(ActionEvent evt) throws NamingException, IOException {
		Context context = new InitialContext();
		ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context
				.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service"
						+ "/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		SpecialEventServicesRemote specialEventServicesRemote = (SpecialEventServicesRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/"
						+ "SpecialEventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote");

		NotSubscribed.setVisible(false);
		if (participatebutt.getText().equals("Participate")) {
			Participation participation = new Participation(0, specialEventServicesRemote.find(event.getId()),
					LoginController.getLoggedUser(), 0);

			// eventManagementServicesRemote.assignGuestToASpecialEvent(participationServiceRemote.update(participation),);
			participationServiceRemote.save(participation);

			System.out.println(participation.toString());
		} else {

			Participation participation1 = eventManagementServicesRemote
					.UserParticipation(LoginController.getLoggedUser(), event);
			participation1.setStatus(0);
			participationServiceRemote.update(participation1);
			checkEvent(durationAnchor);
			NotSubscribed.setVisible(false);
		}
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

	@FXML
	void close_app(ActionEvent event) {
		System.exit(0);
	}
}
