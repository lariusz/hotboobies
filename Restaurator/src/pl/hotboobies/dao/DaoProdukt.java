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
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Produkt
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoProdukt {
	
	/** Obiekt ¿ród³ danych*/	
	private DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	private Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private Statement st;
		
	/**
	 * Pobiera wszystkie nazwy dla wszystkich produktów z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieNazwy() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT nazwa FROM produkt");
	}
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich produktów z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieKolumny() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM PRODUKT");
	}
	
	/**
	 * Pobiera wszystkie kolumny dla produktów z grupy
	 * @param grupa produktów dla której maja zostaæ zwrócone wyniki
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieKolumny(int grupa) throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM produkt WHERE id_grupa = " + grupa);
	}

	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
	 */
	private DataSource utworzZrodloDanych(){
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
	 * @throws SQLException
	 */
	private void otworzPolaczenie() throws SQLException {
		conn = ds.getConnection();
		st = conn.createStatement();
	}
	
	
	/**
	 * Zamyka po³¹czenie z baz¹ danych
	 * @throws SQLException
	 */
	public void zamknijPolaczenie() throws SQLException{
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	}

}
