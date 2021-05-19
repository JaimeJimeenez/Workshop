package Integracion.Cliente;

import static org.junit.Assert.*;
import org.junit.Test;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;

public class leerPorNif {

	@Test
	public void correcto() {
		String nif = "987654321";
		DAOCliente daoCliente = FactoriaIntegracion.obtenerInstancia().crearCliente();
		assertTrue(daoCliente.leerPorNif(nif).getNif().equals(nif));
	}
}
