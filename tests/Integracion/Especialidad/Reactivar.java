package Integracion.Especialidad;

import static org.junit.Assert.*;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class Reactivar {

	@Test
	public void correcto()
	{
		DAOEspecialidad daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		assertTrue(daoEspecialidad.reactivar(5) == 5); 
	}
	//No hemos podido realizar un test del fallo de la reactivar debido a no encontrar la posibilidad de realizarlo

}
