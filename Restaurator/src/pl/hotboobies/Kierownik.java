package pl.hotboobies;

import javax.faces.bean.ManagedBean;

/**
 * 	Kontroler dla czynności wykonywanych przez Kierownika
 *  @author <a href="mailto:mlarysz@us.edu.pl">Michał Larysz</a>
 */
@ManagedBean
public class Kierownik {
	
	Raport raport;
	
	public Kierownik() {
		super();
		raport = new Raport();
	}

	/**
	 * Wyświetla wybrany typ raportu
	 * @param typ - typ raportu
	 */
	public void pokazRaport(String typ){
		switch(typ){
		case "typ1": raport.generujRaportTyp1(); break;
		case "typ2": raport.generujRaportTyp2(); break;
		case "typ3": raport.generujRaportTyp3(); break;
		case "typ4": raport.generujRaportTyp4(); break;
		}
		//TODO obsługa wyświetlenia raportu na formatce
	}
	
	
	/**
	 * Pozwala uzupełnić zapasy magazynowe wybranego produktu
	 */
	public void uzupełnijStan(Produkt produkt){
		//TODO do przemyślenia czy będziemy realizować to za pomoca okienka modalnego czy w inny sposób		
	}

}
