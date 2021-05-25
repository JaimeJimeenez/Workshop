package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TReparacion;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

public class Baja {
	private static final String TESTEO = "TESTREPARACION";
	private static DAOReparacion dao;
	private static int idReparacion;
	TReparacion corr;
	public Baja(){
		this.corr = new TReparacion(1,"2020-12-12","2021-12-12",TESTEO, 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Before
	public void initTest()
	{
		do {
			idReparacion = dao.alta(corr);
		} while (idReparacion == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	
	@Test
	public void correcto() {
		int idEsperado;
		do {
			idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().baja(idReparacion);
		} while (idEsperado == -4);
		assertTrue(idEsperado > 0);
	}
	//No hemos podido realizar un test del fallo de la baja debido a no encontrar la posibilidad de realizarlo
}
