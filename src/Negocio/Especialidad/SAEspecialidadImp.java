/**
 * 
 */
package Negocio.Especialidad;

import java.util.Collection;

import Integracion.Especialidad.DAOEspecialidad;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Mecanico.DAOMecanico;
import Negocio.DataCorrect;
import Negocio.Mecanico.TMecanico;

public class SAEspecialidadImp implements SAEspecialidad {
	
	public int alta(TEspecialidad tEspecialidad) {
		if(tEspecialidad == null)
			return 0;
		
		if(!DataCorrect.stringCorrecto(tEspecialidad.getTipo()))
			return 0;
		
		DAOEspecialidad dao = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		TEspecialidad leido = dao.leerPorTipo(tEspecialidad.getTipo());
		int resultado = -1;
		
		if(leido == null)
			return dao.alta(tEspecialidad);
		if(leido.getId() == -4)
			return leido.getId();
		if(!leido.isActivo())
			return dao.reactivar(leido.getId());
		
		return resultado;
	}
	
	public int baja(int id) {
		if(!DataCorrect.numeroMayorCero(id))
			return 0;
		
		DAOEspecialidad dao = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		TEspecialidad leido = dao.mostrar(id);
		
		DAOMecanico daoM = FactoriaIntegracion.obtenerInstancia().crearMecanico();
		Collection<TMecanico> tMecanico = daoM.mostrarMecanicosEspecialidad(id);
		
	
		if(tMecanico != null)
			return -2;
		
		if(leido == null)
			return -1;
		else if(leido.getId() == -4)
			return -4;
		else if (!leido.isActivo())
			return -1;
		else
			return dao.baja(id);
	}

	
	public int modificar(TEspecialidad tEspecialidad) {
		if(!DataCorrect.numeroMayorCero(tEspecialidad.getId()))
			return 0;
		if(!DataCorrect.stringCorrecto(tEspecialidad.getTipo()))
			return 0;
		
		DAOEspecialidad dao = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();	
		TEspecialidad leido = dao.mostrar(tEspecialidad.getId());
		
		int resultado;
		
		if (leido == null)
			resultado = -1;
		else if(leido.getId() == -4)
			resultado = -4;
		else if(!leido.isActivo())
			resultado = -1;
		else if(dao.leerPorTipo(tEspecialidad.getTipo()) != null)
			resultado = -2;
		else
			resultado = dao.modificar(tEspecialidad);
		
		return resultado;
	}

	
	public TEspecialidad mostrar(int id) {
		if(!DataCorrect.numeroMayorCero(id))
			return new TEspecialidad(0);
		
		DAOEspecialidad dao = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		TEspecialidad leido = dao.mostrar(id);
		
		if(leido == null || !leido.isActivo())
			return new TEspecialidad(-1);
		else
			return leido;
	}

	
	public Collection<TEspecialidad> listar() {
		DAOEspecialidad dao = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		Collection<TEspecialidad> resultado = dao.listar();
		
		return resultado;
	}

}