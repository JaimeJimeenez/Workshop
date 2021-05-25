package Negocio.Cliente;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

public class Baja {
	
	private static String CLIENTE_TEST = "TESTCLIENTE";
	private static String DNI_TEST = "06708032J";
	private static int idCliente;
	private static int idVehiculo;
	
	private static TCliente tCliente;
	private static SACliente saCliente;
	private static SAVehiculo saVehiculo;
	
	@BeforeClass
	public static void initClass() {
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		saVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		tCliente = new TParticular("Pepe", "924214451", DNI_TEST, "addidas.com/es");
		do {
			idCliente = saCliente.alta(tCliente);
			if (idCliente == -1) {
				TCliente c = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(tCliente.getNif());
				idCliente = c != null ? c.getId() : -1;
			}
		} while (idCliente == -4);
		tCliente.setId(idCliente);
	}
	
	@Before
	public void initTest() {
		while (saCliente.alta(tCliente) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCliente.baja(idCliente);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		int resultado = saCliente.baja(-idCliente);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testDatosNoEncontrados() {
		while (saCliente.baja(idCliente) == -4);
		int resultado = saCliente.baja(idCliente);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testVehiculoActivo() {
		do {
			TVehiculo TVEHICULOTEST = new TVehiculo("1234AEX", "Porsche", idCliente);
			idVehiculo = saVehiculo.alta(TVEHICULOTEST);
			
			if (idVehiculo == -1) {
				TVehiculo v = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(TVEHICULOTEST.getMatricula());
				idVehiculo = v.getId();
			}
		} while (idVehiculo == -4);
		int resultado = saCliente.baja(idCliente);
		while (saVehiculo.baja(idVehiculo) == -4);
		assertTrue(resultado == -2);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (saVehiculo.baja(idVehiculo) == -4);
		while (saCliente.baja(idCliente) == -4);
	}
}
