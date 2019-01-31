package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.hr;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
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

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.CompanyRep;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Customer;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote;

public class SignInController implements Initializable {

	@FXML
	private JFXTextField name;

	@FXML
	private JFXTextField login;

	@FXML
	private JFXTextField email;

	@FXML
	private JFXTextField number;

	@FXML
	private JFXTextField title;

	@FXML
	private JFXTextField country;

	@FXML
	private JFXTextField state;

	@FXML
	private JFXTextField street;

	@FXML
	private JFXTextField zipCode;

	@FXML
	private RadioButton customer;

	@FXML
	private ToggleGroup haja;

	@FXML
	private RadioButton companyRep;

	@FXML
	private JFXTextArea discription;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXPasswordField passwordTwo;

	@FXML
	private Label message;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@SuppressWarnings("deprecation")
	@FXML
	void SignIn(ActionEvent event) throws NamingException {

		Context context = new InitialContext();

		CustomerServiceRemote customerServiceRemote = (CustomerServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Customer"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.CustomerServiceRemote");

		CompanyRepServiceRemote companyRepServiceRemote = (CompanyRepServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/Company"
						+ "RepService!tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceRemote");

		UserServiceRemote userServiceRemote = (UserServiceRemote) context
				.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/User"
						+ "Service!tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceRemote");

