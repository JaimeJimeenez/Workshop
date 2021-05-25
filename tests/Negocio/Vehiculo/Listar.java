package Negocio.Vehiculo;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	private static String MARCA_TEST = "TESTVEHICULO";
	private static String MAT_TEST = "0000XXX";
	private static TVehiculo TVEHTEST = new TVehiculo(MAT_TEST, MARCA_TEST, 59);
	private static int idVeh;
	private static SAVehiculo saVeh;
	
	@BeforeClass
	public static void initClass() {
		saVeh = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		idVeh = saVeh.alta(TVEHTEST);
	}
	
	@Test
	public void correcto() {
		SAVehiculo saVeh = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		Collection<TVehiculo> lista = saVeh.listar();
		assertTrue(lista != null && lista.iterator().next().getId() > 0); 
	}
	
	@AfterClass
	public static void destroyClass() {
		saVeh.baja(idVeh);
	}
}
