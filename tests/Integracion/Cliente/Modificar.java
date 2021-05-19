package Integracion.Cliente;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;

@RunWith(value = Parameterized.class)
public class Modificar {

	@Parameters
	public static Iterable<TCliente[]> getData() {
		return Arrays.asList(new TCliente[][] {{
			//TODO
		}});
	}
	
	private TCliente tClienteCorrecto;
	private DAOCliente daoCliente;
	
	public Modificar(TCliente tClienteCorrecto) {
		this.tClienteCorrecto = tClienteCorrecto;
	}
	
	@Before
	public void init() { daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente(); }
	
	@Test
	public void correcto() {
		int resultado = daoCliente.modificar(tClienteCorrecto);
		assertTrue(resultado > 0);
	}
}
