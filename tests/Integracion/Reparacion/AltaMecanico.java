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
public class AltaMecanico {

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
			{new TTrabaja(1, 2, 10), 
				new TTrabaja(2, 3, 1000)}
		});
	}
	
	public TTrabaja correcto;
	public TTrabaja horasIncorrectas;
	
	public AltaMecanico(TTrabaja correcto, TTrabaja horasIncorrectas) {
		this.correcto = correcto;
		this.horasIncorrectas = horasIncorrectas;
	}
	
	@Test
	public void correcto() {
		TTrabaja idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaMecanico(correcto);
		assertTrue(idEsperado.getIdReparacion() > 0);
	}
	
	@Test
	public void horasIncorrectas() {
		TTrabaja idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaMecanico(horasIncorrectas);
		assertTrue(idEsperado.getIdReparacion() == -4);
	}
	
}
