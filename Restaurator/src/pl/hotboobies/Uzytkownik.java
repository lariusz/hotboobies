package pl.hotboobies;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

public class Uzytkownik implements Serializable{
	
	private static final long serialVersionUID = 6938394833521667060L;	

	private String rola;
	private String identyfikator;
	private String login = "";
	private String haslo = "";
	private String imie;
	private String nazwisko;
	private String mail;
	private String telefon;
	private boolean zablokowany;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = String.valueOf(telefon);
	}
	public boolean isZablokowany() {
		return zablokowany;
	}
	public void setZablokowany(boolean zablokowany) {
		this.zablokowany = zablokowany;
	}
	
	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public String getIdentyfikator() {
		return identyfikator;
	}

	public void setIdentyfikator(String identyfikator) {
		this.identyfikator = identyfikator;
	}
}
