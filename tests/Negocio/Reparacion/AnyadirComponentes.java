package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class AnyadirComponentes {
	@Parameters
	public static Iterable<Object[]> getData() {		
		return Arrays.asList(new Object[][]{
			{	
				new TEmplea(2,2,1000,10),
				new TEmplea(0,0,1000,10),
				new TEmplea(22, 2, 1000, 10),
				new TEmplea(2,22,1000,10),
				new TEmplea(1,1,1000,10)
			}
		});
	}
	private TEmplea correcto;
	private TEmplea datosIncorrectos;
	private TEmplea reparacionNoEncontrada;
	private TEmplea componenteNoEncontrado;
	private TEmplea yaExiste;
	private SAReparacion sa;
	
	public AnyadirComponentes(TEmplea correcto, TEmplea datosIncorrectos,
			TEmplea reparacionNoEncontrada, TEmplea componenteNoEncontrado,
			TEmplea yaExiste)
	{
		this.correcto = correcto;
		this.datosIncorrectos = datosIncorrectos;
		this.reparacionNoEncontrada = reparacionNoEncontrada;
		this.componenteNoEncontrado = componenteNoEncontrado;
		this.yaExiste = yaExiste;
	}
	@Before
	public void init()
	{
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();
	}
	@Test
	public void testCorrecto()
	{
		TEmplea resultado = sa.anyadirComponente(correcto);
		System.out.println("Correct " + resultado.getIdReparacion());
		assertTrue(resultado.getIdReparacion() > 0);
	}
	@Test
	public void componteIncorrecto()
	{
		TEmplea resultado = sa.anyadirComponente(componenteNoEncontrado);
		System.out.println("Componente Incorrect " + resultado.getIdReparacion());
		assertTrue(resultado.getIdReparacion() == -1);
	}
	@Test
	public void testDatosIncorrectos()
	{
		TEmplea resultado = sa.anyadirComponente(datosIncorrectos);
		assertTrue(resultado.getIdReparacion() == 0);
	}
	@Test
	public void reparacionIncorrecto()
	{
		TEmplea resultado = sa.anyadirComponente(reparacionNoEncontrada);
		System.out.println("Reparacion Incorrect " + resultado.getIdReparacion());
		assertTrue(resultado.getIdReparacion() == -2);
	}
	@Test
	public void existe()
	{
		TEmplea resultado = sa.anyadirComponente(yaExiste);
		System.out.println("Existe Incorrect " + resultado.getIdReparacion());
		assertTrue(resultado.getIdReparacion() == -3);
	}
}
