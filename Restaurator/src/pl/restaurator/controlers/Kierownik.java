package pl.restaurator.controlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pl.restaurator.dao.ProduktDao;
import pl.restaurator.dao.ZamowienieDao;
import pl.restaurator.dto.Produkt;
import pl.restaurator.dto.Uzytkownik;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kierownika
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
@ViewScoped
public class Kierownik implements Serializable{
	
	private static final long serialVersionUID = 1705949180511598664L;

	
	public Kierownik() {
		produkty = ProduktDao.pobierzWszystkieKolumny();
	}

	/** Lista produktów do aktualizacji */
	private List<Produkt> produkty = new ArrayList<Produkt>();
	
	public List<Produkt> getProdukty() {
		return produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}


	/**
	 * Aktualizuje wszystkie pozycje.
	 */
	public void aktualizujWszystkie(){
		boolean success = true;
		for(Produkt produktEl : produkty) {
			success = success & ProduktDao.aktulizujIloscProduktu(produktEl.getId(), produktEl.getIlosc() , produktEl.isAktywny());
		} if (success){
			FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacja",
								"Aktualizacja produktów powiod³a siê."));
		}
	}
	
	/**
	 * Zmienia parametr aktywny na przeciwny
	 * @param idProdukt
	 */
	public void zmienAktywuj(int idProdukt){
		for(Produkt produktEl : produkty) {
			if(produktEl.getId() == idProdukt)
				produktEl.setAktywny(!produktEl.isAktywny());
		}
	}
}
