package Negocio.Cliente;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Negocio.FactoriaSA.FactoriaSA;

public class Listar {

	private static String DIRECCION_TEST = "TESTCLIENTE";
	private static String DNI_TEST = "V34430645";
	private static TCliente TCLIENTETEST = new TParticular("marca", "918765432", DNI_TEST, DIRECCION_TEST);
	private static int idCliente;
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initClass(){
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		idCliente = saCliente.alta(TCLIENTETEST);
	}
	
	@Test
	public void correcto() {
		SACliente saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		assertTrue(saCliente.listar() != null && saCliente.listar().iterator().next().getId() > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		saCliente.baja(idCliente);
	}
}

