package Integracion.Proveedor;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;

public class Listar {
	private static String DIRECCION_TEST = "TESTPROVEEDORDAO";
	private static String NIF_TEST = "111111111";
	private static TProveedor TPROVEEDORTEST = new TProveedor(NIF_TEST,"987654321",DIRECCION_TEST);
	private static int idProveedor;
	private static DAOProveedor daoProveedor;
	@BeforeClass
	public static void initClass() {
		daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();
		do{
			idProveedor = daoProveedor.alta(TPROVEEDORTEST);
		}
		while(idProveedor == -4);
	}
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaProveedor(idProveedor) == -4);
	}
	@Test
	public void correcto()
	{
		SAProveedor saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		assertTrue(saProveedor.listar()!= null); 
	}
}