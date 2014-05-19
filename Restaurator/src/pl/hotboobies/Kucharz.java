package pl.hotboobies;

import java.util.List;

import javax.faces.bean.ManagedBean;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kucharza
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
public class Kucharz {
	
	/** Zamówienia które pobra³ kucharz do przygotowania */
	private List<Zamowienie> zamowienia;
	
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
}
