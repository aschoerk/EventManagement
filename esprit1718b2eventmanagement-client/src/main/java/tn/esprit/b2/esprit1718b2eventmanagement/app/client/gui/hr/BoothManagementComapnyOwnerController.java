package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;

public class BoothManagementComapnyOwnerController implements Initializable {
	@FXML
	private Label userName;

	@FXML
	private ImageView close;
	 @FXML
	    private TableView<Booth> boothtable;
	 @FXML
	    private AreaChart<?, ?> charte;

	    @FXML
	    private TableColumn<Booth, String> colname;

	    @FXML
	    private TableColumn<Booth, String> coltype;

	    @FXML
	    private TableColumn<Booth, String> colrep;

	    @FXML
	    private TableColumn<Booth, Integer> colrate;

	    @FXML
	    private TableColumn<Booth, Integer> visites;
	@FXML
	void back(MouseEvent event) {

	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void logout(MouseEvent event) throws IOException {

		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.setText(LoginController.getLoggedUser().getName());
		Context context;
		try {
			context = new InitialContext();
			BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
					"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
      List<Booth>booths=boothServiceRemote.findAll();
      resetTableData(booths);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XYChart.Series series = new XYChart.Series();
		try {
			series = getchart();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		charte.getData().add(series);

		
	}
	private XYChart.Series getchart() throws NamingException {
		
		Context context = new InitialContext();
		context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		List<Booth>booths=boothServiceRemote.findAll();

		XYChart.Series series = new XYChart.Series();

		for (Booth t : booths)

		{
			
				
					series.getData().add(new XYChart.Data(t.getName(), t.getVisits()));
				

			

		}

		return series;

	}
	@FXML
	void goAddABoothManager(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("AddABoothManager.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToAdd(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("AddAShift.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
    void gotoaddbooth(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../exhibHall/BoothAddFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
	@FXML
    void gotoprods(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../exhibHall/ProductAdd.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
	public void resetTableData(List<Booth> list) {

        ObservableList<Booth> data = FXCollections.observableArrayList(list);
        colname.setCellValueFactory(
                new PropertyValueFactory<Booth, String>("name")
        );

        coltype.setCellValueFactory(
                new PropertyValueFactory<Booth, String>("type")
        );

        colrep.setCellValueFactory(
                new PropertyValueFactory<Booth, String>("extLink")
        );

        colrate.setCellValueFactory(
                new PropertyValueFactory<Booth, Integer>("rating")
        );

        visites.setCellValueFactory(
                new PropertyValueFactory<Booth, Integer>("visits")
        );

        boothtable.setItems(data);

    }
	
	}

