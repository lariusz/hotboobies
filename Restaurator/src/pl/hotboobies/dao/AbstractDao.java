package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class AbstractDao {

	/** Obiekt �r�d� danych*/	
	protected static DataSource ds;
	
	/** Obiekt po��czenia z baza danych */	
	protected static Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	protected static Statement st;
	
	
	/**
	 * Wyszukuje w JNDI po��czenie do bazy danych
	 * @return obiekt �r�d�a danych
	 */
	protected static DataSource utworzZrodloDanych(){
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
	 * Otwiera po��czenie z baz� danych
	 */
	protected static void otworzPolaczenie() {
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
	 * Zamyka po��czenie z baz� danych
	 */
	protected static void zamknijPolaczenie() {
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