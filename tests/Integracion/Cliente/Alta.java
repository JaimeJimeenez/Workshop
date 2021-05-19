package Integracion.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][] {
			{ new TCliente()}
		});
	}
	
	private TCliente tClienteCorrecto;
	private TCliente tClienteIncorrecto;
	private DAOCliente daoCliente;
	
	public Alta(TCliente tClienteCorrecto, TCliente tClienteIncorrecto) {
		this.tClienteCorrecto = tClienteCorrecto;
		this.tClienteIncorrecto = tClienteIncorrecto;
	}
	
	@Before
	public void init() { daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = daoCliente.alta(tClienteCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = daoCliente.alta(tClienteIncorrecto);
		assertTrue(resultado == 0);
	}
}
