package Negocio.Cliente;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Mostrar {

	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{
			{ 4, 0, 100 }
		});
	}
	
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private SACliente saCliente;
	
	public Mostrar(int idCorrecto, int idIncorrecto, int idNoEncontrado) {
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado;
	}
	
	@Before
	public void init() { saCliente = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	@Test
	public void testCorrecto() {
		TCliente resultado = saCliente.mostrar(idCorrecto);
		assertTrue(resultado.getId() > 0);
	}
	
	@Test
	public void testNoEncontrado() {
		TCliente resultado = saCliente.mostrar(idNoEncontrado);
		assertTrue(resultado.getId() == -1);
	}
	
	@Test
	public void testIncorrecto() {
		TCliente resultado = saCliente.mostrar(idIncorrecto);
		assertTrue(resultado.getId() == 0);
	}
}
