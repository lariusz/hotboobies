package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class AbstractDao {

	/** Obiekt ¿ród³ danych*/	
	protected static DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	protected static Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	protected static Statement st;
	
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
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
	 * Otwiera po³¹czenie z baz¹ danych
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
	 * Zamyka po³¹czenie z baz¹ danych
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