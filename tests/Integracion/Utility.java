package Integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utility {
	public static int bajaFisicaMecanico(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"DELETE FROM trabaja WHERE id_mecanico=?");
			pstmt = con.prepareStatement(
					"DELETE FROM mecanico WHERE id_mecanico=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
	
	public static int bajaFisicaEspecialidad(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("DELETE FROM especialidad WHERE id_especialidad=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
	
	public static int bajaFisicaProveedor(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("DELETE FROM proveedor WHERE id_proveedor=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}

	public static int bajaFisicaVehiculo(int id) {
		PreparedStatement preparedStatement = null;
		try (Connection conection = DataBaseConnection.getConnection()) {
			preparedStatement = conection.prepareStatement("DELETE FROM vehiculo WHERE id_vehiculo =?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}
		return id;
	}

	public static int bajaFisicaReparacion(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"DELETE FROM reparacion WHERE id_reparacion=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
	
	public static int bajaFisicaCliente(int id){
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"DELETE FROM cliente WHERE id_cliente=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		return id;
	}
	
	public static int bajaFisicaComponente(int id) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("DELETE FROM cliente WHERE id_cliente=?");
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return -4;
		}
		
		return id;
	}

}
