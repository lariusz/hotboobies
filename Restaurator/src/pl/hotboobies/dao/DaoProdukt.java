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

import pl.hotboobies.Produkt;

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
	 * Pobiera wszystkie kolumny dla produktów z grupy
	 * @param grupa produktów dla której maja zostaæ zwrócone wyniki
	 * @return kolekcjê obiektów Zamowienie
	 */
	public List<Produkt> pobierzWszystkieKolumny(int grupa) {
		List<Produkt> produktyGrupy = new ArrayList<Produkt>();
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
	 * Pobiera wszystkie kolumny pozycji i produktów dla konkretnego zamówienia
	 * @param identyfikator zamówienia dla którego maja zostaæ zwrócone wyniki
	 * @return kolekcjê obiektów Zamowienie
	 */
	public List<Produkt> pobierzPozycjeZamowienia(int idZamowienia) {
		List<Produkt> produktyGrupy = new ArrayList<Produkt>();
		try {
			otworzPolaczenie();
			ResultSet wszystkieProdukty = st.executeQuery("SELECT * FROM POZYCJA P, PRODUKT PR WHERE P.ID_PRODUKT=PR.ID_PRODUKT AND P.ID_ZAMOWIENIE = "+ idZamowienia);
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
	 * @param id produkty dla którego ma zostaæ zwrócony wynik
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public ResultSet pobierzProdukt(int id) throws SQLException{
		otworzPolaczenie();
		return st.executeQuery("SELECT * FROM produkt WHERE id_produkt = " + id);
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
		if(ds == null)
			ds = utworzZrodloDanych();
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
