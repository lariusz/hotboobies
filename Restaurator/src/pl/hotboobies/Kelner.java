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
 * 	Kontroler dla czynnoœci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kelner implements Serializable{
	
	private static final long serialVersionUID = -2043621321850425369L;

	/** Zamówienia, które utworzy³ kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia = new LinkedList<Zamowienie>();
	
	/** Zamówienia, które przygotowa³ kucharz i s¹ gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania = new LinkedList<Zamowienie>();

	public int getIloscNowychZamowien() {
		return noweZamowienia.size();
	}

	public int getIloscZamowienDoPodania() {
		return zamowieniaDoPodania.size();
	}
	
	/** Wybrana nazwa grupy produktów */
	private String nazwaGrupy;
	
	/** Lista produktów wybranej grupy */
	private List<Produkt> produktyGrupy = new LinkedList<Produkt>();
	
	public List<Produkt> getProduktyGrupy() {
		wyswietlProduktyGrupy();
		return produktyGrupy;
	}

	public void setProduktyGrupy(List<Produkt> produktyGrupy) {
		this.produktyGrupy = produktyGrupy;
	}

	/** Lista grup produktów */
	private List<Grupa> grupy = new LinkedList<Grupa>();

	public String getNazwaGrupy() {
		return nazwaGrupy;
	}

	public void setNazwaGrupy(String nazwaGrupy) {
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
	 * Tworzy puste zamówienie ze statusem <b>Tymczasowe</b>
	 */
	public String dodajZamowienie() throws SQLException{
		Zamowienie tymczasowe = new Zamowienie();
		tymczasowe.setIdKelnera(uzytkownik.getIdentyfikator());
		tymczasowe.setIdStatus(1); // Status: Tymczasowe
		pobierzNazwyGrupProduktow();
		return "zamowienie";
	}
	
	public void wyswietlProduktyGrupy(){
		for (Grupa grupa : grupy) {
			if(grupa.getNazwa().equals(nazwaGrupy)){
				pobierzNazwyProduktow(grupa.getId());
			}
		}	
	}
	
	
	/**
	 * Pobiera z bazy identyfikatory i nazwy grup produktów umieszcza je w obiektach grup
	 * i obiekty umieszcza na liœcie grup produktów. Lista jest czyszczona na pocz¹tku,
	 * poniewa¿ czynnoœæ bedzie powtarzana przy sk³adaniu ka¿dego zamówienia.
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
	 * Pobiera z bazy wszystkie informacje o produktach danej grupy  i umieszcza obiekty na liœcie produktów.
	 * @param grupa - identyfikator grupy produktów
	 */
	private void pobierzNazwyProduktow(int grupa){
		produktyGrupy.clear();
		DaoProdukt daoProdukt = new DaoProdukt();
		try {
		ResultSet wszystkieProdukty = daoProdukt.pobierzWszystkieKolumny(grupa);
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
	 * Uswa zamówienie z listy zamówieñ
	 */
	public void usunZamowienie(){
		
	}

	/**
	 * Dodaje do listy zamówieñ oczekuj¹cych na pobranie przez kucharza
	 * Zmienia status zamówienia na <b>W kuchni</b>
	 */
	public void doKuchni(){
		
	}
	
	/**
	 * Usuwa z listy zamówieñ do podania
	 * Zmienia status zamówienia na <b>Zaserwowany</b>
	 */
	public void doKlienta(){
		
	}
	
	/**
	 * Dodaje produkt do tymczasowego zamówienia
	 */
	public void dodajProdukt(){
		
	}
	
	/**
	 * Usuwa produkt z tymczasowego zamówienia
	 */
	public void usunProdukt(){
		
	}
	
	/**
	 * Zapisuje tymczasowe zamówienie w bazie danych nadaj¹c mu status <b>Zamówiony</b>.
	 */
	public void zapisz(){
		
	}
	
	/**
	 * Anuluje sk³adanie zamówienia, dodaj¹c do bazy informacje o przyczynie anulowania.
	 */
	public void anuluj(){
		
	}
	
	/**
	 * Pobiera listê produktów przynale¿¹cych do danej grupy (kategorii)
	 * @param grupa - kategoria produktów
	 */
	public void pokazProdukty(String grupa){
		
	}
	
	
}
