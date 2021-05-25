package Integracion.Componente;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Mostrar {

	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public Mostrar() {
		componenteCorrecto = new TComponente("TESTCOMPONENTE", 4, 100, "TCOMPONENTETEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Before
	public void initTest() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		TComponente resultado = daoComponente.mostrar(idComponente);
		assertTrue(resultado != null && resultado.getId() > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
