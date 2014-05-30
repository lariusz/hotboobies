package pl.hotboobies;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoProdukt;
import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */

@ManagedBean
@SessionScoped
public class Kucharz {
	
	/** Zam�wienia kt�re pobra� kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	private String Komunikat;
	private int licznik;

	@ManagedProperty(value="#{logowanie.zalogowany}")
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
		DaoZamowienie dao = new DaoZamowienie();
		List<Zamowienie> ostatnieNieprzydzielone = dao.pobierzNajstarszeNieprzydzielone();
		
		if (!ostatnieNieprzydzielone.isEmpty()) {
			DaoZamowienie daoPrzypisz = new DaoZamowienie();
			daoPrzypisz.przypiszZamowienieKucharzowi(uzytkownik.getIdentyfikator(), ostatnieNieprzydzielone.get(0).getIdZamowienia());
			wyswietlZamowieniaMoje();
		}		
		else  {
		Komunikat="Brak zam�wie� do pobrania!";	
		}
		return null;
	}

	/**
	 * Pozwala zwr�ci� zam�wienia kt�re zosta�y pobrane na list� zam�wie� kucharza.
	 * Zmienia status zam�wienia na <b>W kuchni</b>
	 */
	public String zwrocZamowienie(int idZamowienia){
	
		DaoZamowienie daoPrzypisz = new DaoZamowienie();
		daoPrzypisz.zwrocZamowienie(idZamowienia);
		wyswietlZamowieniaMoje();
	
		return null;
	}	
	 
	/**
	 * Przekazuje przygotowane zam�wienia do listy zam�wie� kelnera kt�ry zainicjowa� sk�adanie zam�wienia.
	 * Status zam�wienia zmienia sie na <b>Do podania</b>
	 */
	public String doKelnera(int idZamowienia){
		
		DaoZamowienie daoPrzypisz = new DaoZamowienie();
		daoPrzypisz.przekazDoKelnera(idZamowienia);
		wyswietlZamowieniaMoje();
		
		return null;
	}
	
	public void wyswietlZamowieniaWolne() {
	
		DaoZamowienie dao = new DaoZamowienie();
		List<Zamowienie> wszystkie = dao.pobierzWszystkieNieprzydzielone();	
		zamowienia=wszystkie;
		
	}
		
	
	public void wyswietlZamowieniaMoje() {
		DaoZamowienie dao = new DaoZamowienie();
		List<Zamowienie> wszystkieZamowienia = dao.pobierzPrzydzieloneDoKucharza(uzytkownik.getIdentyfikator());

		for (Zamowienie zamowienie : wszystkieZamowienia) {
			DaoProdukt daoProdukt = new DaoProdukt();
			List<Produkt> produkty = daoProdukt.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		
		zamowienia = wszystkieZamowienia;
		Komunikat="";
		licznik=zamowienia.size();
	
	}



}
