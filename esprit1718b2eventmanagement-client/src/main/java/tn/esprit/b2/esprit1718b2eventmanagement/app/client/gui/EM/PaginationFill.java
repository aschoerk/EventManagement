package tn.esprit.b2.esprit1718b2eventmanagement.app.client.gui.EM;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PhotoResult;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.jfoenix.controls.JFXTextField;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PaginationFill {

	private ImageView image;
	private ImageView icon;
	private String address;
	private String type;
	private String name;
	private String rating;

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public ImageView getIcon() {
		return icon;
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView displayimage(byte[] imageInByte) throws IOException {

		InputStream in = new ByteArrayInputStream(imageInByte);
		return new ImageView(new Image(in));

	}

	public List<PaginationFill> getSearchInformation(String search)
			throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCEU2Gvk7VRw0Fo149V6-HBAg-FZfvz3DQ").build();

		TextSearchRequest req = PlacesApi.textSearchQuery(context, search);
		PlacesSearchResponse res = req.await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<PaginationFill> pf = new ArrayList<PaginationFill>();
		int i = 0;
		while (i < res.results.length ) {
			Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
			PlaceDetails pdr = new PlaceDetails();
			pdr = PlacesApi
					.placeDetails(context,
							gson2.toJson(res.results[i].placeId).substring(1, res.results[i].placeId.length() + 1))
					.await();

			Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
			PhotoResult pr = new PhotoResult();
			PaginationFill p = new PaginationFill();
			try {
			System.out.println(gson3.toJson(pdr.photos[0].photoReference).substring(1,
									pdr.photos[0].photoReference.length() + 1));
			pr = PlacesApi
					.photo(context,
							gson3.toJson(pdr.photos[0].photoReference).substring(1,
									pdr.photos[0].photoReference.length() + 1))
					.maxHeight(400).maxWidth(250).await();
			
			p.setImage(displayimage(pr.imageData));} catch (Exception e) {
				// TODO: handle exception
				p.setImage(new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSi_m8NVMyrlXTAREZclJRXySWPGcPlsxO6Jaf5YwSI_LPxF4SB"));
			}
			p.setAddress(gson2.toJson(pdr.formattedAddress).replaceAll("\"", ""));
			System.out.println(gson3.toJson(pdr.name).replaceAll("\"", ""));
			p.setName(gson2.toJson(pdr.name).replaceAll("\"", ""));
			p.setType(gson2.toJson(res.results[i].types[0]).replaceAll("\"", ""));
			p.setRating(gson2.toJson(pdr.rating).replaceAll("\"", ""));
			p.setIcon(new ImageView(gson2.toJson(pdr.icon).replaceAll("\"", "")));
			pf.add(p);
			i++;
		}

		return pf;

	}

	public VBox createPage(Integer pageindex, List<PaginationFill> pf, JFXTextField textfield) {
		VBox box = new VBox( pf.size()+1);
		int page = pageindex ;
		for (int i = page; i <  page +1; i++) {
			VBox element = new VBox();
			element.setStyle("-fx-background-color: white;");
			HBox el = new HBox(12);
			el.getChildren().addAll(pf.get(i).getIcon(), new Label(pf.get(i).getName()));

			Label text = new Label("Address :" + pf.get(i).getAddress());
			Label text2 = new Label("Type :" + pf.get(i).getType());
			Label text3 = new Label("Rating :" + pf.get(i).getRating());
			ImageView im =pf.get(i).getImage();
			im.setFitWidth(400);
			im.setFitHeight(150);
			String s =  pf.get(i).getAddress();
			element.getChildren().addAll(im, el, text, text2, text3, new Separator());
			box.getChildren().addAll(element);
			box.addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
                public void handle(
                        final MouseEvent mouseEvent) {
                              textfield.setText(s);
                    }
                });
		}
		return box;
	}

	
}
