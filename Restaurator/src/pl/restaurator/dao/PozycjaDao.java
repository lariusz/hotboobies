package pl.restaurator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Pozycja
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class PozycjaDao extends AbstractDao {

	
	/**
	 * Pobiera wszystkie nazwy dla wszystkich grup produktów z bazy danych
	 * @return zbiór wyników
	 */
	public static void dodajPozycjeZamowienia(int idZamowienia, int idProdukt,	int ilosc) {
		otworzPolaczenie();
		try {
			int id = pobierzIdOstatniejPozycji();
			st.executeUpdate("INSERT INTO pozycja "
					+ "(id_pozycja, id_zamowienie, id_produkt, ilosc_zamawianych) VALUES('"
					+ (id + 1) + "', '" + idZamowienia + "', '" + idProdukt
					+ "', '" + ilosc + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			zamknijPolaczenie();
		}
	}
	
	
	/**
	 * Usuwa wszystkie pozycje z danego zamówienia
	 * @param idZamowienia
	 */
	public static void usunPozycje(int idZamowienia) {
		otworzPolaczenie();
		try {
			st.executeUpdate("DELETE FROM pozycja WHERE id_zamowienie = " + idZamowienia);
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			zamknijPolaczenie();
		}
	}
	
	
	/**
	 * Pobiera identyfikator ostatniej pozycji
	 * @return id ostatniej pozycji
	 * @throws SQLException
	 */
	private static int pobierzIdOstatniejPozycji() throws SQLException{
		ResultSet max = st.executeQuery("SELECT max(id_pozycja) FROM pozycja");
		max.next();
		return max.getInt(1);
	}
	
}
