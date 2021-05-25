package Integracion.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;

public class Mostrar {
	private static final String TESTEO = "TESTREPARACION";
	public static TReparacion correcto;
	private static DAOReparacion dao;
	private static int idReparacion;
	
	public Mostrar() {
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
	public void correcto() {
		TReparacion result;
		Collection<Object> res;
		do {
			res = dao.mostrar(idReparacion);
			result = (TReparacion) res.toArray()[0];
		} while (result.getId() == -4);
		assertTrue(result.getId() > 0);
	}
	
}