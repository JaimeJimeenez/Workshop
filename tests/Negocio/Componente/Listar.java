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

public class Listar {
	private static final String TESTEO = "TESTCOMPONENTE";
	private static final String DNI = "14915998S";
	
	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idComponente, idProveedor;
	private static TComponente componente;
	
	private Collection<TComponente> listaComponente;

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();

		do{
		idProveedor = saProveedor.alta(new TProveedor(DNI,"111111111",TESTEO));

		if (idProveedor == -1)
			idProveedor = FactoriaIntegracion.obtenerInstancia()
					.crearProveedor().leerPorNIF(DNI).getId();
		}while(idProveedor == -4);

		componente = new TComponente("TESTCOMPONENTE", idProveedor, 20.50f, "TESTCOMPONENTE", 5);
		
		do{
			idComponente = saComponente.alta(componente);

			if (idComponente == -1)
				idComponente = FactoriaIntegracion.obtenerInstancia()
						.crearComponente().leerPorMarcaModelo(componente.getMarca(), componente.getModelo()).getId();
		}while(idProveedor == -4);
	
	}

	@AfterClass
	public static void destroyClass() {
		while (saComponente.baja(idComponente) == -4);
		while (saProveedor.baja(idProveedor) == -4);
	}

	@Test
	public void testCorrecto() {
		listaComponente = saComponente.listar();

		int res;
		String message = "";
		if (listaComponente != null) {
			res = ((TComponente) listaComponente.toArray()[0]).getId();
			if(res == -4)
				message = "Error en la base de datos";
		} else
			message = "No hay componentes";

		boolean result = listaComponente != null && (listaComponente.iterator().next()).getId() > 0;
		
		assertTrue(message, result);
	}
}
