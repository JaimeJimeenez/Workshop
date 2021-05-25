package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class BorrarMecanicoReparacion {
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
		TTrabaja trabaja;
		do {
			trabaja = saReparacion.anyadirMecanicoReparacion(new TTrabaja(idReparacion, idMecanico, 55));
		} while (trabaja.getIdReparacion() == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		int emplea;
		do {
			emplea = saReparacion.baja(idReparacion);
		} while (emplea == -4);
		do {
			emplea = saVehiculo.baja(idVehiculo);
		} while (emplea == -4);
		do {
			emplea = saCliente.baja(idCliente);
		} while (emplea == -4);
		do {
			saMecanico.baja(idMecanico);
		} while (emplea == -4);
		do {
			saEspecialidad.baja(idEspecialidad);
		} while (emplea == -4);
	}
		
	@Test
	public void testCorrecto() {
		TTrabaja resultado;
		do{
			resultado = saReparacion.borrarMecanicoReparacion(new TTrabaja(idReparacion, idMecanico));
		} while (resultado.getIdReparacion() == -4);
		assertTrue(resultado.getIdMecanico() > 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		TTrabaja resultado;
		do {
			resultado = saReparacion.borrarMecanicoReparacion(new TTrabaja(-idReparacion));
		} while (resultado.getIdReparacion() == -4);
		
		assertTrue(resultado.getIdReparacion() == 0);
	}
	
	@Test
	public void testNoEncontrado() {
		TTrabaja resultado;
		do {
			resultado = saReparacion.borrarMecanicoReparacion(new TTrabaja(idReparacion, idMecanico));
		} while (resultado.getIdReparacion() == -4);
		do {
			resultado = saReparacion.borrarMecanicoReparacion(new TTrabaja(idReparacion, idMecanico));
		} while (resultado.getIdReparacion() == -4);
		assertTrue(resultado.getIdReparacion() == -1);
	}
	
}