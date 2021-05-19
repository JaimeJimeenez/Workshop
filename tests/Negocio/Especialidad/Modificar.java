package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

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
	public static Iterable<TEspecialidad[]> getData()
	{
		return Arrays.asList(new TEspecialidad[][]{{new TEspecialidad("fusibles", 4),new TEspecialidad("pint ura", 2), new TEspecialidad("noencontrado", 30), new TEspecialidad("luces",3)}});
	}
	
	private SAEspecialidad saEspecialidad;
	private TEspecialidad tEspecialidadCorrecta;
	private TEspecialidad tEspecialidadIncorrecto;
	private TEspecialidad tEspecialidaNoEncontrado;
	private TEspecialidad tEspecialidadRepetido;
	public Modificar(TEspecialidad tEspecialidadCorrecta, TEspecialidad tEspecialidadIncorrecto, TEspecialidad tEspecialidaNoEncontrado, TEspecialidad tEspecialidadRepetido)
	{
		this.tEspecialidadCorrecta = tEspecialidadCorrecta;
		this.tEspecialidadIncorrecto = tEspecialidadIncorrecto;
		this.tEspecialidaNoEncontrado = tEspecialidaNoEncontrado;
		this.tEspecialidadRepetido = tEspecialidadRepetido;
		
	}
	@Before
	public void init()
	{
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();	
	}
	@Test
	public void correcto()
	{
		int result =  saEspecialidad.modificar(tEspecialidadCorrecta);
		assertTrue(result > 0);
	}
	@Test
	public void incorrecto()
	{
		int result =  saEspecialidad.modificar(tEspecialidadIncorrecto);
		assertTrue(result == 0);
	}
	@Test
	public void noEncontrado()
	{
		int result =  saEspecialidad.modificar(tEspecialidaNoEncontrado);
		assertTrue(result == -1);
	}
	@Test
	public void repetido()
	{
		int result =  saEspecialidad.modificar(tEspecialidadRepetido);
		assertTrue(result == -2);
	}
}
