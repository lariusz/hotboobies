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
 * Klasa udost�pniaj�ca metody dost�pu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a> *
 */
public class DaoUzytkownik {
	
	private Connection conn;
	private Statement st;
	private Context initContext;
	private DataSource ds;
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich u�ytkownik�w z bazy danych
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkich() throws SQLException{				
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM uzytkownik");

	}
	
	/**
	 * Wyszukuje w JNDI po��czenie do bazy danych
	 * @return obiekt �r�d�a danych
	 */
	private DataSource utworzZrodloDanych(){
		DataSource ds = null;
		try {
			initContext = new InitialContext();
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
	public void zamknijPolaczenie() throws SQLException{
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	}

}
