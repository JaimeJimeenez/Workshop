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
public class Modificar {
	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][] {
			{new TReparacion(3, 2, "2020-11-11", "2020-12-12", "frenos", 1000),
				new TReparacion(7, 7, "44-45-32", "5234-2345-253", "frenos", 5000),
				new TReparacion(2, 3, "2020-11-11", "2020-12-12", "fkjhfjkñalskdjfñalskdjfdfañlskdjfñalskdjfjñlasdjfñalksjdf", 2000)}
		});
	}
	public TReparacion tReparacionCorrecto;
	public TReparacion tReparacionFechasErroneas;
	public TReparacion tReparacionFueraDeRango;
	
	public Modificar(TReparacion tReparacionCorrecto, TReparacion tReparacionFechasErroneas, TReparacion tReparacionFueraDeRango) {
		this.tReparacionCorrecto = tReparacionCorrecto;
		this.tReparacionFechasErroneas = tReparacionFechasErroneas;
		this.tReparacionFueraDeRango = tReparacionFueraDeRango;
	}
	
	@Test
	public void correcto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().modificar(tReparacionCorrecto);
		assertTrue(idEsperado > 0);
	}
	
	@Test
	public void incorrectoFechas() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().modificar(tReparacionFechasErroneas);
		assertTrue(idEsperado == -4);
	}
	
	@Test
	public void incorrectoRango() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().modificar(tReparacionFueraDeRango);
		assertTrue(idEsperado == -4);
	}
}