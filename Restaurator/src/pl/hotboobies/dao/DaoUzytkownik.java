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

import pl.hotboobies.Uzytkownik;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoUzytkownik {
	
	/** Obiekt ¿ród³ danych*/	
	private DataSource ds;
	
	/** Obiekt po³¹czenia z baza danych */	
	private Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private Statement st;
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich u¿ytkowników z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public List<Uzytkownik> pobierzWszystkich() {				
		if(ds == null)
			ds = utworzZrodloDanych();
		List<Uzytkownik> wszyscy = new ArrayList<Uzytkownik>();
		try{
		otworzPolaczenie();
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
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
	 */
	private DataSource utworzZrodloDanych(){
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
