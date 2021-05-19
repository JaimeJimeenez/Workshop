package Integracion.Especialidad;

import static org.junit.Assert.*;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class Listar {

	@Test
	public void correcto()
	{
		DAOEspecialidad daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		assertTrue(daoEspecialidad.listar()!= null); 
	}

}
