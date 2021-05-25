package Negocio.Proveedor;

import java.util.Collection;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Proveedor.DAOProveedor;
import Negocio.DataCorrect;
import Negocio.Componente.TComponente;

public class SAProveedorImp implements SAProveedor{

	@Override
	public int alta(TProveedor tProveedor) {
		
		if (tProveedor == null)
			return 0;
		if (!DataCorrect.stringSoloDigitosCorrecto(tProveedor.getTelefono(), 9))
			return 0;
		
		DAOProveedor dao = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor leido = dao.leerPorNIF(tProveedor.getNIF());
		
		int resultado = -1;
		if (leido == null)
			return dao.alta(tProveedor);
		if(leido.getId() == -4)
			return -4;
		if (!leido.isActivo())
			return dao.reactivar(leido.getId());
		
		return resultado;
	}
	@Override
	public int baja(int id) {
		if (id <= 0)
			return 0;
		
		DAOProveedor dao = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor leido = dao.mostrar(id);
		Collection<TComponente> tComponente = FactoriaIntegracion.obtenerInstancia().crearComponente().mostrarComponentesProveedor(id);
		if (leido == null)
			return -1;
		if(leido.getId() == -4)
			return -4;
		if(!leido.isActivo())
			return -1;
		if (tComponente != null && tComponente.iterator().next().getId() == -4)
			return -4;
		if (tComponente != null && tComponente.iterator().next().getId() > 0)
			return -2;
	
		return dao.baja(id);
	}
	@Override
	public int modificar(TProveedor tProveedor) {
		
		if (tProveedor == null)
			return 0;
		if (!DataCorrect.numeroMayorCero(tProveedor.getId()))
			return 0;
		
		DAOProveedor dao = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor leido = dao.mostrar(tProveedor.getId());
		if (leido != null && leido.getId() == -4)
			return -4;
		
		if(tProveedor.getNIF().equals(""))
			tProveedor.setNIF(leido.getNIF());
		if(tProveedor.getDireccion().equals(""))
			tProveedor.setDireccion(leido.getDireccion());
		if(tProveedor.getTelefono().equals(""))
			tProveedor.setDireccion(leido.getDireccion());
		
		if (!DataCorrect.stringSoloDigitosCorrecto(tProveedor.getTelefono(), 9))
			return 0;
		TProveedor repetido = dao.leerPorNIF(tProveedor.getNIF());
		int resultado;
		resultado = -1;
		if (leido == null || !leido.isActivo())
			resultado = -1;
		else if ( repetido!= null && repetido.getId() != leido.getId())
			resultado = -2;
		else
			resultado = dao.modificar(tProveedor);
		
		return resultado;
	}

	@Override
	public TProveedor mostrar(int id) {
		if(id <= 0)
			return new TProveedor(0);
		
		DAOProveedor dao = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor leido = dao.mostrar(id);
		if (leido != null && leido.getId() == -4)
			return leido;
		
		if(leido == null || !leido.isActivo())
			return new TProveedor(-1);
		else
			return leido;
	}

	@Override
	public Collection<TProveedor> listar() {
		DAOProveedor dao = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		Collection<TProveedor> resultado = dao.listar();
		
		return resultado;
	}
	
	
}
