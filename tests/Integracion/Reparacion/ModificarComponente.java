package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Reparacion.TEmplea;

public class ModificarComponente {
	
	public static TEmplea correcto;
	private static DAOReparacion dao;
	private int idReparacion;
	
	public ModificarComponente(){
		this.correcto = new TEmplea(1, 1, 20000, 200);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Before
	public void initTest() {
		while ((idReparacion = dao.altaComponente(correcto).getIdReparacion()) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (dao.bajaComponente(correcto).getIdReparacion() == -4);
	}
	
	@Test
	public void correcto() {
		TEmplea esperado;
		do {
			esperado = dao.modificarComponente(new TEmplea(1, 1, 100, 1000));
		} while (esperado.getIdReparacion() == -4);
		assertTrue(esperado.getIdReparacion() > 0);
	}
	//No hemos podido realizar un test del fallo de la baja debido a no encontrar la posibilidad de realizarlo
}
