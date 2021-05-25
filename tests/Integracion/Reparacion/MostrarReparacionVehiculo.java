package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

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


public class MostrarReparacionVehiculo {
	private static final String TESTEO = "TESTREPARACION";
	public static TReparacion correcto;
	private static DAOReparacion dao;
	private static int idReparacion;
	
	public MostrarReparacionVehiculo(){
		correcto = new TReparacion(1,"2020-12-12","2021-12-12",TESTEO, 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	@Before
	public void initTest() {
		while ((idReparacion = dao.alta(correcto)) == -4);
	}
		
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	@Test
	public void correcto()
	{
		Collection<TReparacion> res;
		TReparacion repara;
		do {
			res = dao.mostrarReparacionesVehiculo(1);
			repara = (TReparacion) res.toArray()[0];
		} while (repara.getId() == -4);
		assertTrue(res != null); 
	}
}
