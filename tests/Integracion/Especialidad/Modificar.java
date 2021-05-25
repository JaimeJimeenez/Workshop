package Integracion.Especialidad;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;

public class Modificar {
	
	private static final String TIPO_TEST = "TESTESPECIALIDADAO";
	private static final TEspecialidad TESPECIALIDADTEST =  new TEspecialidad(TIPO_TEST);
	private static int idEspecialidad;
	private static DAOEspecialidad daoEspecialidad;
	
	@BeforeClass
	public static void initClass() {
		daoEspecialidad = FactoriaIntegracion.obtenerInstancia().crearEspecialidad();
		do{
			idEspecialidad = daoEspecialidad.alta(TESPECIALIDADTEST);
		}
		while(idEspecialidad == -4);
		TESPECIALIDADTEST.setId(idEspecialidad);
	}
	@AfterClass
	public static void destroyClass() {
		while(Utility.bajaFisicaEspecialidad(idEspecialidad) == -4);
	}
	@Test
	public void correcto()
	{
		TESPECIALIDADTEST.setTipo("tipo");
		int result =  daoEspecialidad.modificar(TESPECIALIDADTEST);
		assertTrue(result > 0);
	}
}
