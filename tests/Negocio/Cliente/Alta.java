package Negocio.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	
	private static TCliente TCLIENTETESTPARTICULAR = new TParticular("TESTPARTICULARDOS", "918765432", "80885693Y", "Calle ni idea");
	private static TCliente TCLIENTETESTEMPRESA =new TEmpresa("TESTEMPRESADOS", "981234567", "22334456J", "testempresa.com");  
	private TCliente tClienteIncorrecto;
	private static int idClienteParticular;
	private static int idClienteEmpresa;
	private static SACliente saCliente;
	
	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][] {
			{
				new TParticular("TESTPARTICULARDOS", "91876543210", "155232378C", "Calle de ser o no ser")
			},
			{
				null
			}
		});
	}
	
	public Alta(TCliente tClienteIncorrecto) {
		this.tClienteIncorrecto = tClienteIncorrecto;
	}
	
	@BeforeClass
	public static void initClass() {
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		idClienteParticular = saCliente.alta(TCLIENTETESTPARTICULAR);
		do {
			TCliente t = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(TCLIENTETESTPARTICULAR.getNif());
			idClienteParticular = t.getId();
		} while (idClienteParticular == -4);
		
		idClienteEmpresa = saCliente.alta(TCLIENTETESTEMPRESA);
		do {
			TCliente t = FactoriaIntegracion.obtenerInstancia().crearCliente().leerPorNif(TCLIENTETESTEMPRESA.getNif());
			idClienteEmpresa = t.getId();
		} while (idClienteEmpresa == -4);
	}
	
	@Before
	public void init() { 
		while (saCliente.baja(idClienteEmpresa) == -4);
		while (saCliente.baja(idClienteParticular) == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (saCliente.baja(idClienteEmpresa) == -4);
		while (saCliente.baja(idClienteParticular) == -4);
	}
	
	@Test
	public void testParticularCorrecto() {
		int resultado = saCliente.alta(TCLIENTETESTPARTICULAR);
		assertTrue(resultado > 0);
	}
	public void testEmpresaCorrecto() {
		int resultado = saCliente.alta(TCLIENTETESTEMPRESA);
		assertTrue(resultado > 0);
	}
	@Test
	public void testParticularRepetido() {
		saCliente.alta(TCLIENTETESTPARTICULAR);
		int resultado = saCliente.alta(TCLIENTETESTPARTICULAR);
		assertTrue(resultado == -1);
	}
	@Test
	public void testEmpresaRepetido() {
		saCliente.alta(TCLIENTETESTEMPRESA);
		int resultado = saCliente.alta(TCLIENTETESTEMPRESA);
		assertTrue(resultado == -1);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = saCliente.alta(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
	
	@Test
	public void testDatosIncorrectos() {
		int resultado = saCliente.alta(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
}
