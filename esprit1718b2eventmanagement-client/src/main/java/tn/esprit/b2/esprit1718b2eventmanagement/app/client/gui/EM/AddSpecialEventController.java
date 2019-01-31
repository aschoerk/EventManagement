/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.maps.errors.ApiException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AddSpecialEventController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane parent;

	@FXML
	private JFXComboBox<String> eventType;

	@FXML
	private JFXTextField eventName;

	@FXML
	private JFXTextField eventBanner;

	@FXML
	private JFXTextField eventSector;

	@FXML
	private JFXDatePicker eventDate;

	@FXML
	private JFXTextField eventPhone;

	@FXML
	private JFXDatePicker eventEnd;

	@FXML
	private JFXTextArea eventDescription;

	@FXML
	private TextField eventLink;

	@FXML
	private ImageView closeButton;

	@FXML
	private ImageView banner;

	@FXML
	private AnchorPane anchorBanner;

	@FXML
	private AnchorPane anchorLocation;

	@FXML
	private JFXTextField adress;

	@FXML
	private Pagination SearchResults;

	@FXML
	private JFXTextField finalAddress;

	@FXML
	private Pane loadingPane;
	@FXML
	private JFXTextField pricefield;

	@FXML
	private JFXRadioButton freeradio;

	@FXML
	private ToggleGroup pricing;

	@FXML
	private JFXRadioButton priceradio;

	@FXML
	private JFXComboBox<String> privacyCombo;

	@FXML
	private ScrollPane userPane;

	@FXML
	private VBox userVBox;

	@FXML
	private JFXCheckBox customercb;

	@FXML
	private JFXCheckBox coomrepcb;

	@FXML
	private JFXTimePicker eventStime;

	@FXML
	private JFXTimePicker eventEtime;

	@FXML
	private JFXTextArea guestmessage;

	@FXML
	private JFXButton guestConfirm;

	List<User> userlist = new ArrayList<>();

	List<User> guestList = new ArrayList<>();

	List<Participation> invitationList = new ArrayList<>();

	File file;

	SpecialEvent specialEvent = new SpecialEvent();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		eventType.getItems().addAll("Live event", "Virtual Seminar");
		privacyCombo.getItems().addAll("Private", "Public");
		NumberValidator numberValidator = new NumberValidator();
		RequiredFieldValidator fieldValidator = new RequiredFieldValidator();

		numberValidator.setMessage("Phone Number should be composed of numbers only ");
		fieldValidator.setMessage("Required field !");

		eventPhone.getValidators().addAll(numberValidator, fieldValidator);
		eventName.getValidators().add(fieldValidator);
		eventName.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (!newValue) {
					eventName.validate();
					eventName.setStyle("-fx-font-size: 10.0;");
				} else {
					eventName.setStyle("-fx-font-size: 12.0;");
				}

			}
		});
		eventPhone.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (!newValue) {
					eventPhone.validate();
					eventPhone.setStyle("-fx-font-size: 10.0;");
				}

			}
		});

	}

	@FXML
	void closeApp(MouseEvent event) {
		ColorAdjust adjust = new ColorAdjust();
		adjust.setBrightness(-1);

		closeButton.setEffect(adjust);
		System.exit(0);
	}

	@FXML
	void opacityChangeEnter(MouseEvent event) {
		closeButton.setOpacity(0.8);
	}

	@FXML
	void opacityChangeExit(MouseEvent event) {
		closeButton.setOpacity(1);
	}

	@FXML
	void chooseBanner(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		Window stage = null;
		file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			openFile(file);
			eventBanner.setText(file.toURI().toString());
			anchorBanner.setVisible(true);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// Run on UI thread
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							anchorBanner.setVisible(false);
						}
					});
				}
			}, 2500);
		}

	}

	@FXML
	void visibleBanner(MouseEvent event) {
		if (eventBanner.getText() != "" && (!anchorBanner.isVisible())) {

			anchorBanner.setVisible(true);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// Run on UI thread
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							anchorBanner.setVisible(false);
						}
					});
				}
			}, 2500);

		} else if (eventBanner.getText().isEmpty()) {
			anchorBanner.setVisible(false);
		}

	}

	@FXML
	void specialType(ActionEvent event) {
		if (eventType.getValue() == "Live event") {
			anchorLocation.setVisible(true);
		} else {
			anchorLocation.setVisible(false);
		}

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

		banner.setImage(image);
		banner.setFitHeight(143);
		banner.setFitWidth(671);

	}

	@FXML
	void searchForLocation(ActionEvent event) throws ApiException, InterruptedException, IOException {
		loadingPane.setVisible(true);

		Task<List<PaginationFill>> searchaction = new Task<List<PaginationFill>>() {
			@Override
			public List<PaginationFill> call() throws ApiException, InterruptedException, IOException {
				PaginationFill paginationFill = new PaginationFill();
				List<PaginationFill> pf;

				pf = paginationFill.getSearchInformation(adress.getText());

				return pf;
			}
		};

		searchaction.setOnSucceeded(e -> {
			if (searchaction.getValue() != null) {
				PaginationFill paginationFill = new PaginationFill();
				SearchResults.setPageCount(searchaction.getValue().size());
				SearchResults.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
				SearchResults.setPageFactory(new Callback<Integer, Node>() {
					@Override
					public Node call(Integer pageIndex) {

						return paginationFill.createPage(pageIndex, searchaction.getValue(), finalAddress);

					}
				});
				SearchResults.setVisible(true);
				loadingPane.setVisible(false);
			}

		});
		searchaction.setOnFailed(e -> {
			loadingPane.setVisible(false);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Oops, something went wrong !");
			alert.setContentText("Could not find the searched place");

			Exception ex = new FileNotFoundException("Could not find the searched place");

			// Create expandable Exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);

			alert.showAndWait();

		});
		new Thread(searchaction).start();

	}

	@FXML
	void disablePrice(ActionEvent event) {
		pricefield.setDisable(true);
		pricefield.getValidators().clear();
	}

	@FXML
	void enablePrice(ActionEvent event) {
		pricefield.setDisable(false);
		DoubleValidator doubleValidator = new DoubleValidator();
		doubleValidator.setMessage("This price does not seem right");
		pricefield.getValidators().add(doubleValidator);
		pricefield.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (!newValue) {
					pricefield.validate();
					pricefield.setStyle("-fx-font-size: 10.0;");
				} else {
					pricefield.setStyle("-fx-font-size: 12.0;");
				}

			}
		});

	}

	@FXML
	void onPrivacy(ActionEvent event) throws NamingException {
		if (privacyCombo.getValue() == "Private") {
			userPane.setVisible(true);

		} else {
			userPane.setVisible(false);
		}

	}

	@FXML
	void checkComp(ActionEvent event) throws NamingException {
		loadingPane.setVisible(true);
		Task<List<User>> check = new Task<List<User>>() {

			@Override
			protected List<User> call() throws Exception {
				loadingPane.setVisible(true);
				Context context = new InitialContext();
				userVBox.getChildren().clear();
				UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(
						"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/UserService!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

				if (coomrepcb.isSelected()) {
					if (customercb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) != 1) {

								userlist.add(u);
							}
						}
						return userlist;
					}
					if (!customercb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) == 0) {

								userlist.add(u);
							}
						}
						return userlist;
					}
				} else {

					if (customercb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) == -1) {
								userlist.add(u);
							}
						}
						return userlist;
					}

					if (!customercb.isSelected()) {
						userlist.clear();
						userVBox.getChildren().clear();
					}
				}

				return userlist;

			}
		};

		check.setOnSucceeded(e -> {
			Task<Integer> onsucc = new Task<Integer>() {

				@Override
				protected Integer call() throws Exception {

					if (check.getValue() != null) {

						for (User u : check.getValue()) {
							HBox h = new HBox();
							VBox v = new VBox();
							JFXCheckBox cbUser = new JFXCheckBox();
							ImageView imUser = new ImageView();
							Label idLabel = new Label();
							idLabel.setVisible(false);

							idLabel.setText(u.getId() + "");

							cbUser.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									if (cbUser.isSelected()) {
										guestList.add(u);
									} else {
										if (guestList.contains(u)) {
											guestList.remove(u);
										}
										System.out.println(u.getName());
									}
								}
							});
							if (u.getProfilePic() != null) {
								imUser.setImage(new Image(u.getProfilePic().toURI().toString()));
							} else {
								imUser.setFitHeight(45);
								imUser.setFitWidth(40);
								imUser.setImage(new Image(
										"https://www.shareicon.net/download/2017/01/06/868320_people_512x512.png"));
							}
							Label nameLabel = new Label(u.getName());
							Label mailLabel = new Label(u.getEmail());
							Label jobLabel = new Label(u.getJobtitle());
							v.getChildren().addAll(nameLabel, jobLabel, mailLabel);
							h.getChildren().addAll(cbUser, imUser, v);
							userVBox.getChildren().addAll(idLabel, h, new Separator());

						}

					}
					return 0;
				}

				@Override
				protected void succeeded() {
					// TODO Auto-generated method stub
					super.succeeded();
					userVBox.setVisible(true);
					loadingPane.setVisible(false);

				}

			};
			new Thread(onsucc).run();

		});
		Thread t = new Thread(check);

		t.run();

	}

	@FXML
	void checkCustomer(ActionEvent event) throws NamingException {
		loadingPane.setVisible(true);
		Task<List<User>> check = new Task<List<User>>() {

			@Override
			protected List<User> call() throws Exception {
				Context context = new InitialContext();
				userVBox.getChildren().clear();
				UserServiceRemote userServiceRemote = (UserServiceRemote) context.lookup(
						"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/UserService!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

				if (customercb.isSelected()) {
					if (coomrepcb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) != 1) {

								userlist.add(u);
							}
						}
					}
					if (!coomrepcb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) == -1) {

								userlist.add(u);
							}
						}
					}
				} else {

					if (coomrepcb.isSelected()) {
						userlist.clear();
						List<User> users = userServiceRemote.findAll();
						for (User u : users) {
							if (userServiceRemote.checkRole(u) == 0) {
								userlist.add(u);
							}
						}
					}

					if (!coomrepcb.isSelected()) {
						userlist.clear();
						userVBox.getChildren().clear();
					}
				}

				return userlist;
			}
		};

		check.setOnSucceeded(e -> {
			if (check.getValue() != null) {
				for (User u : check.getValue()) {
					HBox h = new HBox();
					VBox v = new VBox();
					JFXCheckBox cbUser = new JFXCheckBox();
					ImageView imUser = new ImageView();
					Label idLabel = new Label();
					idLabel.setVisible(false);

					cbUser.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if (cbUser.isSelected()) {
								guestList.add(u);
							} else {
								if (guestList.contains(u)) {
									guestList.remove(u);
								}
								System.out.println(u.getName());
							}
						}
					});
					if (u.getProfilePic() != null) {
						imUser.setImage(new Image(u.getProfilePic().toURI().toString()));
					} else {
						imUser.setFitHeight(45);
						imUser.setFitWidth(40);
						imUser.setImage(
								new Image("https://www.shareicon.net/download/2017/01/06/868320_people_512x512.png"));
					}
					Label nameLabel = new Label(u.getName());
					Label mailLabel = new Label(u.getEmail());
					Label jobLabel = new Label(u.getJobtitle());
					v.getChildren().addAll(nameLabel, jobLabel, mailLabel);
					h.getChildren().addAll(cbUser, imUser, v);
					userVBox.getChildren().addAll(idLabel, h, new Separator());

				}

			}
			loadingPane.setVisible(false);
		});
		check.setOnFailed(e -> {
			loadingPane.setVisible(false);
		});
		new Thread(check).run();
	}

	@FXML
	void SaveAction(ActionEvent event) throws NamingException, IOException {
		Context context = new InitialContext();
		SpecialEventServicesRemote specialEventServicesRemote = (SpecialEventServicesRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/"
						+ "SpecialEventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote");

		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

		specialEvent.setCategory(eventType.getValue());
		if (eventType.getValue().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Please select an event type");
			alert.setContentText("Creating an event requires a category please choose one before proceeding");
			alert.showAndWait();
		} else if (eventType.getValue().equals("Live Event")) {
			specialEvent.setAddress(finalAddress.getText());
		} else {
			specialEvent.setAddress("none");
		}

		LocalDate now = LocalDate.now();
		if (eventDate.getValue().isBefore(now)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Check your start");
			alert.setContentText("the start date must not be before today");
			alert.showAndWait();
		} else {
			if (eventDate.getValue().isAfter(eventEnd.getValue())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Check your start/end date");
				alert.setContentText("the start date must be the same or after the end date");
				alert.showAndWait();

			} else {
				if (eventDate.getValue().isEqual(eventEnd.getValue())) {
					if (eventStime.getValue().isAfter(eventEtime.getValue())) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Check your start/end time");
						alert.setContentText("the start time must be the same or after the end time");
						alert.showAndWait();
					}
				}
				{
					specialEvent.setName(eventName.getText());
					specialEvent.setStartDate(Date.valueOf(eventDate.getValue()));
					specialEvent.setEndDate(Date.valueOf(eventEnd.getValue()));
					
						specialEvent.setDescription(eventDescription.getText());
						String[] schemes = { "http", "https" };
						UrlValidator urlValidator = new UrlValidator(schemes);
						if (!urlValidator.isValid(eventLink.getText())) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText("Link not valid");
							alert.setContentText(
									"Check your link please , you link must look like https://www.stackoverflow.com/");
							alert.showAndWait();
						} else {
							specialEvent.setRepPhone(Long.parseLong(eventPhone.getText()));
							specialEvent.setExternalLink(eventLink.getText());
							if (priceradio.isSelected()) {
								specialEvent.setPrice(Float.parseFloat(pricefield.getText()));
							} else {
								specialEvent.setPrice(0);
							}

							if (privacyCombo.getValue() == "Private") {
								specialEvent.setPrivacy(0);

							} else {
								specialEvent.setPrivacy(1);
							}
							specialEvent.setEventSector(eventSector.getText());
							specialEvent.setEventBanner(file);

							specialEvent.setStartTime(Time.valueOf(eventStime.getValue()));
							specialEvent.setEndTime(Time.valueOf(eventEtime.getValue()));

							CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context.lookup(
									"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
											+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

							specialEvent.setCompanyRep(
									companyRepServiceRemote.find(LoginController.getLoggedUser().getId()));
							SpecialEvent specialEvent2 = new SpecialEvent();
							specialEvent2 = specialEventServicesRemote.update(specialEvent);
							Participation participationOwner = new Participation(2, specialEvent2,
									LoginController.getLoggedUser(), 0);
							ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context
									.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
							participationServiceRemote.update(participationOwner);
							Stage stage = (Stage) closeButton.getScene().getWindow();
							Parent root;
							root = FXMLLoader.load(getClass().getResource("../EM/OwnerEventPage.fxml"));
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
							
							
							
							if (specialEvent.getPrivacy() == 0) {
								if (guestList.isEmpty()) {
									Alert alert2 = new Alert(AlertType.ERROR);
									alert2.setHeaderText("Please select your guest list");
									alert2.setContentText("A private event requires a guest list to be assigned to it");
									alert2.showAndWait();
								} else {
									if (userPane.isDisable()) {
										for (Participation p : invitationList) {
											eventManagementServicesRemote.assignGuestToASpecialEvent(p, specialEvent2);
										}
									} else {
										Alert all = new Alert(AlertType.ERROR);
										all.setTitle("Select your guest List ");
										all.setContentText("no guest selected ");
										all.showAndWait();

									}

								}
							}
						}
					
				}
			}
		}
	}

	@FXML
	void ConfirmGuests(ActionEvent event) {
		if (guestList.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Please select your guest list");
			alert.setContentText("A private event requires a guest list to be assigned to it");
			alert.showAndWait();
		} else {
			for (User u : guestList) {
				Participation participation = new Participation();

				participation.setMessage(guestmessage.getText());
				participation.setUser(u);
				participation.setStatus(1);
				invitationList.add(participation);
			}
			guestConfirm.setText("Guest List Confirmed");
			userPane.setDisable(true);
		}

	}
}
