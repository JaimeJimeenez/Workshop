package Integracion.Cliente;

import static org.junit.Assert.*;

import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;

public class Reactivar {

	@Test
	public void correcto() {
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		TCliente tCliente = daoCliente.mostrar(5);
		assertTrue(daoCliente.reactivar(tCliente) == 5);
	}
}
