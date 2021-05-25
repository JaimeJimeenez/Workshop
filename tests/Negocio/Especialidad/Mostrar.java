package Negocio.Especialidad;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;

public class Mostrar {
	
	private static final String TIPO_TEST = "TESTESPECIALIDAD";
	private static int idEspecialidad;
	private static SAEspecialidad saEspecialidad;
	@BeforeClass
	public static void initClass() {
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		idEspecialidad = saEspecialidad.alta(new TEspecialidad(TIPO_TEST));
		do
		{
			TEspecialidad m = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().leerPorTipo(TIPO_TEST);
			idEspecialidad = m != null ? m.getId() : -1;
		}while(idEspecialidad == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while(saEspecialidad.baja(idEspecialidad) == -4);
	}
	@Test
	public void correcto()
	{
		TEspecialidad resultado = saEspecialidad.mostrar(idEspecialidad);
		int res = resultado.getId();
		String message = "";
		if (res == 0)
			message = "Datos Incorrectos";
		else if (res == -1)
			message = "Especialidad No Encontrado";
		else if (res == -4)
			message = "Fallo SQL";
		assertTrue(message, res > 0);
	}
	@Test
	public void noEncontrado()
	{
		saEspecialidad.baja(idEspecialidad);
		TEspecialidad resultado = saEspecialidad.mostrar(idEspecialidad);
		saEspecialidad.alta(new TEspecialidad(TIPO_TEST));
		int res = resultado.getId();
		String message = "";
		if (res > 0)
			message = "Datos Correctos";
		else if (res == 0)
			message = "Datos Incorrectos";
		else if (res == -4)
			message = "Fallo SQL";
		assertTrue(message, res == -1);
	}
	@Test
	public void incorrecto()
	{
		TEspecialidad resultado = saEspecialidad.mostrar(-idEspecialidad);
		int res = resultado.getId();
		String message = "";
		if (res > 0)
			message = "Datos Correctos";
		else if (res == -1)
			message = "Especialidad No Encontrado";
		else if (res == -4)
			message = "Fallo SQL";
		assertTrue(message, res == 0);
	}
}
