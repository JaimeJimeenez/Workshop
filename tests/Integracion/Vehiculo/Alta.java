package Integracion.Vehiculo;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Vehiculo.TVehiculo;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<TVehiculo[]>getData() {
		return Arrays.asList(new TVehiculo[][]
				{{new TVehiculo("0000YYY", "TESTVEHICULO_DAO", 59)}
				});
	}
	
	private TVehiculo tVehiculoCorrecto;
	private DAOVehiculo daoVehiculo;
	private static int idVeh;
	
	public Alta(TVehiculo tVehiculoCorrecto) {
		this.tVehiculoCorrecto = tVehiculoCorrecto;
	}
	
	@Before 
	public void init() {
		daoVehiculo = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
	}
	
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaVehiculo(idVeh) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = daoVehiculo.alta(tVehiculoCorrecto);
		idVeh = resultado;
		assertTrue(resultado > 0);
	}
	
}
