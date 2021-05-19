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
public class BajaComponente {
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TEmplea(2, 2)}
		});
	}
	private TEmplea idCorrecto;
	public BajaComponente(TEmplea idCorrecto, TEmplea idIncorrecto)
	{
		this.idCorrecto = idCorrecto;
	}
	@Test
	public void correcto() {
		TEmplea esperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().bajaComponente(idCorrecto);
		assertTrue(esperado.getIdComponente() > 0);
	}
	//No hemos podido realizar un test del fallo de la baja debido a no encontrar la posibilidad de realizarlo
}