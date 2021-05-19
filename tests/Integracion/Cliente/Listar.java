package Integracion.Cliente;

import static org.junit.Assert.*;

import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.FactoriaSA.FactoriaSA;

public class Listar {

	@Test
	public void correcto() {
		SACliente saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		assertTrue(saCliente.listar() != null);
	}
}
