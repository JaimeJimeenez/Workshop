package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TReparacion;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;


public class Alta {
	private static final String TESTEO = "TESTREPARACION";
	
	private static int idReparacion;
	private static DAOReparacion dao;
	private TReparacion corr;
	
	public Alta()
	{
		this.corr = new TReparacion(1,"2020-12-12","2021-12-12",TESTEO, 1000);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaReparacion(idReparacion) == -4);
	}
	
	@Test
	public void correcto() {
		do {
			idReparacion = FactoriaIntegracion.obtenerInstancia().crearReparacion().alta(corr);
		} while (idReparacion == -4);
		assertTrue(idReparacion > 0);
	}
}
