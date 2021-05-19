package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.Especialidad.SAEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Baja {
	
	@Parameters
	public static Iterable<Integer[]>getData()
	{		
		return Arrays.asList(new Integer[][]
				{{2, 0, 2147483647, 1}, 
				});
	}
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private int idMecanicoActivo;
	private SAEspecialidad saEspecialidad;
	public Baja(int idCorrecto, int idIncorrecto, int idNoEncontrado, int idMecanicoActivo)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado;
		this.idMecanicoActivo = idMecanicoActivo;
	}
	@Before
	public void init()
	{
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();	
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saEspecialidad.baja(idCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saEspecialidad.baja(idIncorrecto);
		assertTrue(resultado == 0);
	}
	@Test
	public void testDatosNoEncontrado()
	{
		int resultado = saEspecialidad.baja(idNoEncontrado);
		System.out.println(resultado);
		assertTrue(resultado == -1);
	}
	@Test
	public void testMecanicoActivo()
	{
		int resultado = saEspecialidad.baja(idMecanicoActivo);
		assertTrue(resultado == -2);
	}
}
