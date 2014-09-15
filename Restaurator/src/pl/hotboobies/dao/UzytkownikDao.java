package pl.hotboobies.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.hotboobies.dto.Uzytkownik;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Uzytkownik
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class UzytkownikDao extends Connector {
	

	/**
	 * Pobiera wszystkie kolumny dla wszystkich u¿ytkowników z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public static List<Uzytkownik> pobierzWszystkich() {				
		List<Uzytkownik> wszyscy = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
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
	
	public static Uzytkownik pobierzUzytkownika(String login, String haslo) {				
		Uzytkownik user = null;
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE login = '" + login.toLowerCase() + "' AND haslo = '" + haslo + "'");
		while(uzytkownik.next()){
			user = new Uzytkownik(
					uzytkownik.getInt("id_uzytkownik"),
					uzytkownik.getString("login"),
					uzytkownik.getString("haslo"),
					uzytkownik.getInt("id_rola"),
					uzytkownik.getString("imie"),
					uzytkownik.getString("nazwisko"),
					uzytkownik.getString("mail"),
					uzytkownik.getString("telefon"),
					uzytkownik.getInt("blokuj") == 1);
		}
		uzytkownik.close();
		zamknijPolaczenie();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	public static boolean czyJestUserWBazie(String login, String haslo) {				
		boolean jest = false;
		PreparedStatement ps = null;		
		otworzPolaczenie();
		try{
		ps = conn.prepareStatement("SELECT login, haslo FROM uzytkownik where login= ? and haslo= ?");
		ps.setString(1, login.toLowerCase());
		ps.setString(2, haslo);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			jest = true;
		}
		
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
				zamknijPolaczenie();
		}
		return jest;
	}
	
	public static List<Uzytkownik> pobierzKelnerow() {
		List<Uzytkownik> kelnerzy = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE id_rola = 2");
		while(uzytkownik.next()){
			kelnerzy.add(new Uzytkownik(
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
		return kelnerzy;
	}
	
	public static List<Uzytkownik> pobierzKucharzy() {
		List<Uzytkownik> kucharze = new ArrayList<Uzytkownik>();
		otworzPolaczenie();
		try{
		ResultSet uzytkownik =  st.executeQuery("SELECT * FROM uzytkownik WHERE id_rola = 3");
		while(uzytkownik.next()){
			kucharze.add(new Uzytkownik(
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
		return kucharze;
	}


}
