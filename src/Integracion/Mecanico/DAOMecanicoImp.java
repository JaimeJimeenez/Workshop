package Integracion.Mecanico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Especialidad.TEspecialidad;
import Negocio.Mecanico.TMecanico;

public class DAOMecanicoImp implements DAOMecanico {

	@Override
	public int alta(TMecanico tMecanico) {
		PreparedStatement pstmt = null;
		int id = -1;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement(
					"INSERT INTO mecanico(DNI, sueldo, telefono, nombre, cuenta, IDEspecialidad, actividad) VALUES(?, ?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tMecanico.getDNI());
			pstmt.setFloat(2, tMecanico.getSueldo());
			pstmt.setString(3, tMecanico.getTelefono());
			pstmt.setString(4, tMecanico.getNombre());
			pstmt.setString(5, tMecanico.getCuenta());
			if (tMecanico.getIdEspecialidad() != 0) {
				pstmt.setInt(6, tMecanico.getIdEspecialidad());
			} else {
				pstmt.setNull(6, java.sql.Types.INTEGER);
			}
			pstmt.setBoolean(7, true);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return -4;
		}

		return id;
	}

	@Override
	public int baja(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("UPDATE mecanico SET actividad=FALSE WHERE id_mecanico=?",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			
			return -4;
		}

		return id;
	}

	@Override
	public int modificar(TMecanico tMecanico) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;

				pstmt = con.prepareStatement("UPDATE mecanico SET sueldo = ?, telefono = ?, cuenta = ?, "
						+ "IDEspecialidad = ?, nombre = ?, dni = ? WHERE id_mecanico=?");
				pstmt.setFloat(1, tMecanico.getSueldo());
				pstmt.setString(2, tMecanico.getTelefono());
				pstmt.setString(3, tMecanico.getCuenta());
				pstmt.setInt(4, tMecanico.getIdEspecialidad());
				pstmt.setString(5, tMecanico.getNombre());
				pstmt.setString(6, tMecanico.getDNI());
				pstmt.setInt(7, tMecanico.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -4;
		}
		
		return tMecanico.getId();
	}

	@Override
	public TMecanico mostrar(int id) {

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM mecanico WHERE id_mecanico = ?");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TMecanico(rs.getString("DNI"), rs.getInt("id_mecanico"), rs.getFloat("sueldo"),
						rs.getBoolean("actividad"), rs.getString("nombre"), rs.getInt("IDEspecialidad"),
						rs.getString("telefono"), rs.getString("cuenta"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new TMecanico(-4);
		}

		return null;
	}

	@Override
	public Collection<TMecanico> listar() {
		ArrayList<TMecanico> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM mecanico WHERE actividad = 1");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TMecanico>();

				do {
					resultado.add(new TMecanico(rs.getString("DNI"), rs.getInt("id_mecanico"), rs.getFloat("sueldo"),
							rs.getBoolean("actividad"), rs.getString("nombre"), rs.getInt("IDEspecialidad"),
							rs.getString("telefono"), rs.getString("cuenta")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado = new ArrayList<TMecanico>();
			resultado.add(new TMecanico(-4));
		}

		return resultado;
	}

	@Override
	public TMecanico leerPorNif(String DNI) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM mecanico WHERE DNI = ?");

			pstmt.setString(1, DNI);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TMecanico(rs.getString("DNI"), rs.getInt("id_mecanico"), rs.getFloat("sueldo"),
						rs.getBoolean("actividad"), rs.getString("nombre"), rs.getInt("IDEspecialidad"),
						rs.getString("telefono"), rs.getString("cuenta"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new TMecanico(-4);
		}

		return null;
	}

	@Override
	public int reactivar(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE mecanico SET actividad = 1 WHERE id_mecanico=?");

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}

	@Override
	public Collection<TMecanico> mostrarMecanicosEspecialidad(int idEspecialidad) {
		ArrayList<TMecanico> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM mecanico WHERE actividad = 1 AND IDEspecialidad = ?");

			pstmt.setInt(1, idEspecialidad);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TMecanico>();

				do {
					resultado.add(new TMecanico(rs.getString("DNI"), rs.getInt("id_mecanico"), rs.getFloat("sueldo"),
							rs.getBoolean("actividad"), rs.getString("nombre"), rs.getInt("IDEspecialidad"),
							rs.getString("telefono"), rs.getString("cuenta")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado = new ArrayList<TMecanico>();
			resultado.add(new TMecanico(-4));
		}

		return resultado;
	}

}
