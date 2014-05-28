package pl.hotboobies;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoProdukt;
import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */

@ManagedBean
@SessionScoped
public class Kucharz {
	
	/** Zamówienia które pobra³ kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	private String Komunikat;

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
	
	/**
	 * Pobiera zamówienie z listy zamówieñ o statusie <b>W kuchni</b> i dodaje go do swojej listy zamówieñ 
	 * ustawiaj¹c status zamówienia na <b>Przygotywywany</b>
	 */
	public String pobierzZamowienie(){
		System.out.println("errr!!!!!");
		DaoZamowienie dao = new DaoZamowienie();
		ArrayList<Zamowienie> ostatnieNieprzydzielone = (ArrayList<Zamowienie>) dao.pobierzNajstarszeNieprzydzielone();
		
		if (!ostatnieNieprzydzielone.isEmpty()) {
			DaoZamowienie daoPrzypisz = new DaoZamowienie();
			daoPrzypisz.przypiszZamowienieKucharzowi(uzytkownik.getIdentyfikator(), ostatnieNieprzydzielone.get(0).getIdZamowienia());
			wyswietlZamowieniaMoje();
		}
		
		else  {
		Komunikat="Brak zamówieñ do pobrania!";	
		}
		wyswietlZamowieniaMoje();
		return null;
	}

	/**
	 * Pozwala zwróciæ zamówienia które zosta³y pobrane na listê zamówieñ kucharza.
	 * Zmienia status zamówienia na <b>W kuchni</b>
	 */
	public String zwrocZamowienie(int idZamowienia){
	
		DaoZamowienie daoPrzypisz = new DaoZamowienie();
		daoPrzypisz.zwrocZamowienie(idZamowienia);
		wyswietlZamowieniaMoje();
	
		return null;
	}	
	 
	/**
	 * Przekazuje przygotowane zamówienia do listy zamówieñ kelnera który zainicjowa³ sk³adanie zamówienia.
	 * Status zamówienia zmienia sie na <b>Do podania</b>
	 */
	public String doKelnera(int idZamowienia){
		
		DaoZamowienie daoPrzypisz = new DaoZamowienie();
		daoPrzypisz.przekazDoKelnera(idZamowienia);
		wyswietlZamowieniaMoje();
		
		return null;
	}
	
	public void wyswietlZamowieniaWolne() {
	
		DaoZamowienie dao = new DaoZamowienie();
		ArrayList<Zamowienie> wszystkie = (ArrayList<Zamowienie>) dao.pobierzWszystkieNieprzydzielone();	
		zamowienia=wszystkie;
		
	}
		
	
	public String wyswietlZamowieniaMoje() {
		System.out.println("wyswietlZamowieniaMoje");
		DaoZamowienie dao = new DaoZamowienie();
		ArrayList<Zamowienie> wszystkieZamowienia = (ArrayList<Zamowienie>) dao.pobierzPrzydzieloneDoKucharza(uzytkownik.getIdentyfikator());

		for (Zamowienie zamowienie : wszystkieZamowienia) {
			DaoProdukt daoProdukt = new DaoProdukt();
			List<Produkt> produkty = (ArrayList<Produkt>) daoProdukt.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		
		zamowienia = wszystkieZamowienia;
		Komunikat="";
		return "kucharz-moje";
	}



}
