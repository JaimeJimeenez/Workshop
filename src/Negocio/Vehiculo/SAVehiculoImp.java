package Negocio.Vehiculo;

import java.util.Collection;
import Negocio.DataCorrect;

import Integracion.Cliente.DAOCliente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Vehiculo.DAOVehiculo;
import Negocio.Cliente.TCliente;

public class SAVehiculoImp implements SAVehiculo {

	@Override
	public int alta(TVehiculo tVehiculo) {
		
		if (tVehiculo == null) 
			return 0;
		
		if (!matriculaCorrecta(tVehiculo.getMatricula()))
			return 0;
		
		if (!modeloCorrecto(tVehiculo.getModelo()))
			return 0;
		
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leidoCliente = daoCliente.mostrar(tVehiculo.getIdCliente());
		
		if (leidoCliente == null)//TODO  Dudoso
			return -2;
		if (!leidoCliente.isActivo())
			daoCliente.reactivar(leidoCliente);
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.leerPorMatricula(tVehiculo.getMatricula());
		
		int resultado = -1;
		
		if (leido == null)
			return dao.alta(tVehiculo);
		if (!leido.isActividad())
			return dao.reactivar(leido.getId());
		
		return resultado;
	}
	
	private boolean matriculaCorrecta(String matricula) {
		if (matricula.length() != 7)
			return false;
		
		for (int i = 0; i < 4; i++) 
			if (!Character.isDigit(matricula.charAt(i)))
				return false;
		
		
		for (int i = 4; i < matricula.length(); i++)
			if (!Character.isLetter(matricula.charAt(i)))
				return false;
		
		
		return true;
	}
	
	private boolean modeloCorrecto(String modelo) {
		return (modelo.length() > 0 && modelo.length() < 50);
	}

	@Override
	public int baja(int id) {
		
		if (id <= 0)
			return 0;
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(id);
		
		int resultado;
		
		if (leido == null || !leido.isActividad())
			return -1;
		else 
			resultado = dao.baja(id);
		
		return resultado;
	}

	@Override
	public int modificar(TVehiculo tVehiculo) {
		if (tVehiculo == null)
			return 0;
		
		if (tVehiculo.getIdCliente() == -1)
			return 0;
		
		if (tVehiculo.getId() == -1)
			return 0;
		
		if (!matriculaCorrecta(tVehiculo.getMatricula())) 
			return 0;
		
		if (!modeloCorrecto(tVehiculo.getModelo()))
			return 0;
		
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leidoCliente = daoCliente.mostrar(tVehiculo.getIdCliente());
		
		if (leidoCliente == null)
			return -2;
		if (!leidoCliente.isActivo())
			daoCliente.reactivar(leidoCliente);
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(tVehiculo.getId());
		
		int resultado = -1;
		
		if (leido == null || !leido.isActividad())
			return -1;
		else 
			resultado = dao.modificar(tVehiculo);
		
		return resultado;
	}

	@Override 
	public TVehiculo mostrar(int id) {
		if (id <= 0)
			return new TVehiculo(0);
		
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		TVehiculo leido = dao.mostrar(id);
		
		if (leido == null || !leido.isActividad())
			return new TVehiculo(-1);
		
		return leido;
	}

	@Override
	public Collection<TVehiculo> listar() {
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		return dao.listar();
	}

	@Override
	public Collection<TVehiculo> mostrarVehiculoCliente(int idCliente) {
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		return dao.mostrarVehiculoCliente(idCliente);
	}

}
