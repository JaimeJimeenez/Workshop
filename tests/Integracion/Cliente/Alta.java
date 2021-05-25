package Integracion.Cliente;

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
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.Cliente.TEmpresa;

public class Alta {
	
	private static final String MODULO = "cliente";
	private static String DIRECCION_TEST = "TESTCLIENTEDAO";
	private static String DNI_TEST = "95946823T";
	private static TCliente TCLIENTETEST = new TParticular("TESTCLIENTE", "987654321", DNI_TEST, DIRECCION_TEST);
	private static int idCliente;
	private static DAOCliente daoCliente;
	
	@BeforeClass
	public static void init() { daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente(); }
	
	@Test
	public void testCorrecto() {
		idCliente = daoCliente.alta(TCLIENTETEST);
		assertTrue(idCliente > 0);
	}
	
	@AfterClass
	public static void destroyClass() {
		while (Utility.bajaFisicaCliente(idCliente) == -4);
	}
}
