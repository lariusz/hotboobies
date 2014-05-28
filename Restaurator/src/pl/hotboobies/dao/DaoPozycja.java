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
 * Klasa udost�pniaj�ca metody dost�pu do bazy danych dla obiektu Pozycja
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a> *
 */
public class DaoPozycja {

	
	/** Obiekt �r�d� danych*/	
	private DataSource ds;
	
	/** Obiekt po��czenia z baza danych */	
	private Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private Statement st;
		
	/**
	 * Pobiera wszystkie nazwy dla wszystkich grup produkt�w z bazy danych
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public void dodajPozycjeZamowienia(int idZamowienia, int idProdukt,	int ilosc) {
		if (ds == null)
			ds = utworzZrodloDanych();
		try {
			otworzPolaczenie();
			int id = pobierzIdOstatniejPozycji();
			st.executeUpdate("INSERT INTO pozycja "
					+ "(id_pozycja, id_zamowienie, id_produkt, ilosc) VALUES('"
					+ (id + 1) + "', '" + idZamowienia + "', '" + idProdukt
					+ "', '" + ilosc + "')");
			zamknijPolaczenie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private int pobierzIdOstatniejPozycji() throws SQLException{
		
		ResultSet max = st.executeQuery("SELECT max(id_pozycja) FROM pozycja");
		max.next();
		return max.getInt(1);
	}
	
	
	// Pobiera wszystkie pozycje 
	public ResultSet pobierzWszystkie() throws SQLException{				
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM pozycja");

	}
	
	/**
	 * Wyszukuje w JNDI po��czenie do bazy danych
	 * @return obiekt �r�d�a danych
	 */
	private DataSource utworzZrodloDanych(){
		try {
			Context initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	/**
	 * Otwiera po��czenie z baz� danych
	 * @throws SQLException
	 */
	private void otworzPolaczenie() throws SQLException {
		conn = ds.getConnection();
		st = conn.createStatement();
	}
	
	
	/**
	 * Zamyka po��czenie z baz� danych
	 * @throws SQLException
	 */
	private void zamknijPolaczenie() throws SQLException{
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	}

}