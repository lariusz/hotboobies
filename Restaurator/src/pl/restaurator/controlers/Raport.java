package pl.restaurator.controlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pl.restaurator.dao.ProduktDao;
import pl.restaurator.dao.UzytkownikDao;
import pl.restaurator.dao.ZamowienieDao;
import pl.restaurator.dto.Produkt;
import pl.restaurator.dto.Uzytkownik;
import pl.restaurator.dto.Zamowienie;

/**
 * 	Klasa pozwalaj¹ca na generowanie raportów.
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a>
 */

@ManagedBean
@RequestScoped
public class Raport implements Serializable{

	private static final long serialVersionUID = 4994624611961321842L;
	
	private Date dataOd;
	private Date dataDo;	
	private List<Uzytkownik> kelnerzy = new ArrayList<>();
	private List<Uzytkownik> kucharze = new ArrayList<>();
	private List<Zamowienie> zamowieniaKucharza = new ArrayList<>();
	private List<Zamowienie> zamowieniaKelnera = new ArrayList<>();
	private List<Zamowienie> zamowienia = new ArrayList<>();
	private List<Zamowienie> zamowieniaAnulowane = new ArrayList<>();
	private List<Produkt> zamowioneProdukty = new ArrayList<>();
	
	private int kucharzId;
	private int kelnerId;
	
	public int getKucharzId() {
		return kucharzId;
	}

	public void setKucharzId(int kucharzId) {
		this.kucharzId = kucharzId;
	}

	public int getKelnerId() {
		return kelnerId;
	}

	public void setKelnerId(int kelnerId) {
		this.kelnerId = kelnerId;
	}
	
	public List<Uzytkownik> getKelnerzy() {
		kelnerzy = UzytkownikDao.pobierzKelnerow();
		return kelnerzy;
	}

	public void setKelnerzy(List<Uzytkownik> kelnerzy) {
		this.kelnerzy = kelnerzy;
	}

	public List<Uzytkownik> getKucharze() {		
		kucharze = UzytkownikDao.pobierzKucharzy();
		return kucharze;
	}

	public void setKucharze(List<Uzytkownik> kucharze) {
		this.kucharze = kucharze;
	}
	
	public Date getDataOd() {
		return dataOd;
	}

	public void setDataOd(Date dataOd) {
		this.dataOd = dataOd;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public void setDataDo(Date dataDo) {
		this.dataDo = dataDo;
	}
	
	public List<Zamowienie> getZamowieniaKucharza() {
		return zamowieniaKucharza;
	}

	public void setZamowieniaKucharza(List<Zamowienie> zamowieniaKucharza) {
		this.zamowieniaKucharza = zamowieniaKucharza;
	}

	public List<Zamowienie> getZamowieniaKelnera() {
		return zamowieniaKelnera;
	}

	public void setZamowieniaKelnera(List<Zamowienie> zamowieniaKelnera) {
		this.zamowieniaKelnera = zamowieniaKelnera;
	}

	public List<Zamowienie> getZamowienia() {
		return zamowienia;
	}

	public void setZamowienia(List<Zamowienie> zamowienia) {
		this.zamowienia = zamowienia;
	}

	public List<Zamowienie> getZamowieniaAnulowane() {
		return zamowieniaAnulowane;
	}

	public void setZamowieniaAnulowane(List<Zamowienie> zamowieniaAnulowane) {
		this.zamowieniaAnulowane = zamowieniaAnulowane;
	}

	public List<Produkt> getZamowioneProdukty() {
		return zamowioneProdukty;
	}

	public void setZamowioneProdukty(List<Produkt> zamowioneProdukty) {
		this.zamowioneProdukty = zamowioneProdukty;
	}

	public int getIloscZamowienKucharza() {
		return zamowieniaKucharza.size();
	}
	
	public int getIloscZamowienKelnera() {
		return zamowieniaKelnera.size();
	}
	
	public int getIloscZamowien() {
		return zamowienia.size();
	}
	
	public int getIloscZamowienAnulowanych() {
		return zamowieniaAnulowane.size();
	}
	
	public int getIloscProduktow() {
		return zamowioneProdukty.size();
	}
	
	/**
	 * Generuje raport zamówieñ kucharza
	 */
	public void generujRaportKucharza(){
		zamowieniaKucharza = ZamowienieDao.pobierzZamowieniaKucharza(kucharzId, dataOd, dataDo);
		for (Zamowienie zamowienie : zamowieniaKucharza) {
			List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		if(zamowieniaKucharza.size() == 0)
			pokazKomunikatBrakWynikow();
	}

	/**
	 * Generuje raport zamówieñ kelnera
	 */
	public void generujRaportKelnera(){
		zamowieniaKelnera = ZamowienieDao.pobierzZamowieniaKelnera(kelnerId, dataOd, dataDo);
		for (Zamowienie zamowienie : zamowieniaKelnera) {
			List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		if(zamowieniaKelnera.size() == 0)
			pokazKomunikatBrakWynikow();
	}

	/**
	 * Generuje raport zamóionych produktów
	 */
	public void generujRaportZamowionychProduktow(){
		zamowioneProdukty = ZamowienieDao.pobierzZamowioneProdukty(dataOd, dataDo);
		if(zamowioneProdukty.size() == 0)
			pokazKomunikatBrakWynikow();
	}

	/**
	 * Generuje raport zamówieñ
	 */
	public void generujRaportZamowien(){
		zamowienia = ZamowienieDao.pobierzZamowienia(dataOd, dataDo);
		for (Zamowienie zamowienie : zamowienia) {
			List<Produkt> produkty = ProduktDao.pobierzPozycjeZamowienia(zamowienie.getIdZamowienia());
			zamowienie.setProdukty(produkty);
		}
		if(zamowienia.size() == 0)
			pokazKomunikatBrakWynikow();
	}
	
	/**
	 * Generuje raport anulowanych zamówieñ
	 */
	public void generujRaportAnulowanychZamowien(){
		zamowieniaAnulowane  = ZamowienieDao.pobierzAnulowaneZamowienia(dataOd, dataDo);
		if(zamowieniaAnulowane.size() == 0)
			pokazKomunikatBrakWynikow();
	}
	
	public void pokazKomunikatBrakWynikow(){
        FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zapytanie nie zwróci³o ¿adnych wyników", 
				"Zapytanie nie zwróci³o ¿adnych wyników"));
	}

}
