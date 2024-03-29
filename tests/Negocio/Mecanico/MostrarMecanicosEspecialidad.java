package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;
import Presentacion.Controlador.Eventos;

public class MostrarMecanicosEspecialidad {
	private static final String NOMBRE = "TESTMECANICO";

	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static int idMecanico, idEspecialidad;
	private static TMecanico mecanico;

	private Collection<TMecanico> listaMecanico;

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

		mecanico = new TMecanico("46684293C", 1500.5f, NOMBRE, idEspecialidad,
				"654185630", "123456789012345678901234");

		do {
			idMecanico = saMecanico.alta(mecanico);

			if (idMecanico == -1)
				idMecanico = FactoriaIntegracion.obtenerInstancia()
						.crearMecanico().leerPorNif(mecanico.getDNI()).getId();
		} while (idMecanico == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (saMecanico.baja(idMecanico) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);
	}

	@Test
	public void testCorrecto() {
		listaMecanico = saMecanico.mostrarMecanicosEspecialidad(idEspecialidad);

		int res;
		String message = "";

		if (listaMecanico != null) {
			res = ((TMecanico) listaMecanico.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la especialidad no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay mec�nicos en la especialidad ";

		boolean result = listaMecanico != null
				&& (listaMecanico.iterator().next()).getId() > 0;

		assertTrue(message, result);
	}

	@Test
	public void testDatosIncorrectos() {
		listaMecanico = saMecanico
				.mostrarMecanicosEspecialidad(-idEspecialidad);

		int res;
		String message = "";

		if (listaMecanico != null) {
			res = ((TMecanico) listaMecanico.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la especialidad no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay mec�nicos en la especialidad ";

		boolean result = listaMecanico != null
				&& listaMecanico.iterator().next().getId() == 0;

		assertTrue(message, result);
	}

	@Test
	public void testEspecialidadNoEncontrada() {
		while (saMecanico.baja(idMecanico) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);

		listaMecanico = saMecanico.mostrarMecanicosEspecialidad(idEspecialidad);

		int res;
		String message = "";

		if (listaMecanico != null) {
			res = ((TMecanico) listaMecanico.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la especialidad no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay mec�nicos en la especialidad ";

		boolean result = listaMecanico != null && listaMecanico.iterator().next().getId() == -3;
		while (saEspecialidad.alta(new TEspecialidad(NOMBRE)) == -4);
		while (saMecanico.alta(mecanico) == -4);

		assertTrue(message, result);
	}
}
