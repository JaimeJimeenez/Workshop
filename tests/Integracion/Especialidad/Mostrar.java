package Integracion.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;

@RunWith(value = Parameterized.class)
public class Mostrar {
	@Parameters
	public static Iterable<Integer[]> getData()
	{
		return Arrays.asList(new Integer[][]{{2}});
	}
	private int idCorrecto;
	public Mostrar(int idCorrecto)
	{
		this.idCorrecto = idCorrecto;
	}
	@Test
	public void correcto()
	{
		TEspecialidad result = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().mostrar(idCorrecto);
		assertTrue(result.getId() > 0);
	}
}
