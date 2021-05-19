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
public class Modificar {

	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][]{
			
		});
	}
	
	private TCliente tClienteCorrecto;
	private TCliente tClienteIncorrecto;
	private TCliente tClienteNoEncontrado;
	private TCliente tClienteRepetido;
	private SACliente saCliente;
	
	public Modificar(TCliente tClienteCorrecto, TCliente tClienteIncorrecto, TCliente tClienteNoEncontrado, TCliente tClienteRepetido) {
		this.tClienteCorrecto = tClienteCorrecto;
		this.tClienteIncorrecto = tClienteIncorrecto;
		this.tClienteNoEncontrado = tClienteNoEncontrado;
		this.tClienteRepetido = tClienteRepetido;
	}
	
	@Before
	public void init() { saCliente = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = saCliente.modificar(tClienteCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCliente.modificar(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testNoEncontrado() {
		int resultado = saCliente.modificar(tClienteNoEncontrado);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testRepetido() {
		int resultado = saCliente.modificar(tClienteRepetido);
		assertTrue(resultado == -2);
	}
}
