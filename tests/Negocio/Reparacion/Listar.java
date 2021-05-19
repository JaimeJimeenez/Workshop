package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	@Test
	public void correcto()
	{
		SAReparacion sa = FactoriaSA.obtenerInstancia().crearSAReparacion();
		assertTrue(sa.listar()!= null); 
	}
}
