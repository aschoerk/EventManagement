package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr.LoginController;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;

public class MyEventsPageController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXTreeTableView<TableFiller> eventTable;

	@FXML
	private Label eventNumber;

	@FXML
	private TreeTableColumn<TableFiller, String> eventstatus;

	@FXML
	void initialize() throws NamingException {
		JFXTreeTableColumn<TableFiller, String> eventName = new JFXTreeTableColumn<>("Event Name");

		JFXTreeTableColumn<TableFiller, String> eventType = new JFXTreeTableColumn<>("Type");

		JFXTreeTableColumn<TableFiller, String> eventStart = new JFXTreeTableColumn<>("Start Date");

		JFXTreeTableColumn<TableFiller, String> eventCompany = new JFXTreeTableColumn<>("Company");

		JFXTreeTableColumn<TableFiller, String> eventRate = new JFXTreeTableColumn<>("Your Rate");

		JFXTreeTableColumn<TableFiller, String> eventstatus = new JFXTreeTableColumn<>("");
		eventName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventName;
					}
				});

		eventCompany.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventCompany;
					}
				});
		eventRate.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventRate;
					}
				});
		eventStart.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventStart;
					}
				});
		eventstatus.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventStatus;
					}
				});
		eventType.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<TableFiller, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableFiller, String> param) {
						return param.getValue().getValue().EventType;
					}
				});
		Context context = new InitialContext();
		EventManagementServicesRemote eventManagementServicesRemote = (EventManagementServicesRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		List<SpecialEvent> specialEvents = eventManagementServicesRemote
				.findParticipationByUser(LoginController.getLoggedUser());
		ObservableList<TableFiller> tableFillers = FXCollections.observableArrayList();

		for (SpecialEvent s : specialEvents) {

			Participation p = new Participation();
			p = eventManagementServicesRemote.UserParticipation(LoginController.getLoggedUser(), s);
			String status;
			if (p.getStatus() == 1) {
				status = "Invited";

			} else {
				status = "Subscribed";
			}
			System.out.println(s.getName() + "fghjklmù");
			TableFiller filler = new TableFiller(s.getName(), s.getCategory(), s.getStartDate() + "", "hjhfj",
					p.getRate() + "", status);
			filler.toString();
			tableFillers.add(filler);

		}
eventNumber.setText(""+specialEvents.size()+"");
		final TreeItem<TableFiller> root = new RecursiveTreeItem<TableFiller>(tableFillers,
				RecursiveTreeObject::getChildren);
		eventTable.getColumns().setAll(eventName, eventCompany, eventRate, eventStart, eventstatus, eventType);

		eventTable.setRoot(root);
		eventTable.setShowRoot(false);

	}
    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent root;
		Stage stage = (Stage) eventNumber.getScene().getWindow();
		
		root = FXMLLoader.load(getClass().getResource("AllEventsFXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void close(ActionEvent event) {
 System.exit(0);
    }

	class TableFiller extends RecursiveTreeObject<TableFiller> {
		StringProperty EventName;
		StringProperty EventType;
		StringProperty EventStart;
		StringProperty EventCompany;
		StringProperty EventRate;
		StringProperty EventStatus;

		public TableFiller(String eventName, String eventType, String eventStart, String eventCompany, String eventRate,
				String eventStatus) {

			this.EventName = new SimpleStringProperty(eventName);
			this.EventType = new SimpleStringProperty(eventType);
			this.EventStart = new SimpleStringProperty(eventStart);
			this.EventCompany = new SimpleStringProperty(eventCompany);
			this.EventRate = new SimpleStringProperty(eventRate);
			this.EventStatus = new SimpleStringProperty(eventStatus);
		}

		@Override
		public String toString() {
			return "TableFiller [EventName=" + EventName + ", EventType=" + EventType + ", EventStart=" + EventStart
					+ ", EventCompany=" + EventCompany + ", EventRate=" + EventRate + ", EventStatus=" + EventStatus
					+ "]";
		}

	}
}
