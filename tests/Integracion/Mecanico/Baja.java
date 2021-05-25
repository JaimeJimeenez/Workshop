package Integracion.Mecanico;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Mecanico.TMecanico;

public class Baja {
	private static final String NOMBRE = "TESTMECANICODAO";

	private TMecanico mecanicoCorrecto;
	private static DAOMecanico daoMecanico;
	private static int idMecanico;

	public Baja() {
		this.mecanicoCorrecto = new TMecanico("123456789", 1500.5f, NOMBRE, 2, "654185630", "123456789012345678901234");
		daoMecanico = FactoriaIntegracion.obtenerInstancia().crearMecanico();
	}

	@Before
	public void initTest() {
		while ((idMecanico = daoMecanico.alta(mecanicoCorrecto)) == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaMecanico(idMecanico) == -4);
	}

	@Test
	public void testCorrecto() {
		int res = daoMecanico.baja(idMecanico);

		String message = "";

		if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res > 0);
	}
}