		message.setText("");
		Boolean valider = false;
		if (customer.isSelected()) {
			Customer customer = new Customer();
			if (!name.getText().isEmpty()) {
				customer.setName(name.getText());
				if (!login.getText().isEmpty()) {
					if (userServiceRemote.checkLogin(login.getText())) {
						customer.setLogin(login.getText());
						if (!password.getText().isEmpty()) {
							if (!passwordTwo.getText().isEmpty()) {
								if (password.getText().equals(passwordTwo.getText())) {
									customer.setPassword(password.getText());
									if (!email.getText().isEmpty()&& EmailValidator.getInstance().isValid(email.getText())) {
										customer.setEmail(email.getText());
										if (!number.getText().isEmpty()) {
											if (checkNumber(number.getText())) {
												if (!discription.getText().isEmpty())
													customer.setDescription(discription.getText());
												if (!title.getText().isEmpty())
													customer.setJobtitle(title.getText());
												if (!country.getText().isEmpty())
													customer.setCountry(country.getText());
												if (!state.getText().isEmpty())
													customer.setState(state.getText());
												if (!street.getText().isEmpty())
													customer.setStreet(street.getText());
												if (!zipCode.getText().isEmpty())
													customer.setZipCode((zipCode.getText()));
												customer.setPhone(Integer.parseInt(number.getText()));
												System.out.println(customer.toString());

												// ******************7aja w mchet******************
												final String username = "pdev.esprit.2018@gmail.com";
												final String password = "pdevesprit2018";

												Properties props = new Properties();
												props.put("mail.smtp.host", "smtp.gmail.com");
												props.put("mail.smtp.socketFactory.port", "465");
												props.put("mail.smtp.socketFactory.class",
														"javax.net.ssl.SSLSocketFactory");
												props.put("mail.smtp.auth", "true");
												props.put("mail.smtp.port", "465");

												Session session = Session.getDefaultInstance(props,
														new javax.mail.Authenticator() {
															protected PasswordAuthentication getPasswordAuthentication() {
																return new PasswordAuthentication(username, password);
															}
														});

												try {
													SignInController sc = new SignInController();
													String code = sc.getSaltString();
													Message message = new MimeMessage(session);
													message.setFrom(
															new InternetAddress("mohamedbehaeddine.frigui@esprit.tn"));
													message.setRecipients(Message.RecipientType.TO, InternetAddress
															.parse("mohamedbehaeddine.frigui@gmail.com"));
													message.setSubject("Testing Subject");

													message.setText(code);
													Transport.send(message);
													customer.setCode(code);
													customerServiceRemote.update(customer);
													valider = true;
												} catch (MessagingException e) {
													System.out.println("nope :(");
												}
												// **********************************

											} else
												message.setText("Please insert your valide phone number!");
										} else
											message.setText("Please insert your phone number!");
									} else
										message.setText("Please insert your Email!");
								} else
									message.setText("Your Password Must Much !");
							} else
								message.setText("Please insert your Password again !");
						} else
							message.setText("Please insert your password !");
					} else
						message.setText("This username is taken !");
				} else
					message.setText("Please insert your username !");
			} else
				message.setText("Please insert your Full name !");
		} else {
			CompanyRep companyRep = new CompanyRep();
			if (!name.getText().isEmpty()) {
				companyRep.setName(name.getText());
				if (!login.getText().isEmpty()) {
					if (userServiceRemote.checkLogin(login.getText())) {
						companyRep.setLogin(login.getText());
						if (!password.getText().isEmpty()) {
							if (!passwordTwo.getText().isEmpty()) {
								if (password.getText().equals(passwordTwo.getText())) {
									companyRep.setPassword(password.getText());
									if (!email.getText().isEmpty()
											&& EmailValidator.getInstance().isValid(email.getText())) {

										companyRep.setEmail(email.getText());
										if (!number.getText().isEmpty()) {
											if (checkNumber(number.getText())) {
												if (!discription.getText().isEmpty())
													companyRep.setDescription(discription.getText());
												if (!title.getText().isEmpty())
													companyRep.setJobtitle(title.getText());
												if (!country.getText().isEmpty())
													companyRep.setCountry(country.getText());
												if (!state.getText().isEmpty())
													companyRep.setState(state.getText());
												if (!street.getText().isEmpty())
													companyRep.setStreet(street.getText());
												if (!zipCode.getText().isEmpty())
													companyRep.setZipCode((zipCode.getText()));

												companyRep.setPhone(Integer.parseInt(number.getText()));

												// ******************7aja w mchet******************
												final String username = "pdev.esprit.2018@gmail.com";
												final String password = "pdevesprit2018";

												Properties props = new Properties();
												props.put("mail.smtp.host", "smtp.gmail.com");
												props.put("mail.smtp.socketFactory.port", "465");
												props.put("mail.smtp.socketFactory.class",
														"javax.net.ssl.SSLSocketFactory");
												props.put("mail.smtp.auth", "true");
												props.put("mail.smtp.port", "465");

												Session session = Session.getDefaultInstance(props,
														new javax.mail.Authenticator() {
															protected PasswordAuthentication getPasswordAuthentication() {
																return new PasswordAuthentication(username, password);
															}
														});

												try {
													SignInController sc = new SignInController();
													String code = sc.getSaltString();
													Message message = new MimeMessage(session);
													message.setFrom(
															new InternetAddress("mohamedbehaeddine.frigui@esprit.tn"));
													message.setRecipients(Message.RecipientType.TO,
															InternetAddress.parse(email.getText()));
													message.setSubject("Testing Subject");

													message.setText(code);
													Transport.send(message);
													companyRep.setCode(code);

													companyRepServiceRemote.update(companyRep);
													valider = true;
												} catch (MessagingException e) {
													System.err.println(e);
												}

												// **********************************
											} else
												message.setText("Please insert your valide phone number!");
										} else
											message.setText("Please insert your phone number!");
									} else
										message.setText("Please insert a valid Email!");
								} else
									message.setText("Your Password Must Much !");
							} else
								message.setText("Please insert your Password again !");
						} else
							message.setText("Please insert your password !");
					} else
						message.setText("This username is taken !");
				} else
					message.setText("Please insert your username !");
			} else
				message.setText("Please insert your Full name !");

		}
		if (valider) {
			try {
				Stage stage = (Stage) login.getScene().getWindow();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("Login.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {

			}
		}
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
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
	    void backLogin(MouseEvent event) {
		 try {
				Stage stage = (Stage) login.getScene().getWindow();
				Parent root;
				root = FXMLLoader.load(getClass().getResource("Login.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {

			}
	    }

	    @FXML
	    void closeWindow(ActionEvent event) {
	    	System.exit(0);
	    }
}
