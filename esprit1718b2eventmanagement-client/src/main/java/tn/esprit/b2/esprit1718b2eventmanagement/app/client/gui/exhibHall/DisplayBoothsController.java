package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.exhibHall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Club;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote;

public class DisplayBoothsController implements Initializable {
	@FXML
	private AnchorPane parent;
	@FXML
	private BorderPane lu1;

	@FXML
	private BorderPane mu1;

	@FXML
	private BorderPane ru1;

	@FXML
	private BorderPane ld1;
 static int id;
 
	public static int getId() {
	return id;
}

public static void setId(int id) {
	DisplayBoothsController.id = id;
}

	@FXML
	private BorderPane rd1;
	@FXML
	private JFXButton nextb;

	@FXML
	private JFXButton prevb;
	@FXML
	private ImageView comingsoon, middle;

	@FXML
	private Pane searchbar;

	@FXML
	private JFXTextField searchname;

	@FXML
	private JFXButton showsearchbar;
	@FXML
	private JFXRadioButton info;

	@FXML
	private ToggleGroup type;
    @FXML
    private JFXButton b1,b11,b111,b2,b21;
	@FXML
	private JFXRadioButton show;

	@FXML
	private JFXRadioButton job;
	@FXML
	private JFXComboBox clubscombo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Context context;
		try {

			clubscombo.getItems().addAll(populatecombo());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> boothnames = new ArrayList<String>();
		try {
			context = new InitialContext();
			BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
					"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");

			List<Booth> booths = boothServiceRemote.findAll();

			for (Booth b : booths) {
				boothnames.add(b.getName());
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		;

		String[] words = new String[boothnames.size()];
		words = boothnames.toArray(words);
		TextFields.bindAutoCompletion(searchname, words);

		try {
			Context context1 = new InitialContext();
			BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context1.lookup(
					"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");

			List<Booth> booths = boothServiceRemote.findAll();
			populatebooths(booths, 0);
		} catch (NamingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ObservableList<String> populatecombo() throws NamingException {
		//
		Context context = new InitialContext();
		ClubServiceRemote clubServiceRemote = (ClubServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ClubService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote");

		List<Club> c = clubServiceRemote.findAll();
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Club c1 : c) {
			items.add(c1.getName());
		}

		return items;

	}

	private static int it = 0;

	void populatebooths(List<Booth> booths, int its) throws NamingException, IOException {
	Context	context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		
		for (int i = its; i < 5 + its; i++) {
			System.out.println(i);
			if ((i > booths.size() - 1)) {
				break;

			} else {
				switch (i - its) {
				case 0: {
					Booth b = booths.get(i);

					if (b != null) {
						lu1.getChildren().clear();
						populateleftupper(b);
                        
						System.out.println("this booth id is" + b.getId());
						System.out.println(b.getType());
						if(b.getType().equals("Informational"))
						 {b1.setOnAction(new EventHandler<ActionEvent>(){
		                        public void handle(ActionEvent e) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleBooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             infoBoothController c = fxmlLoader.<infoBoothController>getController();
			                             c.getidBooth(b.getId());
			                                 secondStage.show();
			                              b.setVisits(b.getVisits()+1);
			                              boothServiceRemote.update(b);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
		                             
		                        }
		                    }
		                    );}
						
						else if(b.getType().equals("ShowCase"))
						{
							b1.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                        public void handle(MouseEvent event ) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowCasebooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             showcaseboothController c = fxmlLoader.<showcaseboothController>getController();
			                             c.getidBooth(b.getId());
			                                 secondStage.show();
			                                 b.setVisits(b.getVisits()+1);
			                                 setId(b.getId());
			                                 boothServiceRemote.update(b);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
		                             
		                        }
		                    }
		                    );
							
						}
					} else {

						System.out.println("7aja");
						break;
					}
				}
				case 1: {
					Booth b1 = booths.get(i);
					middle.setVisible(false);
					if (!b1.getName().equals("")) {
						middle.setVisible(true);
						populatemiddle(b1);
						if(b1.getType().equals("Informational"))
						{	b11.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle(ActionEvent e) {
	                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleBooth.fxml"));
	                             Parent root;
								try {
									root = (Parent) fxmlLoader.load();
									Stage secondStage =(Stage) lu1.getScene().getWindow();
		                             secondStage.setScene(new Scene(root));
		                             infoBoothController c = fxmlLoader.<infoBoothController>getController();
		                             c.getidBooth(b1.getId());
		                             b1.setVisits(b1.getVisits()+1);
		                             boothServiceRemote.update(b1);
		                                 secondStage.show();
		                                 
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NamingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                             
	                        }
	                    }
	                    );}
						else if(b1.getType().equals("ShowCase"))
						{
							b11.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                        public void handle(MouseEvent event ) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowCasebooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             showcaseboothController c = fxmlLoader.<showcaseboothController>getController();
			                             c.getidBooth(b1.getId());
			                             b1.setVisits(b1.getVisits()+1);
			                             boothServiceRemote.update(b1);
			                                 secondStage.show();
			                                 
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
		                             
		                        }
		                    }
		                    );
						}
						break;
					} else {

						System.out.println(i);
						break;
					}
				}
				case 2: {
					Booth b3 = booths.get(i);

					if (booths.contains(b3)) {
						populateupperight(b3);
						if(b3.getType().equals("Informational"))
						{ b111.setOnAction(new EventHandler<ActionEvent>(){
		                        public void handle(ActionEvent e) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleBooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             infoBoothController c = fxmlLoader.<infoBoothController>getController();
			                             c.getidBooth(b3.getId());
			                             b3.setVisits(b3.getVisits()+1);
			                             boothServiceRemote.update(b3);
			                                 secondStage.show();
			                                 
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
		                             
		                        }
		                    }
		                    );}
						else if(b3.getType().equals("ShowCase"))
						{
							b111.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                        public void handle(MouseEvent event ) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowCasebooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             showcaseboothController c = fxmlLoader.<showcaseboothController>getController();
			                             c.getidBooth(b3.getId());
			                             b3.setVisits(b3.getVisits()+1);
			                             boothServiceRemote.update(b3);
			                                 secondStage.show();
			                                 
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
		                             
		                        }
		                    }
		                    );
						}

						break;

					}
				}
				case 3: {
					Booth b4 = booths.get(i);

					if (booths.contains(b4))

					{
						populatedownleft(b4);
						if(b4.getType().equals("Informational"))
						{b2.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle(ActionEvent e) {
	                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleBooth.fxml"));
	                             Parent root;
								try {
									root = (Parent) fxmlLoader.load();
									Stage secondStage =(Stage) lu1.getScene().getWindow();
		                             secondStage.setScene(new Scene(root));
		                             infoBoothController c = fxmlLoader.<infoBoothController>getController();
		                             c.getidBooth(b4.getId());
		                             b4.setVisits(b4.getVisits()+1);
		                             boothServiceRemote.update(b4);
		                                 secondStage.show();
		                                 
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NamingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                             
	                        }
	                    }
	                    );}
						else if(b4.getType().equals("ShowCase"))
						{
							b1.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                        public void handle(MouseEvent event ) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowCasebooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             showcaseboothController c = fxmlLoader.<showcaseboothController>getController();
			                             c.getidBooth(b4.getId());
			                             b4.setVisits(b4.getVisits()+1);
			                             boothServiceRemote.update(b4);
			                                 secondStage.show();
			                                 
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
		                             
		                        }
		                    }
		                    );
						}
						break;
					}
				}

				case 4:

				{
					Booth b5 = booths.get(i);
					if (!booths.contains(b5)) {
						System.out.println("nada");

						break;
					}

					else {
						if(b5.getType().equals("ShowCase"))
						{populatedownright(b5);
						b21.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle(ActionEvent e) {
	                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleBooth.fxml"));
	                             Parent root;
								try {
									root = (Parent) fxmlLoader.load();
									Stage secondStage =(Stage) lu1.getScene().getWindow();
		                             secondStage.setScene(new Scene(root));
		                             infoBoothController c = fxmlLoader.<infoBoothController>getController();
		                             c.getidBooth(b5.getId());
		                                 secondStage.show();
		                                 b5.setVisits(b5.getVisits()+1);
		                                 boothServiceRemote.update(b5);
		                                 
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NamingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	                             
	                        }
	                    }
	                    );}
						else if(b5.getType().equals("ShowCase"))
						{
							b1.setOnMouseClicked(new EventHandler<MouseEvent>(){
		                        public void handle(MouseEvent event ) {
		                        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowCasebooth.fxml"));
		                             Parent root;
									try {
										root = (Parent) fxmlLoader.load();
										Stage secondStage =(Stage) lu1.getScene().getWindow();
			                             secondStage.setScene(new Scene(root));
			                             showcaseboothController c = fxmlLoader.<showcaseboothController>getController();
			                             c.getidBooth(b5.getId());
			                             b5.setVisits(b5.getVisits()+1);
			                             boothServiceRemote.update(b5);
			                             
			                                 secondStage.show();
			                                 
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
		                             
		                        }
		                    }
		                    );
						}
						break;
					}
				}
				default:
					System.out.print("end of table");
					break;
				}

			}
		}
	}

	@FXML
	public void next(ActionEvent event) throws NamingException, IOException {

		lu1.getChildren().clear();
		mu1.getChildren().clear();
		ru1.getChildren().clear();
		ld1.getChildren().clear();
		rd1.getChildren().clear();
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");

		List<Booth> booths = boothServiceRemote.findAll();
		it = it + 5;
		if (it < booths.size())
			populatebooths(booths, it);
		else {
			nextb.setDisable(true);
			prevb.setDisable(false);
		}
	}

	@FXML
	public void prev() throws NamingException, IOException {
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");

		List<Booth> booths = boothServiceRemote.findAll();
		if (it > 0) {
			it = it - 5;
			populatebooths(booths, it);
		} else if (it == 0) {
			nextb.setDisable(false);
			prevb.setDisable(true);
		}

	}

	void populateleftupper(Booth b) throws IOException {
		lu1.getChildren().clear();
		ImageView iv = new ImageView();
		InputStream is = new FileInputStream(b.getBanner());
		System.out.println();
		BufferedImage imBuff = ImageIO.read(is);
		WritableImage image = SwingFXUtils.toFXImage(imBuff, null);
		iv.setImage(image);
		lu1.getChildren().add(iv);
		iv.fitWidthProperty().bind(lu1.widthProperty());
		iv.fitHeightProperty().bind(lu1.heightProperty());
	}

	void populatemiddle(Booth b) throws IOException {
		if (b.getBanner().exists()) {
			mu1.getChildren().clear();
			ImageView iv = new ImageView();
			InputStream is = new FileInputStream(b.getBanner());

			Image hhhhhhhhh = new Image(is);

			iv.setImage(hhhhhhhhh);
			mu1.getChildren().add(iv);
			iv.fitWidthProperty().bind(mu1.widthProperty());
			iv.fitHeightProperty().bind(mu1.heightProperty());
		} else {
			mu1.getChildren().add(comingsoon);
			comingsoon.fitWidthProperty().bind(mu1.widthProperty());
			comingsoon.fitHeightProperty().bind(mu1.heightProperty());
		}

	}

	public static int getIt() {
		return it;
	}

	public static void setIt(int it) {
		DisplayBoothsController.it = it;
	}

	void populateupperight(Booth b) throws IOException {
		ru1.getChildren().clear();
		ImageView iv = new ImageView();
		InputStream is = new FileInputStream(b.getBanner());

		Image hhhhhhhhh = new Image(is);

		iv.setImage(hhhhhhhhh);
		ru1.getChildren().add(iv);
		iv.fitWidthProperty().bind(ru1.widthProperty());
		iv.fitHeightProperty().bind(ru1.heightProperty());

	}

	void populatedownright(Booth b) throws IOException {
		rd1.getChildren().clear();
		ImageView iv = new ImageView();
		InputStream is = new FileInputStream(b.getBanner());

		Image hhhhhhhhh = new Image(is);

		iv.setImage(hhhhhhhhh);
		rd1.getChildren().add(iv);
		iv.fitWidthProperty().bind(rd1.widthProperty());
		iv.fitHeightProperty().bind(rd1.heightProperty());

	}

	void populatedownleft(Booth b) throws IOException {
		ld1.getChildren().clear();
		ImageView iv = new ImageView();
		InputStream is = new FileInputStream(b.getBanner());

		Image hhhhhhhhh = new Image(is);

		iv.setImage(hhhhhhhhh);
		ld1.getChildren().add(iv);
		iv.fitWidthProperty().bind(ld1.widthProperty());
		iv.fitHeightProperty().bind(ld1.heightProperty());

	}

	

	@FXML
	void search(ActionEvent event) throws NamingException, IOException {
		String name = searchname.getText();
		lu1.getChildren().clear();
		mu1.getChildren().clear();
		ru1.getChildren().clear();
		ld1.getChildren().clear();
		rd1.getChildren().clear();
		Context context = new InitialContext();
		BoothServiceRemote boothServiceRemote = (BoothServiceRemote) context.lookup(
				"/esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/BoothService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceRemote");
		if (!name.isEmpty()) {
			Booth b = boothServiceRemote.findBoothidbyname(name);

			System.out.println("name :" + b.getId());
			populateleftupper(b);
		}
		int i = getIt();
		if (show.isSelected()) {
			String type = "ShowCase";
			List<Booth> booths = boothServiceRemote.findBoothbytype(type);
			populatebooths(booths, i);
			show.setSelected(false);
		}
		else 	if (job.isSelected()) {
			String type = "Job Offers";
			List<Booth> booths = boothServiceRemote.findBoothbytype(type);
			populatebooths(booths, i);
			job.setSelected(false);
		}
		else if (info.isSelected()) {
			String type = "informational";
			List<Booth> booths = boothServiceRemote.findBoothbytype(type);
			populatebooths(booths, i);
			info.setSelected(false);
		}
		ClubServiceRemote clubServiceRemote = (ClubServiceRemote) context.lookup(
				"esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/ClubService!tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.ClubServiceRemote");

		List<Club> c = clubServiceRemote.findAll();
		String nameclub = (String) clubscombo.getValue();
		for (Club club : c) {
		if (nameclub.equals(club.getName())) {
				int itt = club.getId();
				List<Booth> booths = boothServiceRemote.findBoothbyClub(itt);
				
					populatebooths(booths, i);
					clubscombo.getItems().clear();
					clubscombo.getItems().addAll(populatecombo());
			}

		}
		 if (show.isSelected() && !nameclub.equals("")) {
			String type = "ShowCase";
			for (Club club : c) {
				if (nameclub.equals(club.getName())) {
					int itt = club.getId();
					List<Booth> booths = boothServiceRemote.findBoothbytypeandclub(itt, type);
					lu1.getChildren().clear();
					mu1.getChildren().clear();
					ru1.getChildren().clear();
					ld1.getChildren().clear();
					rd1.getChildren().clear();
						populatebooths(booths, i);
						System.out.println("waaaaaaaaaaaaaaaa");
					
				}
			}

		}

	}
	@FXML
	void clos() {
         System.exit(0);

	}
	
	
	
}
