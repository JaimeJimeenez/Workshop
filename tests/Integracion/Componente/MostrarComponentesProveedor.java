package Integracion.Componente;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;
import Negocio.Proveedor.TProveedor;

public class MostrarComponentesProveedor {
	
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idProveedor, idComponente;
	
	public MostrarComponentesProveedor() {
		componenteCorrecto = new TComponente("TESTCOMPONENTE", 4, 100, "TCOMPONENTETEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
		while ((idProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor().alta(new TProveedor("222222222"))) == -4);
		componenteCorrecto.setIdProveedor(idProveedor);
	}
	
	@Before
	public void initTest() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		Collection<TComponente> resultado = daoComponente.mostrarComponentesProveedor(idProveedor);
		assertTrue(resultado != null && ((TComponente)resultado.toArray()[0]).getId() > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
		while (Utility.bajaFisicaProveedor(idProveedor) == -4);
	}
}
