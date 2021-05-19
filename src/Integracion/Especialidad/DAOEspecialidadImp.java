package Integracion.Especialidad;

import Negocio.Especialidad.TEspecialidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;

public class DAOEspecialidadImp implements DAOEspecialidad {

	@Override
	public TEspecialidad leerPorTipo(String tipo) {//Leer por el nombre de especialidad
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM especialidad WHERE tipo = ?");

			pstmt.setString(1, tipo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TEspecialidad(rs.getString("tipo"), rs.getInt("id_especialidad"),
						rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TEspecialidad(-4);
		}

		return null;
	}

	@Override
	public int alta(TEspecialidad tEspecialidad) {
		PreparedStatement pstmt = null;
		int id = -1;
		
		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("INSERT INTO especialidad(tipo, actividad) VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tEspecialidad.getTipo());
			pstmt.setBoolean(2, true);

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException exception) {
			return -4;
		}

		return id;
	}

	@Override
	public int baja(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("UPDATE especialidad SET actividad=FALSE WHERE id_especialidad=?");

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}

		return id;
	}

	@Override
	public int modificar(TEspecialidad tEspecialidad) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE especialidad SET tipo = ? WHERE id_especialidad=?");
			
			pstmt.setString(1, tEspecialidad.getTipo());
			pstmt.setInt(2, tEspecialidad.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return tEspecialidad.getId();
	}

	@Override
	public TEspecialidad mostrar(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM especialidad WHERE id_especialidad = ?");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new TEspecialidad(rs.getString("tipo"), rs.getInt("id_especialidad"),
						rs.getBoolean("actividad"));
			}
		} catch (SQLException e) {
			return new TEspecialidad(-4);
		}
		return null;
	}

	@Override
	public Collection<TEspecialidad> listar() {
		ArrayList<TEspecialidad> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM especialidad WHERE actividad = 1");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TEspecialidad>();
				
				do {
					resultado.add(new TEspecialidad(rs.getString("tipo"), rs.getInt("id_especialidad"), rs.getBoolean("actividad")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado.add(new TEspecialidad(-4));
			return resultado;
		}

		return resultado;
	}

	public int reactivar(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE especialidad SET actividad=1 WHERE id_especialidad=?");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
}