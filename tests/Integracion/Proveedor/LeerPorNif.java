package Integracion.Proveedor;

import static org.junit.Assert.*;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class LeerPorNif {

	@Test
	public void correcto()
	{
		String nif = "987654321";
		DAOProveedor daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		assertTrue(daoProveedor.leerPorNIF(nif).getNIF().equals(nif)); 
	}
	//No hemos podido realizar un test del fallo de la reactivar debido a no encontrar la posibilidad de realizarlo


}
