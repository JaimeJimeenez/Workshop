package Negocio.Especialidad;

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
	
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private SAEspecialidad saEspecialidad;
	@Parameters
	public static Iterable<Integer[]> getData()
	{
		return Arrays.asList(new Integer[][]{{1,0, 30}});
		
	}
	public Mostrar(int idCorrecto, int idIncorrecto, int idNoEncontrado)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado; 
	}
	@Before
	public void init()
	{
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();	
	}
	@Test
	public void correcto()
	{
		TEspecialidad resultado = saEspecialidad.mostrar(idCorrecto);
		assertTrue(resultado.getId() > 0);
	}
	@Test
	public void noEncontrado()
	{
		TEspecialidad resultado = saEspecialidad.mostrar(idNoEncontrado);
		assertTrue(resultado.getId() == -1);
	}
	@Test
	public void incorrecto()
	{
		TEspecialidad resultado = saEspecialidad.mostrar(idIncorrecto);
		assertTrue(resultado.getId() == 0);
	}
}
