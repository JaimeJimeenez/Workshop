package Integracion.Proveedor;

import static org.junit.Assert.*;

import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class Reactivar {

	@Test
	public void correcto()
	{
		DAOProveedor daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		assertTrue(daoProveedor.reactivar(3) == 3); 
	}
	//No hemos podido realizar un test del fallo de la reactivar debido a no encontrar la posibilidad de realizarlo


}
