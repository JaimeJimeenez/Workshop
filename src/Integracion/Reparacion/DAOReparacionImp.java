package Integracion.Reparacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;

public class DAOReparacionImp implements DAOReparacion {

	@Override
	public int alta(TReparacion tReparacion) {
		PreparedStatement pstmt = null;
		int id = -1;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("INSERT INTO reparacion(averia, fechainicio, fechasalida, presupuesto, id_vehiculo, actividad) VALUES(?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, tReparacion.getAveria());
			Date date = Date.valueOf(tReparacion.getFechaInicio());
			pstmt.setDate(2, date);
			date = Date.valueOf(tReparacion.getFechaSalida());
			pstmt.setDate(3, date);
			pstmt.setFloat(4, tReparacion.getPresupuesto());
			pstmt.setInt(5, tReparacion.getIdVehiculo());
			pstmt.setBoolean(6, true);

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

		} catch (Exception exception) {
			return -4;
		}

		return id;
	}
	
	public TReparacion leerPorDatos(TReparacion tReparacion){
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM reparacion WHERE averia=? AND fechainicio=? AND fechasalida=? AND"
					+ " presupuesto=?");

			pstmt.setString(1, tReparacion.getAveria());
			pstmt.setDate(2, Date.valueOf(tReparacion.getFechaInicio()));
			pstmt.setDate(3, Date.valueOf(tReparacion.getFechaSalida()));
			pstmt.setFloat(4, tReparacion.getPresupuesto());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new TReparacion(rs.getInt("id_reparacion"), rs.getInt("id_vehiculo"),
						rs.getDate("fechainicio").toString(), rs.getDate("fechasalida").toString(), rs.getString("averia"), 
						rs.getFloat("presupuesto"), rs.getBoolean("actividad"));
			}

		} catch (Exception e) {
			return new TReparacion(-4);
		}

		return null;
	}

	@Override
	public int reactivar(TReparacion tReparacion) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement("UPDATE reparacion SET actividad=1 WHERE id_reparacion=?");
			
			pstmt.setInt(1, tReparacion.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tReparacion.getId();
	}

	@Override
	public int baja(int idReparacion) {
		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {

			preparedStatement = conection
					.prepareStatement("UPDATE reparacion SET actividad=FALSE WHERE id_reparacion=?");

			preparedStatement.setInt(1, idReparacion);
			preparedStatement.executeUpdate();

			preparedStatement = conection.prepareStatement("DELETE FROM emplea WHERE ID_Reparacion=?");

			preparedStatement.setInt(1, idReparacion);
			preparedStatement.executeUpdate();

			preparedStatement = conection.prepareStatement("DELETE FROM trabaja WHERE id_reparacion=?");

			preparedStatement.setInt(1, idReparacion);
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return idReparacion;
	}

	@Override
	public TEmplea altaComponente(TEmplea tEmplea) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement(
					"INSERT INTO emplea(id_reparacion, id_componente, precio, cantidad) VALUES(?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, tEmplea.getIdReparacion());
			pstmt.setInt(2, tEmplea.getIdComponente());
			pstmt.setFloat(3, tEmplea.getPrecio());
			pstmt.setInt(4, tEmplea.getCantidad());

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return new TEmplea(-4);
		}

		return tEmplea;
	}

	@Override
	public TTrabaja altaMecanico(TTrabaja tTrabaja) {
		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {

			preparedStatement = conection
					.prepareStatement("INSERT INTO trabaja(horas, id_mecanico, id_reparacion) VALUES (?, ?, ?)");

			preparedStatement.setInt(1, tTrabaja.getHora());
			preparedStatement.setInt(2, tTrabaja.getIdMecanico());
			preparedStatement.setInt(3, tTrabaja.getIdReparacion());

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return new TTrabaja(0);
		}

		return tTrabaja;
	}

	@Override
	public TEmplea bajaComponente(TEmplea tEmplea) {
		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {

			preparedStatement = conection
					.prepareStatement("DELETE FROM emplea WHERE id_componente=? AND id_reparacion=?");

			preparedStatement.setInt(1, tEmplea.getIdComponente());
			preparedStatement.setInt(2, tEmplea.getIdReparacion());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			return new TEmplea(-4);
		}

		return tEmplea;
	}

	@Override
	public TTrabaja bajaMecanico(TTrabaja tTrabaja) {
		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {

			preparedStatement = conection
					.prepareStatement("DELETE FROM trabaja WHERE id_mecanico=? AND id_reparacion=?");

			preparedStatement.setInt(1, tTrabaja.getIdMecanico());
			preparedStatement.setInt(2, tTrabaja.getIdReparacion());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return tTrabaja;
	}

	@Override
	public int modificar(TReparacion tReparacion) {
		try (Connection con = DataBaseConnection.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"UPDATE reparacion SET averia=?, fechainicio=?, fechasalida=?, presupuesto=? WHERE id_reparacion=?");

			pstmt.setString(1, tReparacion.getAveria());
			pstmt.setDate(2, Date.valueOf(tReparacion.getFechaInicio()));
			pstmt.setDate(3, Date.valueOf(tReparacion.getFechaSalida()));
			pstmt.setFloat(4, tReparacion.getPresupuesto());
			pstmt.setInt(5, tReparacion.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			return -4;
		}

		return tReparacion.getId();
	}

	@Override
	public TEmplea modificarComponente(TEmplea tEmplea) {
		try (Connection connection = DataBaseConnection.getConnection()) {
			PreparedStatement preparedStatement = null;
			preparedStatement = connection
					.prepareStatement("UPDATE emplea SET cantidad = ?, precio = ? WHERE id_componente = ? AND id_reparacion = ?");
			preparedStatement.setInt(1, tEmplea.getCantidad());
			preparedStatement.setDouble(2, tEmplea.getPrecio());
			preparedStatement.setInt(3, tEmplea.getIdComponente());
			preparedStatement.setInt(4, tEmplea.getIdReparacion());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			return new TEmplea(-4);
		}
		return tEmplea;
	}

	@Override
	public TTrabaja modificarMecanico(TTrabaja tTrabaja) {

		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {
			preparedStatement = conection.prepareStatement("UPDATE trabaja SET horas =?");

			preparedStatement.setInt(1, tTrabaja.getHora());

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return new TTrabaja(0);
		}

		return tTrabaja;

	}

	@Override
	public Collection<Object> mostrar(int idReparacion) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM reparacion WHERE id_reparacion = ?");

			pstmt.setInt(1, idReparacion);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				ArrayList<Object> result = new ArrayList<Object>();
				result.add(new TReparacion(rs.getInt("id_reparacion"), rs.getInt("id_vehiculo"),
						rs.getString("fechainicio"), rs.getString("fechasalida"), rs.getString("averia"),
						rs.getFloat("presupuesto"), rs.getBoolean("actividad")));

				pstmt = con.prepareStatement("SELECT * FROM emplea WHERE id_reparacion = ?");

				pstmt.setInt(1, idReparacion);
				rs = pstmt.executeQuery();

				ArrayList<TEmplea> componentes = new ArrayList<TEmplea>();
				result.add(componentes);

				while (rs.next()) {
					componentes.add(new TEmplea(rs.getInt("id_reparacion"), rs.getInt("id_componente"),
							rs.getFloat("precio"), rs.getInt("cantidad")));
				}

				pstmt = con.prepareStatement("SELECT * FROM trabaja WHERE id_reparacion = ?");

				pstmt.setInt(1, idReparacion);
				rs = pstmt.executeQuery();

				ArrayList<TTrabaja> mecanicos = new ArrayList<TTrabaja>();
				result.add(mecanicos);

				while (rs.next()) {
					mecanicos.add(
							new TTrabaja(rs.getInt("id_reparacion"), rs.getInt("id_mecanico"), rs.getInt("horas")));
				}

				return result;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public Collection<TReparacion> listar() {
		ArrayList<TReparacion> resultado = null;
		PreparedStatement preparedStatement = null;

		try (Connection connection = DataBaseConnection.getConnection()) {
			preparedStatement = connection.prepareStatement("SELECT * FROM reparacion WHERE actividad = 1");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultado = new ArrayList<TReparacion>();
				do {
					resultado.add(new TReparacion(resultSet.getInt("id_reparacion"), resultSet.getInt("id_vehiculo"),
							resultSet.getDate("fechainicio").toString(), resultSet.getDate("fechaSalida").toString(),
							resultSet.getString("averia"), resultSet.getFloat("presupuesto"),
							resultSet.getBoolean("actividad")));
				} while (resultSet.next());
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}

		return resultado;
	}

	@Override
	public Collection<TReparacion> mostrarReparacionesVehiculo(int idVehiculo) {
		ArrayList<TReparacion> reparaciones = null;
		PreparedStatement preparedStatement = null;

		try (Connection connection = DataBaseConnection.getConnection()) {
			preparedStatement = connection.prepareStatement("SELECT * FROM reparacion WHERE id_vehiculo = ? AND actividad=1");
			preparedStatement.setInt(1, idVehiculo);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				reparaciones = new ArrayList<TReparacion>();
				
				do {
					reparaciones.add(new TReparacion(resultSet.getInt("id_reparacion"), resultSet.getInt("id_vehiculo"),
							resultSet.getDate("fechainicio").toString(), resultSet.getDate("fechaSalida").toString(),
							resultSet.getString("averia"), resultSet.getFloat("presupuesto")));
					
				} while (resultSet.next());
			}

		} catch (SQLException exception) {

		}

		return reparaciones;
	}
	
	public TTrabaja existeMecanico(int idMecanico){
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("SELECT * FROM trabaja WHERE id_mecanico = ?");

			pstmt.setInt(1, idMecanico);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new TTrabaja(rs.getInt("id_reparacion"), rs.getInt("id_mecanico"),
						rs.getInt("hora"));
			}
		} catch (SQLException exception) {
			return new TTrabaja(-4);
		}
		
		return null;
	}
	
	public TEmplea existeComponente(int idComponente){
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {

			pstmt = con.prepareStatement("SELECT * FROM emplea WHERE id_componente = ?");

			pstmt.setInt(1, idComponente);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new TEmplea(rs.getInt("id_reparacion"), rs.getInt("id_componente"),
						rs.getFloat("precio"), rs.getInt("cantidad"));
			}
		} catch (SQLException exception) {
			return new TEmplea(-4);
		}
		
		return null;
	}
}