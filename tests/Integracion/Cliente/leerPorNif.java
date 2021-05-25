package Integracion.Cliente;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;

public class leerPorNif {

	private static final String MODULO = "cliente";
	private static String DIRECCION_TEST = "TESTCLIENTEDAO";
	private static String DNI_TEST = "95946823T";
	private static TCliente TCLIENTETEST = new TParticular("TESTCLIENTE", "987654321", DNI_TEST, DIRECCION_TEST);
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
		String nif = "95946823T";
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		assertTrue(daoCliente.leerPorNif(nif).getNif().equals(nif));
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaCliente(idCliente) == -4);
	}
}
