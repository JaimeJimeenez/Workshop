package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TReparacion;

@RunWith(value = Parameterized.class)
public class LeerPorDatos {
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			{new TReparacion(0, "2020-11-11","2020-12-12","frenos", 1000),
				new TReparacion(0, "22-11-11", "2020-12-12", "frenos", 1000)}
		});
	}
	public TReparacion tReparacionCorrecta;
	public TReparacion tReparacionFechasMal;
	
	public LeerPorDatos(TReparacion tEspecialidadCorrecto, TReparacion tReparacionFechasMal) {
		this.tReparacionCorrecta = tEspecialidadCorrecto;
		this.tReparacionFechasMal = tReparacionFechasMal;
	}
	
	@Test
	public void correcto() {
		TReparacion idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().leerPorDatos(tReparacionCorrecta);
		assertTrue(idEsperado.getId() > 0 && idEsperado.getIdVehiculo() > 0);
	}
	
	@Test
	public void incorrectoFechas() {
		TReparacion idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().leerPorDatos(tReparacionFechasMal);
		assertTrue(idEsperado.getId() == -4);
	}
}
