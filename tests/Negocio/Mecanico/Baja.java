package Negocio.Mecanico;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.TVehiculo;

public class Baja {
	private static final String NOMBRE = "TESTMECANICO";
	private static final String NIF = "46684293C";
	private static final String MATRICULA = "5717ZJY";

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

	@Before
	public void initTest() {
		while (saMecanico.alta(mecanico) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (saMecanico.baja(idMecanico) == -4);
		while (saEspecialidad.baja(idEspecialidad) == -4);
	}

	@Test
	public void testCorrecto() {
		int res = saMecanico.baja(idMecanico);

		String message = "";
		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Mecanico con ID " + idMecanico + " no encontrado";
		else if (res == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res > 0);
	}

	@Test
	public void testDatosIncorrectos() {
		int res = saMecanico.baja(-idMecanico);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == -1)
			message = "Mecanico con ID " + idMecanico + " no encontrado";
		else if (res == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (res == -4)
			message = "Error en la base de datos";

		assertTrue(message, res == 0);
	}

	@Test
	public void testNoEncontrado() {
		while(saMecanico.baja(idMecanico) == -4);

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

	@Test
	public void testMecanicoEnReparacion() {
		int idCliente = crearCliente();
		assertTrue("Fallo cliente", idCliente > 0);

		int idVehiculo = crearVehiculo(idCliente);
		assertTrue("Fallo vehículo", idVehiculo > 0);

		int idReparacion = crearReparacion(idVehiculo);
		assertTrue("Fallo reparación", idReparacion > 0);

		int res = saMecanico.baja(idMecanico);

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Mecanico con ID " + idMecanico + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		while (FactoriaSA.obtenerInstancia().crearSACliente()
				.baja(idCliente) == -4);
		while (FactoriaSA.obtenerInstancia().crearSAVehiculo()
				.baja(idVehiculo) == -4);
		while (FactoriaSA.obtenerInstancia().crearSAReparacion()
				.baja(idReparacion) == -4);

		assertTrue(message, res == -2);
	}

	private int crearCliente() {
		TCliente cliente = new TParticular(NOMBRE, "123456789", NIF,
				"TESTMECANICO");

		int idCliente;

		do {
			idCliente = FactoriaSA.obtenerInstancia().crearSACliente()
					.alta(cliente);

			if (idCliente == -1)
				idCliente = FactoriaIntegracion.obtenerInstancia()
						.crearCliente().leerPorNif(NIF).getId();
		} while (idCliente == -4);

		return idCliente;
	}

	private int crearVehiculo(int idCliente) {
		TVehiculo vehiculo = new TVehiculo(MATRICULA, NOMBRE, idCliente);

		int idVehiculo;

		do {
			idVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo()
					.alta(vehiculo);

			if (idVehiculo == -1)
				idVehiculo = FactoriaIntegracion.obtenerInstancia()
						.crearVehiculo().leerPorMatricula(MATRICULA).getId();
		} while (idVehiculo == -4);

		return idVehiculo;
	}

	private int crearReparacion(int idVehiculo) {
		TReparacion reparacion = new TReparacion(idVehiculo, "2021-02-21",
				"2021-03-21", NOMBRE, 1500);
		Collection<TEmplea> componentes = new ArrayList<TEmplea>();
		Collection<TTrabaja> mecanicos = new ArrayList<TTrabaja>();
		mecanicos.add(new TTrabaja(0, idMecanico, 45));

		int idReparacion;

		do {
			idReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion()
					.alta(reparacion, componentes, mecanicos);

			if (idReparacion == -1)
				idReparacion = FactoriaIntegracion.obtenerInstancia()
						.crearReparacion().leerPorDatos(reparacion).getId();
		} while (idReparacion == -4);

		return idReparacion;
	}
}
