package Negocio.Componente;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.Componente.DAOComponente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Proveedor.DAOProveedor;
import Integracion.Reparacion.DAOReparacion;
import Negocio.DataCorrect;
import Negocio.Mecanico.TMecanico;
import Negocio.Proveedor.TProveedor;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TTrabaja;

public class SAComponenteImp implements SAComponente {

	@Override
	public int alta(TComponente tComponente) {
		if(tComponente == null)
			return 0;
		if(!DataCorrect.numeroMayorCero(tComponente.getPrecio()))
			return 0;
		if(!DataCorrect.numeroMayorCero(tComponente.getIdProveedor()))
			return 0;
		if(tComponente.getStock() < 0)
			return 0;
		
		int idProveedor = idProveedorExiste(tComponente.getIdProveedor());
		if (idProveedor < 0)
			return idProveedor;
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.leerPorMarcaModelo(tComponente.getMarca(), tComponente.getModelo());
		
		int resultado = -1;
		
		if(leido == null)
			return dao.alta(tComponente);
		
		if(leido.getId() == -4)
			return -4;
		
		if(!leido.isActivo())
			return dao.reactivar(leido);
		
		return resultado;
	}

	@Override
	public int baja(int id) {
		if(id <= 0)
			return 0;
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(id);

		if(leido == null)
			return -1;
		else if(leido.getId() == -4)
			return -4;
		else if (!leido.isActivo())
			return -1;
		
		int emplea = componenteEmpleado(id);
		if(emplea < 0)
			return emplea;
		else
			return dao.baja(id);
	}

	@Override
	public int modificar(TComponente tComponente) {
		if(tComponente == null)
			return 0;
		if(!DataCorrect.numeroMayorCero(tComponente.getId()))
			return 0;
		if(tComponente.getPrecio() < 0)
			return 0;
		if(tComponente.getStock() < 0)
			return 0;
		if(tComponente.getIdProveedor() < 0)
			return 0;
		
		int idProveedor = idProveedorExiste(tComponente.getIdProveedor());
		
		if(idProveedor < 0)
			return idProveedor;
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(tComponente.getId());
		
		int resultado;
		
		TComponente aux = dao.leerPorMarcaModelo(tComponente.getMarca(), tComponente.getModelo());
		
		if (leido == null)
			resultado = -1;
		else if (leido.getId() == -4)
			resultado = -4;
		else if (!leido.isActivo())
			resultado = -1;
		else if(aux != null && aux.getId() != tComponente.getId())
			resultado = -2;
		else{
			tComponente.setMarca(tComponente.getMarca().equals("") ? leido.getMarca() : tComponente.getMarca());
			tComponente.setModelo(tComponente.getModelo().equals("") ? leido.getModelo() : tComponente.getModelo());
			tComponente.setStock(tComponente.getStock() == 0 ? leido.getStock() : tComponente.getStock());
			tComponente.setPrecio(tComponente.getPrecio() == 0 ? leido.getPrecio() : tComponente.getPrecio());
			tComponente.setIdProveedor(tComponente.getIdProveedor() == 0 ? leido.getIdProveedor() : tComponente.getIdProveedor());
			resultado = dao.modificar(tComponente);
		}
		
		return resultado;
	}

	@Override
	public TComponente mostrar(int id) {
		if(!DataCorrect.numeroMayorCero(id))
			return new TComponente(0);
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(id);
		
		if(leido == null)
			return new TComponente(-1);
		else if(leido.getId() == -4)
			return new TComponente(-4);
		else if(!leido.isActivo())
			return new TComponente(-1);
		else
			return leido;
	}

	@Override
	public Collection<TComponente> mostrarComponentesProveedor(int idProveedor) {
		Collection<TComponente> resultado = new ArrayList<TComponente>();
		
		if(!DataCorrect.numeroMayorCero(idProveedor))
		{
			resultado.add(new TComponente(0));
			return resultado;
		}
		
		idProveedor = idProveedorExiste(idProveedor);
		if (idProveedor < 0) {
			resultado.add(new TComponente(idProveedor));

			return resultado;
		}
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		resultado = dao.mostrarComponentesProveedor(idProveedor);
		return resultado;
	}
	
	@Override
	public Collection<TComponente> listar() {
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		Collection<TComponente> resultado = dao.listar();
		
		return resultado;
	}

	
	private int idProveedorExiste(int idProveedor) {
		DAOProveedor daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor tProveedor = daoProveedor.mostrar(idProveedor);
		
		if (tProveedor == null)
			return -3;
		
		if(tProveedor.getId() == -4)
			return -4;
		
		if(!tProveedor.isActivo())
			return -3;

		return tProveedor.getId();
	}
	
	private int componenteEmpleado(int idComponente){
		DAOReparacion daoReparacion = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		TEmplea emplea = daoReparacion.existeComponente(idComponente);
		
		if(emplea == null)
			return idComponente;
		
		if(emplea.getIdReparacion() == -4)
			return -4;
		
		return -2;
	}
}
