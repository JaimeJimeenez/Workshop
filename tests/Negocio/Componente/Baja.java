package Negocio.Componente;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TParticular;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.TVehiculo;

public class Baja {
	private static final String DNI = "46684293C";
	private static final String MATRICULA = "5717ZJY";
	
	private static final String TESTEO = "TESTCOMPONENTE";

	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idComponente, idProveedor;
	private static TComponente componente = new TComponente(TESTEO, 0, 20.5f, TESTEO, 12);

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		do{
			idProveedor = saProveedor.alta(new TProveedor(DNI, "985454321", TESTEO));
			
			if (idProveedor == -1)
				idProveedor = FactoriaIntegracion.obtenerInstancia().crearProveedor().leerPorNIF(DNI).getId();
		}while(idProveedor == -4);
		
		componente.setIdProveedor(idProveedor);
		do {
			idComponente = saComponente.alta(componente);

			if (idComponente == -1){
				idComponente = FactoriaIntegracion.obtenerInstancia().crearComponente().leerPorMarcaModelo(TESTEO, TESTEO).getId();
			}
		} while (idComponente == -4);
		componente.setId(idComponente);
	}

	@Before
	public void initTest() {
		while(saComponente.alta(componente) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		saComponente.baja(idComponente);
		saProveedor.baja(idProveedor);
	}

	@Test
	public void testCorrecto() {
		int res = saComponente.baja(idComponente);
		
		String message = "";
		if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Comp con ID " + idComponente + " no encontrado";
		else if (res == -2)
			message = "El mecanico está trabajando en una reparación";
		else if (res == -4)
			message = "Error en la base de datos";
		
		assertTrue(message, res > 0);
	}

	@Test
	public void testDatosIncorrectos() {
		int res = saComponente.baja(-idComponente);

		assertTrue(res == 0);
	}

	@Test
	public void testNoEncontrado() {
		saComponente.baja(idComponente);

		int res = saComponente.baja(idComponente);
		
		saComponente.alta(componente);

		assertTrue(res == -1);
	}

	@Test
	public void testComponenteEnReparacion() {
		int idCliente = crearCliente();
		assertTrue("Fallo cliente", idCliente > 0);

		int idVehiculo = crearVehiculo(idCliente);
		assertTrue("Fallo vehículo", idVehiculo > 0);

		int idReparacion = crearReparacion(idVehiculo);
		assertTrue("Fallo reparación", idReparacion > 0);

		
		int res = saComponente.baja(idComponente);


		while (FactoriaSA.obtenerInstancia().crearSACliente()
				.baja(idCliente) == -4);
		while (FactoriaSA.obtenerInstancia().crearSAVehiculo()
				.baja(idVehiculo) == -4);
		while (FactoriaSA.obtenerInstancia().crearSAReparacion()
				.baja(idReparacion) == -4);

		assertTrue(res == -2);
	}

	private int crearCliente() {
		TCliente cliente = new TParticular(TESTEO, "123456789", DNI,
				"TESTCOMPONENTE");

		int idCliente;

		do {
			idCliente = FactoriaSA.obtenerInstancia().crearSACliente()
					.alta(cliente);

			if (idCliente == -1)
				idCliente = FactoriaIntegracion.obtenerInstancia()
						.crearCliente().leerPorNif(DNI).getId();
		} while (idCliente == -4);

		return idCliente;
	}

	private int crearVehiculo(int idCliente) {
		TVehiculo vehiculo = new TVehiculo(MATRICULA, TESTEO, idCliente);

		int idVehiculo;

		do {
			idVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo()
					.alta(vehiculo);

			if (idVehiculo == -1)
				idVehiculo = FactoriaIntegracion.obtenerInstancia()
						.crearVehiculo().leerPorMatricula(MATRICULA).getId();
		} while (idVehiculo == -4);

		return idVehiculo;
	}

	private int crearReparacion(int idVehiculo) {
		TReparacion reparacion = new TReparacion(idVehiculo, "2021-02-21",
				"2021-03-21", TESTEO, 1500);
		Collection<TEmplea> componentes = new ArrayList<TEmplea>();
		Collection<TTrabaja> mecanicos = new ArrayList<TTrabaja>();
		componentes.add(new TEmplea(0, idComponente, 20.0f, 10));

		int idReparacion;

		idReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion()
				.alta(reparacion, componentes, mecanicos);

		if (idReparacion == -1)
			idReparacion = FactoriaIntegracion.obtenerInstancia()
					.crearReparacion().leerPorDatos(reparacion).getId();

		return idReparacion;
	}
}
