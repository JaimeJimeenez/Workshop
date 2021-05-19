package Integracion.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TReparacion;

@RunWith(value = Parameterized.class)
public class Mostrar {
	
	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{{1}});
	}
	
	private int idCorrecto;
	
	public Mostrar(int idCorrecto) {
		this.idCorrecto = idCorrecto;
	}
	
	@Test
	public void correcto() {
		TReparacion result = (TReparacion) FactoriaIntegracion.obtenerInstancia().crearReparacion().mostrar(idCorrecto);
		assertTrue(result.getId() > 0);
	}
	
}