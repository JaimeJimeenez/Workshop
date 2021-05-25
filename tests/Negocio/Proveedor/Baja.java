package Negocio.Proveedor;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;
import Negocio.FactoriaSA.FactoriaSA;

public class Baja {
	
	private static String PROVEEDOR_TEST = "TESTPROVEEDOR";
	private static String NIF_TEST = "222222222";
	private static int idProveedor;
	private static int idComponente;

	private static TProveedor tProveedor;
	private static SAProveedor saProveedor;
	private static SAComponente saComponente;
	@BeforeClass
	public static void initClass() {
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		tProveedor = new TProveedor(NIF_TEST,"913456743",PROVEEDOR_TEST);
		do{
			idProveedor = saProveedor.alta(tProveedor);
			
			if(idProveedor == -1)
			{
				TProveedor m = FactoriaIntegracion.obtenerInstancia().crearProveedor().leerPorNIF(tProveedor.getNIF());

				idProveedor = m != null ? m.getId() : -1;
			}
		} while(idProveedor == -4);
		tProveedor.setId(idProveedor);
	}
	@Before
	public void initTest() {
		while(saProveedor.alta(tProveedor) == -4);
	}
	@AfterClass
	public static void destroyClass() {
		while(saComponente.baja(idComponente) == -4);
		while(saProveedor.baja(idProveedor) == -4);
		
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saProveedor.baja(idProveedor);
		assertTrue(resultado > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saProveedor.baja(-idProveedor);
		assertTrue(resultado == 0);
	}
	@Test
	public void testDatosNoEncontrado()
	{
		while(saProveedor.baja(idProveedor) == -4);
		int resultado = saProveedor.baja(idProveedor);
		assertTrue(resultado == -1);
	}
	@Test
	public void testComponenteActivo()
	{
		do{
			TComponente TCOMPONENTETEST = new TComponente(PROVEEDOR_TEST, idProveedor, 100, PROVEEDOR_TEST, 6);
			idComponente = saComponente.alta(TCOMPONENTETEST);
	
			if(idComponente == -1)
			{
				TComponente m = FactoriaIntegracion.obtenerInstancia().crearComponente().leerPorMarcaModelo(TCOMPONENTETEST.getMarca(), TCOMPONENTETEST.getModelo());

				idComponente = m.getId();
			}
			
		}while(idComponente == -4);
		int resultado = saProveedor.baja(idProveedor);
		while(saComponente.baja(idComponente) == -4);
		assertTrue(resultado == -2);
	}
}
