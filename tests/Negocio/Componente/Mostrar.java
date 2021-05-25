package Negocio.Componente;


import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;

public class Mostrar {
	private static final String NOMBRE = "TESTCOMPONENTE";

	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idComponente, idProveedor;
	private static TComponente componente;

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();

		do {
			idProveedor = saProveedor.alta(new TProveedor("00000000", "600000000", NOMBRE));

			if (idProveedor == -1)
				idProveedor = FactoriaIntegracion.obtenerInstancia()
						.crearProveedor().leerPorNIF("00000000").getId();

		} while (idProveedor == -4);

		componente = new TComponente("TESTCOMPONENTEMarca", 1, 20.50f, "TESTCOMPONENTEModelo", 5);
		
		do {
			idComponente = saComponente.alta(componente);

			if (idComponente == -1)
				idComponente = FactoriaIntegracion.obtenerInstancia()
						.crearComponente().leerPorMarcaModelo(componente.getMarca(), componente.getModelo()).getId();
		} while (idComponente == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (saComponente.baja(idComponente) == -4);
		while (saProveedor.baja(idProveedor) == -4);
	}

	@Test
	public void testCorrecto() {
		TComponente result = saComponente.mostrar(idComponente);

		int res = result.getId();
		String message = "";

		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Componente con ID " + idComponente + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";
		
		assertTrue(message, res > 0);
	}
	
	@Test
	public void testDatosIncorrecto() {
		TComponente result = saComponente.mostrar(-idComponente);

		int res = result.getId();
		String message = "";

		if (res > 0)
			message = "Datos correctos";
		else if (res == -1)
			message = "Componente con ID " + idComponente + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";
		
		assertTrue(message, res == 0);
	}
	
	@Test
	public void testComponenteNoEncontrado() {
		while(saComponente.baja(idComponente) == -4);
		
		TComponente result = saComponente.mostrar(idComponente);

		int res = result.getId();
		String message = "";

		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -4)
			message = "Error en la base de datos";
		
		while(saComponente.alta(componente) == -4);
		
		assertTrue(message, res == -1);
	}
}

