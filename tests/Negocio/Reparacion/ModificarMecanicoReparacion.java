package Negocio.Reparacion;

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
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

@RunWith(value = Parameterized.class)
public class ModificarMecanicoReparacion {
	
	private static final String TESTEO = "TESTREPARACION";
	private static final String DNI = "14915998S";
	private static final String MATRICULA = "1111AAA";
	private static int idReparacion;
	private static int idVehiculo;
	private static int idCliente;
	private static int idMecanico;
	private static int idEspecialidad;
	private static SAReparacion saReparacion;
	private static SAVehiculo saVehiculo;
	private static SACliente saCliente;
	private static SAMecanico saMecanico;
	private static SAEspecialidad saEspecialidad;
	private static TReparacion tReparacion;
	private TTrabaja correcto;
	private TTrabaja incorrecto;
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TTrabaja(65, 8, 60), new TTrabaja(65, 8, -60)},
			{new TTrabaja(65, 8, 60), new TTrabaja(65, -8, 60)},
			{new TTrabaja(65, 8, 60), new TTrabaja(-65, 8, 60)},
			{new TTrabaja(65, 8, 60), new TTrabaja(0, 8, 60)},
			{new TTrabaja(65, 8, 60), new TTrabaja(65, 0, 60)},
			{new TTrabaja(65, 8, 60), new TTrabaja(0, 0, 60)}
		});
	}
	
	public ModificarMecanicoReparacion(TTrabaja correcto, TTrabaja incorrecto) {
		this.correcto = correcto;
		this.incorrecto = incorrecto;
	}
	
	@BeforeClass
	public static void initClass() {
		saReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion();	
		saVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		saVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saMecanico = FactoriaSA.obtenerInstancia().crearSAMecanico();
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		do {
			idCliente = saCliente.alta(new TParticular(TESTEO, "111111111", DNI, TESTEO));
		} while (idCliente == -4);
		
		
		if (idCliente == -1) {
			do {
				TCliente tCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI);
				idCliente = tCliente.getId();
			} while (idCliente == -4);
		}
		
		assertTrue("Error en la base de datos", idCliente != -4);
		
		do {
			idVehiculo = saVehiculo.alta(new TVehiculo(MATRICULA, TESTEO, idCliente));
		} while (idVehiculo == -4);
		
		if (idVehiculo == -1) {
			do {
				TVehiculo tVehiculo = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MATRICULA);
				idVehiculo = tVehiculo.getId();
			} while (idVehiculo == -4);
			
		}
		
		assertTrue("Error en la base de datos", idVehiculo != -4);
		
		do {
			idEspecialidad = saEspecialidad.alta(new TEspecialidad(TESTEO));
		} while (idEspecialidad == -4);
		
		if (idEspecialidad == -1) {
			do {
				TEspecialidad tEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().leerPorTipo(TESTEO);
				idEspecialidad = tEspecialidad.getId();
			} while (idEspecialidad == -4);
		}
		
		assertTrue("Error en la base de datos", idEspecialidad != -4);
		
		do {
			idMecanico = saMecanico.alta(new TMecanico(DNI, 1000, TESTEO, idEspecialidad, "662734095", "123456789012345678901235"));
		} while (idMecanico == -4);
		
		if (idMecanico == -1) {
			do {
				TMecanico tMecanico = FactoriaIntegracion.obtenerInstancia().crearMecanico().leerPorNif(DNI);
				idMecanico = tMecanico.getId();
			} while (idMecanico == -4);
			
		}
		
		assertTrue("Error en la base de datos", idMecanico != -4);
		
		tReparacion = new TReparacion(idVehiculo, "2020-12-12", "2021-12-12", TESTEO, 1000);
		
		do {
			idReparacion = saReparacion.alta(tReparacion, null, null);
		} while (idReparacion == -4);
		
		if (idReparacion == -1) {
			do {
				TReparacion reparacion = FactoriaIntegracion.obtenerInstancia().crearReparacion().leerPorDatos(tReparacion);
				idReparacion = reparacion.getId();				
			} while (idReparacion == -4);
		}
		
		assertTrue("Error en la base de datos", idReparacion != -4);
	}
	
	@Before
	public void initTest() {
		int res;
		TTrabaja rese;
		do {
			res = saMecanico.modificar(new TMecanico(DNI, 1000, TESTEO, idEspecialidad, "662734095", "123456789012345678901235"));
		} while (res == -4);
		do {
			rese = saReparacion.anyadirMecanicoReparacion(new TTrabaja(idReparacion, idMecanico, 70));
		} while (rese.getIdReparacion() == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		int res;
		do {
			res = saReparacion.baja(idReparacion);
		} while (res == -4);
		do {
			res = saVehiculo.baja(idVehiculo);
		} while (res == -4);
		do {
			res = saMecanico.baja(idMecanico);
		} while (res == -4);
		do {
			res = saCliente.baja(idCliente);
		} while (res == -4);
		do {
			res = saEspecialidad.baja(idEspecialidad);
		} while (res == -4);	
	}
	
	@Test
	public void testCorrecto() {
		TTrabaja res;
		do {
			res = saReparacion.modificarMecanicoReparacion(correcto);
		} while (res.getIdReparacion() == -4);
		assertTrue(res.getIdMecanico() > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		TTrabaja res;
		do {
			res = saReparacion.modificarMecanicoReparacion(incorrecto);
		} while (res.getIdReparacion() == -4);
		assertTrue(res.getIdReparacion() == 0);
	}
	
	@Test
	public void testNoEncontradoError() {
		TTrabaja res;
		TTrabaja aux;
		do {
			res = saReparacion.borrarMecanicoReparacion(correcto);
		} while (res.getIdReparacion() == -4);
		do {
			res = saReparacion.modificarMecanicoReparacion(correcto);
		} while (res.getIdReparacion() == -4);
		do {
			aux = saReparacion.anyadirMecanicoReparacion(new TTrabaja(idReparacion, idMecanico, 70));
		} while (res.getIdReparacion() == -4);
		assertTrue(res.getIdReparacion() == -3);
	}
	
}