package Integracion.Reparacion;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Integracion.Utility;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;

public class BajaMecanico {
	private static TTrabaja corr;
	private static DAOReparacion dao;
		
	@Before
	public void initTest() {
		corr = new TTrabaja(1,1,100);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@Test
	public void correcto() {
		TTrabaja idEsperado;
		do {
			idEsperado = dao.bajaMecanico(new TTrabaja(1, 1));
		} while (idEsperado.getIdReparacion() == -4);
		assertTrue(idEsperado.getIdMecanico() > 0);
	}
	
}