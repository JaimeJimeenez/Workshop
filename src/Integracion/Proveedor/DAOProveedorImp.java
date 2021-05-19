package Integracion.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Proveedor.TProveedor;

public class DAOProveedorImp implements DAOProveedor {

	@Override
	public int alta(TProveedor tProveedor) {
		PreparedStatement pstmt = null;
		int id = -1;
		
		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("INSERT INTO proveedor(NIF, telefono, direccion, actividad) VALUES(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tProveedor.getNIF());
			pstmt.setString(2, tProveedor.getTelefono());
			pstmt.setString(3, tProveedor.getDireccion());
			pstmt.setBoolean(4, true);
			

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

			pstmt = con.prepareStatement("UPDATE proveedor SET actividad=FALSE WHERE id_proveedor=?");
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}
		return id;
	}

	@Override
	public int modificar(TProveedor tProveedor) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE proveedor SET NIF = ? direccion = ?, telefono = ? WHERE id_proveedor=?");
			pstmt.setString(1, tProveedor.getNIF());
			pstmt.setString(2, tProveedor.getDireccion());
			pstmt.setString(3, tProveedor.getTelefono());
			pstmt.setInt(4, tProveedor.getId());
				
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			return -4;
		}
		return tProveedor.getId();
	}
	
	
	
	
	@Override
	public TProveedor mostrar(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM proveedor WHERE id_proveedor = ?");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TProveedor(rs.getString("NIF"), rs.getInt("id_proveedor"), rs.getString("telefono"), 
						rs.getString("direccion"), rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TProveedor(-4);
		}

		return null;
	}

	@Override
	public Collection<TProveedor> listar() {
		ArrayList<TProveedor> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM proveedor WHERE actividad = 1");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TProveedor>();
				
				do {
					resultado.add(new TProveedor(rs.getString("NIF"), rs.getInt("id_proveedor"), rs.getString("telefono"), 
							rs.getString("direccion"), rs.getBoolean("actividad")));
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado.add(new TProveedor(-4));
		}

		return resultado;
	}
	@Override
	public TProveedor leerPorNIF(String NIF) {

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM proveedor WHERE NIF = ?");

			pstmt.setString(1, NIF);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TProveedor(rs.getString("NIF"), rs.getInt("id_proveedor"), rs.getString("telefono"), 
						rs.getString("direccion"), rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TProveedor(-4);
		}

		return null;
	}

	@Override
	public int reactivar(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE proveedor SET actividad = 1 WHERE id_proveedor=?");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
	
}
