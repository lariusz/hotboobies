package pl.hotboobies;

import java.util.List;

import javax.faces.bean.ManagedBean;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */
@ManagedBean
public class Kelner {
	
	/** Zam�wienia, kt�re utworzy� kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia;
	
	/** Zam�wienia, kt�re przygotowa� kucharz i s� gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania;
	
	
	/**
	 * Tworzy puste zam�wienie ze statusem <b>Tymczasowe</b>
	 */
	public void dodajZamowienie(){
		
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
