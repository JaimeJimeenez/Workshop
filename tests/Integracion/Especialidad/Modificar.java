package Integracion.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;

@RunWith(value = Parameterized.class)
public class Modificar {
	@Parameters
	public static Iterable<TEspecialidad[]> getData()
	{
		return Arrays.asList(new TEspecialidad[][]{{new TEspecialidad("fusibles", 4)}});
	}
	private DAOEspecialidad daoEspecialidad;
	private TEspecialidad tEspecialidadCorrecta;
	public Modificar(TEspecialidad tEspecialidadCorrecta)
	{
		this.tEspecialidadCorrecta = tEspecialidadCorrecta;
	}
	@Before
	public void init()
	{
		daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();	
	}
	@Test
	public void correcto()
	{
		int result =  daoEspecialidad.modificar(tEspecialidadCorrecta);
		assertTrue(result > 0);
	}
}
