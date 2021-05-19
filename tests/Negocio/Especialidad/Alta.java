package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<String[]>getData()
	{		
		return Arrays.asList(new String[][]
				{{"motocicleta", "124214"}, 
				{"neumaticos", "3jexf"},
				{ "chapa", ""},
				{"motor","aaaaaaaaaaaaaaaaaaaaaaaaaa"}
				});
	}
	private String tipoCorrecto;
	private String tipoIncorrecto;
	private SAEspecialidad saEspecialidad;
	
	public Alta(String tipoCorrecto, String tipoIncorrecto)
	{
		this.tipoCorrecto = tipoCorrecto;
		this.tipoIncorrecto = tipoIncorrecto;
	}
	@Before
	public void init()
	{
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
	}
	@Test
	public void testCorrecto()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoCorrecto));
		String message = "";
		if(resultado == 0)
			message = "Datos Incorrectos";
		if(resultado == -1)
			message= "Datos Repetidos";
		assertTrue(message, resultado > 0);
	}
	@Test
	public void testRepetido()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoCorrecto));
		assertTrue(resultado == -1);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saEspecialidad.alta(new TEspecialidad(tipoIncorrecto));
		assertTrue(resultado == 0);
	}
}
