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
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoGrupa {
	
	private Connection conn;
	private Statement st;
	private Context initContext;
	private DataSource ds;
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
	 */
	private DataSource utworzZrodloDanych(){
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	/**
	 * Pobiera wszystkie nazwy dla wszystkich grup produktów z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieNazwy() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		conn = ds.getConnection();
		st = conn.createStatement();
		return st.executeQuery("SELECT nazwa FROM GRUPA");	
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
