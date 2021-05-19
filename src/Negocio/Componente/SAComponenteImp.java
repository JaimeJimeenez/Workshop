package Negocio.Componente;

import java.util.Collection;

import Integracion.Componente.DAOComponente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Proveedor.DAOProveedor;
import Negocio.Proveedor.TProveedor;

public class SAComponenteImp implements SAComponente {

	@Override
	public int alta(TComponente tComponente) {
		if(tComponente == null)
			return 0;
		if(tComponente.getPrecio() < 0)
			return 0;
		if(tComponente.getIdProveedor() < 0)
			return 0;
		//TODO Puedes preguntar si el stock puede ser cero
		DAOProveedor daop = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor proveedorLeido = daop.mostrar(tComponente.getIdProveedor());
		if(proveedorLeido == null)
			return 0;
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.leerPorMarcaModelo(tComponente.getMarca(), tComponente.getModelo());
		
		int resultado = -1;
		
		if(leido == null)
			return dao.alta(tComponente);
		if(!leido.isActivo())
			return dao.reactivar(tComponente);
		
		return resultado;
	}

	@Override
	public int baja(int id) {
		if(id <= 0)
			return 0;
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(id);
		
		int resultado;
		if (leido != null && !leido.isActivo())
			resultado = -1;
		else
			resultado = dao.baja(id);
		
		return resultado;
	}

	@Override
	public int modificar(TComponente tComponente) {
		if(tComponente.getId() <= 0)
			return 0;
		if(tComponente.getPrecio() > 0)
			return 0;
		if(tComponente.getIdProveedor() > 0)
			return 0;
		//TODO tenemos que crear un nuevo evento para cuando el id proveedor no esta en la BD
		DAOProveedor daop = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		TProveedor proveedorLeido = daop.mostrar(tComponente.getIdProveedor());
		if(proveedorLeido == null)
			return 0;
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(tComponente.getId());
		
		int resultado;
		
		if (leido == null || !leido.isActivo())
			resultado = -1;
		else if (dao.leerPorMarcaModelo(tComponente.getMarca(), tComponente.getModelo()) != null)
			resultado = -2;
		else
			resultado = dao.modificar(tComponente);
		
		return resultado;
	}

	@Override
	public TComponente mostrar(int id) {
		if(id <= 0)
			return new TComponente(0);
		
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente leido = dao.mostrar(id);
		
		if(leido == null || !leido.isActivo())
			return new TComponente(-1);
		else
			return leido;
	}

	@Override
	public Collection<TComponente> mostrarComponentesProveedor(int idProveedor) {
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		Collection<TComponente> resultado = dao.mostrarComponentesProveedor(idProveedor);
		
		return resultado;
	}

	@Override
	public Collection<TComponente> listar() {
		DAOComponente dao = FactoriaIntegracion.obtenerInstancia().crearComponente();
		Collection<TComponente> resultado = dao.listar();
		
		return resultado;
	}

}
