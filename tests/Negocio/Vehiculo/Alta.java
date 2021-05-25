package Negocio.Vehiculo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TParticular;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	private static String TESTEO = "TESTVEHICULO";
	private static String MAT_TEST = "0000XXX";
	private static final String DNI = "68004439V";
	private static TVehiculo TVEHTEST = new TVehiculo(MAT_TEST, TESTEO, 59);
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", DNI, TESTEO);
	
	
	@Parameters
	public static Iterable<TVehiculo[]> getData() {
		return Arrays.asList(new TVehiculo[][] {
			{TVEHTEST,
				new TVehiculo("", TESTEO, 59), 
				new TVehiculo(MAT_TEST, TESTEO, 1000)
				}});
	}
	
	private TVehiculo tVehCorrecto;
	private TVehiculo tVehIncorrecto;
	private TVehiculo tCliNoExiste;
	private static int idVeh, idCliente;
	private static SAVehiculo saVeh;
	private static SACliente saCliente;
	
	public Alta(TVehiculo tVehCorrecto, TVehiculo tVehIncorrecto, TVehiculo tCliNoExiste) {
		this.tVehCorrecto = tVehCorrecto;
		this.tVehIncorrecto = tVehIncorrecto;
		this.tCliNoExiste = tCliNoExiste;
	}
	
	@BeforeClass
	public static void initClass() {
		saVeh = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();

		do {
			idCliente = saCliente.alta(TCLIENTE_TEST);

			if (idCliente == -1)
				idCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI).getId();
						
		} while (idCliente == -4);

		do {
			TVehiculo m = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MAT_TEST);
			idVeh = m != null ? m.getId() : -1;
		} while (idVeh == -4);
	}
	
	@Before
	public void init() {
		if (idVeh > 0)	
			while(saVeh.baja(idVeh) == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (saVeh.baja(idVeh) == -4);
		while (saCliente.baja(idCliente) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saVeh.alta(tVehCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		int resultado = saVeh.alta(tVehIncorrecto);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testRepetido() {
		saVeh.alta(tVehCorrecto);
		int resultado = saVeh.alta(tVehCorrecto);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testClienteNoExiste() {
		int resultado = saVeh.alta(tCliNoExiste);
		assertTrue(resultado == -2);
	}
}
