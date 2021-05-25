package Negocio.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Modificar {
	private static String PROVEEDOR_TEST = "TESTPROVEEDOR";
	private static String PROVEEDOR2_TEST = "TESTPROVEEDORDOS";
	private static String NIF_TEST = "222222222";
	private static String NIF2_TEST = "333333333";
	private static int idProveedor;
	private static int idProveedor2;
	private static TProveedor TPROVEEDORTEST = new TProveedor(NIF_TEST,"913456743",PROVEEDOR_TEST);
	private static TProveedor TPROVEEDORTEST2 = new TProveedor(NIF2_TEST,"913456712",PROVEEDOR2_TEST);
	private static SAProveedor saProveedor;
	private TProveedor tProveedorIncorrecto;
	@Parameters
	public static Iterable<TProveedor[]> getData()
	{
		return Arrays.asList(new TProveedor[][]{{new TProveedor("765894231", 0, "9123354678", "calle del pinar")}, {null}});
	}

	@BeforeClass
	public static void initClass() {
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		do {
			idProveedor = saProveedor.alta(TPROVEEDORTEST);
			
			if (idProveedor == -1)
				idProveedor = FactoriaIntegracion.obtenerInstancia()
						.crearProveedor().leerPorNIF(TPROVEEDORTEST.getNIF()).getId();
			
		} while (idProveedor == -4);

		TPROVEEDORTEST.setId(idProveedor);
		do {
			idProveedor2 = saProveedor.alta(TPROVEEDORTEST2);
			if (idProveedor2 == -1)
				idProveedor2 =  FactoriaIntegracion.obtenerInstancia()
						.crearProveedor().leerPorNIF(TPROVEEDORTEST2.getNIF()).getId();
		} while (idProveedor2 == -4);
		TPROVEEDORTEST2.setId(idProveedor2);
	}

	public Modificar(TProveedor tProveedorIncorrecto)
	{
		this.tProveedorIncorrecto = tProveedorIncorrecto;
	}
	@Test
	public void correcto()
	{
		int result =  saProveedor.modificar(TPROVEEDORTEST);
		assertTrue(result > 0);
	}
	@Test
	public void incorrecto()
	{
		int result =  saProveedor.modificar(tProveedorIncorrecto);
		assertTrue(result == 0);
	}
	@Test
	public void noEncontrado()
	{
		while(saProveedor.baja(idProveedor) == -4);
		int result =  saProveedor.modificar(TPROVEEDORTEST);
		while(saProveedor.alta(TPROVEEDORTEST) == -4);
		assertTrue(result == -1);
	}
	@Test
	public void repetido()
	{
		TPROVEEDORTEST.setNIF(NIF2_TEST);
		int result =  saProveedor.modificar(TPROVEEDORTEST);
		TPROVEEDORTEST.setNIF(NIF_TEST);
		assertTrue(result == -2);
	}
	@AfterClass
	public static void destroyClass() {
		saProveedor.baja(idProveedor);
		saProveedor.baja(idProveedor2);
	}
}
