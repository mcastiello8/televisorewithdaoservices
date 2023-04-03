package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("Marca"));
				televisoreTemp.setModello(rs.getString("Modello"));
				televisoreTemp.setPollici(rs.getInt("Pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("dataProduzione") != null ? rs.getDate("dataProduzione").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	// ========================================================
	public Televisore get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == 0 || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("Marca"));
					result.setModello(rs.getString("Modello"));
					result.setPollici(rs.getInt("Pollici"));
					result.setDataProduzione(
							rs.getDate("DataProduzione") != null ? rs.getDate("DataProduzione").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public int update(Televisore input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore SET marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public int insert(Televisore input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public int delete(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == 0 || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore WHERE ID=?")) {
			ps.setLong(1, idInput);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List findByExample(Televisore example) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1 ";
		if (example.getMarca() != null && !example.getMarca().isEmpty()) {
			query += " and marca like '" + example.getMarca() + "%' ";
		}

		if (example.getModello() != null && !example.getModello().isEmpty()) {
			query += " and modello like '" + example.getModello() + "%' ";
		}

		if (example.getPollici() != 0) {
			query += " and pollici like '" + example.getPollici() + "%' ";
		}

		if (example.getDataProduzione() != null) {
			query += " and dataproduzione='" + java.sql.Date.valueOf(example.getDataProduzione()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("Marca"));
				televisoreTemp.setModello(rs.getString("Modello"));
				televisoreTemp.setPollici(rs.getInt("Pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("dataProduzione") != null ? rs.getDate("dataProduzione").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public Televisore findTelevisorePiuGrande() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int findHowManyTelevisoriProdottiTraDueDate(LocalDate primaData, LocalDate secondaData) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List whichMarcheTelevisoriProdottiNegliUltimiSeiMesi() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
