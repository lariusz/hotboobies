package pl.hotboobies.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.hotboobies.dto.Produkt;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Produkt
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class ProduktDao extends AbstractDao {
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich grup
	 * @return kolekcjê obiektów Zamowienie
	 */
	public static List<Produkt> pobierzWszystkieKolumny() {
		List<Produkt> produktyGrupy = new ArrayList<Produkt>();
		otworzPolaczenie();
		try {
			ResultSet wszystkieProdukty = st.executeQuery("SELECT * FROM produkt ORDER BY id_grupa");
				while(wszystkieProdukty.next()){
					produktyGrupy.add(new Produkt(wszystkieProdukty.getInt("id_produkt"),
							wszystkieProdukty.getString("nazwa"),
							wszystkieProdukty.getInt("ilosc"),
							wszystkieProdukty.getInt("czas_wykonania"),
							wszystkieProdukty.getInt("aktywny") == 1,
							wszystkieProdukty.getInt("id_grupa"))
							);
						 
				}
				zamknijPolaczenie();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				zamknijPolaczenie();
			}
		
		return produktyGrupy;
	}
		
	
	/**
	 * Pobiera wszystkie kolumny dla produktów z grupy
	 * @param grupa produktów dla której maja zostaæ zwrócone wyniki
	 * @return kolekcjê obiektów Zamowienie
	 */
	public static List<Produkt> pobierzWszystkieProdukty(int grupa) {
		List<Produkt> produktyGrupy = new ArrayList<Produkt>();
		otworzPolaczenie();
		try {
			ResultSet wszystkieProdukty = st.executeQuery("SELECT * FROM produkt WHERE id_grupa = " + grupa);
				while(wszystkieProdukty.next()){
					produktyGrupy.add(new Produkt(wszystkieProdukty.getInt("id_produkt"),
							wszystkieProdukty.getString("nazwa"),
							wszystkieProdukty.getBigDecimal("cena"),
							wszystkieProdukty.getInt("ilosc"),
							wszystkieProdukty.getInt("czas_wykonania"),
							wszystkieProdukty.getInt("aktywny") == 1));
				}
				wszystkieProdukty.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				zamknijPolaczenie();
			}		
		return produktyGrupy;
	}
	
	
	/**
	 * Pobiera wszystkie kolumny pozycji i produktów dla konkretnego zamówienia
	 * @param identyfikator zamówienia dla którego maja zostaæ zwrócone wyniki
	 * @return kolekcjê obiektów Zamowienie
	 */
	public static List<Produkt> pobierzPozycjeZamowienia(int idZamowienia) {
		List<Produkt> produktyGrupy = new ArrayList<Produkt>();
		otworzPolaczenie();
		try {
			ResultSet wszystkieProdukty = st.executeQuery("SELECT * FROM POZYCJA P, PRODUKT PR WHERE P.ID_PRODUKT=PR.ID_PRODUKT AND P.ID_ZAMOWIENIE = "+ idZamowienia);
				while(wszystkieProdukty.next()){
					produktyGrupy.add(new Produkt(wszystkieProdukty.getInt("id_produkt"),
							wszystkieProdukty.getString("nazwa"),							
							wszystkieProdukty.getBigDecimal("cena"),
							wszystkieProdukty.getInt("ilosc_zamawianych"),
							wszystkieProdukty.getInt("czas_wykonania")));
				}
				wszystkieProdukty.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				zamknijPolaczenie();
			}
		
		return produktyGrupy;
	}
	
	/**
	 * Aktualizuje iloœæ i aktywnoœæ dla produktu o wskazanym id
	 */
	public static boolean aktulizujIloscProduktu(int idProduktu, int iloscProduktu, boolean aktywny) {
		otworzPolaczenie();		
		try{
		st.executeUpdate("UPDATE produkt SET ilosc = " + iloscProduktu 
				+ ", aktywny = "+ (aktywny ? 1:0) + " WHERE id_produkt = " + idProduktu);	
		}catch (SQLException e){
			e.printStackTrace(); 
			return false;
		} finally{
			zamknijPolaczenie();
		}
		return true;
	}
		
		
	/**
	 * Aktualizuj aktywnoœæ produktu o podanym id
	 */
	public static void aktulizujAktywnosc(int idProduktu, int stan) {
		otworzPolaczenie();
		try {
			st.executeUpdate("UPDATE produkt SET aktywny = " + stan
					+ " WHERE id_produkt = " + idProduktu);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}

}
