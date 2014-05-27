package pl.hotboobies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoPozycja;
import pl.hotboobies.dao.DaoProdukt;
import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */

@ManagedBean
public class Kucharz {
	
	/** Zam�wienia kt�re pobra� kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	
	@ManagedProperty(value="#{logowanie.zalogowany}")
	private Uzytkownik uzytkownik;	

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public List<Zamowienie> getZamowienia() {
		
		return zamowienia;
	}

	public void setZamowienia(List<Zamowienie> zamowienia) {
		this.zamowienia = zamowienia;
	}
	
	/**
	 * Pobiera zam�wienie z listy zam�wie� o statusie <b>W kuchni</b> i dodaje go do swojej listy zam�wie� 
	 * ustawiaj�c status zam�wienia na <b>Przygotywywany</b>
	 */
	public void pobierzZamowienie(){
		
	}

	/**
	 * Pozwala zwr�ci� zam�wienia kt�re zosta�y pobrane na list� zam�wie� kucharza.
	 * Zmienia status zam�wienia na <b>W kuchni</b>
	 */
	public void zwrocZamowienie(){
		
	}	
	
	/**
	 * Przekazuje przygotowane zam�wienia do listy zam�wie� kelnera kt�ry zainicjowa� sk�adanie zam�wienia.
	 * Status zam�wienia zmienia sie na <b>Do podania</b>
	 */
	public void doKelnera(){
		
	}
	
	public void wyswietlZamowieniaWolne() {
	
		DaoZamowienie dao = new DaoZamowienie();
		ArrayList<Zamowienie> wszystkie = (ArrayList<Zamowienie>) dao.pobierzWszystkieNieprzydzielone();	
		zamowienia=wszystkie;
		
	}
	
	
	public void wyswietlZamowieniaMoje() {
		
		DaoZamowienie dao = new DaoZamowienie();
		ArrayList<Zamowienie> wszystkieZamowienia = (ArrayList<Zamowienie>) dao.pobierzPrzydzieloneDoKucharza(uzytkownik.getIdentyfikator());

		for (Zamowienie zamowienie : wszystkieZamowienia) {
			DaoProdukt daoProdukt = new DaoProdukt();
			List<Produkt> produkty = (ArrayList<Produkt>) daoProdukt.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		
		zamowienia = wszystkieZamowienia;
		
	}



}
