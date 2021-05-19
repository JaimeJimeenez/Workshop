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
public class Modificar {
	@Parameters
	public static Iterable<TProveedor[]> getData()
	{
		return Arrays.asList(new TProveedor[][]{{new TProveedor("765894231", 2, "912354678", "calle del pinar")}});
	}
	
	private DAOProveedor daoProveedor;
	private TProveedor tProveedorCorrecta;
	
	public Modificar(TProveedor tProveedorCorrecta)
	{
		this.tProveedorCorrecta = tProveedorCorrecta;
	}
	@Before
	public void init()
	{
		daoProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor();	
	}
	@Test
	public void correcto()
	{
		int result =  daoProveedor.modificar(tProveedorCorrecta);
		assertTrue(result > 0);
	}
}
