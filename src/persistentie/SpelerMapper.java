package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {

	public List<Speler> geefSpelers() {
		List<Speler> spelers = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(ConnectieDB.CONNECTIE_STRING);
				PreparedStatement query = conn.prepareStatement("SELECT * FROM ID399244_g119.Speler");
				ResultSet rs = query.executeQuery()) {

			while (rs.next()) {
				String gebruikersnaam = rs.getString("gebruikersnaam");
				int geboortejaar = rs.getInt("geboortejaar");
				spelers.add(new Speler(gebruikersnaam, geboortejaar));
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return spelers;
	}

}
