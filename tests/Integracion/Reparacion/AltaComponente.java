package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;

@RunWith(value = Parameterized.class)
public class AltaComponente {
	@Parameters
	public static Iterable<Object[]> getData() {

		return Arrays.asList(new Object[][]{
			{	
				new TEmplea(2, 2, 1000,100), 
				new TEmplea(1, 1, 1000,100)
			}
		});
	}
	
	public TEmplea correcto;
	public TEmplea incorrecto;
	
	public AltaComponente(TEmplea correcto,
			TEmplea incorrecto)
	{
		this.correcto = correcto;
		this.incorrecto = incorrecto;
		
	}
	
	@Test
	public void correcto() {
		TEmplea esperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaComponente(correcto);
		assertTrue(esperado.getIdReparacion() > 0);
	}
	@Test
	public void incorrecto() {
		TEmplea esperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaComponente(incorrecto);
		assertTrue(esperado.getIdReparacion() == -4);
	}

}
