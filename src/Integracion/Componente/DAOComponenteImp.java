package Integracion.Componente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Componente.TComponente;

public class DAOComponenteImp implements DAOComponente {

	@Override
	public int alta(TComponente tComponente) {
		PreparedStatement pstmt = null;
		int id = -1;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("INSERT INTO componente(marca, modelo, precio, stock, id_proveedor, actividad) VALUES(?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tComponente.getMarca());
			pstmt.setString(2, tComponente.getModelo());
			pstmt.setFloat(3, tComponente.getPrecio());
			pstmt.setInt(4, tComponente.getStock());
			pstmt.setInt(5, tComponente.getIdProveedor());
			pstmt.setBoolean(6, true);

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

			pstmt = con.prepareStatement("UPDATE componente SET actividad=FALSE WHERE id_componente=?",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}

		return id;
	}

	@Override
	public int modificar(TComponente tComponente) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE componente SET marca=?, modelo=?, precio=?, id_proveedor=?, stock=? WHERE id_componente=?");

			pstmt.setString(1, tComponente.getMarca());
			pstmt.setString(2, tComponente.getModelo());
			pstmt.setFloat(3, tComponente.getPrecio());
			pstmt.setInt(4, tComponente.getIdProveedor());
			pstmt.setInt(5, tComponente.getStock());
			pstmt.setInt(6, tComponente.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}

		return tComponente.getId();
	}

	@Override
	public TComponente mostrar(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM componente WHERE id_componente = ?");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TComponente(id, rs.getInt("id_proveedor"), rs.getString("marca"), rs.getFloat("precio"), rs.getString("modelo"),
						rs.getInt("stock"), rs.getBoolean("actividad"));
			}
		} catch (SQLException e) {
			return new TComponente(-4);
		}

		return null;
	}

	@Override
	public Collection<TComponente> mostrarComponentesProveedor(int id_proveedor) {
		ArrayList<TComponente> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM componente WHERE id_proveedor=? AND actividad=1");

			pstmt.setInt(1, id_proveedor);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TComponente>();
				
				do {
					resultado.add(new TComponente(rs.getInt("id_componente"), rs.getInt("id_proveedor"), rs.getString("marca"), rs.getFloat("precio"),
							rs.getString("modelo"), rs.getInt("stock"), rs.getBoolean("actividad")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado = new ArrayList<TComponente>();
			resultado.add(new TComponente(-4));
			return resultado;
		}

		return resultado;
	}

	@Override
	public Collection<TComponente> listar() {
		ArrayList<TComponente> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM componente WHERE actividad = 1");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TComponente>();
				
				do {
					resultado.add(new TComponente(rs.getInt("id_componente"), rs.getInt("id_proveedor"), rs.getString("marca"), rs.getFloat("precio"),
							rs.getString("modelo"), rs.getInt("stock"), rs.getBoolean("actividad")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado = new ArrayList<TComponente>();
			resultado.add(new TComponente(-4));
			return resultado;
			
		}

		return resultado;
	}

	@Override
	public TComponente leerPorMarcaModelo(String marca, String modelo) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM componente WHERE marca = ? AND modelo = ?");

			pstmt.setString(1, marca);
			pstmt.setString(2, modelo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TComponente(rs.getInt("id_componente"), rs.getInt("id_proveedor"), rs.getString("marca"), rs.getFloat("precio"),
						rs.getString("modelo"), rs.getInt("stock"), rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TComponente(-4);
		}

		return null;
	}

	@Override
	public int reactivar(TComponente tComponente) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"UPDATE componente SET marca=?, modelo=?, precio=?, stock=?, id_proveedor=?, actividad=1 WHERE id_componente=?");

			pstmt.setString(1, tComponente.getMarca());
			pstmt.setString(2, tComponente.getModelo());
			pstmt.setFloat(3, tComponente.getPrecio());
			pstmt.setInt(4, tComponente.getStock());
			pstmt.setInt(5, tComponente.getIdProveedor());
			pstmt.setInt(6, tComponente.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}

		return tComponente.getId();
	}

}
