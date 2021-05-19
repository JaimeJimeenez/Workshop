package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TReparacion;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<String[]> getData() {
		// TODO unimplemented method
		return null;
	}
	
	private String tipoCorrecto;
	private String tipoIncorrecto;
	private SAReparacion saReparacion;
	
	public Alta(String tipoCorrecto, String tipoIncorrecto) {
		this.tipoCorrecto = tipoCorrecto;
		this.tipoIncorrecto = tipoIncorrecto;
	}
	
	@Before
	public void init() {
		saReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion();
	}
	
	@Test
	public void testCorrecto() {
		// TODO unimplemented method
	}
	@Test
	public void testRepetido() {
		// TODO unimplemented method
	}
	@Test
	public void testDatosIncorrectos() {
		// TODO unimplemented method
	}
}