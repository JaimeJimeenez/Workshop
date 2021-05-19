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

public class Baja {
	private static final String NOMBRE_ESPECIALIDAD = "TESTMECANICO";

	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static int idMecanico, idEspecialidad;
	private static TMecanico mecanico;
	
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
		
		mecanico = new TMecanico("46684293C", 1500.5f, "Michael", idEspecialidad, "654185630", "123456789012345678901234");
		
		idMecanico = saMecanico.alta(mecanico);
		
		if(idMecanico == -1)
		{
			TMecanico m = FactoriaIntegracion.obtenerInstancia().crearMecanico().leerPorNif(mecanico.getDNI());

			idMecanico = m.getId();
		}
		
		assertTrue("Error en la base de datos", idMecanico != -4);
	}

	@Before
	public void initTest() {
		if (idMecanico > 0)
			saMecanico.alta(mecanico);
	}

	@AfterClass
	public static void destroyClass() {
		saMecanico.baja(idMecanico);
		saEspecialidad.baja(idEspecialidad);
	}

	@Test
	public void testCorrecto() {
		idMecanico = saMecanico.baja(idMecanico);

		String message = "";
		if (idMecanico == 0)
			message = "Datos incorrectos";
		else if (idMecanico == -1)
			message = "Mecanico con ID " + idMecanico + " no encontrado";
		else if (idMecanico == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (idMecanico == -4)
			message = "Error en la base de datos";

		assertTrue(message, idMecanico > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		idMecanico = saMecanico.baja(-idMecanico);

		String message = "";
		if (idMecanico > 0)
			message = "Datos correctos";
		else if (idMecanico == -1)
			message = "Mecanico con ID " + idMecanico + " no encontrado";
		else if (idMecanico == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (idMecanico == -4)
			message = "Error en la base de datos";

		assertTrue(message, idMecanico == 0);
	}
	
	@Test
	public void testNoEncontrado() {
		idMecanico = saMecanico.baja(idMecanico);
		assertTrue("Error en la base de datos", idMecanico != -4);
		
		int res = saMecanico.baja(idMecanico);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res == -1);
	}
	
	//TODO Test para comprobar si un mecanico esta trabajando en una reparacion
}
