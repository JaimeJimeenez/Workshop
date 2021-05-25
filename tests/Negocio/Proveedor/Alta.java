package Negocio.Proveedor;

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
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	private static String DIRECCION_TEST = "TESTPROVEEDOR";
	private static String NIF_TEST = "222222222";
	private static TProveedor TPROVEEDORTEST = new TProveedor(NIF_TEST,"913456743",DIRECCION_TEST);
	@Parameters
	public static Iterable<TProveedor[]>getData()
	{
		return Arrays.asList(new TProveedor[][]
				{{TPROVEEDORTEST,
				  new TProveedor(NIF_TEST,"9134456743", DIRECCION_TEST)},
				{
					TPROVEEDORTEST, null
				}
				});
	}
	private TProveedor tProveedorCorrecto;
	private TProveedor tProveedorIncorrecto;
	private static int idProveedor;
	private static SAProveedor saProveedor;
	
	public Alta(TProveedor tProveedorCorrecto, TProveedor tProveedorIncorrecto)
	{
		this.tProveedorCorrecto = tProveedorCorrecto;
		this.tProveedorIncorrecto = tProveedorIncorrecto;
	}
	@BeforeClass
	public static void initClass() {
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		idProveedor = saProveedor.alta(TPROVEEDORTEST);
		do
		{
			TProveedor m = FactoriaIntegracion.obtenerInstancia().crearProveedor().leerPorNIF(TPROVEEDORTEST.getNIF());
			idProveedor = m != null ? m.getId() : -1;
		}while(idProveedor == -4);
	}
	@Before
	public void init()
	{
		if (idProveedor > 0)	
			while(saProveedor.baja(idProveedor) == -4);
	}
	@AfterClass
	public static void destroyClass() {
		while(saProveedor.baja(idProveedor) == -4);
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saProveedor.alta(tProveedorCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testRepetido()
	{
		saProveedor.alta(tProveedorCorrecto);
		int resultado = saProveedor.alta(tProveedorCorrecto);
		assertTrue(resultado == -1);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saProveedor.alta(tProveedorIncorrecto);
		assertTrue(resultado == 0);
	}
}
