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
public class ModificarComponente {
	@Parameters
	public static Iterable<Object[]> getData() {		
		return Arrays.asList(new Object[][]{
			{	
				new TEmplea(2,2, 100, 100),
				new TEmplea(0,0, 100, 100),
				new TEmplea(22, 2, 100, 100),
				new TEmplea(2,22, 100, 100)
			}
		});
	}
	private TEmplea correcto;
	private TEmplea datosIncorrecto;
	private TEmplea reparacionError;
	private TEmplea componenteError;
	private SAReparacion sa;
	
	public ModificarComponente(TEmplea correcto, TEmplea datosIncorrecto, 
			TEmplea reparacionError, TEmplea componenteError)
	{
		this.correcto = correcto;
		this.datosIncorrecto = datosIncorrecto;
		this.reparacionError = reparacionError;
		this.componenteError = componenteError;
	}
	@Before
	public void init()
	{
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();	
	}
	@Test
	public void testCorrecto()
	{
		TEmplea resultado = sa.modificarComponenteReparacion(correcto);
		assertTrue(resultado.getIdComponente() > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		TEmplea resultado = sa.modificarComponenteReparacion(datosIncorrecto);
		assertTrue(resultado.getIdReparacion() == 0);
	}
	@Test
	public void testComponenteError()
	{
		TEmplea resultado = sa.modificarComponenteReparacion(componenteError);
		assertTrue(resultado.getIdReparacion() == -1);
	}
	@Test
	public void testReparacionError()
	{
		TEmplea resultado = sa.modificarComponenteReparacion(reparacionError);
		assertTrue(resultado.getIdReparacion() == -2);
	}
}
