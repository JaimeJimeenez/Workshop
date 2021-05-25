package Negocio.Especialidad;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;

public class Baja {
	private static final String TIPO_TEST = "TESTESPECIALIDAD";
	private static final String DNI_TEST = "77088913C";
	private static final TEspecialidad TESPECIALIDADTEST =  new TEspecialidad(TIPO_TEST);
	private static int idEspecialidad, idMecanico;
	private TEspecialidad tEspecialidad;
	private static SAEspecialidad saEspecialidad;
	private static SAMecanico saMecanico;
	public Baja()
	{
		tEspecialidad = TESPECIALIDADTEST;
	}
	@BeforeClass
	public static void initClass() {
		saMecanico = FactoriaSA.obtenerInstancia().crearSAMecanico();
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		do{
			idEspecialidad = saEspecialidad.alta(TESPECIALIDADTEST);
			
			if(idEspecialidad == -1)
			{
				TEspecialidad m = FactoriaIntegracion.obtenerInstancia().crearEspecialidad().leerPorTipo(TESPECIALIDADTEST.getTipo());

				idEspecialidad = m.getId();
			}
			
		}
		while(idEspecialidad == -4);
	}
	@Before
	public void initTest() {
		while(saEspecialidad.alta(tEspecialidad) == -4);
	}
	@AfterClass
	public static void destroyClass() {
		while(saMecanico.baja(idMecanico) == -4);
		while(saEspecialidad.baja(idEspecialidad) == -4);
	}
	@Test
	public void testCorrecto()
	{

		int resultado = saEspecialidad.baja(idEspecialidad);
		String message = "";
		if (resultado == 0)
			message = "Dato Incorrecto";
		else if (resultado == -1)
			message = "Especialidad No Encontrado";
		else if (resultado == -2)
			message = "Mecanico Actio";
		else if (resultado == -4)
			message = "Fallo SQL";
		
		assertTrue(message, resultado > 0);
	}	
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = saEspecialidad.baja(-idEspecialidad);
		String message = "";
		if (resultado > 0)
			message = "Dato correcto";
		else if (resultado == -1)
			message = "Especialidad No Encontrado";
		else if (resultado == -2)
			message = "Mecanico Actio";
		else if (resultado == -4)
			message = "Fallo SQL";
		assertTrue(message, resultado == 0);
	}
	@Test
	public void testDatosNoEncontrado()
	{
		while(saEspecialidad.baja(idEspecialidad) == -4);
		int resultado = saEspecialidad.baja(idEspecialidad);
		String message = "";
		if (resultado > 0)
			message = "Dato correcto";
		else if (resultado == -2)
			message = "Mecanico Actio";
		else if (resultado == -4)
			message = "Fallo SQL";
		assertTrue(message, resultado == -1);
	}
	@Test
	public void testMecanicoActivo()
	{
		do
		{
			idMecanico = saMecanico.alta( new TMecanico(DNI_TEST, 1500, "TESTESPECIALIDAD", idEspecialidad, "916633123", "525426183012345678901234"));
			if(idMecanico == -1)
			{
				TMecanico m = FactoriaIntegracion.obtenerInstancia().crearMecanico().leerPorNif(DNI_TEST);
				idMecanico = m.getId();
			}
		}while(idMecanico < 0);
		int resultado = saEspecialidad.baja(idEspecialidad);
		String message = "";
		if (resultado > 0)
			message = "Dato correcto";
		else if (resultado == 0)
			message = "Dato Incorrecto";
		else if (resultado == -1)
			message = "Especialidad No Encontrado";
		else if (resultado == -4)
			message = "Fallo SQL";
		while(saMecanico.baja(idMecanico) == -4);
		assertTrue(message, resultado == -2);
		
	}
}
