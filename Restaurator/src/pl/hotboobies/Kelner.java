package pl.hotboobies;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoGrupa;
import pl.hotboobies.dao.DaoProdukt;

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
	
	/** Wybrany identyfikator grupy produkt�w */
	private int idGrupy;
	
	public int getIdGrupy() {
		return idGrupy;
	}

	public void setIdGrupy(int idGrupy) {		
		System.out.println("Ustawi�em id grupy na " + idGrupy);
		this.idGrupy = idGrupy;
	}

	/** Wybrana nazwa grupy produkt�w */
	private String nazwaGrupy;
	
	/** Lista produkt�w wybranej grupy */
	private List<Produkt> produktyGrupy = new LinkedList<Produkt>();
	
	public List<Produkt> getProduktyGrupy() {
		pobierzProduktyGrupy();
		return produktyGrupy;
	}

	public void setProduktyGrupy(List<Produkt> produktyGrupy) {
		this.produktyGrupy = produktyGrupy;
	}

	/** Lista grup produkt�w */
	private List<Grupa> grupy = new LinkedList<Grupa>();

	public String getNazwaGrupy() {
		return nazwaGrupy;
	}

	public void setNazwaGrupy(String nazwaGrupy) {
		System.out.println("Ustawi�em nazw� grupy na " + nazwaGrupy);
		this.nazwaGrupy = nazwaGrupy;
	}

	public List<Grupa> getGrupy() {
		return grupy;
	}

	public void setGrupy(List<Grupa> grupy) {
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
		tymczasowe.setIdStatus(1); // Status: Tymczasowe
		pobierzNazwyGrupProduktow();
		return "zamowienie";
	}
	
	/**
	 * Pobiera z bazy identyfikatory i nazwy grup produkt�w umieszcza je w obiektach grup
	 * i obiekty umieszcza na li�cie grup produkt�w. Lista jest czyszczona na pocz�tku,
	 * poniewa� czynno�� bedzie powtarzana przy sk�adaniu ka�dego zam�wienia.
	 */
	private void pobierzNazwyGrupProduktow(){
		grupy.clear();
		DaoGrupa daoGrupa = new DaoGrupa();
		try {
		ResultSet wszystkieGrupy = daoGrupa.pobierzWszystkieKolumny();
			while(wszystkieGrupy.next()){
				grupy.add(new Grupa(wszystkieGrupy.getInt("id_grupa"), wszystkieGrupy.getString("nazwa")));
			}
		wszystkieGrupy.close();
		daoGrupa.zamknijPolaczenie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Pobiera z bazy wszystkie informacje o produktach danej grupy  i umieszcza obiekty na li�cie produkt�w.
	 */
	private void pobierzProduktyGrupy(){
		produktyGrupy.clear();
		DaoProdukt daoProdukt = new DaoProdukt();
		try {
		ResultSet wszystkieProdukty = daoProdukt.pobierzWszystkieKolumny(idGrupy);
			while(wszystkieProdukty.next()){
				produktyGrupy.add(new Produkt(wszystkieProdukty.getInt("id_produkt"),
						wszystkieProdukty.getString("nazwa"),
						wszystkieProdukty.getInt("ilosc"),
						wszystkieProdukty.getInt("czas_wykonania"),
						wszystkieProdukty.getInt("aktywny") == 1));
			}
			wszystkieProdukty.close();
			daoProdukt.zamknijPolaczenie();
		} catch (SQLException e) {
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
	

	
	
}
