package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	@Test
	public void correcto()
	{
		SAReparacion sa = FactoriaSA.obtenerInstancia().crearSAReparacion();
		TReparacion reparacion;
		Collection<TReparacion> col;
		do {
			col = sa.listar();
			reparacion = (TReparacion) col.toArray()[0];
		} while (reparacion.getId() == -4);
		
		assertTrue(col != null); 
	}
}
