package pl.hotboobies.controlers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pl.hotboobies.dao.GrupaDao;
import pl.hotboobies.dao.PozycjaDao;
import pl.hotboobies.dao.ProduktDao;
import pl.hotboobies.dao.ZamowienieDao;
import pl.hotboobies.dto.Grupa;
import pl.hotboobies.dto.Produkt;
import pl.hotboobies.dto.Uzytkownik;
import pl.hotboobies.dto.Zamowienie;

/**
 * 	Kontroler dla czynno�ci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kelner implements Serializable{
	
	
	private static final long serialVersionUID = -2043621321850425369L;

	/** Zam�wienie kt�re jest realizowane przez kelnera */
	private Zamowienie tymczasowe;
	

	@ManagedProperty(value="#{sesja.zalogowany}")
	private Uzytkownik uzytkownik;	
	

	/** Zam�wienia, kt�re utworzy� kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia = new ArrayList<Zamowienie>();
	

	/** Zam�wienia, kt�re przygotowa� kucharz i s� gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania = new ArrayList<Zamowienie>();
	
	
	/** Produkty zam�wienia tymczasowego */
	private List<Produkt> produktyZamowienia = new LinkedList<Produkt>();
	

	/** Wybrany identyfikator grupy produkt�w */
	private int idGrupy;
	

	/** Lista grup produkt�w */
	private List<Grupa> grupy = new LinkedList<Grupa>();
	

	/** Wybrana nazwa grupy produkt�w */
	private String nazwaGrupy;
	
	/** Lista produkt�w wybranej grupy */
	private List<Produkt> produktyGrupy = new LinkedList<Produkt>();
	
	
	public Zamowienie getTymczasowe() {
		return tymczasowe;
	}

	public void setTymczasowe(Zamowienie tymczasowe) {
		this.tymczasowe = tymczasowe;
	}
	

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	
	public List<Zamowienie> getNoweZamowienia() {
		return noweZamowienia;
	}


	public void setNoweZamowienia(List<Zamowienie> noweZamowienia) {
		this.noweZamowienia = noweZamowienia;
	}

	public List<Zamowienie> getZamowieniaDoPodania() {
		return zamowieniaDoPodania;
	}

	public void setZamowieniaDoPodania(List<Zamowienie> zamowieniaDoPodania) {
		this.zamowieniaDoPodania = zamowieniaDoPodania;
	}

	public int getIloscNowychZamowien() {
		pobierzNoweZamowienia();
		return noweZamowienia.size();
	}

	public int getIloscZamowienDoPodania() {
		pobierzZamowieniaDoPodania();
		return zamowieniaDoPodania.size();
	}

	public List<Produkt> getProduktyZamowienia() {
		return produktyZamowienia;
	}

	public void setProduktyZamowienia(List<Produkt> produktyZamowienia) {
		this.produktyZamowienia = produktyZamowienia;
	}
	
	public int getIloscProduktowZamowienia(){
		return produktyZamowienia.size();
	}
	
	public int getIdGrupy() {
		return idGrupy;
	}

	public void setIdGrupy(int idGrupy) {
		this.idGrupy = idGrupy;
	}
	
	public List<Produkt> getProduktyGrupy() {
		pobierzProdukty(idGrupy);
		return produktyGrupy;
	}

	public List<Produkt> produktyGrupy(int idGrupy) {
		pobierzProdukty(idGrupy);
		return produktyGrupy;
	}
	
	public void setProduktyGrupy(List<Produkt> produktyGrupy) {
		this.produktyGrupy = produktyGrupy;
	}

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
	
	
	/**
	 * Pobiera nowe zam�wienia z bazy danych w celu wype�nienia listy.
	 */
	private void pobierzNoweZamowienia(){
			noweZamowienia = ZamowienieDao.pobierzZamowione(uzytkownik.getIdentyfikator());	
			for (Zamowienie zamowienie : noweZamowienia) {
				List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
				zamowienie.setProdukty(produkty);
			}	
	}

	/**
	 * Pobiera zam�wienia do podania z bazy danych w celu wype�nienia listy.
	 */
	private void pobierzZamowieniaDoPodania(){
		zamowieniaDoPodania = ZamowienieDao.pobierzDlaKlienta(uzytkownik.getIdentyfikator());
		for (Zamowienie zamowienie : zamowieniaDoPodania) {
			List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}		
	}


	/**
	 * Tworzy puste zam�wienie ze statusem <b>Tymczasowe</b>
	 */
	public String dodajZamowienie() {
		tymczasowe = new Zamowienie();
		produktyZamowienia = new LinkedList<Produkt>();
		tymczasowe.setIdKelnera(uzytkownik.getIdentyfikator());
		tymczasowe.setIdStatus(1); // Status: Tymczasowe
		pobierzNazwyGrupProduktow();
		return "zamowienie?faces-redirect=true";
	}

	
	/**
	 * Pobiera z bazy identyfikatory i nazwy grup produkt�w umieszcza je w obiektach grup
	 * i obiekty umieszcza na li�cie grup produkt�w.
	 */
	private void pobierzNazwyGrupProduktow(){
		grupy = GrupaDao.pobierzWszystkieGrupy();
	}
	
	
	/**
	 * Pobiera z bazy wszystkie informacje o produktach danej grupy  i umieszcza obiekty na li�cie produkt�w.
	 */
	private void pobierzProdukty(int idGrupy){
		produktyGrupy = ProduktDao.pobierzWszystkieProdukty(idGrupy);
	}
	
	/**
	 * Uswa zam�wienie z listy zam�wie�
	 */
	public String usunZamowienie(int idZamowienia, String przyczyna){
		ZamowienieDao.anulujZamowienie(idZamowienia, przyczyna);
		ZamowienieDao.zmienStatusNaAnulowane(idZamowienia);
		FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacja",
							"Zam�wienie zosta�o usuni�te"));	
		return null;
	}

	
	/**
	 * Dodaje do listy zam�wie� oczekuj�cych na pobranie przez kucharza
	 * Zmienia status zam�wienia na <b>W kuchni</b>
	 */
	public String doKuchni(int idZamowienia){
		ZamowienieDao.zmienStatusZamowienia(idZamowienia, 3);
		return null;
	}
	
	/**
	 * Usuwa z listy zam�wie� do podania
	 * Zmienia status zam�wienia na <b>Zaserwowane</b>
	 */
	public String doKlienta(int idZamowienia){
		ZamowienieDao.zmienStatusZamowienia(idZamowienia, 6);
		return null;
	}
	
	/**
	 * Dodaje produkt do tymczasowego zam�wienia. Je�eli produkt jest na li�cie zam�wienia to 
	 * zostaje zwi�kszona ilo�� sztuk w zam�wieniu
	 */
	public String dodajProdukt(String id) {
		boolean jestNaLiscie = false;
		for (Produkt produktGrupy : produktyGrupy) {
			if (produktGrupy.getId() == Integer.valueOf(id)) {
				if (produktyZamowienia.isEmpty()) {
					produktGrupy.inkrementujIloscZamawianych();
					produktyZamowienia.add(produktGrupy);
					jestNaLiscie = true;
				} else {
					for (Produkt produktZamowienia : produktyZamowienia) {
						if (produktZamowienia.getId() == produktGrupy.getId()) {
							jestNaLiscie = true;
							produktZamowienia.inkrementujIloscZamawianych();
						}
					}
				}
				if(!jestNaLiscie){
					produktGrupy.inkrementujIloscZamawianych();
					produktyZamowienia.add(produktGrupy);
				}
				
			}
		}

		return null;
	}
	
	/**
	 * Dekrementuje ilo�� produktu na zam�wieniu. W przypadku gdy ilo�� produktu jest r�wna 1 to 
	 * usuwa produkt z tymczasowego zam�wienia.
	 */
	public String zmniejszIlo��Produktu(String id){
		for (Produkt produkt : produktyZamowienia) {
			if(produkt.getId() == Integer.valueOf(id)){
				produkt.dekrementujIloscZamawianych();
				if(produkt.getIloscZamawianych() == 0)
					produktyZamowienia.remove(produkt);
				break;
			}
		}
		return null;
	}
	
	/**
	 * Inkrementuje ilo�� produktu na zam�wieniu. 
	 */
	public String zwiekszIlo��Produktu(String id){
		for (Produkt produkt : produktyZamowienia) {
			if(produkt.getId() == Integer.valueOf(id)){
				produkt.inkrementujIloscZamawianych();
				break;
			}
		}
		return null;
	}
	
	/**
	 * Oblicza sum� warto�ci pozycji zam�wienia
	 * @param produktyZamowienia lista produkt�w zam�wienia
	 * @return suma w formacie waluty PLN
	 */
	public String sumaZamowienia(List<Produkt> produktyZamowienia){
		BigDecimal suma = new BigDecimal(0);
		for(Produkt produkt : produktyZamowienia){
			suma = suma.add(produkt.getCena().multiply(BigDecimal.valueOf(produkt.getIloscZamawianych())));
		}
		NumberFormat costFormat = NumberFormat.getCurrencyInstance();
		costFormat.setMinimumFractionDigits(2);
		return costFormat.format(suma);
		
	}
	
	/**
	 * Zapisuje tymczasowe zam�wienie w bazie danych nadaj�c mu status <b>Zam�wiony</b>.
	 */
	public String zapisz() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (produktyZamowienia.size() == 0) {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d",
							"Nie mo�esz zapisa� pustego zam�wienia - dodaj produkty do zam�wienia."));
		}
		if (context.getMessageList().size() > 0) {
			return (null);
		} else {
			tymczasowe.setProdukty(produktyZamowienia);
			tymczasowe.setDataPrzyjecia(new Date());
			tymczasowe.setIdStatus(2);
			int idZamowienia = ZamowienieDao.pobierzIdOstatniegoZamowienia() + 1;
			tymczasowe.setIdZamowienia(idZamowienia);
			ZamowienieDao.dodajZamowione(tymczasowe);
			for (Produkt produkt : produktyZamowienia) {
				PozycjaDao.dodajPozycjeZamowienia(tymczasowe.getIdZamowienia(),
						produkt.getId(), produkt.getIloscZamawianych());
			}
			return "kelner?faces-redirect=true";
		}
	}
	
	
	/**
	 * Przeprowadza walidacje pola Numer stolika
	 * @param context
	 * @param componentToValidate
	 * @param value
	 * @throws ValidatorException
	 */
	public void sprawdzNrStolika(FacesContext context, UIComponent componentToValidate, Object value)
		throws ValidatorException {
		try{
			Integer.valueOf((String) value);
		} catch (NumberFormatException e){
			FacesMessage message =
			new FacesMessage("Numer stolika musi by� liczb�");
			throw new ValidatorException(message);
		}
	}
	
	/**
	 * Anuluje sk�adanie zam�wienia
	 */
	public String anuluj(){
		produktyZamowienia = null;
		tymczasowe = null;
		return "kelner?faces-redirect=true";		
	}	
}
