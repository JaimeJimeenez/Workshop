package Negocio.Vehiculo;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
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

public class Modificar {
	
	private static String TESTEO = "TESTVEHICULO";
	private static String TESTEO2 = "TESTVEHICULODOS";
	private static String MAT_TEST = "0000XXX";
	private static String MAT_TEST2 = "0000ZZZ";
	private static final String DNI = "68004439V";
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", DNI, TESTEO);
	private static TVehiculo TVEHTESTORIGINAL = new TVehiculo(MAT_TEST, TESTEO, 0);
	private static TVehiculo TVEHTEST = new TVehiculo(MAT_TEST, TESTEO, 0);
	private static TVehiculo TVEHTESTMOD = new TVehiculo(MAT_TEST2, TESTEO2, 0);
	
	private static SAVehiculo saVeh;
	private static SACliente saCliente;
	private static int idCliente;
	
	@BeforeClass
	public static void initClass() {
		saVeh = FactoriaSA.obtenerInstancia().crearSAVehiculo();;
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();

		do {
			idCliente = saCliente.alta(TCLIENTE_TEST);
			if (idCliente == -1) 
				idCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI).getId();
		} while (idCliente == -4);
		
		TVEHTEST.setIdCliente(idCliente);
		TVEHTESTMOD.setIdCliente(idCliente);

		int idVeh;
		do {
			idVeh = saVeh.alta(TVEHTEST);
			if (idVeh == -1) 
				idVeh = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MAT_TEST).getId();
		} while (idVeh == -4);
		TVEHTEST.setId(idVeh);
		
		int idVehMod;
		do {
			idVehMod = saVeh.alta(TVEHTESTMOD);
			if (idVehMod == -1) 
				idVehMod = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MAT_TEST2).getId();
		} while (idVehMod == -4);
		TVEHTESTMOD.setId(idVehMod);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (saVeh.baja(TVEHTEST.getId()) == -4);
		while(saVeh.baja(TVEHTESTMOD.getId()) == -4)
		while (saCliente.baja(idCliente) == -4);
	}
	
	@Test
	public void correcto() {
		TVEHTEST.setMatricula("0000YYY");
		int result = saVeh.modificar(TVEHTEST);
		TVEHTEST.setMatricula(TVEHTESTORIGINAL.getMatricula());
		while(saVeh.modificar(TVEHTEST) == -4);
		assertTrue(result > 0);
	}
	
	@Test
	public void incorrecto() {
		TVEHTEST.setId(-TVEHTEST.getId());
		int result = saVeh.modificar(TVEHTEST);
		TVEHTEST.setId(-TVEHTEST.getId());
		assertTrue(result == 0);
	}
	
	@Test
	public void noEncontrado() {
		while (saVeh.baja(TVEHTEST.getId()) == -4);
		
		int result = saVeh.modificar(TVEHTEST);
		
		while (saVeh.alta(TVEHTEST) == -4);
		assertTrue(result == -1);
	}
	
	@Test
	public void clienteNoEncontrado() {
		while (saVeh.baja(TVEHTEST.getId()) == -4);
		while(saVeh.baja(TVEHTESTMOD.getId()) == -4)
		while (saCliente.baja(idCliente) == -4);
		
		int result = saVeh.modificar(TVEHTEST);
		
		while (saCliente.alta(TCLIENTE_TEST) == -4);
		while (saVeh.alta(TVEHTEST) == -4);
		while(saVeh.alta(TVEHTESTMOD) == -4)
		assertTrue(result == -2);
	}
	
	@Test
	public void repetido() {
		TVEHTEST.setMatricula(TVEHTESTMOD.getMatricula());
		TVEHTEST.setModelo(TVEHTESTMOD.getModelo());
		int result =  saVeh.modificar(TVEHTEST);
		
		TVEHTEST.setMatricula(TVEHTESTORIGINAL.getMatricula());
		TVEHTEST.setModelo(TVEHTESTORIGINAL.getModelo());
		assertTrue(result == -3);
	}
}
