package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Mecanico.TMecanico;
import Negocio.Reparacion.TReparacion;

public class Listar {
	private static final String TESTEO = "TESTREPARACION";
	private TReparacion rep;
	private static DAOReparacion dao;
	private static int idReparacion;
	public Listar(){
		rep = new TReparacion(1,"2020-12-12","2021-12-12",TESTEO, 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Before
	public void initTest() {
		while ((idReparacion = dao.alta(rep)) == -4);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	@Test
	public void correcto()
	{
		Collection<TReparacion> res;
		TReparacion result;
		do {
			res = dao.listar();
			result = (TReparacion) res.toArray()[0];
		} while (result.getId() == -4);
		
		assertTrue(res != null); 
	}
	
	

}