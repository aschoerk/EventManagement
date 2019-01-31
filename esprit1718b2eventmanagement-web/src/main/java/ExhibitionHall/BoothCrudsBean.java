package ExhibitionHall;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javafx.scene.image.Image;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceLocal;

@ManagedBean
@ViewScoped
public class BoothCrudsBean {
	@EJB
	private BoothServiceLocal boothServiceLocal;
	private Booth booth;
	private Booth selectedbooth;
	UploadedFile file;
    private String search;
	private List<Booth> booths;
	private List<Booth> booths2;
	private String letter;
	private List<Booth> filteredb;
	private int id;
	private Booth searchedBooth;
	private boolean change;
	private boolean change2;

	@PostConstruct
	public void init() throws FileNotFoundException {
change=false;
change2=false;
		booth = new Booth();

		booths = boothServiceLocal.findAll();
		booths2 = boothServiceLocal.findAll();
		if(change==true)
		{
			setBooths(getFilteredb());
		}
		if(change==true)
		{
			setBooths(getOrderedb());
		}
		

	}

	public void doAddBooth() throws IOException {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		File f = new File(file.getFileName());
		booth.setBanner(f);

		boothServiceLocal.save(booth);
	}
private List<Booth> designatedbooths;

public String getSearch() {
	return search;
}

public void setSearch(String search) {
	this.search = search;
}

public Booth getSearchedBooth() {
	return searchedBooth;
}

public void setSearchedBooth(Booth searchedBooth) {
	this.searchedBooth = searchedBooth;
}

public List<Booth> getDesignatedbooths() {
	return designatedbooths;
}

public void setDesignatedbooths(List<Booth> designatedbooths) {
	this.designatedbooths = designatedbooths;
}

public void doSearchbyname(String search)
{
	searchedBooth=boothServiceLocal.findBoothidbyname(search);
	designatedbooths.add(searchedBooth);

}

public void dofilterbooths()
{
	filteredb=boothServiceLocal.filterbooths(letter);
   setBooths(getFilteredb());
	change=true;
}
private List<Booth> orderedb;

public List<Booth> getOrderedb() {
	return orderedb;
}

public void setOrderedb(List<Booth> orderedb) {
	this.orderedb = orderedb;
}

public void dorderbooths()
{
	orderedb=boothServiceLocal.filtervisits();
   setBooths(getOrderedb());
	change2=true;
}




public List<Booth> getFilteredb() {
	return filteredb;
}

public void setFilteredb(List<Booth> filteredb) {
	this.filteredb = filteredb;
}


public String getLetter() {
	return letter;
}

public void setLetter(String letter) {
	this.letter = letter;
}

public void doupdateBooth(Booth booth) throws IOException {

		boothServiceLocal.update(booth);
	}

	public void dodeleteBooth(Booth booth) throws IOException {

		boothServiceLocal.delete(booth);
	}

	public Booth getBooth() {
		return booth;
	}

	public List<Booth> getBooths() {
		return booths;
	}

	public void setBooths(List<Booth> booths) {
		this.booths = booths;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Booth> getBooths2() {
		return booths2;
	}

	public void setBooths2(List<Booth> booths2) {
		this.booths2 = booths2;
	}

	public Booth getSelectedbooth() {
		return selectedbooth;
	}

	public void setSelectedbooth(Booth selectedbooth) {
		this.selectedbooth = selectedbooth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	

	

}
