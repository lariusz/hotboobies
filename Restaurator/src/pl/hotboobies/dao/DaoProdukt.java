package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pl.hotboobies.Produkt;

/**
 * Klasa udost�pniaj�ca metody dost�pu do bazy danych dla obiektu Produkt
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a> *
 */
public class DaoProdukt {
	
	/** Obiekt �r�d� danych*/	
	private DataSource ds;
	
	/** Obiekt po��czenia z baza danych */	
	private Connection conn;
	
	/** Obiekt zapytania do bazy danych */	
	private Statement st;
		
	/**
	 * Pobiera wszystkie nazwy dla wszystkich produkt�w z bazy danych
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieNazwy() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT nazwa FROM produkt");
	}
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich produkt�w z bazy danych
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public ResultSet pobierzWszystkieKolumny() throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM PRODUKT");
	}
	
	/**
	 * Pobiera wszystkie kolumny dla produkt�w z grupy
	 * @param grupa produkt�w dla kt�rej maja zosta� zwr�cone wyniki
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public Collection<Produkt> pobierzWszystkieKolumny(int grupa) {
		if(ds == null)
			ds = utworzZrodloDanych();
		Collection<Produkt> produktyGrupy = new ArrayList<Produkt>();
		try {
			otworzPolaczenie();
			ResultSet wszystkieProdukty = st.executeQuery("SELECT * FROM produkt WHERE id_grupa = " + grupa);
				while(wszystkieProdukty.next()){
					produktyGrupy.add(new Produkt(wszystkieProdukty.getInt("id_produkt"),
							wszystkieProdukty.getString("nazwa"),
							wszystkieProdukty.getInt("ilosc"),
							wszystkieProdukty.getInt("czas_wykonania"),
							wszystkieProdukty.getInt("aktywny") == 1));
				}
				wszystkieProdukty.close();
				zamknijPolaczenie();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		

		
		return produktyGrupy;
	}
	
	/**
	 * Pobiera wszystkie produkt o podanym id
	 * @param id produkty dla kt�rego ma zosta� zwr�cony wynik
	 * @return zbi�r wynik�w
	 * @throws SQLException
	 */
	public ResultSet pobierzProdukt(int id) throws SQLException{
		if(ds == null)
			ds = utworzZrodloDanych();
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM produkt WHERE id_produkt = " + id);
	}

	/**
	 * Wyszukuje w JNDI po��czenie do bazy danych
	 * @return obiekt �r�d�a danych
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
