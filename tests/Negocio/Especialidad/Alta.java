package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	private static final String TIPO_TEST = "TESTESPECIALIDAD";
	@Parameters
	public static Iterable<String[]>getData()
	{			return Arrays.asList(new String[][]
				{{TIPO_TEST, "124214"}, //Fallo por usar digitos
				{ TIPO_TEST, ""},//Fallo por paso de cadena vacia
				});
	}
	private String tipoCorrecto;
	private String tipoIncorrecto;
	private static SAEspecialidad saEspecialidad;
	private static int idEspecialidad;
	public Alta(String tipoCorrecto, String tipoIncorrecto)
	{
		this.tipoCorrecto = tipoCorrecto;
		this.tipoIncorrecto = tipoIncorrecto;
	}

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

	@Before
	public void initTest() {
		if (idEspecialidad > 0)
			while(saEspecialidad.baja(idEspecialidad) == -4);
		
	}
	@AfterClass
	public static void destroyClass() {
		while(saEspecialidad.baja(idEspecialidad) == -4);
	}
	
	@Test
	public void testCorrecto()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoCorrecto));
		String message = "";
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Datos repetidos";
		else if (resultado == -4)
			message = "Fallo de SQL";
		assertTrue(message, resultado > 0);
	}
	@Test
	public void testRepetido()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoCorrecto));
		resultado = saEspecialidad.alta(new TEspecialidad(tipoCorrecto));
		String message = "";
		if(resultado > 0)
			message = "Datos Correctos";
		else if (resultado == 0)
			message = "Datos Incorrectos";
		else if (resultado == -4)
			message = "Fallo de SQL";
		assertTrue(message, resultado == -1);
		
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoIncorrecto));
		String message = "";
		if (resultado > 0)
			message = "Datos Correctos";
		else if (resultado == -1)
			message = "Datos Repetidos";
		else if (resultado == -4)
			message = "Fallo de SQL";
		assertTrue(message, resultado == 0);
	}
}
