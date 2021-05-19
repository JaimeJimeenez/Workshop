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
public class Alta {
	@Parameters
	public static Iterable<Object[]> getData() {
		//TODO Requiere una mas ejemplos
		return Arrays.asList(new Object[][]{
			//int idVehiculo, String fechaInicio, String fechaSalida, String averia, float presupuesto
			{	
				new TReparacion(2,"2020-11-11","2020-12-12","frenos", 1000),
				new TReparacion(2, "22-11-11", "2020-12-12", "frenos", 1000),
				new TReparacion(2,"2020-11-11","2020-12-12","qwertyuioplaksjdhfgmznxbcvpqowieurtyalskdjfhgmznxba", 1000)
			}
		});
	}
	public TReparacion tReparacionCorrecta;
	public TReparacion tReparacionFechasMal;
	public TReparacion tReparacionFueraDeRango;
	
	public Alta(TReparacion tEspecialidadCorrecto, 
			TReparacion tReparacionFechasMal,
			TReparacion tReparacionFueraDeRango)
	{
		this.tReparacionCorrecta = tEspecialidadCorrecto;
		this.tReparacionFechasMal = tReparacionFechasMal;
		this.tReparacionFueraDeRango = tReparacionFueraDeRango;
		
	}
	
	@Test
	public void correcto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().alta(tReparacionCorrecta);
		assertTrue(idEsperado > 0);
	}	
	@Test
	public void incorrectoFechas() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().alta(tReparacionFechasMal);
		assertTrue(idEsperado == -4);
	}
	
	@Test
	public void incorrectoTamayo() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().alta(tReparacionFueraDeRango);
		assertTrue(idEsperado == -4);
	}
}
