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
public class AltaParticular {
	@Parameters
	public static Iterable<TParticular[]> getData() {
		return Arrays.asList(new TParticular[][] {
			{
				new TParticular("Lucia", "912456879", "16607708R", "dondevivalucia"),
				new TParticular("Marcos", "91234", "76891378M", "hey")
			}
		});
	}
	
	private TParticular tParticularCorrecto;
	private TParticular tParticularIncorrecto;
	private SACliente saParticular;
	
	public AltaParticular(TParticular tParticularCorrecto, TParticular tParticularIncorrecto) {
		this.tParticularCorrecto = tParticularCorrecto;
		this.tParticularIncorrecto = tParticularIncorrecto;
	}
	
	@Before
	public void init() { saParticular = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = saParticular.alta(tParticularCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testRepetido() {
		int resultado = saParticular.alta(tParticularCorrecto);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testDatosIncorrectos() {
		int resultado = saParticular.alta(tParticularIncorrecto);
		assertTrue(resultado == 0);
	}
}
