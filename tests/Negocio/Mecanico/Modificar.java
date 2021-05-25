package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;

public class Modificar {
	private static final String NOMBRE = "TESTMECANICO";
	private static final String NOMBRE2 = "TESTMECANICOAUX";
	private static final String NIF = "46684293C";
	private static final String NIF2 = "59593123P";
	private static final String TELEFONO = "654885630";
	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static int idEspecialidad;
	private static TMecanico mecanico = new TMecanico(NIF, 1500.5f, NOMBRE, 0,
			TELEFONO, "123456789012345678901234");

	private static TMecanico mecanicoModificado = new TMecanico(NIF2, 1500.5f, NOMBRE2, 0,
			"645885621", "783456789012345678901124");

	@BeforeClass
	public static void initClass() {
		saMecanico = FactoriaSA.obtenerInstancia().crearSAMecanico();
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();

		do {
			idEspecialidad = saEspecialidad.alta(new TEspecialidad(NOMBRE));

			if (idEspecialidad == -1)
				idEspecialidad = FactoriaIntegracion.obtenerInstancia()
						.crearEspecialidad().leerPorTipo(NOMBRE).getId();

		} while (idEspecialidad == -4);

		mecanico.setIdEspecialidad(idEspecialidad);
		mecanicoModificado.setIdEspecialidad(idEspecialidad);
		int idMecanico;
		do {
			idMecanico = saMecanico.alta(mecanico);

			if (idMecanico == -1)
				idMecanico = FactoriaIntegracion.obtenerInstancia()
						.crearMecanico().leerPorNif(mecanico.getDNI()).getId();
		} while (idMecanico == -4);

		mecanico.setId(idMecanico);
		int idMecanico2;
		do {
			idMecanico2 = saMecanico.alta(mecanicoModificado);

			if (idMecanico2 == -1)
				idMecanico2 = FactoriaIntegracion.obtenerInstancia()
						.crearMecanico().leerPorNif(mecanicoModificado.getDNI()).getId();
		} while (idMecanico2 == -4);
		mecanicoModificado.setId(idMecanico2);
	}

	@AfterClass
	public static void destroyClass() {
		while (saMecanico.baja(mecanico.getId()) == -4);
		while (saMecanico.baja(mecanicoModificado.getId()) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);
	}

	@Test
	public void testCorrecto() {
		mecanico.setTelefono("654897512");
		int res = saMecanico.modificar(mecanico);
		mecanico.setTelefono(TELEFONO);

		String message = "";
		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Mecanico con ID " + res + " no encontrado";
		else if (res == -2)
			message = "Nuevos datos repetidos";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		mecanicoModificado.setId(-mecanicoModificado.getId());
		int res = saMecanico.modificar(mecanicoModificado);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == -1)
			message = "Mecanico con ID " + res + " no encontrado";
		else if (res == -2)
			message = "Nuevos datos repetidos";
		else if (res == -4)
			message = "Error en la base de datos";

		mecanicoModificado.setId(-mecanicoModificado.getId());
		assertTrue(message, res == 0);
	}

	@Test
	public void testMecanicoNoEncontrado() {
		while (saMecanico.baja(mecanico.getId()) == -4);

		int res = saMecanico.modificar(mecanico);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -4)
			message = "Error en la base de datos";

		while (saMecanico.alta(mecanico) == -4);
		assertTrue(message, res == -1);
	}
	
	@Test
	public void testEspecialidadNoEncontrado() {
		while (saMecanico.baja(mecanico.getId()) == -4);
		while (saMecanico.baja(mecanicoModificado.getId()) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);

		int res = saMecanico.modificar(mecanico);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -4)
			message = "Error en la base de datos";

		while (saEspecialidad.alta(new TEspecialidad(NOMBRE)) == -4);
		while (saMecanico.alta(mecanico) == -4);
		while (saMecanico.alta(mecanicoModificado) == -4);
		assertTrue(message, res == -3);
	}

	@Test
	public void testDatosRepetidos() {
		mecanico.setDNI(mecanicoModificado.getDNI());
		int res = saMecanico.modificar(mecanico);
		mecanico.setDNI(NIF);
		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Mecanico con ID " + res + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res == -2);
	}
}
