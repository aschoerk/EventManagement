package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.validator.EmailValidator;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;

public class AddABoothManager implements Initializable {
	@FXML
	private AnchorPane parent;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField login;

	@FXML
	private JFXTextField password;

	@FXML
	private JFXTextField email;

	@FXML
	private Label message;

	@FXML
	private JFXTextField moneyAmount;

	@FXML
	private Label userName;

	@FXML
	private ImageView close;

	@FXML
	void Go_auction_Owner(MouseEvent event) {

	}

	@FXML
	void back(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) message.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("BoothManagementComapnyOwner.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void closeWindow(MouseEvent event) {
System.exit(0);
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) message.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void close_app(MouseEvent event) {
System.exit(0);
	}

	@FXML
	void submit(ActionEvent event) throws NamingException, IOException {
		CompanyRep companyRep = new CompanyRep();
		if (!name.getText().isEmpty()) {
			companyRep.setName(name.getText());
			if (!email.getText().isEmpty() && EmailValidator.getInstance().isValid(email.getText())) {
				companyRep.setEmail(email.getText());
				if (!moneyAmount.getText().isEmpty()) {
					companyRep.setHourPrice(Float.parseFloat(moneyAmount.getText()));
					if (!login.getText().isEmpty()) {
						companyRep.setLogin(login.getText());
						if (!password.getText().isEmpty()) {
							Context context = new InitialContext();
							CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context.lookup(
									"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
											+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");
							companyRep.setPassword(password.getText());
							companyRep.setAccessType(1);
							CompanyRep companyRep2 = companyRepServiceRemote
									.find(LoginController.getLoggedUser().getId());
							CompanyServiceRemote companyServiceRemote = (CompanyServiceRemote) context.lookup(
									"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
											+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.service.hr.CompanyServiceRemote");

							companyRep.setCompany(companyServiceRemote.find(companyRep2.getCompany().getId()));

							final String username = "pdev.esprit.2018@gmail.com";
							final String password = "pdevesprit2018";

							Properties props = new Properties();
							props.put("mail.smtp.host", "smtp.gmail.com");
							props.put("mail.smtp.socketFactory.port", "465");
							props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
							props.put("mail.smtp.auth", "true");
							props.put("mail.smtp.port", "465");

							Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {
									return new PasswordAuthentication(username, password);
								}
							});

							try {

								Message message = new MimeMessage(session);
								message.setFrom(new InternetAddress("mohamedbehaeddine.frigui@esprit.tn"));
								message.setRecipients(Message.RecipientType.TO,
										InternetAddress.parse(companyRep.getEmail()));
								message.setSubject("Testing Subject");

								message.setText("your Login is :" + companyRep.getLogin() + "\nyour password is:"
										+ companyRep.getPassword() + "\nPlease Change this as soon as possible!!");
								Transport.send(message);
								Calendar calendar = Calendar.getInstance();
								companyRep.setWorkingMonth(calendar.get(Calendar.MONTH));
								companyRep.setPhone(1);
								companyRepServiceRemote.update(companyRep);
								System.out.println("yap");
								Parent root;
								Stage stage = (Stage) userName.getScene().getWindow();
								root = FXMLLoader.load(getClass().getResource("Login.fxml"));
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.show();
							} catch (MessagingException e) {
								System.out.println("nope");
							}

						} else
							message.setText("please fill the password input");
					} else
						message.setText("please fill the login input!");
				} else
					message.setText("please fill the money amount!!");
			} else
				message.setText("please fill the email input!!");
		} else
			message.setText("please fill the name input!!");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DoubleValidator numberValidator = new DoubleValidator();
		RequiredFieldValidator fieldValidator = new RequiredFieldValidator();

		numberValidator.setMessage("Phone Number should be composed of numbers only ");
		fieldValidator.setMessage("Required field !");

		moneyAmount.getValidators().addAll(numberValidator, fieldValidator);
		name.getValidators().add(fieldValidator);
		email.getValidators().add(fieldValidator);
		password.getValidators().add(fieldValidator);
		login.getValidators().add(fieldValidator);

	}

}
