package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
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

@RunWith(value = Parameterized.class)
public class Alta {
	private static final String NOMBRE = "TESTMECANICO";
	private static final String DNI_CORRECTO = "46684293C";

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
				{new TMecanico(DNI_CORRECTO, 1500.5f, NOMBRE, 2, "654185630",
						"123456789012345678901234"),
						new TMecanico("AGVWGVWFW", 1500, "Michael", 2,
								"654185630", "123456789012345678901234")},
				{new TMecanico(DNI_CORRECTO, 1500.5f, NOMBRE, 2, "654185630",
						"123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, -1500, "Michael", 2,
								"654185630", "123456789012345678901234")},
				{new TMecanico(DNI_CORRECTO, 1500.5f, NOMBRE, 2, "654185630",
						"123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, 1500, "Mic3234hael", 2,
								"654185630", "123456789012345678901234")},
				{new TMecanico(DNI_CORRECTO, 1500.5f, NOMBRE, 2, "654185630",
						"123456789012345678901234"),
						new TMecanico("", 0, "", 0, "", "")}});
	}

	private TMecanico mecanicoCorrecto, mecanicoIncorrecto;
	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static int idMecanico, idEspecialidad;

	public Alta(TMecanico mecanicoCorrecto, TMecanico mecanicoIncorrecto) {
		this.mecanicoCorrecto = mecanicoCorrecto;
		this.mecanicoIncorrecto = mecanicoIncorrecto;

		this.mecanicoCorrecto.setIdEspecialidad(idEspecialidad);
	}

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

		do {
			TMecanico m = FactoriaIntegracion.obtenerInstancia().crearMecanico()
					.leerPorNif(DNI_CORRECTO);
			idMecanico = m != null ? m.getId() : -1;
		} while (idMecanico == -4);
	}

	@Before
	public void initTest() {
		while (saMecanico.baja(idMecanico) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (saMecanico.baja(idMecanico) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);
	}

	@Test
	public void testCorrecto() {
		int res = saMecanico.alta(mecanicoCorrecto);
		
		String message = "";
		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Datos repetidos";
		else if (res == -3)
			message = "ID especialidad no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res > 0);
	}

	@Test
	public void testRepetido() {
		while (saMecanico.alta(mecanicoCorrecto) == -4);

		int res = saMecanico.alta(mecanicoCorrecto);

		String message = "";
		if (res > 0)
			message = "Datos Correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -3)
			message = "ID especialidad no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res == -1);
	}

	@Test
	public void testDatosIncorrectos() {
		int res = saMecanico.alta(mecanicoIncorrecto);

		String message = "";
		if (res > 0)
			message = "Datos Correctos";
		else if (res == -1)
			message = "Datos repetidos";
		else if (res == -3)
			message = "ID especialidad no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res == 0);
	}

	@Test
	public void testIDEspecialidadNoEncontrado() {
		while (saEspecialidad.baja(idEspecialidad) == -4);

		int res = saMecanico.alta(mecanicoCorrecto);

		String message = "";
		if (res > 0)
			message = "Datos Correctos";
		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Datos repetidos";
		else if (res == -4)
			message = "Error en la base de datos";

		while(saEspecialidad.alta(new TEspecialidad(NOMBRE)) == -4);

		assertTrue(message, res == -3);
	}
}
