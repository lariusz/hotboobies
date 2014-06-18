package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Pozycja
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoPozycja {

	
	/** Obiekt ¿ród³ danych*/	
	private static DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	private static Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private static Statement st;
		
	/**
	 * Pobiera wszystkie nazwy dla wszystkich grup produktów z bazy danych
	 * @return zbiór wyników
	 */
	public static void dodajPozycjeZamowienia(int idZamowienia, int idProdukt,	int ilosc) {
		otworzPolaczenie();
		try {
			int id = pobierzIdOstatniejPozycji();
			st.executeUpdate("INSERT INTO pozycja "
					+ "(id_pozycja, id_zamowienie, id_produkt, ilosc) VALUES('"
					+ (id + 1) + "', '" + idZamowienia + "', '" + idProdukt
					+ "', '" + ilosc + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			zamknijPolaczenie();
		}
	}
	
	
	/**
	 * Usuwa wszystkie pozycje z danego zamówienia
	 * @param idZamowienia
	 */
	public static void usunPozycje(int idZamowienia) {
		otworzPolaczenie();
		try {
			st.executeUpdate("DELETE FROM pozycja WHERE id_zamowienie = " + idZamowienia);
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			zamknijPolaczenie();
		}
	}
	
	
	/**
	 * Pobiera identyfikator ostatniej pozycji
	 * @return id ostatniej pozycji
	 * @throws SQLException
	 */
	private static int pobierzIdOstatniejPozycji() throws SQLException{
		ResultSet max = st.executeQuery("SELECT max(id_pozycja) FROM pozycja");
		max.next();
		return max.getInt(1);
	}
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
	 */
	private static DataSource utworzZrodloDanych(){
		try {
			Context initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}

	
	/**
	 * Otwiera po³¹czenie z baz¹ danych
	 */
	private static void otworzPolaczenie(){
		if (ds == null)
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
	private static void zamknijPolaczenie(){
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
