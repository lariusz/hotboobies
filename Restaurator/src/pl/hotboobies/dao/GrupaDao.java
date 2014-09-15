package pl.hotboobies.dao;

import java.sql.Connection;
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

import pl.hotboobies.dto.Grupa;

/**
 * Klasa udost�pniaj�ca metody dost�pu do bazy danych dla obiektu Grupa
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a> *
 */
public class GrupaDao {
	
	/** Obiekt �r�d� danych*/	
	private static DataSource ds;
	
	/** Obiekt po��czenia z baza danych */	
	private static Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private static Statement st;
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich grup produkt�w z bazy danych
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public static List<Grupa> pobierzWszystkieGrupy() {
		List<Grupa> grupy = new ArrayList<Grupa>();
		try {
			otworzPolaczenie();
			ResultSet wszystkieGrupy = st.executeQuery("SELECT * FROM grupa");
			while (wszystkieGrupy.next()) {
				grupy.add(new Grupa(wszystkieGrupy.getInt("id_grupa"),
						wszystkieGrupy.getString("nazwa")));
			}
			wszystkieGrupy.close();
			zamknijPolaczenie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grupy;
	}
	
	
	/**
	 * Wyszukuje w JNDI po��czenie do bazy danych
	 * @return obiekt �r�d�a danych
	 */
	private static DataSource utworzZrodloDanych(){
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
	private static void otworzPolaczenie() {
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
	 * Zamyka po��czenie z baz� danych
	 */
	private static void zamknijPolaczenie() {
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
