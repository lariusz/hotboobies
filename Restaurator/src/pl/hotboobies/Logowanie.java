package pl.hotboobies;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.hotboobies.dao.DaoUzytkownik;

@ManagedBean
@SessionScoped
public class Logowanie implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Uzytkownik zalogowany = new Uzytkownik();
	private String komunikat = "";
	

	public String getKomunikat() {
		return komunikat;
	}


	public void setKomunikat(String komunikat) {
		this.komunikat = komunikat;
	}


	public Uzytkownik getZalogowany() {
		return zalogowany;
	}


	public void setZalogowany(Uzytkownik zalogowany) {
		this.zalogowany = zalogowany;
	}
	

	/**
	 * Waliduje czy wprowadzony login ma wi�cej ni� 3 znaki.
	 * @return <code>null</code> je�eli walidacja nie powiod�a si� co powoduje prze�adowanie formularza
	 * 			lub to co zwr�ci metoda {@link #zaloguj()}
	 */
	public String walidujPola() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (zalogowany.getLogin().length() < 3) {
			context.addMessage("loginForm:login", 
			new FacesMessage("Login musi posiadac co najmniej 3 znaki"));
		}
		if (context.getMessageList().size() > 0) {
			return(null);
			} else {
				return zaloguj();
			}
	}


	/**
	 * Sprawdza czy u�ytkownik kt�ry pr�buje si� zalogowa� istnieje w bazie danych. 
	 * Metoda weryfikuje r�wnie� czy loguj�cy nie jest zablokowany. Je�eli obydwie weryfikacje
	 * przesz�y pozytywnie to przekierowuje na strone zgodnie z nadan� rol�
	 * @return <code>null</code> je�eli nie mo�na by�o dopasowa� u�ytkownika z bazy lub u�ytkownik jest zablokowany
	 * 			<br />w przeciwnym razie zwraca nazw� strony na kt�ra ma zostac przekierowany
	 */
	public String zaloguj() {
		String wynik = null;
		DaoUzytkownik dao = new DaoUzytkownik();
		ResultSet uzytkownik = null;
		try{
			uzytkownik = dao.pobierzWszystkich();
			while(uzytkownik.next()){
				if(zalogowany.getLogin().toLowerCase().equals(uzytkownik.getString("login").toLowerCase()) && 
					zalogowany.getHaslo().equals(uzytkownik.getString("haslo"))){
					if(uzytkownik.getInt("blokuj") != 1){
						zalogowany.setImie(uzytkownik.getString("imie"));
						zalogowany.setNazwisko(uzytkownik.getString("nazwisko"));
						zalogowany.setMail(uzytkownik.getString("mail"));
						zalogowany.setTelefon(uzytkownik.getInt("telefon"));
						zalogowany.setIdentyfikator(String.valueOf(uzytkownik.getInt("id_uzytkownik")));
						int rola = uzytkownik.getInt("id_rola");
						switch(rola){
							case 1: wynik = "kierownik"; break;
							case 2: wynik = "kelner" ; break;
							case 3: wynik = "kucharz"; break;
						}
					} else {
						komunikat = "U�ytkownik jest zablokowany. Skontaktuj si� z administratorem";
					}
					break;
				} else{
					komunikat = "Wpisa�e� niepoprawny login i/lub has�o";
				}
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					uzytkownik.close();
					dao.zamknijPolaczenie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			return wynik;
		
	}



}
