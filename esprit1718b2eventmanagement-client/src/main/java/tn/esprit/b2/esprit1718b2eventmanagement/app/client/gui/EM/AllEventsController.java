/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.Projet;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices.EventServicesRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */

public class AllEventsController implements Initializable {

	@FXML
	private Pagination pagination;
	@FXML
	private AnchorPane anchor;
	@FXML
	private TabPane tabpane;
	@FXML
	private AnchorPane countdownPane;
	@FXML
	private AnchorPane detailAnchor;
	@FXML
	private Label eventdName;

	@FXML
	private Label eventdDesc;

	@FXML
	private ImageView featuredSpbanner;

	@FXML
	private AnchorPane featuredSpAnchor;

	@FXML
	private Label notifLabel;

	@FXML
	private Pane fortherippler2;
	@FXML
	private Label eventSector;

	@FXML
	private Label eventtimeCom;
	@FXML
	private Label eventEnd;

	@FXML
	private Label eventStart;

	@FXML
	private Label eventCompany;

	@FXML
	private JFXTextField byname;

	@FXML
	private JFXComboBox<String> searchByprice;

	@FXML
	private Label featureevent;
	@FXML
	private JFXTextField searchBySector;
	@FXML
	private Label featuredSpName;
	private static List<Event> events;
	private static Event event;
	private double xOffset = 0;
	private double yOffset = 0;
	// private static final int DEFAULT_STARTING_X_POSITION = 0;
	// private static final int DEFAULT_ENDING_X_POSITION = -120;
	@FXML
	private AnchorPane parent;
	@FXML
	private AnchorPane notifAnchor;

	@FXML
	private VBox notifVbox;
	private static Event eventTovisit;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		searchByprice.getItems().addAll("High", "Low");
	
