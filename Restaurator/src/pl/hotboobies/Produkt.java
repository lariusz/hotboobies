package pl.hotboobies;

import java.util.Date;

import javax.faces.bean.ManagedBean;

public class Produkt {	
	
	private int id;
	private String nazwa;
	private String grupa;
	private int ilosc;
	private int iloscZamawianych;
	private Date czasAktualizacji;
	private int czasWykonania;
	private boolean aktywny;

	
	public Produkt(int id, String nazwa, int ilosc,
			 int czasWykonania, boolean aktywny) {
		super();
		this.id = id;
		this.nazwa = nazwa;
		this.ilosc = ilosc;		
		this.czasWykonania = czasWykonania;
		this.aktywny = aktywny;
	}
	
	public boolean inkrementujIloscZamawianych(){
		iloscZamawianych++;
		return true;
	}
	
	public boolean dekrementujIloscZamawianych(){
		if(iloscZamawianych>0){
		iloscZamawianych--;
		return true;
		}
		else return false;
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

}
