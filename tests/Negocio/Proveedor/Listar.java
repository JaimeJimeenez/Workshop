package Negocio.Proveedor;

import static org.junit.Assert.*;

import org.junit.Test;
import Negocio.FactoriaSA.FactoriaSA;

public class Listar {

	@Test
	public void correcto()
	{
		SAProveedor saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		assertTrue(saProveedor.listar()!= null); 
	}
}
