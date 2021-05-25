package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

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

public class Mostrar {
	private static final String NOMBRE = "TESTMECANICO";

	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static int idMecanico, idEspecialidad;
	private static TMecanico mecanico;

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
		TMecanico result = saMecanico.mostrar(idMecanico);

		int res = result.getId();
		String message = "";

		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Mecánico con ID " + idMecanico + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";
		
		assertTrue(message, res > 0);
	}
	
	@Test
	public void testDatosIncorrecto() {
		TMecanico result = saMecanico.mostrar(-idMecanico);

		int res = result.getId();
		String message = "";

		if (res > 0)
			message = "Datos correctos";
		else if (res == -1)
			message = "Mecánico con ID " + idMecanico + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";
		
		assertTrue(message, res == 0);
	}
	
	@Test
	public void testMecanicoNoEncontrado() {
		while(saMecanico.baja(idMecanico) == -4);
		
		TMecanico result = saMecanico.mostrar(idMecanico);

		int res = result.getId();
		String message = "";

		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -4)
			message = "Error en la base de datos";
		
		while(saMecanico.alta(mecanico) == -4);
		
		assertTrue(message, res == -1);
	}
}