		Context context;
		SpecialEvent featuredEvent;
		try {
			context = new InitialContext();

			EventServicesRemote eventServicesRemote = (EventServicesRemote) context.lookup(
					"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices.EventServicesRemote");

			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

			TextFields.bindAutoCompletion(byname, eventManagementServicesRemote.findAllNames());
			List<Event> ee = eventManagementServicesRemote.findPublicEvents();
			AllEventsController.setEvents(ee);
			featuredEvent = eventManagementServicesRemote.featuredSpecialEvent();
			if (featuredEvent != null) {

				featuredSpName.setText(featuredEvent.getName());
				featuredSpbanner.setPreserveRatio(false);
				featuredSpbanner.fitHeightProperty().bind(featuredSpAnchor.heightProperty());
				featuredSpbanner.fitWidthProperty().bind(featuredSpAnchor.widthProperty());
				featuredSpbanner.setImage(convertFiletoImage(featuredEvent.getEventBanner()));
				System.out.println(featuredEvent.getName());
			}
			List<Participation> participations = eventManagementServicesRemote
					.UserNotification(LoginController.getLoggedUser());
			for (Participation p : participations) {
				Notifications notifications = Notifications.create().title("Pending invitation")
						.text("You have been invited to " + p.getEvent().getName()
								+ "\n Please open your notification pannel to respond")
						.hideAfter(Duration.seconds(15)).position(Pos.TOP_RIGHT)
						.graphic((AnchorPane) FXMLLoader.load(getClass().getResource("Notificationfxml.fxml")));

				notifications.show();
			}
			;

			notifLabel.setText(participations.size() + "");
		} catch (NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeStageDrageable();

		pagination.setPageCount(pageNumbers());
		pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {

				return createPage(pageIndex);
			}

		});

	}

	public int pageNumbers() {

		if (((events.size()) % 6) == 0) {
			return ((events.size()) / 6);
		}
		int i = (events.size()) - ((events.size()) % 6);

		return ((i / 6) + 1);

	}

	private int j = 0;

	public int itemsPerPage(int i) {
		if (events.size() - i == 0) {
			j = 0;
			return 6;
		}

		return 6;
	}

	@FXML
	private Text text;

	public VBox createPage(int pageIndex) {
		VBox box = new VBox(pageNumbers());
		int page = 0;
		int x;
		if (pageIndex + 1 == pageNumbers()) {
			x = (events.size() - j);
		} else {
			x = itemsPerPage(j);

		}

		page = pageIndex * x;
		for (int i = page; i < page + x; i++) {

			VBox element = new VBox();
			element.setStyle("-fx-background-color: white;");
			AnchorPane content;
			AllEventsController.setEvent(AllEventsController.getEvents().get(j));
			System.out.println(AllEventsController.getEvents().get(j).getName());
			EventAnchorLayoutController anchorLayoutController = new EventAnchorLayoutController();
			anchorLayoutController.setId(j);
			try {

				content = (AnchorPane) FXMLLoader.load(getClass().getResource("EventAnchorLayout.fxml"));
				content.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

					countdownPane.getChildren().clear();
					System.out.println(events.get(anchorLayoutController.getId()).getStartDate());
					eventdName.setText(events.get(anchorLayoutController.getId()).getName());

					Countdown countdown = new Countdown();
					Date d = events.get(anchorLayoutController.getId()).getStartDate();

					System.out.println(System.currentTimeMillis());
					long durationn = ((2 * 3600000 + d.getTime()
							+ events.get(anchorLayoutController.getId()).getStartTime().getTime()
							- System.currentTimeMillis()));
					if (durationn > 0) {
						eventtimeCom.setText("Starts in");
						countdownPane.getChildren().add(countdown.initCD(durationn));
						countdown.startCD();
					} else {

						long dur = ((2 * 3600000 + events.get(anchorLayoutController.getId()).getEndDate().getTime()
								+ events.get(anchorLayoutController.getId()).getEndTime().getTime()
								- System.currentTimeMillis()));
						if (dur > 0) {
							eventtimeCom.setText("Ends in");
							countdownPane.getChildren().add(countdown.initCD(dur));
							countdown.startCD();
						} else {
							eventtimeCom.setText("Ended");
						}
					}

					eventSector.setText(events.get(anchorLayoutController.getId()).getEventSector());
					eventStart.setText(events.get(anchorLayoutController.getId()).getStartDate() + " at "
							+ events.get(anchorLayoutController.getId()).getStartTime());
					eventEnd.setText(events.get(anchorLayoutController.getId()).getEndDate() + " at "
							+ events.get(anchorLayoutController.getId()).getEndTime());
					text.setText(events.get(anchorLayoutController.getId()).getDescription());
					detailAnchor.setVisible(true);
					eventTovisit = events.get(anchorLayoutController.getId());

				});
				j++;
				element.getChildren().setAll(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			box.getChildren().addAll(element);
		}
		return box;
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
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	public static List<Event> getEvents() {
		return events;
	}

	public static void setEvents(List<Event> events) {
		AllEventsController.events = events;
	}

	public static Event getEvent() {
		return event;
	}

	public static void setEvent(Event event) {
		AllEventsController.event = event;
	}

	public WritableImage convertFiletoImage(File f) throws IOException {
		File initialFile = f;
		InputStream is = new FileInputStream(initialFile);
		BufferedImage imBuff = ImageIO.read(is);
		WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
		return image;
	}

	private SpecialEvent featuredEvent;

	@FXML
	void newsChange(MouseEvent event) {
		Context context;

		try {
			context = new InitialContext();

			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

			featuredEvent = eventManagementServicesRemote.featuredSpecialEvent();
			if (featuredEvent != null) {

				System.out.println(featuredEvent.getName());
				featuredSpName.setText(featuredEvent.getName());
				CompanyServiceRemote companyServiceRemote = (CompanyServiceRemote) context.lookup(
						"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CompanyService!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote");

				Company company = companyServiceRemote.find(featuredEvent.getCompanyRep().getCompany().getId());

				featureevent.setText(company.getName());
				featuredSpbanner.setPreserveRatio(false);
				featuredSpbanner.fitHeightProperty().bind(featuredSpAnchor.heightProperty());
				featuredSpbanner.fitWidthProperty().bind(featuredSpAnchor.widthProperty());
				featuredSpbanner.setImage(convertFiletoImage(featuredEvent.getEventBanner()));
				System.out.println(featuredEvent.getName());
			}
		} catch (NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void closeNotif(MouseEvent event) {

		notifAnchor.setVisible(false);
	}

	int i = 1;

	@FXML
	void onNotif(MouseEvent event) throws NamingException {
		DatabaseNotifWatcher databaseNotifWatcher;
		try {
			databaseNotifWatcher = new DatabaseNotifWatcher(notifVbox, notifLabel);

			Timeline fiveSecondsWonder = new Timeline(
					new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							ExecutorService executor = Executors.newSingleThreadExecutor();
							try {

								if (databaseNotifWatcher.call() == null)
									executor.execute(databaseNotifWatcher);
							} catch (NamingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}));

			fiveSecondsWonder.play();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		notifAnchor.setVisible(true);

	}

	private Stage stage;

	@FXML
	void visitEventAction(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) tabpane.getScene().getWindow();
		EventPageController.setPrevstage(stage);
		root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static Event getEventTovisit() {
		return eventTovisit;
	}

	public static void setEventTovisit(Event eventTovisit) {
		AllEventsController.eventTovisit = eventTovisit;
	}

	@FXML
	void searchByName(ActionEvent event) throws NamingException, IOException {
		if (byname.getText().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("No Name given ");
			a.setContentText("Please enter the event name you are looking for!");
			a.showAndWait();
		} else {
			Context context = new InitialContext();
			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
			setEventTovisit(eventManagementServicesRemote.findByName(byname.getText()));
			Parent root;
			stage = (Stage) byname.getScene().getWindow();
			EventPageController.setPrevstage(stage);
			root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	void ArrageByprice(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		List<Event> ee;
		if (searchByprice.getValue().equals("High")) {
			ee = eventManagementServicesRemote.findByPrice(1);
		} else {
			ee = eventManagementServicesRemote.findByPrice(0);
		}

		AllEventsController.setEvents(ee);

		pagination.setPageCount(pageNumbers());
		pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {

				return createPage(pageIndex);
			}

		});
	}

	@FXML
	void SearchBySector(ActionEvent event) {

	}

	@FXML
	void gotoEventhist(ActionEvent event) throws IOException {
		Parent root;
		stage = (Stage) byname.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MyEventsPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void gotofeatured(ActionEvent event) throws IOException {
		AllEventsController.setEventTovisit(featuredEvent);
		Parent root;
		stage = (Stage) byname.getScene().getWindow();
		EventPageController.setPrevstage(stage);
		root = FXMLLoader.load(getClass().getResource("EventPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
