package Integracion.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Integracion.DataBaseConnection;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.TEspecialidad;

public class DAOClienteImp implements DAOCliente{

	@Override
	public int alta(TCliente tCliente) {
		PreparedStatement pstmt = null;
		int id = -1;
		
		try (Connection con = DataBaseConnection.getConnection()) {
			String values;
			boolean is_empresa = tCliente instanceof TEmpresa;
			if(is_empresa)
				values = "INSERT INTO cliente(id_cliente, actividad, nombre, telefono, tipo_cliente, nif, pagina_web) VALUES(?, ?, ?, ?, ?, ?, ?)";
			else
				values = "INSERT INTO cliente(id_cliente, actividad, nombre, telefono, tipo_cliente, dni, direccion) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(values, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, tCliente.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, tCliente.getNombre());
			pstmt.setString(4, tCliente.getTelefono());
			pstmt.setString(6, tCliente.getNif());
			if(is_empresa)
			{	
				pstmt.setString(5, "E");
				pstmt.setString(7, ((TEmpresa) tCliente).getPaginaWeb());
			}
			else
			{
				pstmt.setString(5, "P");
				pstmt.setString(7, ((TParticular) tCliente).getDireccion());
			}

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

			pstmt = con.prepareStatement("UPDATE cliente SET actividad=FALSE WHERE id_cliente=?", PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}

		return id;
	}

	@Override
	public int modificar(TCliente tCliente) {
		PreparedStatement pstmt = null;
		int id = -1;
		
		try (Connection con = DataBaseConnection.getConnection()) {
			String values;
			boolean is_empresa = tCliente instanceof TEmpresa;
			
			if(is_empresa)
				values = "UPDATE cliente SET nombre=?, telefono=?, tipo_cliente=?, nif=?, pagina_web=? WHERE id_cliente=?";
			else//
				values = "UPDATE cliente SET nombre=?, telefono=?, tipo_cliente=?, dni=?, direccion=? WHERE id_cliente=?";
			
			pstmt = con.prepareStatement(values, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, tCliente.getNombre());
			pstmt.setString(2, tCliente.getTelefono());
			pstmt.setString(4, tCliente.getNif());
			if(is_empresa)
			{
				
				pstmt.setString(3, "E");
				pstmt.setString(5, ((TEmpresa) tCliente).getPaginaWeb());
			}
			else
			{
				pstmt.setString(3, "P");
				pstmt.setString(5, ((TParticular) tCliente).getDireccion());
			}
			pstmt.setInt(6, tCliente.getId());

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			return -4;
		}

		return tCliente.getId();
	}

	@Override
	public TCliente mostrar(int id) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if(rs.getString("tipo_cliente").equals("P"))
					return new TParticular(rs.getString("nombre"), rs.getInt("id_cliente"), rs.getString("telefono"), 
							rs.getString("dni"), rs.getString("direccion"), rs.getBoolean("actividad"));
				else
					return new TEmpresa(rs.getString("nombre"), rs.getString("telefono"),rs.getInt("id_cliente"),
							rs.getString("nif"), rs.getString("pagina_web"), rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TCliente(-4);
		}

		return null;
	}

	@Override
	public Collection<TCliente> listar() {
		ArrayList<TCliente> resultado = null;

		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM cliente WHERE actividad = 1");

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado = new ArrayList<TCliente>();
				
				do {
					TCliente curr;
					if(rs.getString("tipo_cliente").equals("P"))
						curr= new TParticular(rs.getString("nombre"), rs.getInt("id_cliente"), rs.getString("telefono"), 
								rs.getString("dni"), rs.getString("direccion"), rs.getBoolean("actividad"));
					else
						curr = new TEmpresa(rs.getString("nombre"), rs.getString("telefono"),rs.getInt("id_cliente"),
								rs.getString("nif"), rs.getString("pagina_web"), rs.getBoolean("actividad"));
					resultado.add(curr);
				} while (rs.next());
			}

		} catch (SQLException e) {
			resultado = new ArrayList<>();
			resultado.add(new TCliente(-4));
		}

		return resultado;
	}

	@Override
	public TCliente leerPorNif(String nif) {
		PreparedStatement pstmt = null;

		try (Connection con = DataBaseConnection.getConnection()) {
			pstmt = con.prepareStatement("SELECT * FROM cliente WHERE dni = ? OR nif = ?");

			pstmt.setString(1, nif);
			pstmt.setString(2, nif);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if(rs.getString("tipo_cliente").equals("P"))
					return new TParticular(rs.getString("nombre"), rs.getInt("id_cliente"), rs.getString("telefono"), 
							rs.getString("dni"), rs.getString("direccion"), rs.getBoolean("actividad"));
				else
					return new TEmpresa(rs.getString("nombre"), rs.getString("telefono"),rs.getInt("id_cliente"),
							rs.getString("nif"), rs.getString("pagina_web"), rs.getBoolean("actividad"));
			}

		} catch (SQLException e) {
			return new TCliente(-4);
		}

		return null;
	}

	@Override
	public int reactivar(TCliente tCliente) {
		PreparedStatement pstmt = null;
		int id = -1;
		
		try (Connection con = DataBaseConnection.getConnection()) {
			String values;
			boolean is_empresa = tCliente instanceof TEmpresa;
			
			if(is_empresa)
				values = "UPDATE cliente SET actividad=?, nombre=?, telefono=?, tipo_cliente=?, nif=?, pagina_web=? WHERE id_cliente=?";
			else//
				values = "UPDATE cliente SET  actividad=?, nombre=?, telefono=?, tipo_cliente=?, dni=?, direccion=? WHERE id_cliente=?";
			
			pstmt = con.prepareStatement(values, PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setBoolean(1, true);
			pstmt.setString(2, tCliente.getNombre());
			pstmt.setString(3, tCliente.getTelefono());
			pstmt.setString(5, tCliente.getNif());
			if(is_empresa)
			{
				
				pstmt.setString(4, "E");
				pstmt.setString(6, ((TEmpresa) tCliente).getPaginaWeb());
			}
			else
			{
				pstmt.setString(4, "P");
				pstmt.setString(6, ((TParticular) tCliente).getDireccion());
			}
			pstmt.setInt(7, tCliente.getId());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException exception) {
			return -4;
		}

		return tCliente.getId();
	}

}
