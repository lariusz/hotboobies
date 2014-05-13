package pl.hotboobies;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.primefaces.event.SelectEvent;

@ManagedBean
public class Logowanie implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String login = "";
	private String haslo = "";

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
					.executeQuery("SELECT login, haslo FROM UZYTKOWNIK");

			while(uzytkownik.next()){
				if(login.equals(uzytkownik.getString("login")) && haslo.equals(uzytkownik.getString("haslo"))){
					wynik = "kierownik";
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
