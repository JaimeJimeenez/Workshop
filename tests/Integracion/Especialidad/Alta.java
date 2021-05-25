package Integracion.Especialidad;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;


public class Alta {

	private static final String TIPO_CORRECTO = "TESTESPECIALIDADAO";
	private static DAOEspecialidad daoEspecialidad;
	private static int idEspecialidad;
	@BeforeClass
	public static void initClass() {
		daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
	}
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaEspecialidad(idEspecialidad) == -4);
	}
	@Test
	public void testCorrecto()
	{
		idEspecialidad = daoEspecialidad.alta(new TEspecialidad(TIPO_CORRECTO));
		String message = "Fallo";
		if(idEspecialidad == -4)
			message = "Fallo SQL";
		
		assertTrue(message, idEspecialidad > 0);
		
	}
	

}
