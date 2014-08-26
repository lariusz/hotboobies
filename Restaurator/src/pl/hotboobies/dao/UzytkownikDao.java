package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pl.hotboobies.Uzytkownik;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class UzytkownikDao {
	
	/** Obiekt ¿ród³ danych*/	
	private static DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	private static Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private static Statement st;
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich u¿ytkowników z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public static List<Uzytkownik> pobierzWszystkich() {				
		List<Uzytkownik> wszyscy = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik");
		while(uzytkownik.next()){
			wszyscy.add(new Uzytkownik(
					uzytkownik.getInt("id_uzytkownik"),
					uzytkownik.getString("login"),
					uzytkownik.getString("haslo"),
					uzytkownik.getInt("id_rola"),
					uzytkownik.getString("imie"),
					uzytkownik.getString("nazwisko"),
					uzytkownik.getString("mail"),
					uzytkownik.getString("telefon"),
					uzytkownik.getInt("blokuj") == 1));
		}
		uzytkownik.close();
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return wszyscy;
	}
	
	public static Uzytkownik pobierzUzytkownika(String login, String haslo) {				
		Uzytkownik user = null;
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE login = '" + login.toLowerCase() + "' AND haslo = '" + haslo + "'");
		while(uzytkownik.next()){
			user = new Uzytkownik(
					uzytkownik.getInt("id_uzytkownik"),
					uzytkownik.getString("login"),
					uzytkownik.getString("haslo"),
					uzytkownik.getInt("id_rola"),
					uzytkownik.getString("imie"),
					uzytkownik.getString("nazwisko"),
					uzytkownik.getString("mail"),
					uzytkownik.getString("telefon"),
					uzytkownik.getInt("blokuj") == 1);
		}
		uzytkownik.close();
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public static boolean czyJestUserWBazie(String login, String haslo) {				
		boolean jest = false;
		PreparedStatement ps = null;		
		otworzPolaczenie();
		try{
		ps = conn.prepareStatement("SELECT login, haslo FROM uzytkownik where login= ? and haslo= ?");
		ps.setString(1, login.toLowerCase());
		ps.setString(2, haslo);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			jest = true;
		}
		
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
				zamknijPolaczenie();
		}
		return jest;
	}
	
	public static List<Uzytkownik> pobierzKelnerow() {
		List<Uzytkownik> kelnerzy = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE id_rola = 2");
		while(uzytkownik.next()){
			kelnerzy.add(new Uzytkownik(
					uzytkownik.getInt("id_uzytkownik"),
					uzytkownik.getString("login"),
					uzytkownik.getString("haslo"),
					uzytkownik.getInt("id_rola"),
					uzytkownik.getString("imie"),
					uzytkownik.getString("nazwisko"),
					uzytkownik.getString("mail"),
					uzytkownik.getString("telefon"),
					uzytkownik.getInt("blokuj") == 1));
		}
		uzytkownik.close();
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return kelnerzy;
	}
	
	public static List<Uzytkownik> pobierzKucharzy() {
		List<Uzytkownik> kucharze = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE id_rola = 3");
		while(uzytkownik.next()){
			kucharze.add(new Uzytkownik(
					uzytkownik.getInt("id_uzytkownik"),
					uzytkownik.getString("login"),
					uzytkownik.getString("haslo"),
					uzytkownik.getInt("id_rola"),
					uzytkownik.getString("imie"),
					uzytkownik.getString("nazwisko"),
					uzytkownik.getString("mail"),
					uzytkownik.getString("telefon"),
					uzytkownik.getInt("blokuj") == 1));
		}
		uzytkownik.close();
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return kucharze;
	}
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
	 */
	private static DataSource utworzZrodloDanych(){
		DataSource ds = null;
		try {
			InitialContext initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	/**
	 * Otwiera po³¹czenie z baz¹ danych
	 */
	private static void otworzPolaczenie() {
		if(ds == null)
			ds = utworzZrodloDanych();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Zamyka po³¹czenie z baz¹ danych
	 */
	public static void zamknijPolaczenie() {
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



}
