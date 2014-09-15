package pl.hotboobies.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.hotboobies.dto.Produkt;
import pl.hotboobies.dto.Zamowienie;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Zamowienie
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class ZamowienieDao extends AbstractDao {

	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich zamówieñ nieprzydzielonych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public static List<Zamowienie> pobierzWszystkieNieprzydzielone(){				
		List<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet wszystkieZamowienia = st.executeQuery("SELECT * FROM zamowienie  WHERE ID_STATUS=3 ORDER BY ID_ZAMOWIENIE");
		while(wszystkieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszystkieZamowienia.getInt("id_zamowienie"), wszystkieZamowienia.getInt("id_status"),
					wszystkieZamowienia.getTimestamp("data_przyjecia"), wszystkieZamowienia.getInt("nr_stolika"),
					wszystkieZamowienia.getInt("id_uzytkownik"), wszystkieZamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return wszystkie;
	}
	
	/**
	 * Pobiera wszystkie kolumny dla ostatniego nieprzydzielongo zamówienia (max 1 wynik)
	 * @return pojedynczy wynik
	 * @throws SQLException
	 */
	public static List<Zamowienie> pobierzNajstarszeNieprzydzielone(){				
		List<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet wszysieZamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS=3 AND ROWNUM <= 1 ORDER BY ID_ZAMOWIENIE");
		while(wszysieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszysieZamowienia.getInt("id_zamowienie"), wszysieZamowienia.getInt("id_status"),
					wszysieZamowienia.getTimestamp("data_przyjecia"), wszysieZamowienia.getInt("nr_stolika"),
					wszysieZamowienia.getInt("id_uzytkownik"), wszysieZamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return wszystkie;
	}
	

	/**
	 * Pobiera wszystkie kolumny dla zamówieñ przypisanych do kucharza o odpowiednim id
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public static List<Zamowienie> pobierzPrzydzieloneDoKucharza(int idKucharza){				
		List<Zamowienie> wszystkie = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet wszysieZamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS = 4 AND KUCHARZ_ID = "+idKucharza+"  ORDER BY ID_ZAMOWIENIE");
		while(wszysieZamowienia.next()){
			wszystkie.add(new Zamowienie(
					wszysieZamowienia.getInt("id_zamowienie"), wszysieZamowienia.getInt("id_status"),
					wszysieZamowienia.getTimestamp("data_przyjecia"), wszysieZamowienia.getInt("nr_stolika"),
					wszysieZamowienia.getInt("id_uzytkownik"), wszysieZamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return wszystkie;
	}
	
	
	public static List<Zamowienie> pobierzZamowione(int idUzytkownika) {
		List<Zamowienie> noweZamowienia = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet nowe = st.executeQuery("SELECT * FROM zamowienie "				
				+ "WHERE id_status = 2 AND ID_UZYTKOWNIK = " + idUzytkownika + " ORDER BY ID_ZAMOWIENIE");	
		while(nowe.next()){
			noweZamowienia.add(new Zamowienie(
					nowe.getInt("id_zamowienie"), nowe.getInt("id_status"),
					nowe.getTimestamp("data_przyjecia"), nowe.getInt("nr_stolika"),
					nowe.getInt("id_uzytkownik"), nowe.getInt("kucharz_id")));
		}
		nowe.close();
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		
		return noweZamowienia;
	}
	


	public static List<Zamowienie> pobierzDlaKlienta(int idUzytkownika) {
		List<Zamowienie> zamowieniaDoPodania = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet nowe = st.executeQuery("SELECT * FROM zamowienie "				
				+ "WHERE id_status = 5 AND ID_UZYTKOWNIK = " + idUzytkownika + " ORDER BY ID_ZAMOWIENIE");	
		while(nowe.next()){
			zamowieniaDoPodania.add(new Zamowienie(
					nowe.getInt("id_zamowienie"), nowe.getInt("id_status"),
					nowe.getTimestamp("data_przyjecia"), nowe.getInt("nr_stolika"),
					nowe.getInt("id_uzytkownik"), nowe.getInt("kucharz_id")));
		}
		nowe.close();
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		
		return zamowieniaDoPodania;
	}
	
	/**
	 * Pobiera id ostatniego zamówienia.
	 * @return id zamówienia
	 */
	public static int pobierzIdOstatniegoZamowienia(){
		int idOstatniego = 0;
		otworzPolaczenie();
		try{
		ResultSet max = st.executeQuery("SELECT max(id_zamowienie) FROM zamowienie");
		max.next();
		idOstatniego = max.getInt(1);
		max.close();
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return idOstatniego;		
	}
	
	/**
	 * Pobiera id ostatniego anulowanego zamówienia.
	 * @return id zamówienia
	 */
	private static int pobierzIdOstatniegoAnulowanego(){
		int idOstatniego = 0;
		otworzPolaczenie();
		try{
		ResultSet max = st.executeQuery("SELECT max(id_anulowane) FROM anulowane");
		max.next();
		idOstatniego = max.getInt(1);
		max.close();
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return idOstatniego;		
	}
	
	
	public static void dodajZamowione(Zamowienie tymczasowe) {
		otworzPolaczenie();
		try{
		String sql = "INSERT INTO zamowienie "
				+ "(id_zamowienie, nr_stolika, id_status, id_uzytkownik, data_przyjecia) VALUES('"
				+ tymczasowe.getIdZamowienia() + "', '" + tymczasowe.getNrStolika() + "', '"
				+ tymczasowe.getIdStatus() + "', '"
				+ tymczasowe.getIdKelnera() + "', " 
				+ "TO_DATE('" + new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(tymczasowe.getDataPrzyjecia())
				+ "', 'YYYY/MM/DD hh24:mi:ss'))";
		st.executeUpdate(sql);	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}
	
	/**
	 * Zapisuje w tabeli anulowane szczegó³y zamówienia o danym identyfikatorze
	 * @param idZamowienia
	 * @param przyczyna
	 */
	public static void anulujZamowienie(int idZamowienia, String przyczyna) {
		int id_anulowane = pobierzIdOstatniegoAnulowanego() + 1;
		otworzPolaczenie();
		try {
			st.execute("INSERT INTO anulowane "
					+ "(id_anulowane ,id_zamowienie, przyczyna, data_anul) VALUES('"
					+ id_anulowane + "', '" + idZamowienia + "', '"
					+ przyczyna + "', "
					+ "TO_DATE('" + new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(new Date())
					+ "', 'YYYY/MM/DD hh24:mi:ss'))");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		
	}
	
	
	/**
	 * Przypisuje zamówienie odpowiedniemu kucharzowi 
	 * @return void
	 */
	public static void przypiszZamowienieKucharzowi(int idKucharza, int idZamowienia) {
		otworzPolaczenie();
		try{
		st.executeUpdate("UPDATE ZAMOWIENIE SET KUCHARZ_ID = " + idKucharza +", ID_STATUS = 4 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}
	
	/**
	 * Przypisuje zamówienie odpowiedniemu kucharzowi 
	 * @return void
	 */
	public static void zmienStatusZamowienia(int idZamowienia, int idStatus) {
		otworzPolaczenie();
		try{
		st.executeUpdate("UPDATE zamowienie SET id_status = " + idStatus +"WHERE id_zamowienie = " + idZamowienia);	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}
	
	/**
	 * Usuwa zamówienie o danym identyfikatorze
	 * @param idZamowienia
	 */
	public static void usunZamowienie(int idZamowienia) {
		otworzPolaczenie();
		try {
			st.executeUpdate("DELETE FROM zamowienie WHERE id_zamowienie = " + idZamowienia);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}
	

	
	/**
	 * Zmienia status zamówienia na anulowane
	 * @param idZamowienia
	 * @param przyczyna
	 */
	public static void zmienStatusNaAnulowane(int idZamowienia) {
		otworzPolaczenie();			
		try {
			st.executeUpdate("UPDATE ZAMOWIENIE SET ID_STATUS = 7 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			zamknijPolaczenie();
		}
		
	}
	
	
	/**
	 * Zwraca zamówienie do puli zamówieñ nieprzydzielonych
	 * @return void
	 */
	public static void zwrocZamowienie(int idZamowienia) {
		otworzPolaczenie();
		try{
		st.executeUpdate("UPDATE ZAMOWIENIE SET ID_STATUS = 3 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}
	
	/**
	 * Przekazuje zrealizowane zamówienie do Kelnera
	 * @return void
	 */
	public static void przekazDoKelnera(int idZamowienia) {
		otworzPolaczenie();
		try{
		st.executeUpdate("UPDATE ZAMOWIENIE SET ID_STATUS = 5 WHERE ID_ZAMOWIENIE = " + idZamowienia);	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
	}


	public static List<Zamowienie> pobierzZamowieniaKucharza(int identyfikator,
			Date dataOd, Date dataDo) {
		List<Zamowienie> zamowieniaKucharza = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet zamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE id_status = 6 AND kucharz_id = "+identyfikator
				+" AND (data_przyjecia BETWEEN '" +new SimpleDateFormat("YYYY/MM/dd").format(dataOd)
				+ "' AND '" +new SimpleDateFormat("YYYY/MM/dd").format(dataDo)+ "') ORDER BY ID_ZAMOWIENIE");
		while(zamowienia.next()){
			zamowieniaKucharza.add(new Zamowienie(
					zamowienia.getInt("id_zamowienie"), zamowienia.getInt("id_status"),
					zamowienia.getTimestamp("data_przyjecia"), zamowienia.getInt("nr_stolika"),
					zamowienia.getInt("id_uzytkownik"), zamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return zamowieniaKucharza;		
	}
	
	public static List<Zamowienie> pobierzZamowieniaKelnera(int identyfikator,
			Date dataOd, Date dataDo) {
		List<Zamowienie> zamowieniaKelnera = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet zamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS = 6 AND id_uzytkownik = "+identyfikator
				+" AND (data_przyjecia BETWEEN '" +new SimpleDateFormat("YYYY/MM/dd").format(dataOd)
				+ "' AND '" +new SimpleDateFormat("YYYY/MM/dd").format(dataDo)+ "') ORDER BY ID_ZAMOWIENIE");
		while(zamowienia.next()){
			zamowieniaKelnera.add(new Zamowienie(
					zamowienia.getInt("id_zamowienie"), zamowienia.getInt("id_status"),
					zamowienia.getTimestamp("data_przyjecia"), zamowienia.getInt("nr_stolika"),
					zamowienia.getInt("id_uzytkownik"), zamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return zamowieniaKelnera;		
	}

	public static List<Produkt> pobierzZamowioneProdukty(Date dataOd, Date dataDo) {
		List<Produkt> zamowioneProdukty = new ArrayList<Produkt>();
		otworzPolaczenie();
		try{
		ResultSet produkty = st.executeQuery("SELECT p.NAZWA, p.CENA, sum(poz.ILOSC_ZAMAWIANYCH) "
				+ "FROM produkt p INNER JOIN pozycja poz USING(id_produkt) "
				+ "INNER JOIN zamowienie z USING (id_zamowienie) "
				+ "WHERE z.id_status = 6"
				+" AND (data_przyjecia BETWEEN '" +new SimpleDateFormat("YYYY/MM/dd").format(dataOd)
				+ "' AND '" +new SimpleDateFormat("YYYY/MM/dd").format(dataDo)+ "') GROUP BY p.NAZWA, p.CENA");
		while(produkty.next()){
			zamowioneProdukty.add(new Produkt(produkty.getString(1),							
					produkty.getBigDecimal(2),
					produkty.getInt(3)));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return zamowioneProdukty;		
	}

	public static List<Zamowienie> pobierzZamowienia(Date dataOd, Date dataDo) {
		List<Zamowienie> wszystkieZamowienia = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet zamowienia = st.executeQuery("SELECT * FROM zamowienie WHERE ID_STATUS = 6 "
				+"AND (data_przyjecia BETWEEN '" +new SimpleDateFormat("YYYY/MM/dd").format(dataOd)
				+ "' AND '" +new SimpleDateFormat("YYYY/MM/dd").format(dataDo)+ "') ORDER BY ID_ZAMOWIENIE");
		while(zamowienia.next()){
			wszystkieZamowienia.add(new Zamowienie(
					zamowienia.getInt("id_zamowienie"), zamowienia.getInt("id_status"),
					zamowienia.getTimestamp("data_przyjecia"), zamowienia.getInt("nr_stolika"),
					zamowienia.getInt("id_uzytkownik"), zamowienia.getInt("kucharz_id")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return wszystkieZamowienia;	
	}

	public static List<Zamowienie> pobierzAnulowaneZamowienia(Date dataOd, Date dataDo) {
		List<Zamowienie> zamowieniaAnulowane = new ArrayList<Zamowienie>();
		otworzPolaczenie();
		try{
		ResultSet zamowienia = st.executeQuery("SELECT ID_ZAMOWIENIE, z.NR_STOLIKA, z.DATA_PRZYJECIA, a.DATA_ANUL, a.PRZYCZYNA "
				+ "FROM zamowienie z INNER JOIN anulowane a USING(id_zamowienie) "
				+ "WHERE z.id_status = 7"
				+" AND (data_przyjecia BETWEEN '" +new SimpleDateFormat("YYYY/MM/dd").format(dataOd)
				+ "' AND '" +new SimpleDateFormat("YYYY/MM/dd").format(dataDo)+ "')");
		while(zamowienia.next()){
			zamowieniaAnulowane.add(new Zamowienie(
					zamowienia.getInt("id_zamowienie"), zamowienia.getInt("nr_stolika"),
					zamowienia.getTimestamp("data_przyjecia"),
					zamowienia.getTimestamp("data_anul"),
					zamowienia.getString("przyczyna")));
		}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			zamknijPolaczenie();
		}
		return zamowieniaAnulowane;	
	}
		
	


}
