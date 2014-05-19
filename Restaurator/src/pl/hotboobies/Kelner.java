package pl.hotboobies;

import java.util.List;

import javax.faces.bean.ManagedBean;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
public class Kelner {
	
	/** Zamówienia, które utworzy³ kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia;
	
	/** Zamówienia, które przygotowa³ kucharz i s¹ gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania;
	
	
	/**
	 * Tworzy puste zamówienie ze statusem <b>Tymczasowe</b>
	 */
	public void dodajZamowienie(){
		
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
