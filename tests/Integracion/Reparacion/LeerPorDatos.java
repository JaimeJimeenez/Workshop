package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TReparacion;

public class LeerPorDatos {
	private static final String TESTEO = "TESTREPARACION";
	public TReparacion tReparacionCorrecta;
	private static DAOReparacion dao;
	private static int idReparacion;
	
	public LeerPorDatos() {
		this.tReparacionCorrecta = new TReparacion(1,"2020-12-12","2021-12-12",TESTEO, 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Before
	public void initTest() {
		while ((idReparacion = dao.alta(tReparacionCorrecta)) == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	@Test
	public void correcto() {
		TReparacion idEsperado;
		do {
			idEsperado = dao.leerPorDatos(tReparacionCorrecta);
		} while (idEsperado.getId() == -4);
		assertTrue(idEsperado.getId() > 0 && idEsperado.getIdVehiculo() > 0);
	}
}
