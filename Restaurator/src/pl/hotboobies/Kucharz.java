package pl.hotboobies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
public class Kucharz {
	

	/** Zamówienia które pobra³ kucharz do przygotowania */
	private List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
	
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
							zamowieniaResult.getString("id_status").trim().equals("3") ? "W kuchni" : "Inne",
							Integer.valueOf(zamowieniaResult.getString("id_status").trim()),
							zamowieniaResult.getDate("data_przyjecia"),
							"Stolik 5",
							zamowieniaResult.getString("kucharz_id")
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
				if(zamowieniaResult.getString("id_status").trim().equals("4") && zamowieniaResult.getString("kucharz_id").trim().equals("3")) {
					zamowienia.add(new Zamowienie(
							zamowieniaResult.getString("id_status").trim().equals("4") ? "Przygotowywany" : "Inne",
							Integer.valueOf(zamowieniaResult.getString("id_status").trim()),
							zamowieniaResult.getDate("data_przyjecia"),
							"Stolik 5",
							zamowieniaResult.getString("kucharz_id")
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
