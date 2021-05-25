package Integracion.Componente;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Modificar {
	
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public Modificar() {
		componenteCorrecto =  new TComponente("TESTCOMPONENTE", 4, 100, "TCOMPONENTETEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Before
	public void init() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = daoComponente.modificar(new TComponente(idComponente, 4, "TESTCOMPONENTE", 1000, "TESTCOMPONENTE", 100));
		assertTrue(resultado > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
