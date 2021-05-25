package Integracion.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TTrabaja;

public class ModificarMecanico {
	
	public static TTrabaja correcto;
	private static DAOReparacion dao;
	private int idReparacion;
	
	public ModificarMecanico() {
		correcto = new TTrabaja(1, 1, 600);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	

	@Before
	public void initTest() {
		while ((idReparacion = dao.altaMecanico(correcto).getIdReparacion()) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (dao.bajaMecanico(correcto).getIdReparacion() == -4);
	}
	
	
	@Test
	public void correcto() {
		TTrabaja idEsperado; 
		do {
			idEsperado = dao.modificarMecanico(new TTrabaja(1, 1, 100));
		} while (idEsperado.getIdReparacion() == -4);
		assertTrue(idEsperado.getIdReparacion() > 0);
	}
	
}