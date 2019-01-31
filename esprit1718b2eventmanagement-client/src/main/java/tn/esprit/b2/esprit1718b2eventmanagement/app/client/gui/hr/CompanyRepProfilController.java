package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.b2.esprit1718b2eventmanagement.app.client.businessFirmGui.MyAlert;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

public class CompanyRepProfilController implements Initializable {

	@FXML
	private VBox booth;

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField login;

	@FXML
	private JFXTextField country;

	@FXML
	private JFXTextField state;

	@FXML
	private JFXTextField zipCode;

	@FXML
	private JFXTextField street;

	@FXML
	private JFXTextField mail;

	@FXML
	private JFXTextField phone;

	@FXML
	private JFXTextField title;

	@FXML
	private JFXTextField departement;

	@FXML
	private JFXTextField workPhone;

	@FXML
	private JFXTextField hourPrice;

	@FXML
	private JFXTextArea description;

	@FXML
	private ImageView photo;

	File file;

	@FXML
	void Go_auction_Owner(MouseEvent event) {

	}

	@FXML
	void back(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) hourPrice.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("CompanyOwnerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void boothManagement(MouseEvent event) {

	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void companyOwnerUpdate(ActionEvent event) throws NamingException, IOException {
		CompanyRep companyRep = new CompanyRep();
		Context context = new InitialContext();

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

		companyRep = companyRepServiceRemote.find(LoginController.getLoggedUser().getId());

		companyRep.setId(LoginController.getLoggedUser().getId());

		companyRep.setName(name.getText());
		companyRep.setLogin(login.getText());

		companyRep.setEmail(mail.getText());

		companyRep.setCountry(country.getText());

		companyRep.setState(state.getText());

		companyRep.setStreet(street.getText());

		companyRep.setPhone(Integer.parseInt(phone.getText()));

		companyRep.setDescription(description.getText());

		companyRep.setDepartment(departement.getText());
		companyRep.setJobtitle(title.getText());

		companyRep.setWorkPhone(Integer.parseInt(workPhone.getText()));

		companyRepServiceRemote.update(companyRep);

		if (!phone.getText().isEmpty() && !phone.getText().equals("0")&& checkNumber(phone.getText())) {
			Parent root;
			Stage stage = (Stage) hourPrice.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("CompanyOwnerHome.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			MyAlert.ErrorBox("Please put a valid phone number");
		}
	}

	@FXML
	void goToCompanyOwnerHome(MouseEvent event) {

	}

	@FXML
	void goToEvents(MouseEvent event) {

	}

	@FXML
	void goToProfils(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) hourPrice.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("CompanyProfilUserProfile.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) hourPrice.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void selectPhoto(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		Window stage = null;
		file = fileChooser.showOpenDialog(stage);
		if (file != null)
			openFile(file);
	}

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

		photo.setImage(image);

	}

	private Boolean checkNumber(String string) {

		try {
			Float.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.setText(LoginController.getLoggedUser().getName());
		Context context;
		try {
			context = new InitialContext();
			CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
							+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
			CompanyRep companyRep = new CompanyRep();
			companyRepServiceRemote.find(LoginController.getLoggedUser().getId());
			User user = new User();
			user = LoginController.getLoggedUser();
			name.setText(user.getName());
			login.setText(user.getLogin());
			mail.setText(user.getEmail());
			country.setText(user.getCountry());
			state.setText(user.getState());
			street.setText(user.getStreet());
			phone.setText("" + user.getPhone());

			description.setText(user.getDescription());
			title.setText(user.getJobtitle());
			departement.setText(companyRep.getDepartment());
			workPhone.setText("" + companyRep.getWorkPhone());
			hourPrice.setText("" + companyRep.getHourPrice());

			File initialFile = user.getProfilePic();
			file = user.getProfilePic();
			if (file != null) {
				InputStream is = new FileInputStream(initialFile);
				BufferedImage imBuff = ImageIO.read(is);
				WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
				photo.setImage(image);
			}
		} catch (NamingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

}
