package pl.hotboobies;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class Uzytkownik implements Serializable{
	
	private static final long serialVersionUID = 6938394833521667060L;
	
	private String login = "";
	private String haslo = "";
	private String imie;
	private String nazwisko;
	private String mail;
	private String telefon;
	boolean czyZablokowany;
	
	public Uzytkownik(){
		super();
	}
	
	public Uzytkownik(String login, String haslo, String imie, String nazwisko,
			String mail, String telefon, boolean czyZablokowany) {
		super();
		this.login = login;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.mail = mail;
		this.telefon = telefon;
		this.czyZablokowany = czyZablokowany;
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
	public boolean isCzyZablokowany() {
		return czyZablokowany;
	}
	public void setCzyZablokowany(boolean czyZablokowany) {
		this.czyZablokowany = czyZablokowany;
	}
}
