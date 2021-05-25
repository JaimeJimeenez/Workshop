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
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

@RunWith(value = Parameterized.class)
public class Modificar {
	
	private static final String TESTEO = "TESTREPARACION";
	private static final String DNI = "14915998S";
	private static final String MATRICULA = "1111AAA";
	
	private static int idReparacion, idVehiculo, idCliente;
	private static SAReparacion sa;
	private static SAVehiculo saV;
	private static SACliente saC;
	private static TReparacion rep;
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TReparacion(65, 83, "2020-12-12", "2021-12-12", TESTEO, 1000), 
				new TReparacion(65, 83, "Hola","2021-12-12", TESTEO, 1000)},
			{new TReparacion(65, 83, "", "2021-12-12", TESTEO, 1000), 
				new TReparacion(65, 83, "2020-12-12","Hola", TESTEO, 1000)},
			{new TReparacion(65, 83, "2020-12-12", "", TESTEO, 1000), 
				new TReparacion(65, 83, "2020-12-12", "2021-12-12", TESTEO, -1000)},
			{new TReparacion(65, 83, "2020-12-12", "2021-12-12", "", 1000), 
				new TReparacion(65, -83, "2020-12-12", "2021-12-12", TESTEO, 1000)}
		});
	}
	
	private TReparacion corr, incorr;
	
	public Modificar(TReparacion c, TReparacion i){
		corr = c;
		incorr = i;
	}
	
	@BeforeClass
	public static void initClass() {
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();	
		saV = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saC = FactoriaSA.obtenerInstancia().crearSACliente();
		
		do {
			idCliente = saC.alta(new TParticular(TESTEO,"111111111",DNI, TESTEO));
		} while (idCliente == -4);
		
		if (idCliente == -1){
			do {
				TCliente m = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(DNI);
				idCliente = m.getId();
			} while (idCliente == -4);
			
		}
		
		assertTrue("Error en la base de datos", idCliente != -4);
		
		/**/
		
		do {
			idVehiculo = saV.alta(new TVehiculo(MATRICULA, TESTEO, idCliente));
		}while (idVehiculo == -4);
		
		if(idVehiculo == -1)
		{
			do {
				TVehiculo m = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MATRICULA);
				idVehiculo = m.getId();
			}while (idVehiculo == -4);
		}
		
		assertTrue("Error en la base de datos", idVehiculo != -4);
		
		/**/
		
		rep = new TReparacion(idVehiculo, "2020-12-12", "2021-12-12", TESTEO, 1000);
		
		do {
			idReparacion = sa.alta(rep, null, null);
		} while (idReparacion == -4);
		
		if (idReparacion == -1) {
			do {
				TReparacion r = FactoriaIntegracion.obtenerInstancia().crearReparacion().leerPorDatos(rep);
				idReparacion = r.getId();
			} while (idReparacion == -4);
		}
		
		assertTrue("Error en la base de datos", idReparacion != -4);
	}
	@Before
	public void initTest()
	{
		int res;
		do {
			res = sa.alta(rep, null, null);
		} while (res == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		int res;
		do{
			res = sa.baja(idReparacion);
		} while (res == -4);
		do{
			res = saV.baja(idVehiculo);
		} while (res == -4);
		do{
			res = saC.baja(idCliente);
		} while (res == -4);
	}
	@Test
	public void correcto() {
		int result;
		do {
			result = sa.modificar(corr);
		} while (result == -4);
		assertTrue(result > 0);
	}
	
	@Test
	public void noActivo() {
		int res;
		do {
			res = sa.baja(idReparacion);
		} while (res == -4);
		do{
			res = sa.modificar(corr);
		} while (res == -4);
		assertTrue(res == -1);
	}
	
	@Test
	public void incorrecto() {
		int result;
		do {
			result = sa.modificar(incorr);
		} while (result == -4);
		assertTrue(result == 0);
	}
	
}