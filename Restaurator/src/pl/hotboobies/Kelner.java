package pl.hotboobies;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.hotboobies.dao.DaoGrupa;
import pl.hotboobies.dao.DaoPozycja;
import pl.hotboobies.dao.DaoProdukt;
import pl.hotboobies.dao.DaoZamowienie;

/**
 * 	Kontroler dla czynnoœci wykonywanych przez Kelnera
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */
@ManagedBean
@SessionScoped
public class Kelner implements Serializable{
	
	private static final long serialVersionUID = -2043621321850425369L;

	/** Zamówienie które jest realizowane przez kelnera */
	private Zamowienie tymczasowe;
	
	/** Zamówienia, które utworzy³ kelner w celu przekazania do kuchni */
	private List<Zamowienie> noweZamowienia = new LinkedList<Zamowienie>();
	
	public List<Zamowienie> getNoweZamowienia() {
		noweZamowienia.clear();
		DaoZamowienie daoZamowienie = new DaoZamowienie();
		ArrayList<Zamowienie> noweZamowienia =  (ArrayList<Zamowienie>) daoZamowienie.pobierzZamowione();	
		return noweZamowienia;
	}

	public void setNoweZamowienia(List<Zamowienie> noweZamowienia) {
		this.noweZamowienia = noweZamowienia;
	}

	/** Zamówienia, które przygotowa³ kucharz i s¹ gotowe do wydania */
	private List<Zamowienie> zamowieniaDoPodania = new LinkedList<Zamowienie>();
	

	public List<Zamowienie> getZamowieniaDoPodania() {
		return zamowieniaDoPodania;
	}

	public void setZamowieniaDoPodania(List<Zamowienie> zamowieniaDoPodania) {
		this.zamowieniaDoPodania = zamowieniaDoPodania;
	}

	public int getIloscNowychZamowien() {
		getNoweZamowienia();
		return noweZamowienia.size();
	}

	public int getIloscZamowienDoPodania() {
		return zamowieniaDoPodania.size();
	}
	
	/** Produkty zamówienia tymczasowego */
	private List<Produkt> produktyZamowienia;
	
	public List<Produkt> getProduktyZamowienia() {
		return produktyZamowienia;
	}

	public void setProduktyZamowienia(List<Produkt> produktyZamowienia) {
		this.produktyZamowienia = produktyZamowienia;
	}

	/** Wybrany identyfikator grupy produktów */
	private int idGrupy;
	
	public int getIdGrupy() {
		return idGrupy;
	}

	public void setIdGrupy(int idGrupy) {
		this.idGrupy = idGrupy;
	}

	/** Wybrana nazwa grupy produktów */
	private String nazwaGrupy;
	
	/** Lista produktów wybranej grupy */
	private List<Produkt> produktyGrupy = new LinkedList<Produkt>();
	
	public List<Produkt> getProduktyGrupy() {
		pobierzProdukty();
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
		System.out.println("Ustawi³em nazwê grupy na " + nazwaGrupy);
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
		tymczasowe = new Zamowienie();
		produktyZamowienia = new LinkedList<Produkt>();
		tymczasowe.setIdKelnera(uzytkownik.getIdentyfikator());
		tymczasowe.setIdStatus(1); // Status: Tymczasowe
		tymczasowe.setIdZamowienia(new DaoZamowienie().pobierzIdOstatniegoZamowienia()+1);
		pobierzNazwyGrupProduktow();
		return "zamowienie";
	}
	
	public String przejdzDoZamowien(){
		return "zamowienie";
	} 
	
	/**
	 * Pobiera z bazy identyfikatory i nazwy grup produktów umieszcza je w obiektach grup
	 * i obiekty umieszcza na liœcie grup produktów. Lista jest czyszczona na pocz¹tku,
	 * poniewa¿ czynnoœæ bedzie powtarzana przy sk³adaniu ka¿dego zamówienia.
	 */
	private void pobierzNazwyGrupProduktow(){
		grupy.clear();
		DaoGrupa daoGrupa = new DaoGrupa();
		grupy = (List<Grupa>) daoGrupa.pobierzWszystkieKolumny();
	}
	
	
	/**
	 * Pobiera z bazy wszystkie informacje o produktach danej grupy  i umieszcza obiekty na liœcie produktów.
	 */
	private void pobierzProdukty(){
		produktyGrupy.clear();
		DaoProdukt daoProdukt = new DaoProdukt();
		produktyGrupy = (List<Produkt>) daoProdukt.pobierzWszystkieKolumny(idGrupy);
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
	 * Dodaje produkt do tymczasowego zamówienia. Je¿eli produkt jest na liœcie zamówienia to 
	 * zostaje zwiêkszona iloœæ sztuk w zamówieniu
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
	 * Usuwa produkt z tymczasowego zamówienia. W przypadku gdy iloœæ produktu jest wiêksza od 1 to 
	 * jest pomniejszana
	 */
	public String usunProdukt(String id){
		for (Produkt produkt : produktyZamowienia) {
			if(produkt.getId() == Integer.valueOf(id)){
				produkt.dekrementujIloscZamawianych();
				if(produkt.getIloscZamawianych() == 0)
					produktyZamowienia.remove(produkt);
			}
		}
		return null;
	}
	
	/**
	 * Zapisuje tymczasowe zamówienie w bazie danych nadaj¹c mu status <b>Zamówiony</b>.
	 */
	public String zapisz(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (produktyZamowienia.size() == 0) {
			context.addMessage("zamowienieForm:zamowienieMessage", new FacesMessage(
					"Nie mo¿esz zapisaæ pustego zamówienia"));
		}
		if (context.getMessageList().size() > 0) {
			return (null);
		} else {
			tymczasowe.setProdukty(produktyZamowienia);
			tymczasowe.setDataPrzyjecia(new Date());
			tymczasowe.setIdStatus(2);
			DaoZamowienie daoZamowienie = new DaoZamowienie();
			DaoPozycja daoPozycja = new DaoPozycja();			
			daoZamowienie.dodajZamowione(tymczasowe);				
			for(Produkt produkt : produktyZamowienia){			
				daoPozycja.dodajPozycjeZamowienia(tymczasowe.getIdZamowienia(), produkt.getId(), produkt.getIloscZamawianych());
			}					
			return "kelner";
		}
	}
	
	/**
	 * Anuluje sk³adanie zamówienia, dodaj¹c do bazy informacje o przyczynie anulowania.
	 */
	public String anuluj(){
		produktyZamowienia = null;
		tymczasowe = null;
		return "kelner";		
	}	
}
