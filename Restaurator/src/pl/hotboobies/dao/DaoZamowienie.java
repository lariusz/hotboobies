package pl.hotboobies.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	 * Pobiera wszystkie kolumny dla wszystkich zamówieñ nieprzydzielonych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public Collection<Zamowienie> pobierzWszystkieNieprzydzielone(){				
		Collection<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		try{
		otworzPolaczenie();
		ResultSet wszysieZamowienia = st.executeQuery("SELECT * FROM zamowienie  WHERE ID_STATUS=3 ORDER BY ID_ZAMOWIENIE");
		while(wszysieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszysieZamowienia.getInt("id_zamowienie"), wszysieZamowienia.getInt("id_status"),
					wszysieZamowienia.getTimestamp("data_przyjecia"), wszysieZamowienia.getInt("nr_stolika"),
					wszysieZamowienia.getInt("id_uzytkownik"), wszysieZamowienia.getInt("kucharz_id")));
		}
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return wszystkie;
	}
	
	/**
	 * Pobiera wszystkie kolumny dla ostatniego nieprzydzielongo zamówienia (max 1 wynik)
	 * @return pojedynczy wynik
	 * @throws SQLException
	 */
	public Collection<Zamowienie> pobierzNajstarszeNieprzydzielone(){				
		Collection<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		try{
		otworzPolaczenie();
		ResultSet wszysieZamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS=3 AND ROWNUM <= 1 ORDER BY ID_ZAMOWIENIE");
		while(wszysieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszysieZamowienia.getInt("id_zamowienie"), wszysieZamowienia.getInt("id_status"),
					wszysieZamowienia.getTimestamp("data_przyjecia"), wszysieZamowienia.getInt("nr_stolika"),
					wszysieZamowienia.getInt("id_uzytkownik"), wszysieZamowienia.getInt("kucharz_id")));
		}
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return wszystkie;
	}
	

	/**
	 * Pobiera wszystkie kolumny dla zamówieñ przypisanych do kucharza o odpowiednim id
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public Collection<Zamowienie> pobierzPrzydzieloneDoKucharza(int idKucharza){				
		Collection<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		try{
		otworzPolaczenie();
		ResultSet wszysieZamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS = 4 AND KUCHARZ_ID = "+idKucharza+"  ORDER BY ID_ZAMOWIENIE");
		while(wszysieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszysieZamowienia.getInt("id_zamowienie"), wszysieZamowienia.getInt("id_status"),
					wszysieZamowienia.getTimestamp("data_przyjecia"), wszysieZamowienia.getInt("nr_stolika"),
					wszysieZamowienia.getInt("id_uzytkownik"), wszysieZamowienia.getInt("kucharz_id")));
		}
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return wszystkie;
	}
	
	
	public List<Zamowienie> pobierzZamowione(int idUzytkownika) {
		List<Zamowienie> noweZamowienia = new ArrayList<Zamowienie>();
		try{
		otworzPolaczenie();
		ResultSet nowe = st.executeQuery("SELECT * FROM zamowienie "				
				+ "WHERE id_status = 2 AND ID_UZYTKOWNIK = " + idUzytkownika + " ORDER BY ID_ZAMOWIENIE");	
		while(nowe.next()){
			noweZamowienia.add(new Zamowienie(
					nowe.getInt("id_zamowienie"), nowe.getInt("id_status"),
					nowe.getTimestamp("data_przyjecia"), nowe.getInt("nr_stolika"),
					nowe.getInt("id_uzytkownik"), nowe.getInt("kucharz_id")));
		}
		nowe.close();
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return noweZamowienia;
	}
	


	public List<Zamowienie> pobierzDlaKlienta(int idUzytkownika) {
		List<Zamowienie> zamowieniaDoPodania = new ArrayList<Zamowienie>();
		try{
		otworzPolaczenie();
		ResultSet nowe = st.executeQuery("SELECT * FROM zamowienie "				
				+ "WHERE id_status = 5 AND ID_UZYTKOWNIK = " + idUzytkownika + " ORDER BY ID_ZAMOWIENIE");	
		while(nowe.next()){
			zamowieniaDoPodania.add(new Zamowienie(
					nowe.getInt("id_zamowienie"), nowe.getInt("id_status"),
					nowe.getTimestamp("data_przyjecia"), nowe.getInt("nr_stolika"),
					nowe.getInt("id_uzytkownik"), nowe.getInt("kucharz_id")));
		}
		nowe.close();
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return zamowieniaDoPodania;
	}
	
	public int pobierzIdOstatniegoZamowienia() throws SQLException{
		otworzPolaczenie();
		ResultSet max = st.executeQuery("SELECT max(id_zamowienie) FROM zamowienie");
		max.next();
		int idOstatniego = max.getInt(1);
		max.close();
		zamknijPolaczenie();
		return idOstatniego;
		
	}
	
	
	public void dodajZamowione(Zamowienie tymczasowe) {
		try{
		otworzPolaczenie();
		st.executeUpdate("INSERT INTO zamowienie "
				+ "(id_zamowienie, id_status, id_uzytkownik, data_przyjecia) VALUES('"
				+ tymczasowe.getIdZamowienia() + "', '" + tymczasowe.getIdStatus() + "', '"
				+ tymczasowe.getIdKelnera() + "', " 
				+ "TO_DATE('" + new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(tymczasowe.getDataPrzyjecia())
				+ "', 'YYYY/MM/DD hh24:mi:ss'))");	
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Przypisuje zamówienie odpowiedniemu kucharzowi 
	 * @return void
	 */
	public void przypiszZamowienieKucharzowi(int idKucharza, int idZamowienia) {
		try{
		otworzPolaczenie();
		st.executeUpdate("UPDATE ZAMOWIENIE SET KUCHARZ_ID = " + idKucharza +", ID_STATUS = 4 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Przypisuje zamówienie odpowiedniemu kucharzowi 
	 * @return void
	 */
	public void zmienStatusZamowienia(int idZamowienia, int idStatus) {
		try{
		otworzPolaczenie();
		st.executeUpdate("UPDATE zamowienie SET id_status = " + idStatus +"WHERE id_zamowienie = " + idZamowienia);	
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Usuwa zamówienie o danym identyfikatorze
	 * @param idZamowienia
	 */
	public void usunZamowienie(int idZamowienia) {
		try {
			otworzPolaczenie();
			st.executeUpdate("DELETE FROM zamowienie WHERE id_zamowienie = " + idZamowienia);
			zamknijPolaczenie();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Zwraca zamówienie do puli zamówieñ nieprzydzielonych
	 * @return void
	 */
	public void zwrocZamowienie(int idZamowienia) {
		if(ds == null)
			ds = utworzZrodloDanych();
		try{
		otworzPolaczenie();
		st.executeUpdate("UPDATE ZAMOWIENIE SET ID_STATUS = 3 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Przekazuje zrealizowane zamówienie do Kelnera
	 * @return void
	 */
	public void przekazDoKelnera(int idZamowienia) {
		if(ds == null)
			ds = utworzZrodloDanych();
		try{
		otworzPolaczenie();
		st.executeUpdate("UPDATE ZAMOWIENIE SET ID_STATUS = 5 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		zamknijPolaczenie();
		}catch (SQLException e){
			e.printStackTrace();
		}
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
