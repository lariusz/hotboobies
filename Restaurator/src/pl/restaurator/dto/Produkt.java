package pl.restaurator.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;

public class Produkt {	
	
	private int id;
	private String nazwa;
	private String grupa;
	private int idGrupa;
	private int ilosc;
	private int iloscZamawianych;
	private BigDecimal cena;
	private Date czasAktualizacji;
	private int czasWykonania;
	private boolean aktywny;
	private int sumaZamawianych;


	public Produkt(int id, String nazwa, BigDecimal cena, int ilosc,
			 int czasWykonania, boolean aktywny) {
		this.id = id;
		this.nazwa = nazwa;
		this.cena = cena;
		this.ilosc = ilosc;		
		this.czasWykonania = czasWykonania;
		this.aktywny = aktywny;
	}
	
	public Produkt(String nazwa, BigDecimal cena, int sumaZamawianych) {
		this.nazwa = nazwa;
		this.cena = cena;
		this.sumaZamawianych = sumaZamawianych;

	}
	
	public Produkt(int id, String nazwa, BigDecimal cena, int iloscZamawianych,
			 int czasWykonania) {
		this.id = id;
		this.nazwa = nazwa;
		this.cena = cena;
		this.iloscZamawianych = iloscZamawianych;		
		this.czasWykonania = czasWykonania;
	}
	
	
	public Produkt(int id, String nazwa, BigDecimal cena, int ilosc,
			 int czasWykonania, boolean aktywny, int idGrupa) {
		this.id = id;
		this.nazwa = nazwa;
		this.cena = cena;
		this.ilosc = ilosc;		
		this.czasWykonania = czasWykonania;
		this.aktywny = aktywny;
		this.idGrupa=idGrupa;
	}
	
	public boolean inkrementujIloscZamawianych(){
		iloscZamawianych++;
		return true;
	}
	
	public boolean inkrementujIlosc(){
		if(ilosc == 0){
			aktywny = true;
		}
		ilosc++;
		return true;
	}
	
	public boolean dekrementujIloscZamawianych(){
		if(iloscZamawianych>0){
		iloscZamawianych--;
		return true;
		}
		else return false;
	}
	
	public boolean dekrementujIlosc(){
		if(ilosc == 1){
			ilosc--;
			aktywny = false;
			return true;
		}
		else if(ilosc > 1){
			ilosc--;
			return true;
		}
		else return false;
	}

	
	public int getSumaZamawianych() {
		return sumaZamawianych;
	}

	public void setSumaZamawianych(int sumaZamawianych) {
		this.sumaZamawianych = sumaZamawianych;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getGrupa() {
		return grupa;
	}
	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}
	public int getIlosc() {
		return ilosc;
	}
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	public int getIloscZamawianych() {
		return iloscZamawianych;
	}

	public void setIloscZamawianych(int iloscZamawianych) {
		this.iloscZamawianych = iloscZamawianych;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Date getCzasAktualizacji() {
		return czasAktualizacji;
	}
	public void setCzasAktualizacji(Date czasAktualizacji) {
		this.czasAktualizacji = czasAktualizacji;
	}
	public boolean isAktywny() {
		return aktywny;
	}
	public void setAktywny(boolean aktywny) {
		this.aktywny = aktywny;
	}
	public int getCzasWykonania() {
		return czasWykonania;
	}
	public void setCzasWykonania(int czasWykonania) {
		this.czasWykonania = czasWykonania;
	}
		
	public int getIdGrupa() {
		return idGrupa;
	}

	public void setIdGrupa(int idGrupa) {
		this.idGrupa = idGrupa;
	}

}
