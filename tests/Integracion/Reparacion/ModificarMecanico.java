package Integracion.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TTrabaja;

@RunWith(value = Parameterized.class)
public class ModificarMecanico {
	
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TTrabaja(1, 2, 10)}
		});
	}
	
	private TTrabaja correcto;
	
	public ModificarMecanico(TTrabaja correcto) {
		this.correcto = correcto;
	}
	
	@Test
	public void correcto() {
		TTrabaja idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().modificarMecanico(correcto);
		assertTrue(idEsperado.getIdReparacion() > 0);
	}
	
}