package Negocio.Especialidad;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	private static final String TIPO_TEST = "TESTESPECIALIDAD";
	private static int idEspecialidad;
	private static SAEspecialidad saEspecialidad;
	@BeforeClass
	public static void initClass()
	{
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		idEspecialidad = saEspecialidad.alta(new TEspecialidad(TIPO_TEST));
	}
	@Test
	public void correcto()
	{
		Collection<TEspecialidad> lista = saEspecialidad.listar();
		assertTrue(lista != null && lista.iterator().next().getId() > 0); 
	}
	@AfterClass
	public static void destroyClass()
	{
		saEspecialidad.baja(idEspecialidad);
	}
}
