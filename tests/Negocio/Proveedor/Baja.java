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
public class Baja {
	
	@Parameters
	public static Iterable<Integer[]>getData()
	{		
		return Arrays.asList(new Integer[][]
				{{2,0,40, 1}, 
				});
	}
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private int idComponenteActivo;
	private SAProveedor saProveedor;
	public Baja(int idCorrecto,int idIncorrecto,int idNoEncontrado, int idComponenteActivo)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado;
		this.idComponenteActivo = idComponenteActivo;
	}
	@Before
	public void init()
	{
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();	
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saProveedor.baja(idCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saProveedor.baja(idIncorrecto);
		assertTrue(resultado == 0);
	}
	@Test
	public void testDatosNoEncontrado()
	{
		int resultado = saProveedor.baja(idNoEncontrado);
		System.out.println(resultado);
		assertTrue(resultado == -1);
	}
	@Test
	public void testComponenteActivo()
	{
		int resultado = saProveedor.baja(idComponenteActivo);
		assertTrue(resultado == -2);
	}
}
