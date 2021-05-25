package Integracion.Componente;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Componente.TComponente;

public class Reactivar {
	
	private TComponente componenteCorrecto;
	private static DAOComponente daoComponente;
	private static int idComponente;
	
	public Reactivar() {
		componenteCorrecto = new TComponente("TESTCO", 4, 1500.5f, "TCOMPTEST", 100);
		daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
		
		while ((idComponente = daoComponente.alta(componenteCorrecto)) == -4);
		while (daoComponente.baja(idComponente) == -4);
	}
	
	@Test
	public void testCorrecto() {
		int resultado = daoComponente.reactivar(componenteCorrecto);
		System.out.println(resultado);
		assertTrue(resultado > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (idComponente > 0 && Utility.bajaFisicaComponente(idComponente) == -4);
	}
}
