package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote;

public class DatabaseNotifWatcher extends Task<List<Participation>> {
	@FXML
	private VBox vbox;
	@FXML
	private Label label;

	private int i = 0;

	public DatabaseNotifWatcher(VBox vb, Label lb) throws NamingException {
		// initialize here
		this.vbox = vb;
		this.label = lb;

	}

	@Override
	protected List<Participation> call() throws Exception {

		updateUI();
		return null;
	}

	private void updateUI() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Context context;
				try {
					context = new InitialContext();

					EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context
							.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");

					List<Participation> participations = new ArrayList<>();
					participations = eventManagementServicesRemote.UserNotification(LoginController.getLoggedUser());
					if (participations.size() != 0) {

						System.out.println(participations.size());

					}
					// Set your new values in your UI
					// Call the method in your UI to update values.
					label.setText(participations.size() + "");
					i = participations.size();
					vbox.getChildren().clear();
					for (Participation p : participations) {
						HBox hb = new HBox();
						Label lname = new Label();
						lname.setText("You 're invited to a " + p.getEvent().getCategory() + " :"
								+ p.getEvent().getName() + " ");
						Label lmessage = new Label();
						lmessage.setText("Special message : " + p.getMessage());
						VBox vb = new VBox();
						vb.getChildren().addAll(lname, lmessage);
						JFXButton accept = new JFXButton();
						accept.setText("Accept");
						accept.setRipplerFill(Paint.valueOf("#1c4464"));
						accept.setStyle("-fx-background-color : #f4f474");

						accept.addEventHandler(ActionEvent.ACTION, e -> {
							try {

								ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context
										.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service"
												+ "/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
								p.setStatus(0);
								participationServiceRemote.update(p);

								hb.getChildren().clear();
								i = i - 1;
								label.setText(i + "");

							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
						JFXButton decline = new JFXButton();
						decline.setText("Decline");

						decline.setRipplerFill(Paint.valueOf("#1c4464"));
						decline.setStyle("-fx-background-color : #b4dcbc");
						decline.addEventHandler(ActionEvent.ACTION, e -> {
							try {

								ParticipationServiceRemote participationServiceRemote = (ParticipationServiceRemote) context
										.lookup("/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service"
												+ "/ParticipationService!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceRemote");
								participationServiceRemote.delete(p);

								hb.getChildren().clear();

							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						});
						vb.setMinWidth(235);
						hb.getChildren().addAll(vb, new Separator(Orientation.VERTICAL), accept, decline);
						vbox.getChildren().addAll(hb, new Separator());
						i++;
					}
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
	}
}