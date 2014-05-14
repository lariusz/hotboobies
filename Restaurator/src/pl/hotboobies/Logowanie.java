package pl.hotboobies;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean
@RequestScoped
public class Logowanie implements Serializable  {

	private static final long serialVersionUID = 1L;

	Uzytkownik zalogowany = new Uzytkownik();


	public Uzytkownik getZalogowany() {
		return zalogowany;
	}


	public void setZalogowany(Uzytkownik zalogowany) {
		this.zalogowany = zalogowany;
	}
	

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


	public String zaloguj() {
		Context initContext;
		Connection conn = null;
		Statement st = null;
		String wynik = "blad";
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/oracle");
			conn = ds.getConnection();
			st = conn.createStatement();
			ResultSet uzytkownik = st
					.executeQuery("SELECT login, haslo,  blokuj, id_rola, imie, "
							+ "nazwisko, mail, telefon FROM UZYTKOWNIK");
			while(uzytkownik.next()){
				if(zalogowany.getLogin().toLowerCase().equals(uzytkownik.getString("login").toLowerCase()) && 
					zalogowany.getHaslo().equals(uzytkownik.getString("haslo"))){
					if(uzytkownik.getInt("blokuj") != 1){
						zalogowany.setImie(uzytkownik.getString("imie"));
						zalogowany.setNazwisko(uzytkownik.getString("nazwisko"));
						zalogowany.setMail(uzytkownik.getString("mail"));
						zalogowany.setTelefon(uzytkownik.getInt("telefon"));
						int rola = uzytkownik.getInt("id_rola");
						switch(rola){
							case 1: wynik = "kierownik"; break;
							case 2: wynik = "kelner"; break;
							case 3: wynik = "kucharz"; break;
						}
					} else {
						wynik = "zablokowany";
					}
					
				}
			}

			uzytkownik.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return wynik;

		
	}



}
