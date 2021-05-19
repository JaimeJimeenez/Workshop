package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
	private static final String NOMBRE_ESPECIALIDAD = "TESTMECANICO";
	private static final String DNI_CORRECTO = "46684293C";
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico("AGVWGVWFW", 1500, "Michael", 2, "654185630", "123456789012345678901234") },
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, -1500, "Michael", 2, "654185630", "123456789012345678901234") },
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, 1500, "Mic3234hael", 2, "654185630", "123456789012345678901234") },
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, 1500, "Michael", -3, "654185630", "123456789012345678901234") },
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, 1500, "Michael", 2, "6541fwfw85630", "123456789012345678901234") },
				{ new TMecanico(DNI_CORRECTO, 1500.5f, "Michael", 2, "654185630", "123456789012345678901234"),
						new TMecanico(DNI_CORRECTO, 1500, "Michael", 2, "654185630", "34") } });
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

		idEspecialidad = saEspecialidad.alta(new TEspecialidad(NOMBRE_ESPECIALIDAD));

		if (idEspecialidad == -1) {
			TEspecialidad especialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad()
					.leerPorTipo(NOMBRE_ESPECIALIDAD);

			idEspecialidad = especialidad.getId();
		}
		
		assertTrue("Error en la base de datos", idEspecialidad != -4);
		
		TMecanico m = FactoriaIntegracion.obtenerInstancia().crearMecanico().leerPorNif(DNI_CORRECTO);
		idMecanico = m != null ? m.getId() : -1;
		
		assertTrue("Error en la base de datos", idMecanico != -4);
	}

	@Before
	public void initTest() {
		if (idMecanico > 0)
			saMecanico.baja(idMecanico);
	}

	@AfterClass
	public static void destroyClass() {
		saMecanico.baja(idMecanico);
		saEspecialidad.baja(idEspecialidad);
	}

	@Test
	public void testCorrecto() {
		idMecanico = saMecanico.alta(mecanicoCorrecto);

		String message = "";
		if (idMecanico == 0)
			message = "Datos incorrectos";
		else if (idMecanico == -1)
			message = "Datos repetidos";
		else if (idMecanico == -3)
			message = "ID especialidad no encontrado";
		else if (idMecanico == -4)
			message = "Error en la base de datos";

		assertTrue(message, idMecanico > 0);
	}

	@Test
	public void testRepetido() {
		idMecanico = saMecanico.alta(mecanicoCorrecto);
		
		assertTrue("Error en la base de datos", idMecanico != -4);
		
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
		idMecanico = saMecanico.alta(mecanicoIncorrecto);

		String message = "";
		if (idMecanico > 0)
			message = "Datos Correctos";
		else if (idMecanico == -1)
			message = "Datos repetidos";
		else if (idMecanico == -3)
			message = "ID especialidad no encontrado";
		else if (idMecanico == -4)
			message = "Error en la base de datos";

		assertTrue(message, idMecanico == 0);
	}

	@Test
	public void testIDEspecialidadNoEncontrado() {
		saEspecialidad.baja(idEspecialidad);
		
		idMecanico = saMecanico.alta(mecanicoCorrecto);

		String message = "";
		if (idMecanico > 0)
			message = "Datos Correctos";
		if (idMecanico == 0)
			message = "Datos incorrectos";
		else if (idMecanico == -1)
			message = "Datos repetidos";
		else if (idMecanico == -4)
			message = "Error en la base de datos";

		
		saEspecialidad.alta(new TEspecialidad(NOMBRE_ESPECIALIDAD));
		
		assertTrue(message, idMecanico == -3);
	}
}
