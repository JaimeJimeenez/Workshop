package Negocio.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Modificar {
	@Parameters
	public static Iterable<TProveedor[]> getData()
	{
		return Arrays.asList(new TProveedor[][]{{new TProveedor("765894231", 2, "912354678", "calle del pinar"),new TProveedor("765894231", 0, "912354678", "calle del pinar"), new TProveedor("765894231", 60, "912354678", "calle del pinar"), new TProveedor("987654321", 3, "912354678", "calle del pinar")}});
	}
	
	private SAProveedor saProveedor;
	private TProveedor tProveedorCorrecta;
	private TProveedor tProveedorIncorrecto;
	private TProveedor tProveedorNoEncontrado;
	private TProveedor tProveedorRepetido;
	public Modificar(TProveedor tProveedorCorrecta, TProveedor tProveedorIncorrecto, TProveedor tProveedorNoEncontrado, TProveedor tProveedorRepetido)
	{
		this.tProveedorCorrecta = tProveedorCorrecta;
		this.tProveedorIncorrecto = tProveedorIncorrecto;
		this.tProveedorNoEncontrado = tProveedorNoEncontrado;
		this.tProveedorRepetido = tProveedorRepetido;
	}
	@Before
	public void init()
	{
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();	
	}
	@Test
	public void correcto()
	{
		int result =  saProveedor.modificar(tProveedorCorrecta);
		assertTrue(result > 0);
	}
	@Test
	public void incorrecto()
	{
		int result =  saProveedor.modificar(tProveedorIncorrecto);
		assertTrue(result == 0);
	}
	@Test
	public void noEncontrado()
	{
		int result =  saProveedor.modificar(tProveedorNoEncontrado);
		assertTrue(result == -1);
	}
	@Test
	public void repetido()
	{
		int result =  saProveedor.modificar(tProveedorRepetido);
		assertTrue(result == -2);
	}
}
