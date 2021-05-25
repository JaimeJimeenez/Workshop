package Integracion.Vehiculo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class Listar {

	@Test
	public void correcto() {
		DAOVehiculo dao = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		assertTrue(dao.listar()!= null); 
	}

}