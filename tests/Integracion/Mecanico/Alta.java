package Integracion.Mecanico;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Mecanico.TMecanico;

public class Alta {
	private static final String NOMBRE = "TESTMECANICODAO";

	private TMecanico mecanicoCorrecto;
	private static DAOMecanico daoMecanico;
	private static int idMecanico;

	public Alta() {
		this.mecanicoCorrecto = new TMecanico("123456789", 1500.5f, NOMBRE, 2, "654185630", "123456789012345678901234");
		daoMecanico = FactoriaIntegracion.obtenerInstancia().crearMecanico();
	}

	@AfterClass
	public static void destroyClass() {
		while (idMecanico > 0 && Utility.bajaFisicaMecanico(idMecanico) == -4);
	}

	@Test
	public void testCorrecto() {
		idMecanico = daoMecanico.alta(mecanicoCorrecto);

		String message = "";

		if (idMecanico == -4)
			message = "Error en la base de datos";

		assertTrue(message, idMecanico > 0);
	}
}
