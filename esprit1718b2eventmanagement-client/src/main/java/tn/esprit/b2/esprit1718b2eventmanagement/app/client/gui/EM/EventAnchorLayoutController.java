package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote;

public class EventAnchorLayoutController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView eventBanner;

	@FXML
	private Label companyName;

	@FXML
	private Label eventName;

	@FXML
	private Label timeLabel;

	@FXML
	private AnchorPane companyIcon;

	@FXML
	private AnchorPane eventAnchor;

	@FXML
	private JFXButton participateButton;
	private Event tevent = AllEventsController.getEvent();
	private int id;

	@FXML
	private Pane fortherippler;

	@FXML
	void initialize() throws NamingException, IOException {

		eventName.setText(tevent.getName());
		if (tevent instanceof SpecialEvent) {
			Context context = new InitialContext();
			SpecialEventServicesRemote specialEventServicesRemote = (SpecialEventServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/"
							+ "SpecialEventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote");
			EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
			Participation participation = eventManagementServicesRemote
					.UserParticipation(LoginController.getLoggedUser(), tevent);
			timeLabel.setText(tevent.getEventSector());
			if (participation.getStatus() == 1) {
				participateButton.setText("Accept Invitation");
			} else if (participation.getStatus() == 0) {
				participateButton.setText("Participated");
				participateButton.setDisable(true);
			} else if (participation.getStatus() == 2) {
				participateButton.setText("Owner");
				participateButton.setDisable(true);
			}
			SpecialEvent specialEvent = specialEventServicesRemote.find(tevent.getId());
			File initialFile = specialEvent.getEventBanner();
			InputStream is = new FileInputStream(initialFile);
			BufferedImage imBuff = ImageIO.read(is);
			WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
			eventBanner.setPreserveRatio(false);
			eventBanner.fitHeightProperty().bind(eventAnchor.heightProperty());
			eventBanner.fitWidthProperty().bind(eventAnchor.widthProperty());
			eventBanner.setImage(image);
			eventBanner.toBack();
			CompanyServiceRemote companyServiceRemote = (CompanyServiceRemote) context.lookup(
					"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CompanyService!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote");
			Company company = companyServiceRemote.find(specialEvent.getCompanyRep().getCompany().getId());
			companyName.setText(company.getName());
			File initialFilee = company.getLogo();
			if (initialFilee != null) {
				InputStream iss = new FileInputStream(initialFilee);
				BufferedImage imBufff = ImageIO.read(iss);
				WritableImage imagee = SwingFXUtils.toFXImage(imBufff, null);
				ImageView in = new ImageView(imagee);
				in.setPreserveRatio(false);
				in.setFitWidth(70);
				in.setFitHeight(63);
				companyIcon.getChildren().add(in);
			}
			companyIcon.toFront();
			companyName.toFront();
			eventName.toFront();
		}
		this.setId(tevent.getId());
		JFXRippler rippler = new JFXRippler(fortherippler);
		rippler.setRipplerFill(Paint.valueOf("#d3c41f"));
		rippler.toFront();
		eventAnchor.getChildren().add(rippler);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventAnchorLayoutController() {
		super();
	}

	public Label getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(String timeLabe) {
		// timeLabel.setText(timeLabe);
	}

	@FXML
	void participateAction(ActionEvent event) throws NamingException {
		Context context = new InitialContext();
		ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context
				.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service"
						+ "/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		SpecialEventServicesRemote specialEventServicesRemote = (SpecialEventServicesRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/"
						+ "SpecialEventServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesRemote");

		if (participateButton.getText().equals("Participate")) {
			Participation participation = new Participation(0, specialEventServicesRemote.find(tevent.getId()),
					LoginController.getLoggedUser(), 0);

			// eventManagementServicesRemote.assignGuestToASpecialEvent(participationServiceRemote.update(participation),);
			participationServiceRemote.save(participation);
			participateButton.setText("Participated");
			participateButton.setDisable(true);
			System.out.println(participation.toString());
		} else {

			Participation participation1 = eventManagementServicesRemote
					.UserParticipation(LoginController.getLoggedUser(), tevent);
			participation1.setStatus(0);
			participationServiceRemote.update(participation1);

			participateButton.setText("Participated");
			participateButton.setDisable(true);
		}
	}

	@FXML
	void showDetails(MouseEvent event) {

	}

}
