package Integracion.Componente;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class LeerMarcaPorModelo {

	private static final String MARCA_TEST = "TESTCOMPONENTE";
	private static final String MODELO_TEST = "TCOMPONENTE";
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public LeerMarcaPorModelo() {
		componenteCorrecto = new TComponente(MARCA_TEST, 4, 100, MODELO_TEST, 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Before
	public void initTest() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		TComponente resultado = daoComponente.leerPorMarcaModelo(MARCA_TEST, MODELO_TEST);
		assertTrue(resultado != null && resultado.getId() > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
