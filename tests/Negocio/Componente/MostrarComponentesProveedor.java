package Negocio.Componente;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;

public class MostrarComponentesProveedor {
	private static final String NOMBRE = "TESTCOMPONENTE";
	private static final String DNI = "14915998S";
	
	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idComponente, idProveedor;
	private static TComponente componente = new TComponente(NOMBRE, 1, 20.50f, NOMBRE, 5);

	private Collection<TComponente> listaComponente;

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();

		do {
			idProveedor = saProveedor.alta(new TProveedor(DNI, "600000000", NOMBRE));

			if (idProveedor == -1)
				idProveedor = FactoriaIntegracion.obtenerInstancia()
						.crearProveedor().leerPorNIF(DNI).getId();

		} while (idProveedor == -4);


		do {
			idComponente = saComponente.alta(componente);

			if (idComponente == -1)
				idComponente = FactoriaIntegracion.obtenerInstancia()
						.crearComponente().leerPorMarcaModelo(componente.getMarca(), componente.getModelo()).getId();
		} while (idComponente == -4);
		
		componente.setId(idComponente);
	}

	@AfterClass
	public static void destroyClass() {
		while (saComponente.baja(idComponente) == -4);
		while (saProveedor.baja(idProveedor) == -4);
	}

	@Test
	public void testCorrecto() {
		while(saComponente.alta(componente) == -4);
		listaComponente = saComponente.mostrarComponentesProveedor(idProveedor);

		int res;
		String message = "";

		if (listaComponente != null) {
			res = ((TComponente) listaComponente.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la proveedor no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay componentes en la proveedor ";

		while(saComponente.baja(componente.getId()) == -4);
		
		boolean result = listaComponente != null
				&& (listaComponente.iterator().next()).getId() > 0;

		assertTrue(message, result);
	}

	@Test
	public void testDatosIncorrectos() {
		listaComponente = saComponente
				.mostrarComponentesProveedor(-idProveedor);

		int res;
		String message = "";

		if (listaComponente != null) {
			res = ((TComponente) listaComponente.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la proveedor no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay componentes en la proveedor ";

		boolean result = listaComponente != null
				&& listaComponente.iterator().next().getId() == 0;

		assertTrue(message, result);
	}

	@Test
	public void testProveedorNoEncontrada() {
		while (saComponente.baja(idComponente) == -4);
		while (saProveedor.baja(idProveedor) == -4);

		listaComponente = saComponente.mostrarComponentesProveedor(idProveedor);

		int res;
		String message = "";

		if (listaComponente != null) {
			res = ((TComponente) listaComponente.toArray()[0]).getId();

			if (res == 0)
				message = "Listar incorrecto";
			else if (res == -3)
				message = "ID de la proveedor no encontrado";
			else if (res == -4)
				message = "Error en la base de datos";

		} else
			message = "No hay componentes en la proveedor ";

		boolean result = listaComponente != null && listaComponente.iterator().next().getId() == -3;
		while (saProveedor.alta(new TProveedor(DNI, "600000000", NOMBRE)) == -4);
		while (saComponente.alta(componente) == -4);

		assertTrue(message, result);
	}
}
