
package Integracion.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

@RunWith(value = Parameterized.class)
public class Baja {
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{3}
		});
	}
	private int idCorrecto;
	public Baja(int idCorrecto)
	{
		this.idCorrecto = idCorrecto;
	}
	@Test
	public void correcto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearProveedor().baja(idCorrecto);
		assertTrue(idEsperado > 0);
	}
	//No hemos podido realizar un test del fallo de la baja debido a no encontrar la posibilidad de realizarlo


}
