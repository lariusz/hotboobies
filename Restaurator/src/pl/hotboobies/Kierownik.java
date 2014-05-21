package pl.hotboobies;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kierownika
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
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
	 * Wy�wietla wybrany typ raportu
	 * @param typ - typ raportu
	 */
	public void pokazRaport(String typ){
		switch(typ){
		case "typ1": raport.generujRaportTyp1(); break;
		case "typ2": raport.generujRaportTyp2(); break;
		case "typ3": raport.generujRaportTyp3(); break;
		case "typ4": raport.generujRaportTyp4(); break;
		}
		//TODO obs�uga wy�wietlenia raportu na formatce
	}
	
	
	/**
	 * Pozwala uzupe�ni� zapasy magazynowe wybranego produktu
	 */
	public void uzupe�nijStan(Produkt produkt){
		//TODO do przemy�lenia czy b�dziemy realizowa� to za pomoca okienka modalnego czy w inny spos�b		
	}

}
