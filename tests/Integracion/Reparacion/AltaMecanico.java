package Integracion.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;


public class AltaMecanico {
	private static DAOReparacion dao;
	
	public TTrabaja correcto;
	
	public AltaMecanico() {
		this.correcto = new TTrabaja(1,1,100);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	@AfterClass
	public static void destroyClass() {
		while(dao.bajaMecanico(new TTrabaja(1,1)).getIdReparacion() == -4);
	}
	
	@Test
	public void correcto() {
		TTrabaja idEsperado; 
		do {
			idEsperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaMecanico(correcto);
		} while (idEsperado.getIdReparacion() == -4);
		assertTrue(idEsperado.getIdReparacion() > 0);
	}
	
}