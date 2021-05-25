package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Modificar {
	private static final String TIPO_TEST = "TESTESPECIALIDAD";
	private static final String TIPO_TEST2 = "TESTESPECIALIDADDOS";
	@Parameters
	public static Iterable<TEspecialidad[]> getData()
	{
		return Arrays.asList(new TEspecialidad[][]{{new TEspecialidad("", 2)}
		,{new TEspecialidad("pint ura", 2)}
		,{ new TEspecialidad("luce21s",3)}
		,{ new TEspecialidad("luces",-3)}
		,{null}});
	}
	
	private static SAEspecialidad saEspecialidad;
	private static TEspecialidad tEspecialidadCorrecta;
	private TEspecialidad tEspecialidadIncorrecto;
	private static TEspecialidad tEspecialidadRepetido;
	private static int idEspecialidad;
	private static int idEspecialidad2;
	public Modificar( TEspecialidad tEspecialidadIncorrecto)
	{
		this.tEspecialidadIncorrecto = tEspecialidadIncorrecto;
	}
	@BeforeClass
	public static void initClass() {
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		tEspecialidadCorrecta = new TEspecialidad(TIPO_TEST);
		tEspecialidadRepetido = new TEspecialidad(TIPO_TEST2);
		do {
			idEspecialidad = saEspecialidad.alta(tEspecialidadCorrecta);
			if (idEspecialidad == -1)
				idEspecialidad = FactoriaIntegracion.obtenerInstancia()
						.crearEspecialidad().leerPorTipo(TIPO_TEST).getId();
		} while (idEspecialidad == -4 );

		tEspecialidadCorrecta.setId(idEspecialidad);
		do {
			idEspecialidad2 = saEspecialidad.alta(tEspecialidadRepetido);
			if (idEspecialidad2 == -1)
				idEspecialidad2 = FactoriaIntegracion.obtenerInstancia()
						.crearEspecialidad().leerPorTipo(TIPO_TEST2).getId();
		} while (idEspecialidad2 == -4);
		tEspecialidadRepetido.setId(idEspecialidad2);
	}

	@Test
	public void correcto()
	{
		int result =  saEspecialidad.modificar(tEspecialidadCorrecta);
		assertTrue(result > 0);
	}
	@Test
	public void incorrecto()
	{
		int result =  saEspecialidad.modificar(tEspecialidadIncorrecto);
		assertTrue(result == 0);
	}
	@Test
	public void noEncontrado()
	{
		saEspecialidad.baja(idEspecialidad);
		int result =  saEspecialidad.modificar(tEspecialidadCorrecta);
		saEspecialidad.alta(tEspecialidadCorrecta);
		assertTrue(result == -1);
	}
	@Test
	public void repetido()
	{
		tEspecialidadCorrecta.setTipo(TIPO_TEST2);
		int result =  saEspecialidad.modificar(tEspecialidadCorrecta);
		tEspecialidadCorrecta.setTipo(TIPO_TEST);
		assertTrue(result == -2);
	}
	@AfterClass
	public static void destroyClass() {
		saEspecialidad.baja(idEspecialidad);
		saEspecialidad.baja(idEspecialidad2);
	}
}
