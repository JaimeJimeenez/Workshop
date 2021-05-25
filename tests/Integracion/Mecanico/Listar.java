package Integracion.Mecanico;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Mecanico.TMecanico;

public class Listar {
	private static final String NOMBRE = "TESTMECANICODAO";

	private TMecanico mecanicoCorrecto;
	private static DAOMecanico daoMecanico;
	private static int idMecanico;

	public Listar() {
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
		Collection<TMecanico> res = daoMecanico.listar();

		String message = "";

		if (res == null)
			message = "No hay mecanicos";
		else if (((TMecanico)res.toArray()[0]).getId() == -4)
			message = "Error en la base de datos";

		assertTrue(message, res != null && ((TMecanico)res.toArray()[0]).getId() > 0);
	}
}
