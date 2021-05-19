package Negocio.Cliente;

import java.util.Collection;

import Integracion.Cliente.DAOCliente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.DataCorrect;

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

		if (tCliente instanceof TEmpresa && !cifCorrecto(tCliente.getNif()))
			return 0;
		
		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.leerPorNif(tCliente.getNif());

		int resultado = -1;

		if (leido == null)
			return dao.alta(tCliente);
		if (!leido.isActivo())
			return dao.reactivar(tCliente);

		return resultado;
	}

	private boolean cifCorrecto(String cif){
		return true;
	}

	@Override
	public int baja(int id) {
		if (id <= 0)
			return 0;

		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente leido = dao.mostrar(id);

		int resultado;

		if (leido == null || !leido.isActivo())
			resultado = -1;
		else
			resultado = dao.baja(id);

		return resultado;
	}

	@Override
	public int modificar(TCliente tCliente) {

		if (tCliente.getId() <= 0)
			return 0;

		if (!DataCorrect.stringCorrecto(tCliente.getNombre()))
			return 0;

		if (!DataCorrect.stringSoloDigitosCorrecto(tCliente.getTelefono(), 9))
			return 0;

		DAOCliente dao = FactoriaIntegracion.obtenerInstancia().crearCliente();

		TCliente leido = dao.mostrar(tCliente.getId());

		int resultado;

		if (leido == null || !leido.isActivo())
			resultado = -1;
		else if (dao.leerPorNif(tCliente.getNif()) != null)
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