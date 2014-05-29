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

import pl.hotboobies.Grupa;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Grupa
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoGrupa {
	
	/** Obiekt ¿ród³ danych*/	
	private DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	private Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private Statement st;
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich grup produktów z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public List<Grupa> pobierzWszystkieKolumny() {
		if (ds == null)
			ds = utworzZrodloDanych();
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
	private void zamknijPolaczenie() throws SQLException{
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	}

}
