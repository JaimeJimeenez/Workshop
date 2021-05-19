package Integracion.Proveedor;

import static org.junit.Assert.*;

import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Proveedor.SAProveedor;

public class Listar {
	@Test
	public void correcto()
	{
		SAProveedor saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		assertTrue(saProveedor.listar()!= null); 
	}
	//No hemos podido realizar un test del fallo de la listar debido a no encontrar la posibilidad de realizarlo
}