package Negocio.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class MostrarReparacionVehiculo {
	@Parameters
	public static Iterable<Integer[]>getData()
	{		
		return Arrays.asList(new Integer[][]{
			{2, 22}
		});
	}
	private int idCorrecto;
	private int idIncorrecto;
	private SAReparacion sa;
	
	public MostrarReparacionVehiculo(int idCorrecto, int idIncorrecto)
	{
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
	}
	@Before
	public void init()
	{
		sa = FactoriaSA.obtenerInstancia().crearSAReparacion();	
	}
	@Test
	public void testCorrecto()
	{
		Collection<TReparacion> res = sa.mostrarReparacionVehiculo(idCorrecto);
		for (TReparacion r : res){
			System.out.println(r.getId());
			assertTrue(r.getId() > 0 && r.getIdVehiculo() == idCorrecto);
		}
	}
	@Test
	public void testNoEncontrado()
	{
		Collection<TReparacion> res = sa.mostrarReparacionVehiculo(idIncorrecto);
		assertTrue(res == null);
	}
}
