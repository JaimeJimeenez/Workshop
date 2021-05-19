package Integracion.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Proveedor.TProveedor;

@RunWith(value = Parameterized.class)
public class Alta {
	@Parameters
	public static Iterable<TProveedor[]>getData()
	{
		return Arrays.asList(new TProveedor[][]
				{{new TProveedor("357436789","913452543","calle del abeto"), new TProveedor("146436789","9134456743","calle del manzano")}
				});
	}
	private TProveedor tProveedorCorrecto;
	private TProveedor tProveedorIncorrecto;
	private DAOProveedor daoProveedor;
	
	public Alta(TProveedor tProveedorCorrecto, TProveedor tProveedorIncorrecto)
	{
		this.tProveedorCorrecto = tProveedorCorrecto;
		this.tProveedorIncorrecto = tProveedorIncorrecto;
	}
	@Before
	public void init()
	{
		daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();
	}
	@Test
	public void testCorrecto()
	{
		int resultado = daoProveedor.alta(tProveedorCorrecto);
		assertTrue(resultado > 0);
	}
	@Test
	public void testDatosIncorrectos()
	{
		int resultado = daoProveedor.alta(tProveedorIncorrecto);
		assertTrue(resultado == 0);
	}
	
}
