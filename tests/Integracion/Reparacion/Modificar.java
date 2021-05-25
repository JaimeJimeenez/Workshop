package Integracion.Reparacion;

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
import Negocio.Reparacion.TReparacion;

public class Modificar {
	
	private TReparacion corr;
	private static int idReparacion;
	private DAOReparacion dao;

	public Modificar() {
		this.corr = new TReparacion(1, "2020-11-11", "2020-12-12", "frenos", 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Before
	public void initTest() {
		while ((idReparacion = dao.alta(corr)) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	@Test
	public void correcto() {
		int idEsperado;
		do {
			idEsperado = dao.modificar( new TReparacion(idReparacion, 1, "2020-11-11", "2020-12-12", "ruedas", 100));
		} while (idEsperado == -4);
		assertTrue(idEsperado > 0);
	}
}