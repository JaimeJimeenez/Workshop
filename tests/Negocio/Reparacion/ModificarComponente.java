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
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

@RunWith(value = Parameterized.class)
public class ModificarComponente {
	
	private static final String TESTEO = "TESTREPARACION";
	private static final String DNI = "14915998S";
	private static final String MATRICULA = "1111AAA";
	private static int idReparacion, idVehiculo, idCliente, idComponente, idProveedor;
	private static SAReparacion sa;
	private static SAVehiculo saV;
	private static SACliente saC;
	private static SAComponente saCo;
	private static SAProveedor saP;
	private static TReparacion rep;
	private TEmplea corr, incorr;
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TEmplea(65, 12, 1000, 10), new TEmplea(65, 12, -1000, 10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(65, 12, 100, -10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(-65, 12, 100, 10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(65, -12, 100, 10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(0, 12, 100, 10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(65, 0, 100, 10)},
			{new TEmplea(65, 12, 1000, 10), new TEmplea(0, 0, 100, 10)}
		});
	}
	
	public ModificarComponente(TEmplea c, TEmplea i){
		corr = c;
		incorr = i;
	}
	
	@BeforeClass
	public static void initClass() {
		
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();	
		saV = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saC = FactoriaSA.obtenerInstancia().crearSACliente();
		saCo = FactoriaSA.obtenerInstancia().crearSAComponente();
		saP = FactoriaSA.obtenerInstancia().crearSAProveedor();
		
		do {
			idProveedor = saP.alta(new TProveedor(DNI,"111111111",TESTEO));
		} while (idProveedor == -4);
		
		
		if (idProveedor == -1) {
			do {
				TProveedor m = FactoriaIntegracion.obtenerInstancia().crearProveedor().leerPorNIF(DNI);
				idProveedor = m.getId();
			} while (idProveedor == -4);
			
		}
		
		assertTrue("Error en la base de datos", idProveedor != -4);
		
		do {
			idComponente = saCo.alta(new TComponente(TESTEO, idProveedor, 1000, TESTEO, 1000));
		} while (idComponente == -4);
		
		
		if (idComponente == -1){
			do {
				TComponente m = FactoriaIntegracion.obtenerInstancia().crearComponente().leerPorMarcaModelo(TESTEO, TESTEO);
				idComponente = m.getId();
			} while (idComponente == -4);
			
		}
		
		assertTrue("Error en la base de datos", idComponente != -4);
		
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
		} while (idVehiculo == -4);
		
		
		if(idVehiculo == -1)
		{
			do {
				TVehiculo m = FactoriaIntegracion.obtenerInstancia().crearVehiculo().leerPorMatricula(MATRICULA);
				idVehiculo = m.getId();
			} while (idVehiculo == -4);
			
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
		int emp;
		TEmplea emplea;
		do {
			emplea = sa.borrarComponenteReparacion(new TEmplea(idReparacion, idComponente));
		} while (emplea.getIdReparacion() == -4);
		do {
			emp = saCo.modificar(new TComponente(idComponente, idProveedor, TESTEO, 10000, TESTEO, 10000));
		} while (emp == -4);
		do {
			emplea = sa.anyadirComponente(new TEmplea(idReparacion, idComponente, 20, 20));
		} while (emplea.getIdReparacion() == -4);
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
		do{
			res = saCo.baja(idComponente);
		} while (res == -4);
		do{
			res = saP.baja(idProveedor);
		} while (res == -4);
	}
	
	@Test
	public void testCorrecto()
	{
		TEmplea emplea;
		do {
			emplea = sa.modificarComponenteReparacion(corr);
		} while(emplea.getIdReparacion() == -4);
		assertTrue(emplea.getIdComponente() > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		TEmplea emp;
		do {
			emp = sa.modificarComponenteReparacion(incorr);
		} while (emp.getIdComponente() == -4);
		assertTrue(emp.getIdReparacion() == 0);
	}
	@Test
	public void testNoEncontradoError()
	{
		TEmplea emp;
		do {
			emp = sa.borrarComponenteReparacion(corr);
		} while (emp.getIdReparacion() == -4);
		do {
			emp = sa.modificarComponenteReparacion(corr);
		} while (emp.getIdReparacion() == -4);
		assertTrue(emp.getIdReparacion() == -3);
	}
}
