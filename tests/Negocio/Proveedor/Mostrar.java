package Negocio.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Mostrar {
	
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private SAProveedor saProveedor;
	@Parameters
	public static Iterable<Integer[]> getData()
	{
		return Arrays.asList(new Integer[][]{{1, 0 , 50}});
		
	}
	public Mostrar(int idCorrecto, int idIncorrecto, int idNoEncontrado)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado; 
	}
	@Before
	public void init()
	{
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();	
	}
	@Test
	public void correcto()
	{
		TProveedor resultado = saProveedor.mostrar(idCorrecto);
		assertTrue(resultado.getId() > 0);
	}
	@Test
	public void noEncontrado()
	{
		TProveedor resultado = saProveedor.mostrar(idNoEncontrado);
		assertTrue(resultado.getId() == -1);
	}
	@Test
	public void incorrecto()
	{
		TProveedor resultado = saProveedor.mostrar(idIncorrecto);
		assertTrue(resultado.getId() == 0);
	}

}
