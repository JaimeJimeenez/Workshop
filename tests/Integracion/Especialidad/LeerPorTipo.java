package Integracion.Especialidad;

import static org.junit.Assert.*;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class LeerPorTipo {

	@Test
	public void correcto()
	{
		String tipo = "contrachapa";
		DAOEspecialidad daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		assertTrue(daoEspecialidad.leerPorTipo(tipo).getTipo().equals(tipo)); 
	}
	//No hemos podido realizar un test del fallo de la reactivar debido a no encontrar la posibilidad de realizarlo


}
