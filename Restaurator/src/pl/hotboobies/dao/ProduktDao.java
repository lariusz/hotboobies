package pl.hotboobies.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.hotboobies.dto.Produkt;

/**
 * Klasa udost�pniaj�ca metody dost�pu do bazy danych dla obiektu Produkt
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha� Larysz</a> *
 */
public class ProduktDao extends AbstractDao {
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich grup
	 * @return kolekcj� obiekt�w Zamowienie
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
	 * Pobiera wszystkie kolumny dla produkt�w z grupy
	 * @param grupa produkt�w dla kt�rej maja zosta� zwr�cone wyniki
	 * @return kolekcj� obiekt�w Zamowienie
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
	 * Pobiera wszystkie kolumny pozycji i produkt�w dla konkretnego zam�wienia
	 * @param identyfikator zam�wienia dla kt�rego maja zosta� zwr�cone wyniki
	 * @return kolekcj� obiekt�w Zamowienie
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
	 * Aktualizuje ilo�� i aktywno�� dla produktu o wskazanym id
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
	 * Aktualizuj aktywno�� produktu o podanym id
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
