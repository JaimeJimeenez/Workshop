package Integracion.Componente;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Listar {
	
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public Listar() {
		componenteCorrecto =  new TComponente("TESTCOMPONENTE", 4, 100, "TCOMPONENTETEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
	}
	
	@Before
	public void init() {
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
	}
	
	@Test
	public void testCorrecto() {
		Collection<TComponente> resultado = daoComponente.listar();
		assertTrue(resultado != null && ((TComponente)resultado.toArray()[0]).getId() > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
