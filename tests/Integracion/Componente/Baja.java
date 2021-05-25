package Integracion.Componente;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Baja {
	
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public Baja() {
		componenteCorrecto = new TComponente("TESTCOMPONENTE", 4, 1500.5f, "TCOMPONENTETEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Before
	public void init() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = daoComponente.baja(idComponente);
		assertTrue(resultado > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
