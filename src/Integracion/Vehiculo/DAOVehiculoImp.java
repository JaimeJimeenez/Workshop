package Integracion.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Vehiculo.TVehiculo;

public class DAOVehiculoImp implements DAOVehiculo {

	@Override
	public int alta(TVehiculo tVehiculo) {
		PreparedStatement preparedStatement = null;
		int id = -1;
		
		try (Connection conection = DataBaseConnection.getConnection()) {
			preparedStatement = conection.prepareStatement("INSERT INTO vehiculo(matricula, modelo, actividad, idCliente) VALUES(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, tVehiculo.getMatricula());
			preparedStatement.setString(2, tVehiculo.getModelo());
			preparedStatement.setBoolean(3, true);
			preparedStatement.setInt(4, tVehiculo.getIdCliente());
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) 
				id = resultSet.getInt(1);
			
		} catch (SQLException exception) {
			return -4;
		}
		return id;
	}

	@Override
	public int baja(int id) {
		PreparedStatement preparedStatement = null;

		try (Connection conection = DataBaseConnection.getConnection()) {

			preparedStatement = conection.prepareStatement("UPDATE vehiculo SET actividad=FALSE WHERE id_vehiculo=?", PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			return -4;
		}

		return id;
	}

	@Override
	public int modificar(TVehiculo tVehiculo) {
		try (Connection conection = DataBaseConnection.getConnection()) {
			PreparedStatement preparedStatement = null;
			
			if (!tVehiculo.getMatricula().equals("")) {
				preparedStatement = conection.prepareStatement("UPDATE vehiculo SET matricula = ? WHERE id_vehiculo=?");
				preparedStatement.setString(1, tVehiculo.getMatricula());
				preparedStatement.setInt(2, tVehiculo.getId());
				preparedStatement.executeUpdate();
			}
			
			if (!tVehiculo.getModelo().equals("")){
				preparedStatement = conection.prepareStatement("UPDATE vehiculo SET modelo = ? WHERE id_vehiculo=?");
				preparedStatement.setString(1, tVehiculo.getModelo());
				preparedStatement.setInt(2, tVehiculo.getId());
				preparedStatement.executeUpdate();
			}
			
			if (tVehiculo.getIdCliente() > 0){
				preparedStatement = conection.prepareStatement("UPDATE vehiculo SET IDCliente = ? WHERE id_vehiculo=?");
				preparedStatement.setInt(1, tVehiculo.getIdCliente());
				preparedStatement.setInt(2, tVehiculo.getId());
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException exception) {
			return -4;
		}
		
		return tVehiculo.getId();
	}

	@Override
	public TVehiculo mostrar(int id) {

		PreparedStatement preparedStatement = null;

		try (Connection connection = DataBaseConnection.getConnection()) {
			preparedStatement = connection.prepareStatement("SELECT * FROM vehiculo WHERE id_vehiculo = ?");

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return new TVehiculo(resultSet.getInt("id_vehiculo"), resultSet.getString("matricula"), resultSet.getString("modelo"), resultSet.getBoolean("actividad"), 
						resultSet.getInt("idCliente"));
			}

		} catch (SQLException exception) {
			return new TVehiculo(-4);
		}

		return null;
	}

	@Override
	public Collection<TVehiculo> listar() {
		ArrayList<TVehiculo> vehiculos = null;

		PreparedStatement preparedStatement = null;

		try (Connection connection = DataBaseConnection.getConnection()) {
			preparedStatement = connection.prepareStatement("SELECT * FROM vehiculo WHERE actividad = 1");

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				vehiculos = new ArrayList<TVehiculo>();
				
				do {
					vehiculos.add(new TVehiculo(resultSet.getInt("id_vehiculo"), resultSet.getString("matricula"), resultSet.getString("modelo"), resultSet.getBoolean("actividad"), 
							resultSet.getInt("idCliente")));
				} while (resultSet.next());
			}

		} catch (SQLException exception) {
			vehiculos = new ArrayList<TVehiculo>();
			vehiculos.add(new TVehiculo(-4));
		}

		return vehiculos;
	}

	@Override
	public TVehiculo leerPorMatricula(String matricula) {
		PreparedStatement preparedStatement = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			preparedStatement = con.prepareStatement("SELECT * FROM vehiculo WHERE matricula=?");

			preparedStatement.setString(1, matricula);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return new TVehiculo(resultSet.getInt("id_vehiculo"), resultSet.getString("matricula"), resultSet.getString("modelo"), resultSet.getBoolean("actividad"), 
						resultSet.getInt("idCliente"));
			}

		} catch (SQLException exception) {
			return new TVehiculo(-4);
		}

		return null;
	}

	@Override
	public int reactivar(int id) {
		try (Connection connection = DataBaseConnection.getConnection()) {
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement("UPDATE vehiculo SET actividad = 1 WHERE id_vehiculo=?");
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}
		return id;
	}

	@Override
	public Collection<TVehiculo> mostrarVehiculoCliente(int idCliente) {
		ArrayList<TVehiculo> vehiculos = null;

		PreparedStatement preparedStatement = null;

		try (Connection connection = DataBaseConnection.getConnection()) {
			preparedStatement = connection.prepareStatement("SELECT * FROM vehiculo WHERE actividad = 1 AND idCliente=?");

			preparedStatement.setInt(1, idCliente);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				vehiculos = new ArrayList<TVehiculo>();
				
				do {
					vehiculos.add(new TVehiculo(resultSet.getInt("id_vehiculo"), resultSet.getString("matricula"), resultSet.getString("modelo"), resultSet.getBoolean("actividad"),
							resultSet.getInt("idCliente")));
				} while (resultSet.next());
			}

		} catch (SQLException exception) {
			vehiculos = new ArrayList<TVehiculo>();
			vehiculos.add(new TVehiculo(-4));
		}

		return vehiculos;
	}

}
