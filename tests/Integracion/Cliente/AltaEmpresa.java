package Integracion.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TEmpresa;

@RunWith(value = Parameterized.class)
public class AltaEmpresa {

	@Parameters
	public static Iterable<TEmpresa[]> getData(){
		return Arrays.asList(new TEmpresa[][] {
			//TODO
		});
	}
	
	private TEmpresa tEmpresaCorrecto;
	private TEmpresa tEmpresaIncorrecto;
	private DAOCliente daoEmpresa;
	
	public AltaEmpresa(TEmpresa tEmpresaCorrecto, TEmpresa tEmpresaIncorrecto) {
		this.tEmpresaCorrecto = tEmpresaCorrecto;
		this.tEmpresaIncorrecto = tEmpresaIncorrecto;
	}
	
	@Before
	public void init() { daoEmpresa = FactoriaIntegracion.obtenerInstancia().crearCliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = daoEmpresa.alta(tEmpresaCorrecto);
		assertTrue(resultado > 0);
	}
	
	public void testIncorrecto() {
		int resultado = daoEmpresa.alta(tEmpresaIncorrecto);
		assertTrue(resultado == 0);
	}
}
