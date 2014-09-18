package pl.restaurator.dto;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

public class Uzytkownik implements Serializable{
	
	private static final long serialVersionUID = 6938394833521667060L;	

	private int identyfikator;
	private String login = "";
	private String haslo = "";
	private int idRola;
	private String imie;
	private String nazwisko;
	private String mail;
	private String telefon;
	private boolean zablokowany;
	
	public Uzytkownik(){
		super();
	}
	
	public Uzytkownik(int identyfikator, String login, String haslo,
			int idRola, String imie, String nazwisko, String mail,
			String telefon, boolean zablokowany) {
		super();
		this.identyfikator = identyfikator;
		this.login = login;
		this.haslo = haslo;
		this.idRola = idRola;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.mail = mail;
		this.telefon = telefon;
		this.zablokowany = zablokowany;
	}
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
	public int getIdRola() {
		return idRola;
	}
	public void setIdRola(int idRola) {
		this.idRola = idRola;
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
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public boolean isZablokowany() {
		return zablokowany;
	}
	public void setZablokowany(boolean zablokowany) {
		this.zablokowany = zablokowany;
	}
	
	public int getIdentyfikator() {
		return identyfikator;
	}

	public void setIdentyfikator(int identyfikator) {
		this.identyfikator = identyfikator;
	}
}
