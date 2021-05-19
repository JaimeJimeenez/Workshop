package Integracion.Reparacion;

import static org.junit.Assert.*;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TReparacion;

public class Reactivar {

	TReparacion tReparacion;
	
	@Test
	public void correcto()
	{
		DAOReparacion daoReparacion = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		assertTrue(daoReparacion.reactivar(tReparacion) == tReparacion.getId()); 
	}
	// No hemos podido realizar un test del fallo de reactivar debido a no encontrar la posibilidad de realizarlo

}