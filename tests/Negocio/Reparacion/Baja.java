package Negocio.Reparacion;

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
	public static Iterable<Integer[]>getData()
	{		
		return Arrays.asList(new Integer[][]
				{{5, 0, 21}, 
				});
	}
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private SAReparacion sa;
	public Baja(int idCorrecto, int idIncorrecto, int idNoEncontrado)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado;
	}
	@Before
	public void init()
	{
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();	
	}
	@Test
	public void testCorrecto()
	{
		int resultado = sa.baja(idCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = sa.baja(idIncorrecto);
		assertTrue(resultado == 0);
	}
	@Test
	public void testDatosNoEncontrado()
	{
		int resultado = sa.baja(idNoEncontrado);
		assertTrue(resultado == -1);
	}

}
