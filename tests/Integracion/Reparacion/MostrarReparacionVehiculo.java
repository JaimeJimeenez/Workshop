package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;

@RunWith(value = Parameterized.class)
public class MostrarReparacionVehiculo {
	@Parameters
	public static Iterable<Integer[]> getData()
	{
		return Arrays.asList(new Integer[][]{{2}});
	}
	private int idCorrecto;
	public MostrarReparacionVehiculo(int idCorrecto)
	{
		this.idCorrecto = idCorrecto;
	}
	@Test
	public void correcto()
	{
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		assertTrue(dao.mostrarReparacionesVehiculo(idCorrecto)!= null); 
	}
}
