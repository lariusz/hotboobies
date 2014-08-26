package pl.hotboobies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.hotboobies.dao.ProduktDao;
import pl.hotboobies.dao.ZamowienieDao;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */

@ManagedBean
@SessionScoped
public class Kucharz extends Uzytkownik implements Serializable {
	
	private static final long serialVersionUID = 360396799453001524L;

	/** Zam�wienia kt�re pobra� kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	private String Komunikat;
	private int licznik;

	@ManagedProperty(value="#{sesja.zalogowany}")
	private Uzytkownik uzytkownik;	

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public String getKomunikat() {
		return Komunikat;
	}

	public void setKomunikat(String komunikat) {
		Komunikat = komunikat;
	}
	
	public List<Zamowienie> getZamowienia() {
		
		return zamowienia;
	}

	public void setZamowienia(List<Zamowienie> zamowienia) {
		this.zamowienia = zamowienia;
	}
	
	public int getLicznik() {
		return licznik;
	}

	public void setLicznik(int licznik) {
		this.licznik = licznik;
	}
	
	/**
	 * Pobiera zam�wienie z listy zam�wie� o statusie <b>W kuchni</b> i dodaje go do swojej listy zam�wie� 
	 * ustawiaj�c status zam�wienia na <b>Przygotywywany</b>
	 */
	public String pobierzZamowienie(){
		
		List<Zamowienie> ostatnieNieprzydzielone = ZamowienieDao.pobierzNajstarszeNieprzydzielone();
		
		if (!ostatnieNieprzydzielone.isEmpty()) {
			ZamowienieDao.przypiszZamowienieKucharzowi(uzytkownik.getIdentyfikator(), ostatnieNieprzydzielone.get(0).getIdZamowienia());
			wyswietlZamowieniaMoje();
		}		
		else  {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacja", 
					"Brak zam�wie� do pobrania - spr�buj za chwil�."));
		}
		return null;
	}

	/**
	 * Pozwala zwr�ci� zam�wienia kt�re zosta�y pobrane na list� zam�wie� kucharza.
	 * Zmienia status zam�wienia na <b>W kuchni</b>
	 */
	public String zwrocZamowienie(int idZamowienia){
	
		ZamowienieDao.zwrocZamowienie(idZamowienia);
		wyswietlZamowieniaMoje();
	
		return null;
	}	
	 
	/**
	 * Przekazuje przygotowane zam�wienia do listy zam�wie� kelnera kt�ry zainicjowa� sk�adanie zam�wienia.
	 * Status zam�wienia zmienia sie na <b>Do podania</b>
	 */
	public String doKelnera(int idZamowienia){
		
		ZamowienieDao.przekazDoKelnera(idZamowienia);
		wyswietlZamowieniaMoje();
		
		return null;
	}
	
	public void wyswietlZamowieniaWolne() {	
		List<Zamowienie> wszystkie = ZamowienieDao.pobierzWszystkieNieprzydzielone();	
		zamowienia=wszystkie;
		
	}
		
	
	public void wyswietlZamowieniaMoje() {
		List<Zamowienie> wszystkieZamowienia = ZamowienieDao.pobierzPrzydzieloneDoKucharza(uzytkownik.getIdentyfikator());

		for (Zamowienie zamowienie : wszystkieZamowienia) {
			List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		
		zamowienia = wszystkieZamowienia;
		licznik=zamowienia.size();
	
	}



}
