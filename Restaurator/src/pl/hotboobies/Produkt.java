package pl.hotboobies;

import java.util.Date;

import javax.faces.bean.ManagedBean;

public class Produkt {
	
	private String nazwa;
	private String grupa;
	private int ilosc;
	private Date czasAktualizacji;
	private boolean aktywny;
	private int czasWykonania;
	
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
