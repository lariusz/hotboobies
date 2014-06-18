package pl.hotboobies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoUzytkownik;

/**
 * 	Klasa pozwalaj�ca na generowanie raport�w.
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */

@ManagedBean
@SessionScoped
public class Raport implements Serializable{

	private static final long serialVersionUID = 4994624611961321842L;
	
	private Date dataOd;
	private Date dataDo;	
	private List<Uzytkownik> kelnerzy = new ArrayList<>();
	private List<Uzytkownik> kucharze = new ArrayList<>();
	private Uzytkownik kelner;
	private Uzytkownik kucharz;
	
	
	public Uzytkownik getKelner() {
		return kelner;
	}

	public void setKelner(Uzytkownik kelner) {
		this.kelner = kelner;
	}

	public Uzytkownik getKucharz() {
		return kucharz;
	}

	public void setKucharz(Uzytkownik kucharz) {
		this.kucharz = kucharz;
	}
	
	public List<Uzytkownik> getKelnerzy() {
		return kelnerzy = DaoUzytkownik.pobierzKelnerow();
	}

	public void setKelnerzy(List<Uzytkownik> kelnerzy) {
		this.kelnerzy = kelnerzy;
	}

	public List<Uzytkownik> getKucharze() {		
		return kucharze = DaoUzytkownik.pobierzKucharzy();
	}

	public void setKucharze(List<Uzytkownik> kucharze) {
		this.kucharze = kucharze;
	}
	
	public Date getDataOd() {
		return dataOd;
	}

	public void setDataOd(Date dataOd) {
		this.dataOd = dataOd;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public void setDataDo(Date dataDo) {
		this.dataDo = dataDo;
	}
	
	

	/**
	 * Generuje raport zam�wie� kucharza
	 */
	public void generujRaportKucharza(){
		
	}

	/**
	 * Generuje raport zam�wie� kelnera
	 */
	public void generujRaportKelnera(int idUzytkownika, Date dataOd, Date dataDo){
		
	}

	/**
	 * Generuje raport zam�ionych produkt�w
	 */
	public void generujRaportZamowionychProduktow(Date dataOd, Date dataDo){
		
	}

	/**
	 * Generuje raport zam�wie�
	 */
	public void generujRaportZamowien(){
		
	}
	
	/**
	 * Generuje raport anulowanych zam�wie�
	 */
	public void generujRaportAnulowanychZamowien(Date dataOd, Date dataDo){
		
	}

}
