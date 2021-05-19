package Negocio.Proveedor;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<TProveedor[]>getData()
	{
		return Arrays.asList(new TProveedor[][]
				{{new TProveedor("123436789","913456743","calle del pinar"), new TProveedor("123436789","9134456743","calle del pinar")}
				});
	}
	private TProveedor tProveedorCorrecto;
	private TProveedor tProveedorIncorrecto;
	private SAProveedor saProveedor;
	
	public Alta(TProveedor tProveedorCorrecto, TProveedor tProveedorIncorrecto)
	{
		this.tProveedorCorrecto = tProveedorCorrecto;
		this.tProveedorIncorrecto = tProveedorIncorrecto;
	}
	@Before
	public void init()
	{
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saProveedor.alta(tProveedorCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testRepetido()
	{
		int resultado = saProveedor.alta(tProveedorCorrecto);
		assertTrue(resultado == -1);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saProveedor.alta(tProveedorIncorrecto);
		assertTrue(resultado == 0);
	}
	
}
