package ExhibitionHall;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceLocal;

@ManagedBean
@ViewScoped
public class searchbooths {
	@EJB
	private BoothServiceLocal boothServiceLocal;
	private Booth booth;
	private String letter;
	private List<Booth> filteredb;
	@PostConstruct
	public void init()
	{
		booth=new Booth();
	}
	
	public void dofilterbooths()
	{
		filteredb=boothServiceLocal.filterbooths(letter);
		for(Booth b:filteredb)
		{
			System.out.println(b.getName());
		}
	}
	 public void handleKeyEvent() {
		 filteredb=boothServiceLocal.filterbooths(letter);
		 
	       
	    }
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
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
	

}
