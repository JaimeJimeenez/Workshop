package Integracion.Reparacion;

import static org.junit.Assert.assertTrue;

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
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;


public class AltaComponente {
	private static DAOReparacion dao;
	
	
	public TEmplea correcto;
	
	public AltaComponente()
	{
		this.correcto = new TEmplea(1,1,100,100);
		dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
	}
	
	
	@AfterClass
	public static void destroyClass() {
		while(dao.bajaComponente(new TEmplea(1, 1)).getIdReparacion() == -4);
	}
		
	
	@Test
	public void correcto() {
		TEmplea esperado;
		do {
			esperado = FactoriaIntegracion.obtenerInstancia().crearReparacion().altaComponente(correcto);
		} while (esperado.getIdReparacion() == -4);
		assertTrue(esperado.getIdReparacion() > 0);
	}

}
