package Negocio.Especialidad;

import static org.junit.Assert.*;

import org.junit.Test;

import Negocio.FactoriaSA.FactoriaSA;

public class Listar {
	
	@Test
	public void correcto()
	{
		SAEspecialidad saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		assertTrue(saEspecialidad.listar()!= null); 
	}
	
}
