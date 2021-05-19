package Integracion.Cliente;

import java.util.Arrays;
import static org.junit.Assert.*;

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
	public Baja(int idCorrecto) {
		this.idCorrecto = idCorrecto;
	}
	
	@Test
	public void correcto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearCliente().baja(idCorrecto);
		assertTrue(idEsperado > 0);
	}
}
