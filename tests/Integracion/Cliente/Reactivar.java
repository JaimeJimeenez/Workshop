package Integracion.Cliente;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;

public class Reactivar {
	
	private static String DIRECCION_TEST = "TESTCLIENTEDAO";
	private static String DNI_TEST = "30521344E";
	private static TCliente TCLIENTETEST = new TParticular ("TESTCLIENTE", "912437568", DNI_TEST, DIRECCION_TEST);
	private static int idCliente;
	private static DAOCliente daoCliente;
	
	@BeforeClass
	public static void initClass() {
		daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		do {
			idCliente = daoCliente.alta(TCLIENTETEST);
		} while (idCliente == -4);
	}
	
	@Test
	public void correcto() {
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente tCliente = daoCliente.mostrar(5);
		assertTrue(daoCliente.reactivar(tCliente) == 5);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaCliente(idCliente) == -4);
	}
	
}
