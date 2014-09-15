package pl.hotboobies;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pl.hotboobies.dao.UzytkownikDao;
import pl.hotboobies.dto.Uzytkownik;

@ManagedBean
@RequestScoped
public class Logowanie implements Serializable  {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{sesja.zalogowany}")
	private Uzytkownik logujacy;
	
	private String komunikat;
	
	public String getKomunikat() {
		return komunikat;
	}

	public void setKomunikat(String komunikat) {
		this.komunikat = komunikat;
	}


	public Uzytkownik getLogujacy() {
		return logujacy;
	}


	public void setLogujacy(Uzytkownik logujacy) {
		this.logujacy = logujacy;
	}
	

	/**
	 * Waliduje czy wprowadzony login ma wi�cej ni� 3 znaki.
	 * @return <code>null</code> je�eli walidacja nie powiod�a si� co powoduje prze�adowanie formularza
	 * 			lub to co zwr�ci metoda {@link #zaloguj()}
	 */
	public String walidujPola() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (logujacy.getLogin().length() < 3) {
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
		String login = logujacy.getLogin();
		String haslo = logujacy.getHaslo();
		Uzytkownik uzytkownik = null;
		boolean zalogowany = UzytkownikDao.czyJestUserWBazie(login, haslo);
		if(zalogowany){
			uzytkownik = UzytkownikDao.pobierzUzytkownika(login, haslo);
				if (!uzytkownik.isZablokowany()) {
					logujacy.setImie(uzytkownik.getImie());
					logujacy.setNazwisko(uzytkownik.getNazwisko());
					logujacy.setIdentyfikator(uzytkownik.getIdentyfikator());
					logujacy.setIdRola(uzytkownik.getIdRola());
					logujacy.setMail(uzytkownik.getMail());
					logujacy.setZablokowany(uzytkownik.isZablokowany());					
					switch (uzytkownik.getIdRola()) {
					case 1:
						wynik = "kierownik?faces-redirect=true";
						break;
					case 2:
						wynik = "kelner?faces-redirect=true";
						break;
					case 3:
						wynik = "kucharz?faces-redirect=true";
						break;
					}
				} else {
					komunikat = "U�ytkownik jest zablokowany. Skontaktuj si� z administratorem";
				}
			} else {
				komunikat = "Wpisa�e� niepoprawny login i/lub has�o";
			}
		return wynik;
	}
	
	public String wyloguj(){
		logujacy = new Uzytkownik();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index?faces-redirect=true";
	}

}
