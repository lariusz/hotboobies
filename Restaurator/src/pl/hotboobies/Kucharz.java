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
 * 	Kontroler dla czynnoœci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
public class Kucharz {
	
	private Zamowienie wybrane;
	
	/** Zamówienia które pobra³ kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	
	@ManagedProperty(value="#{logowanie.zalogowany}")
	private Uzytkownik uzytkownik;	

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public Zamowienie getWybrane() {
		return wybrane;
	}

	public void setWybrane(Zamowienie wybrane) {
		this.wybrane = wybrane;
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
	public void pobierzZamowienie(){
		
	}

	/**
	 * Pozwala zwróciæ zamówienia które zosta³y pobrane na listê zamówieñ kucharza.
	 * Zmienia status zamówienia na <b>W kuchni</b>
	 */
	public void zwrocZamowienie(){
		
	}	
	
	/**
	 * Przekazuje przygotowane zamówienia do listy zamówieñ kelnera który zainicjowa³ sk³adanie zamówienia.
	 * Status zamówienia zmienia sie na <b>Do podania</b>
	 */
	public void doKelnera(){
		
	}
	
	public void wyswietlZamowieniaWolne() {
	
		DaoZamowienie dao = new DaoZamowienie();
		ResultSet zamowieniaResult = null;
		
		try{
			zamowieniaResult = dao.pobierzWszystkich();
			while(zamowieniaResult.next()){
				if(zamowieniaResult.getString("id_status").trim().equals("3")) {
					zamowienia.add(new Zamowienie(
							0,
							zamowieniaResult.getString("id_status").trim().equals("3") ? "W kuchni" : "Inne",
							Integer.valueOf(zamowieniaResult.getString("id_status").trim()),
							zamowieniaResult.getDate("data_przyjecia"),
							zamowieniaResult.getInt("nr_stolika"),
							zamowieniaResult.getInt("id_uzytkownik"),
							zamowieniaResult.getInt("kucharz_id")
				
							));	
				}
				
							
		
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					zamowieniaResult.close();
					dao.zamknijPolaczenie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		
	}
	
	
	public void wyswietlZamowieniaMoje() {
		
		DaoZamowienie dao = new DaoZamowienie();
		ResultSet zamowieniaResult = null;
		
		try{
			
			
			
			zamowieniaResult = dao.pobierzWszystkich();
			while(zamowieniaResult.next()){
				if(zamowieniaResult.getString("id_status").trim().equals("4") && zamowieniaResult.getInt("kucharz_id")==uzytkownik.getIdentyfikator()) {
					
					List<Produkt> tmp_produkty = new ArrayList<Produkt>();
					
					
					
					DaoPozycja daoPozycja = new DaoPozycja();
					ResultSet pozycjaResult = daoPozycja.pobierzWszystkie();
					
					while(pozycjaResult.next()) {
						if(zamowieniaResult.getInt("ID_ZAMOWIENIE")==pozycjaResult.getInt("ID_ZAMOWIENIE")) {
							tmp_produkty.add(new Produkt(0, "Barszcz czerwony", 1, 10, true));	
						}
					}
					
					zamowienia.add(new Zamowienie(
							zamowieniaResult.getInt("ID_ZAMOWIENIE"),
							zamowieniaResult.getString("id_status").trim().equals("3") ? "W kuchni" : "Inne",
							Integer.valueOf(zamowieniaResult.getString("id_status").trim()),
							zamowieniaResult.getDate("data_przyjecia"),
							zamowieniaResult.getInt("nr_stolika"),
							zamowieniaResult.getInt("id_uzytkownik"),
							zamowieniaResult.getInt("kucharz_id"),
							tmp_produkty
							));	
					
				
				}
				
			
				
				
		
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					zamowieniaResult.close();
					dao.zamknijPolaczenie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		
	}
	
}
