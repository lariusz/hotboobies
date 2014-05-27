package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pl.hotboobies.Zamowienie;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class DaoZamowienie {
	
	private Connection conn;
	private Statement st;
	private Context initContext;
	private DataSource ds;
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich u¿ytkowników z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkich() throws SQLException{				
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM zamowienie");

	}
	
	public ResultSet pobierzZamowione() throws SQLException {
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM zamowienie "
				+ "INNER JOIN status ON zamowienie.ID_STATUS=status.ID_STATUS "
				+ "WHERE zamowienie.id_status = 2");		
	}
	
	public int pobierzIdOstatniegoZamowienia() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		ResultSet max = st.executeQuery("SELECT max(id_zamowienie) FROM zamowienie");
		max.next();
		int idOstatniego = max.getInt(1);
		max.close();
		zamknijPolaczenie();
		return idOstatniego;
		
	}
	
	
	public void dodajZamowione(Zamowienie tymczasowe) throws SQLException {
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		st.executeUpdate("INSERT INTO zamowienie "
				+ "(id_zamowienie, id_status, id_uzytkownik, data_przyjecia) VALUES('"
				+ tymczasowe.getIdZamowienia() + "', '" + tymczasowe.getIdStatus() + "', '"
				+ tymczasowe.getIdKelnera() + "', " 
				+ "TO_DATE('" + new SimpleDateFormat("YYYY/MM/dd").format(tymczasowe.getDataPrzyjecia())
				+ "', 'YY, MM, DD'))");		
	}
	
	/**
	 * Wyszukuje w JNDI po³¹czenie do bazy danych
	 * @return obiekt ¿ród³a danych
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
