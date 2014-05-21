package pl.hotboobies;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kierownika
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kierownik {
	
	Raport raport;
	
	public Kierownik() {
		super();
		raport = new Raport();
	}

	/**
	 * Wyœwietla wybrany typ raportu
	 * @param typ - typ raportu
	 */
	public void pokazRaport(String typ){
		switch(typ){
		case "typ1": raport.generujRaportTyp1(); break;
		case "typ2": raport.generujRaportTyp2(); break;
		case "typ3": raport.generujRaportTyp3(); break;
		case "typ4": raport.generujRaportTyp4(); break;
		}
		//TODO obs³uga wyœwietlenia raportu na formatce
	}
	
	
	/**
	 * Pozwala uzupe³niæ zapasy magazynowe wybranego produktu
	 */
	public void uzupe³nijStan(Produkt produkt){
		//TODO do przemyœlenia czy bêdziemy realizowaæ to za pomoca okienka modalnego czy w inny sposób		
	}

}
