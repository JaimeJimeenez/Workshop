package Negocio.Proveedor;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

public class Mostrar {
	private static String DIRECCION_TEST = "TESTPROVEEDOR";
	private static String NIF_TEST = "222222222";
	private static TProveedor TPROVEEDORTEST = new TProveedor(NIF_TEST,"913456743",DIRECCION_TEST);
	private static SAProveedor saProveedor;
	private static int idProveedor;
	@BeforeClass
	public static void initClass() {
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		idProveedor = saProveedor.alta(TPROVEEDORTEST);
		do
		{	
			TProveedor m = FactoriaIntegracion.obtenerInstancia().crearProveedor().leerPorNIF(NIF_TEST);
			idProveedor = m != null ? m.getId() : -1;

		}while(idProveedor == -4);
		
	}
	@AfterClass
	public static void destroyClass() {
		while(saProveedor.baja(idProveedor) == -4);
	}
	@Test
	public void correcto()
	{
		TProveedor resultado = saProveedor.mostrar(idProveedor);
		
		assertTrue(resultado.getId() > 0);
	}
	@Test
	public void noEncontrado()
	{
		saProveedor.baja(idProveedor);
		TProveedor resultado = saProveedor.mostrar(idProveedor);
		saProveedor.alta(TPROVEEDORTEST);
		assertTrue(resultado.getId() == -1);
	}
	@Test
	public void incorrecto()
	{
		TProveedor resultado = saProveedor.mostrar(-idProveedor);
		assertTrue(resultado.getId() == 0);
	}

}
