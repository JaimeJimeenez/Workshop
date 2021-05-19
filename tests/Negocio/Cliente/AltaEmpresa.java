package Negocio.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class AltaEmpresa {
	@Parameters
	public static Iterable<TEmpresa[]> getData() {
		return Arrays.asList(new TEmpresa[][] {
			{
				new TEmpresa("Pepe", "912234342", "12234456J", "marca.com"),
				new TEmpresa("Rebeca", "9667", "12234456Q", "mercadona.com")	
			}
		});
	}
	
	private TEmpresa tEmpresaCorrecto;
	private TEmpresa tEmpresaIncorrecto;
	private SACliente saEmpresa;
	
	public AltaEmpresa(TEmpresa tEmpresaCorrecto, TEmpresa tEmpresaIncorrecto) {
		this.tEmpresaCorrecto = tEmpresaCorrecto;
		this.tEmpresaIncorrecto = tEmpresaIncorrecto;
	}
	
	@Before
	public void init() { saEmpresa = FactoriaSA.obtenerInstancia().crearSACliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = saEmpresa.alta(tEmpresaCorrecto);
		assertTrue(resultado > 0);
	}
	
	public void testRepetido() {
		int resultado = saEmpresa.alta(tEmpresaCorrecto);
		assertTrue(resultado == -1);
	}
	
	public void testDatosIncorrectos() {
		int resultado = saEmpresa.alta(tEmpresaIncorrecto);
		assertTrue(resultado == 0);
	}
	
}
