package Integracion.Especialidad;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<Object[]> getData() {
		//TODO Requiere una mas ejemplos
		return Arrays.asList(new Object[][]{
			{new TEspecialidad("pintura"), new TEspecialidad("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")}
		});
	}
	public TEspecialidad tEspecialidadCorrecto;
	public TEspecialidad tEspecialidadIncorrecto;
	public Alta(TEspecialidad tEspecialidadCorrecto,TEspecialidad tEspecialidadIncorrecto)
	{
		this.tEspecialidadCorrecto = tEspecialidadCorrecto;
		this.tEspecialidadIncorrecto = tEspecialidadIncorrecto;
		
	}
	@Test
	public void correcto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().alta(tEspecialidadCorrecto);
		assertTrue(idEsperado > 0);
	}
	@Test
	public void incorrecto() {
		int idEsperado = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().alta(tEspecialidadIncorrecto);
		assertTrue(idEsperado == -4);
	}

}
