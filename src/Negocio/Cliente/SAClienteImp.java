package Negocio.Cliente;

import java.util.Collection;

import Integracion.Cliente.DAOCliente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.DataCorrect;
import Negocio.Vehiculo.TVehiculo;

public class SAClienteImp implements SACliente {

	@Override
	public int alta(TCliente tCliente) {
		if (tCliente == null)
			return 0;

		if (!DataCorrect.stringCorrecto(tCliente.getNombre()))
			return 0;

		if (!DataCorrect.stringSoloDigitosCorrecto(tCliente.getTelefono(), 9))
			return 0;

		if (tCliente instanceof TParticular && !DataCorrect.dniCorrecto(tCliente.getNif()) ) 
			return 0;
		
		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.leerPorNif(tCliente.getNif());
		
		int resultado = -1;
		
		if (leido == null)
			return dao.alta(tCliente);
		else if (leido.getId() == -4)
			return -4;
		else if (!leido.isActivo())
			return dao.reactivar(leido);

		return resultado;
	}

	@Override
	public int baja(int id) {
		if (id <= 0)
			return 0;

		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.mostrar(id);

		int resultado;
		if(leido == null)
			return -1;
		if (leido.getId() == -4)
			return -4;
		else if (!leido.isActivo())
			return -1;
		else if (clienteConVehiculo(leido.getId()) != -1)
			return -2;
		else
			resultado = dao.baja(id);

		return resultado;
	}

	private int clienteConVehiculo(int id) {
		Collection<TVehiculo> tVehiculo = FactoriaIntegracion.obtenerInstancia().crearVehiculo().mostrarVehiculoCliente(id);
		if (tVehiculo == null)
			return -1;
		return 0;
	}
	
	@Override
	public int modificar(TCliente tCliente) {
		
		if (tCliente == null)
			return 0;
		
		if (tCliente.getId() <= 0)
			return 0;

		if (!DataCorrect.stringCorrecto(tCliente.getNombre()))
			return 0;

		if (!DataCorrect.stringSoloDigitosCorrecto(tCliente.getTelefono(), 9))
			return 0;

		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.mostrar(tCliente.getId());

		int resultado;

		if (leido.getId() == -4)
			resultado = -4;
		else if (leido == null || !leido.isActivo())
			resultado = -1;
		else if (dao.leerPorNif(tCliente.getNif()) != null && dao.leerPorNif(tCliente.getNif()).getId() != leido.getId())
			resultado = -2;
		else
			resultado = dao.modificar(tCliente);

		return resultado;
	}

	@Override
	public TCliente mostrar(int id) {

		if (id <= 0)
			return new TCliente(0);

		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.mostrar(id);

		if (leido.getId() == -4)
			return new TCliente(-4);
		if (leido == null || !leido.isActivo())
			return new TCliente(-1);
		else
			return leido;
	}

	@Override
	public Collection<TCliente> listar() {
		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		Collection<TCliente> resultado = dao.listar();

		return resultado;
	}

}