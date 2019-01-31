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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Company;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

public class CompanyProfilController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userName.setText(LoginController.getLoggedUser().getName());

		try {
			Context context;
			context = new InitialContext();
			CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
					.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
							+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
  
			CompanyRep companyRep = companyRepServiceRemote.find(LoginController.getLoggedUser().getId());
			int x = companyRepServiceRemote.countNumberOfEmployee(companyRep);
			System.out.println(x);
			employeesNumber.setText("" + x);
			if (companyRep.getCompany().getId() != 0) {
				Company company = companyRep.getCompany();
				name.setText(company.getName());
				about.setText(company.getAbout());
				phone.setText("" + company.getPhoneNumber());
				fbLink.setText(company.getFacebookLink());
				linkedIn.setText(company.getLinkedinLink());
				File initialFile = company.getLogo();
				file = company.getLogo();
				if(file!=null) {
						InputStream is = new FileInputStream(initialFile);
				BufferedImage imBuff = ImageIO.read(is);
				WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
				imageViewer.setImage(image);
				}
			 
				
			}
		} catch (NamingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	private JFXTextArea about;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField employeesNumber;

	@FXML
	private JFXTextField phone;

	@FXML
	private JFXTextField fbLink;

	@FXML
	private JFXTextField linkedIn;

	@FXML
	private AnchorPane anchorer;

	@FXML
	private ImageView imageViewer;

	@FXML
	private JFXTextField webSite;

	@FXML
	private JFXTextField year;

	File file;

	@FXML
	void back(MouseEvent event) throws IOException {
		Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyProfilUserProfile.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void companyUpdate(ActionEvent event) throws NamingException {
		Context context = new InitialContext();

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
		CompanyServiceRemote companyServiceRemote = (CompanyServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote");
		CompanyRep companyRep = companyRepServiceRemote.find(LoginController.getLoggedUser().getId());

		int id = companyRep.getCompany().getId();
		if (checkNumber(year.getText())) {
			Company company = new Company();
			company.setId(id);
			company.setYearOfEstablishment(Integer.parseInt(year.getText()));
			company.setWebsiteLink(webSite.getText());
			company.setFacebookLink(fbLink.getText());
			company.setLogo(file);
			company.setName(name.getText());
			company.setAbout(about.getText());
			company.setNumberOfEmployes(Integer.parseInt(employeesNumber.getText()));
			companyServiceRemote.update(company);
		}
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

	@FXML
	void selectImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		Window stage = null;
		file = fileChooser.showOpenDialog(stage);
		if (file != null)
			openFile(file);

	}

	private void openFile(File file) {

		Image image = new Image(file.toURI().toString(), 671, // requested width
				143, // requested height
				true, // preserve ratio
				false, true // load in background
		);

		imageViewer.setImage(image);

	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
	}

	private Boolean checkNumber(String string) {

		try {
			Float.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	@FXML
    void Go_auction_Owner(KeyEvent event) {

    }

    

    @FXML
    void boothManagement(MouseEvent event) throws IOException {
    	Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("BoothManagementCompanyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

  
    @FXML
    void goToCompanyOwnerHome(KeyEvent event) throws IOException {
    	Stage stage = (Stage) close.getScene().getWindow();
		Parent root;
		root = FXMLLoader.load(getClass().getResource("CompanyOwnerHome.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

   
}
