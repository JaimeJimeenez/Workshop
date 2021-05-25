package Negocio.Cliente;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

public class Mostrar {
	
	private static String WEB_TEST = "testcliente.es";
	private static String NIF_TEST = "96172397J";
	private static TCliente TCLIENTETEST = new TParticular("TESTCLIENTE", "987854321", NIF_TEST, WEB_TEST);
	private static SACliente saCliente;
	private static int idCliente;
	
	@BeforeClass
	public static void initClass() {
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		idCliente = saCliente.alta(TCLIENTETEST);
		
		do {
			TCliente c = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(NIF_TEST);
			idCliente = c != null ? c.getId() : -1;
		} while (idCliente == -4);
	}
	
	@Test
	public void testCorrecto() {
		TCliente resultado = saCliente.mostrar(idCliente);
		assertTrue(resultado.getId() > 0);
	}
	
	@Test
	public void testNoEncontrado() {
		saCliente.baja(idCliente);
		TCliente resultado = saCliente.mostrar(idCliente);
		saCliente.alta(TCLIENTETEST);
		assertTrue(resultado.getId() == -1);
	}
	
	@Test
	public void testIncorrecto() {
		TCliente resultado = saCliente.mostrar(-idCliente);
		assertTrue(resultado.getId() == 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (saCliente.baja(idCliente) == -4);
	}
}
