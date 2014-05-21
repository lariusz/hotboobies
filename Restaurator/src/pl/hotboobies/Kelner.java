package pl.hotboobies;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import pl.hotboobies.dao.DaoGrupa;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kelner implements Serializable{
	
	private static final long serialVersionUID = -2043621321850425369L;

	/** Zam�wienia, kt�re utworzy� kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia = new LinkedList<Zamowienie>();
	
	/** Zam�wienia, kt�re przygotowa� kucharz i s� gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania = new LinkedList<Zamowienie>();

	public int getIloscNowychZamowien() {
		return noweZamowienia.size();
	}

	public int getIloscZamowienDoPodania() {
		return zamowieniaDoPodania.size();
	}
	
	private List<Produkt> produkty;
	
	private List<String> grupy = new LinkedList<String>();
	private String grupa;

	public String getGrupa() {
		return grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public List<String> getGrupy() {
		return grupy;
	}

	public void setGrupy(List<String> grupy) {
		this.grupy = grupy;
	}

	@ManagedProperty(value="#{logowanie.zalogowany}")
	private Uzytkownik uzytkownik;
	
	
	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	/**
	 * Tworzy puste zam�wienie ze statusem <b>Tymczasowe</b>
	 */
	public String dodajZamowienie() throws SQLException{
		Zamowienie tymczasowe = new Zamowienie();
		tymczasowe.setIdKelnera(uzytkownik.getIdentyfikator());	
		pobierzNazwyGrupProduktow();
		return "zamowienie";
	}
	
	private void pobierzNazwyGrupProduktow(){
		try{
		DaoGrupa grupa = new DaoGrupa();
		ResultSet nazwyGrup = grupa.pobierzWszystkieNazwy();
		while(nazwyGrup.next()){
			grupy.add(nazwyGrup.getString("nazwa"));
		}
		nazwyGrup.close();
		grupa.zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Uswa zam�wienie z listy zam�wie�
	 */
	public void usunZamowienie(){
		
	}

	/**
	 * Dodaje do listy zam�wie� oczekuj�cych na pobranie przez kucharza
	 * Zmienia status zam�wienia na <b>W kuchni</b>
	 */
	public void doKuchni(){
		
	}
	
	/**
	 * Usuwa z listy zam�wie� do podania
	 * Zmienia status zam�wienia na <b>Zaserwowany</b>
	 */
	public void doKlienta(){
		
	}
	
	/**
	 * Dodaje produkt do tymczasowego zam�wienia
	 */
	public void dodajProdukt(){
		
	}
	
	/**
	 * Usuwa produkt z tymczasowego zam�wienia
	 */
	public void usunProdukt(){
		
	}
	
	/**
	 * Zapisuje tymczasowe zam�wienie w bazie danych nadaj�c mu status <b>Zam�wiony</b>.
	 */
	public void zapisz(){
		
	}
	
	/**
	 * Anuluje sk�adanie zam�wienia, dodaj�c do bazy informacje o przyczynie anulowania.
	 */
	public void anuluj(){
		
	}
	
	/**
	 * Pobiera list� produkt�w przynale��cych do danej grupy (kategorii)
	 * @param grupa - kategoria produkt�w
	 */
	public void pokazProdukty(String grupa){
		
	}
	
	
}
