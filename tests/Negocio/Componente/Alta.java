package Negocio.Componente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;

@RunWith(value = Parameterized.class)
public class Alta {
	private static final String MARCA = "TESTCOMPONENTE";
	private static final String MODELO = "TESTCOMPONENTE";
	private static final String NOMBRE = "11111111";

	@Parameters
	public static Iterable<Object[]> getData() {
		return Arrays.asList(new Object[][]{
			
			{new TComponente(MARCA, 0, 20.50f, MODELO, 5),
					new TComponente(MARCA, -1, 20.50f, MODELO, 5)},
			{new TComponente(MARCA, 0, 20.50f, MODELO, 5),
						new TComponente(MARCA, 1, -20.50f, MODELO, 5)},
			{new TComponente(MARCA, 0, 20.50f, MODELO, 5),
								new TComponente(MARCA, 1, 20.50f, MODELO, -5)},
				});
	}

	private TComponente componenteCorrecto, componenteIncorrecto;
	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idComponente, idProveedor;

	public Alta(TComponente componenteCorrecto, TComponente componenteIncorrecto) {
		this.componenteCorrecto = componenteCorrecto;
		this.componenteIncorrecto = componenteIncorrecto;

		this.componenteCorrecto.setIdProveedor(idProveedor);
	}

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();

		do{
		idProveedor = saProveedor.alta(new TProveedor(NOMBRE, "987654321", "TESTCOMPONENTE"));
		}while(idProveedor == -4);
		
		if (idProveedor == -1)
			idProveedor = FactoriaIntegracion.obtenerInstancia()
					.crearProveedor().leerPorNIF(NOMBRE).getId();
		

		TComponente m = FactoriaIntegracion.obtenerInstancia().crearComponente().leerPorMarcaModelo(MARCA, MODELO);
		idComponente = m != null ? m.getId() : -1;

	}

	@Before
	public void initTest() {
		while (saComponente.baja(idComponente) == -4);
	}

	@AfterClass
	public static void destroyClass() {
		while (saComponente.baja(idComponente) == -4);
		while (saProveedor.baja(idProveedor) == -4);
	}

	@Test
	public void testCorrecto() {
		int resultado;
		do{
			resultado = saComponente.alta(componenteCorrecto);
		}while(resultado == -4);
		
		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";

		assertTrue(message, resultado > 0);
	}

	@Test
	public void testRepetido() {
		while (saComponente.alta(componenteCorrecto)==-4);

		int resultado;
		do{
			resultado =  saComponente.alta(componenteCorrecto);
		}while(resultado == -4);
		
		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";

		assertTrue(message, resultado ==-1 );

	}

	@Test
	public void testDatosIncorrectos() {

		int resultado;
		do{
			resultado =  saComponente.alta(componenteIncorrecto);
		}while(resultado == -4);
		
		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";

		assertTrue(message, resultado == 0);

	}

	@Test
	public void testIDProveedorNoEncontrado() {
		while(saProveedor.baja(idProveedor)==-4);

		int resultado;
		do{
			resultado =  saComponente.alta(componenteCorrecto);
		}while(resultado == -4);
		
		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";
		
		
		while (saProveedor.alta(new TProveedor(NOMBRE, "987654321", "TESTCOMPONENTE"))==-4);

		assertTrue(message, resultado == -3);
	}
}
