package Negocio.Vehiculo;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TParticular;
import Negocio.FactoriaSA.FactoriaSA;

public class Mostrar {
	private static String TESTEO = "TESTVE";
	private static String MAT_TEST = "0000AAA";
	private static final String DNI = "68004439V";
	private static TVehiculo TVEHTEST = new TVehiculo(MAT_TEST, TESTEO, 59);
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", DNI, TESTEO);
	private static SAVehiculo saVeh;
	private static int idVeh, idCliente;
	private static SACliente saCliente;
	
	@BeforeClass
	public static void initClass() {
		saVeh = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();

		do {
			idCliente = saCliente.alta(TCLIENTE_TEST);
			if (idCliente == -1) idCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI).getId();
		} while (idCliente == -4);
		
		do {
			idVeh = saVeh.alta(new TVehiculo(MAT_TEST, TESTEO, 59));
			if (idVeh == -1) idVeh = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MAT_TEST).getId();
		} while (idVeh == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while(saVeh.baja(idVeh) == -4);
		while (saCliente.baja(idCliente) == -4);
	}
	
	@Test
	public void correcto() {
		TVehiculo resultado = saVeh.mostrar(idVeh);
		assertTrue(resultado.getId() > 0);
	}
	
	@Test
	public void incorrecto() {
		TVehiculo resultado = saVeh.mostrar(-idVeh);
		assertTrue(resultado.getId() == 0);
	}
	
	@Test
	public void noEncontrado() {
		TVehiculo resultado = saVeh.mostrar(99);
		assertTrue(resultado.getId() == -1);
	}

}
