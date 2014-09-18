package pl.restaurator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.restaurator.dto.Grupa;

/**
 * Klasa udostêpniaj¹ca metody dostêpu do bazy danych dla obiektu Grupa
 *  @author <a href="mailto:mlarysz@us.edu.pl">Micha³ Larysz</a> *
 */
public class GrupaDao extends AbstractDao {
	
	
	/**
	 * Pobiera wszystkie kolumny dla wszystkich grup produktów z bazy danych
	 * @return zbiór wyników
	 * @throws SQLException
	 */
	public static List<Grupa> pobierzWszystkieGrupy() {
		List<Grupa> grupy = new ArrayList<Grupa>();
		try {
			otworzPolaczenie();
			ResultSet wszystkieGrupy = st.executeQuery("SELECT * FROM grupa");
			while (wszystkieGrupy.next()) {
				grupy.add(new Grupa(wszystkieGrupy.getInt("id_grupa"),
						wszystkieGrupy.getString("nazwa")));
			}
			wszystkieGrupy.close();
			zamknijPolaczenie();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grupy;
	}
	
}
