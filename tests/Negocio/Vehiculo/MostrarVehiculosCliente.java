package Negocio.Vehiculo;

import static org.junit.Assert.*;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.TMecanico;

public class MostrarVehiculosCliente {
	private static String TESTEO = "TESTVE";
	private static String MAT_TEST = "0000XXX";
	private static final String DNI = "68004439V";
	private static TVehiculo TVEHTEST = new TVehiculo(MAT_TEST, TESTEO, 59);
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", DNI, TESTEO);
	private static SAVehiculo saVeh;
	private static SACliente saCliente;
	private static int idVeh, idCliente;
	private Collection<TVehiculo> listaVeh;
	
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
			idVeh = saVeh.alta(new TVehiculo(MAT_TEST, TESTEO, idCliente));
			if (idVeh == -1) 
				idVeh = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MAT_TEST).getId();
		} while (idVeh == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while(saVeh.baja(idVeh) == -4);
		while (saCliente.baja(idCliente) == -4);
	}
	
	@Test
	public void correcto() {
		Collection<TVehiculo> lista = saVeh.mostrarVehiculoCliente(idCliente);
		assertTrue(lista != null && lista.iterator().next().getId() > 0); 
	}
	
	@Test
	public void testIncorrecto() {
		listaVeh = saVeh.mostrarVehiculoCliente(-idCliente);
		assertTrue(listaVeh == null); 
	}
	
	

}
