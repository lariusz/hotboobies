package pl.hotboobies;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.hotboobies.dao.DaoProdukt;
import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kierownika
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kierownik {
	
	Raport raport;
	private List<Produkt> produkty;
	
	public List<Produkt> getProdukty() {
		return produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}

	public Kierownik() {
		super();
		raport = new Raport();
		 WyswietlStan();
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
	public void uzupelnijStan(Produkt produkt){
		//TODO do przemy�lenia czy b�dziemy realizowa� to za pomoca okienka modalnego czy w inny spos�b		
	}
	
	public void uzupelnijStan(){
		//TODO do przemy�lenia czy b�dziemy realizowa� to za pomoca okienka modalnego czy w inny spos�b		
	
		for(Produkt produktEl : produkty) {
			DaoProdukt daoAktualizuj = new DaoProdukt();
			daoAktualizuj.aktulizujIloscProduktu(produktEl.getId(), produktEl.getIlosc());
		}
		
		WyswietlStan();
	}
	
	public void aktywujProdukt(int idProduktu){
		//TODO do przemy�lenia czy b�dziemy realizowa� to za pomoca okienka modalnego czy w inny spos�b		
	
DaoProdukt daoAktualizuj = new DaoProdukt();
daoAktualizuj.aktulizujAktywnosc(idProduktu, 1);
WyswietlStan();

	}
	
	public void dezaktywujProdukt(int idProduktu){
		//TODO do przemy�lenia czy b�dziemy realizowa� to za pomoca okienka modalnego czy w inny spos�b		
DaoProdukt daoAktualizuj = new DaoProdukt();
daoAktualizuj.aktulizujAktywnosc(idProduktu, 0);
WyswietlStan();

	}
	
	public String WyswietlStan(){
			DaoProdukt dao = new DaoProdukt();
			produkty = dao.pobierzWszystkieKolumny();	
		
		
		return "kierownik-stan";
			}

}
