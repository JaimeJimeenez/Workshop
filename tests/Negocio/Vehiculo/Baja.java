package Negocio.Vehiculo;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

public class Baja {
	private static final String TESTEO = "TESTVEHICULO";
	private static final String MATRICULA = "0000XXX";
	private static final String DNI = "68004439V";
	private static int idVehiculo, idCliente;
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", DNI, TESTEO);
	private static SAVehiculo sa;
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initClass() {
		sa = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();

		do {
			idCliente = saCliente.alta(TCLIENTE_TEST);
			if (idCliente == -1) idCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI).getId();
		} while (idCliente == -4);
		
		do {
			idVehiculo = sa.alta(new TVehiculo(MATRICULA, TESTEO, 59));
			if (idVehiculo == -1) idVehiculo = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MATRICULA).getId();
		} while (idVehiculo == -4);
	}
	
	@Before
	public void initTest() {
		sa = FactoriaSA.obtenerInstancia().crearSAVehiculo();
	}
	
	@AfterClass
	public static void destroyClass() {
		sa.baja(idVehiculo);
		while (saCliente.baja(idCliente) == -4);
	}
		
	@Test
	public void testCorrecto() {
		int resultado = sa.baja(idVehiculo);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testFormatoDatosIncorrectos() {
		int resultado = sa.baja(-1000);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = sa.baja(1000);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testIDInactiva() {
		sa.baja(idVehiculo);
		int resultado = sa.baja(idVehiculo);
		assertTrue(resultado == -2);
	}
	
}
