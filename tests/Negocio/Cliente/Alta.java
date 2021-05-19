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
public class Alta {
	
	private static final String DNI_CORRECTO = "05503905J";
	
	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][] {
			
			{new TCliente("Pedro", "911234789", DNI_CORRECTO),
				new TCliente("Pedro", "12345678", DNI_CORRECTO)
			}
		});
	}
	
	private TCliente tClienteCorrecto;
	private TCliente tClienteIncorrecto;
	private SACliente saCliente;
	
	public Alta(TCliente tClienteCorrecto, TCliente tClienteIncorrecto) {
		this.tClienteCorrecto = tClienteCorrecto;
		this.tClienteIncorrecto = tClienteIncorrecto;
	}
	
	@Before
	public void init() { saCliente = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = saCliente.alta(tClienteCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testRepetido() {
		int resultado = saCliente.alta(tClienteCorrecto);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCliente.alta(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
}
