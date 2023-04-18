package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

	public Speler geefSpeler(String gebruikersnaam, int geboortejaar) {
		Speler speler = null;

		try (Connection conn = DriverManager.getConnection(ConnectieDB.CONNECTIE_STRING);
				PreparedStatement query = conn.prepareStatement(
						"SELECT * FROM ID399244_g119.Speler WHERE gebruikersnaam = ? AND geboortejaar = ?")) {
			query.setString(1, gebruikersnaam);
			query.setInt(2, geboortejaar);
			try (ResultSet rs = query.executeQuery()) {
				if (rs.next()) {
					speler = new Speler(gebruikersnaam, geboortejaar);
					return speler;
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return null;
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws IllegalArgumentException {
		try (Connection conn = DriverManager.getConnection(ConnectieDB.CONNECTIE_STRING);
				PreparedStatement query = conn
						.prepareStatement(
								"INSERT INTO ID399244_g119.Speler (gebruikersnaam, geboortejaar) VALUES (?,?)")) {
			query.setString(1, gebruikersnaam);
			query.setInt(2, geboortejaar);
			query.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException ex) {
			throw new IllegalArgumentException(String
					.format("Gebruikersnaam %s is niet uniek, probeer een andere gebruikersnaam.", gebruikersnaam));
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
