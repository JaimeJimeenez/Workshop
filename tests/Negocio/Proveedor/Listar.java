package Negocio.Proveedor;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	private static String DIRECCION_TEST = "TESTPROVEEDOR";
	private static String NIF_TEST = "222222222";
	private static TProveedor TPROVEEDORTEST = new TProveedor(NIF_TEST,"913456743",DIRECCION_TEST);
	private static int idProveedor;
	private static SAProveedor saProveedor;
	@BeforeClass
	public static void initClass()
	{
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		idProveedor = saProveedor.alta(TPROVEEDORTEST);
	}
	@Test
	public void correcto()
	{
		SAProveedor saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		Collection<TProveedor> lista = saProveedor.listar();
		assertTrue(lista != null && lista.iterator().next().getId() > 0); 
	}
	@AfterClass
	public static void destroyClass()
	{
		saProveedor.baja(idProveedor);
	}
}
