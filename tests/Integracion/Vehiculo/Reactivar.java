package Integracion.Vehiculo;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Vehiculo.TVehiculo;

public class Reactivar {
	
	private static final TVehiculo TESTVEH = new TVehiculo("0000YYY", "TESTVEHICULO_DAO", 59);
	private static int idVeh;
	private static DAOVehiculo daoVeh;
	
	@BeforeClass
	public static void initClass() {
		daoVeh = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		do{
			idVeh = daoVeh.alta(TESTVEH);
		}
		while(idVeh == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaVehiculo(idVeh) == -4);
	}
	
	@Test
	public void correcto() {
		int result = daoVeh.reactivar(idVeh);
		assertTrue(result > 0);
	}

}