package Integracion.Cliente;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;

@RunWith(value = Parameterized.class)
public class Mostrar {
	
	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{
			{ 2 }
		});
	}
	
	private int idCorrecto;
	
	public Mostrar(int idCorrecto) { this.idCorrecto = idCorrecto; }
	
	@Test
	public void correcto() {
		TCliente tCliente = FactoriaIntegracion.obtenerInstancia().crearCliente().mostrar(idCorrecto);
		assertTrue(tCliente.getId() > 0);
	}
}
