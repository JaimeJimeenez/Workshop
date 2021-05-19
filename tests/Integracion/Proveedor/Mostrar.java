package Integracion.Proveedor;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Proveedor.TProveedor;

@RunWith(value = Parameterized.class)
public class Mostrar {
	@Parameters
	public static Iterable<Integer[]> getData()
	{
		return Arrays.asList(new Integer[][]{{2}});
	}
	private int idCorrecto;
	public Mostrar(int idCorrecto)
	{
		this.idCorrecto = idCorrecto;
	}
	@Test
	public void correcto()
	{
		TProveedor result = FactoriaIntegracion.obtenerInstancia().crearProveedor().mostrar(idCorrecto);
		assertTrue(result.getId() > 0);
	}
}
