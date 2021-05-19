package Negocio.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Baja {
	
	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{
			{4, 0, 100 }
		});
	}
	
	private int idCorrecto;
	private int idIncorreto;
	private int idNoEncontrado;
	
	private SACliente saCliente;
	
	public Baja(int idCorrecto, int idIncorrecto, int idNoEncontrado) {
		
	}
	
	@Before
	public void init() { saCliente = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	public void testCorrecto() {
		int resultado = saCliente.baja(idCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		int resultado = saCliente.baja(idIncorreto);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testDatosNoEncontrados() {
		int resultado = saCliente.baja(idNoEncontrado);
		assertTrue(resultado == -1);
	}
	
}
