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
public class Modificar {
	@Parameters
	public static Iterable<TReparacion[]> getData() {
		return Arrays.asList(new TReparacion[][]{
			{new TReparacion(2, "2020-11-11", "2020-12-12", "frenos", 1000),
				new TReparacion(2, "2021-04-12", "2021-04-12", "2021-04-14", 4000)}
		});
	}
	
	private SAReparacion saReparacion;
	private TReparacion tReparacionCorrecta;
	private TReparacion tReparacionNoActiva;
	
	public Modificar(TReparacion tReparacionCorrecta, TReparacion tReparacionNoActiva) {
		this.tReparacionCorrecta = tReparacionCorrecta;
		this.tReparacionNoActiva = tReparacionNoActiva;
		
	}
	
	@Before
	public void init() {
		saReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion();	
	}
	
	@Test
	public void correcto() {
		int result = saReparacion.modificar(tReparacionCorrecta);
		assertTrue(result > 0);
	}
	
	@Test
	public void noActivo() {
		int result = saReparacion.modificar(tReparacionNoActiva);
		assertTrue(result == -1);
	}
	
}