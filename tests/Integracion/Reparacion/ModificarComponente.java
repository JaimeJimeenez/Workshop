package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TEmplea;

@RunWith(value = Parameterized.class)
public class ModificarComponente {
	@Parameters
	public static Iterable<Object[]> getData() {

		return Arrays.asList(new Object[][]{
			{	
				new TEmplea(2, 2, 20000, 200)
			}
		});
	}
	public TEmplea correcto;
	
	public ModificarComponente(TEmplea correcto)
	{
		this.correcto = correcto;	
	}
	
	@Test
	public void correcto() {
		TEmplea esperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().modificarComponente(correcto);
		assertTrue(esperado.getIdReparacion() > 0);
	}
	//No hemos podido realizar un test del fallo de la baja debido a no encontrar la posibilidad de realizarlo
}
