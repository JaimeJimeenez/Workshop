package Integracion.Componente;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Alta {

	private TComponente componenteCorrecto;
	private static int idComponente;
	private static DAOComponente daoComponente;
	
	public Alta() {
		componenteCorrecto = new TComponente("TEST", 2, 1500.5f, "TEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Test
	public void testCorrecto() {
		idComponente = daoComponente.alta(componenteCorrecto);
		assertTrue(idComponente > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (idComponente > 0 && Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
