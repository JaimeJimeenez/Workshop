package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class Listar {

	@Test
	public void correcto()
	{
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		assertTrue(dao.listar()!= null); 
	}

}