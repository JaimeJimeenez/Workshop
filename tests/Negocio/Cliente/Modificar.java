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

@RunWith(value = Parameterized.class)
public class Modificar {

	private static String CLIENTE_TEST = "TESTCLIENTE";
	private static String CLIENTE2_TEST = "TESTCLIENTEDOS";
	private static String DNI_TEST = "29677178W";
	private static String DNI_TEST2 = "52429896Q";
	private static int idCliente;
	private static int idCliente2;
	private static TCliente TCLIENTETEST = new TParticular("Pepe", "968735412", DNI_TEST, CLIENTE_TEST);
	private static TCliente TCLIENTETEST2 = new TParticular("Beatriz", "932165487", DNI_TEST2, CLIENTE2_TEST);
	private static SACliente saCliente;
	private TCliente tClienteIncorrecto;
	
	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][] {
			{new TParticular("Quique", 45, "91334546", "24730338W", "Calle de que", true)}, {null}});
		}
	
	
	@BeforeClass
	public static void initClass() {
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		do {
			idCliente = saCliente.alta(TCLIENTETEST);
			
			if (idCliente == -1)
				idCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(TCLIENTETEST.getNif()).getId();
		} while (idCliente == -4);
		TCLIENTETEST.setId(idCliente);

		do {
			idCliente2 = saCliente.alta(TCLIENTETEST2);
			if (idCliente2 == -1)
				idCliente2 = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(TCLIENTETEST2.getNif()).getId();
		} while (idCliente2 == -4);
		TCLIENTETEST2.setId(idCliente2);
		
	}
	
	public Modificar(TCliente tClienteIncorrecto) {
		this.tClienteIncorrecto = tClienteIncorrecto;
	}
	
	@Test
	public void testCorrecto() {
		int resultado = saCliente.modificar(TCLIENTETEST);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCliente.modificar(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testNoEncontrado() {
		while (saCliente.baja(idCliente) == -4);
		int resultado = saCliente.modificar(TCLIENTETEST);
		while (saCliente.alta(TCLIENTETEST) == -4);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testRepetido() {
		TCLIENTETEST.setNif(DNI_TEST2);
		int resultado = saCliente.modificar(TCLIENTETEST);
		TCLIENTETEST.setNif(DNI_TEST);
		assertTrue(resultado == -2);
	}
	
	@AfterClass
	public static void destroyClass() {
		saCliente.baja(idCliente);
		saCliente.baja(idCliente2);
	}
}
