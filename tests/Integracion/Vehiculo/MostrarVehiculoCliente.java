package Integracion.Vehiculo;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.Cliente.DAOCliente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TParticular;
import Negocio.Vehiculo.TVehiculo;

public class MostrarVehiculoCliente {
	
	private static String TESTEO = "TESTVEHICULO_DAO";
	private static TParticular TCLIENTE_TEST = new TParticular(TESTEO, "111111111", "12638355Q", TESTEO);
	private static int idVeh, idCli;
	private static final TVehiculo TESTVEH = new TVehiculo("0000YYY", TESTEO, idCli);
	private static DAOVehiculo daoVeh;
	private static DAOCliente daoCli;
	
	@BeforeClass
	public static void initClass() {
		daoCli = FactoriaIntegracion.obtenerInstancia().crearCliente();
		daoVeh = FactoriaIntegracion.obtenerInstancia().crearVehiculo();
		
		idCli = daoCli.alta(TCLIENTE_TEST);
		TESTVEH.setIdCliente(idCli);
		idVeh = daoVeh.alta(TESTVEH);
	}
	
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaCliente(idCli) == -4);
		while(Utility.bajaFisicaVehiculo(idVeh) == -4);
	}
	
	@Test
	public void correcto() {
		Collection<TVehiculo> res = daoVeh.mostrarVehiculoCliente(idCli);
		assertTrue(res != null && res.iterator().next().getId() > 0); 
	}
}
