package Negocio.Componente;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.TMecanico;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;

public class Modificar {

	private static final String MARCA = "TESTCOMPONENTE";
	private static final String MARCA2 = "TESTCOMPONENTEDOS";
	private static final String MODELO = "TESTCOMPONENTE";
	private static final String MODELO2 = "TESTCOMPONENTEDOS";

	private static final String TESTEO = "TESTCOMPONENTE";
	private static final String DNI = "14915998S";

	private static final TComponente componente = new TComponente(MARCA, 1, 20.50f, MODELO, 5);

	private static final TComponente componenteModificado = new TComponente(MARCA2, 1, 20.50f, MODELO2, 5);

	private static SAComponente saComponente;
	private static SAProveedor saProveedor;
	private static int idProveedor;

	@BeforeClass
	public static void initClass() {
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();

		do{
			idProveedor = saProveedor
					.alta(new TProveedor(DNI, "111111111", TESTEO));	
			
			if (idProveedor == -1)
				idProveedor = FactoriaIntegracion.obtenerInstancia()
				.crearProveedor().leerPorNIF(DNI).getId();
		}while(idProveedor == -4);

		componente.setIdProveedor(idProveedor);
		componenteModificado.setIdProveedor(idProveedor);
		int idComponente;
		do{
			idComponente = saComponente.alta(componente);
	
			if (idComponente == -1)
				idComponente = FactoriaIntegracion.obtenerInstancia()
						.crearComponente().leerPorMarcaModelo(MARCA, MODELO)
						.getId();
		}while(idComponente == -4);

		componente.setId(idComponente);
		int idComponente2;
		do{
			idComponente2 = saComponente.alta(componenteModificado);
	
			if (idComponente2 == -1)
				idComponente2 = FactoriaIntegracion.obtenerInstancia()
						.crearComponente().leerPorMarcaModelo(MARCA2, MODELO2)
						.getId();
		}while(idComponente == -4);
		componenteModificado.setId(idComponente2);
	}

	@AfterClass public static void destroyClass(){
		while(saComponente.baja(componente.getId()) == -4);
		while(saComponente.baja(componenteModificado.getId()) == -4);
		while(saProveedor.baja(idProveedor) == -4);
	}
	
	@Test
	public void testCorrecto() {
		componente.setPrecio(19);;
		int resultado = saComponente.modificar(componente);
		componente.setPrecio(20);
		
		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";
		
		while(saComponente.modificar(componente) == -4);
		assertTrue(message, resultado > 0);
	}

	@Test
	public void testIncorrecto() {

		componente.setId(-componente.getId());
		int resultado = saComponente.modificar(componente);
		componente.setId(-componente.getId());
		
		assertTrue(resultado == 0);
	}

	@Test
	public void testNoEncontrado() {
		while(saComponente.baja(componente.getId()) == -4);

		int resultado = saComponente.modificar(componente);


		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";

		while(saComponente.alta(componente) == -4);

		assertTrue(message, resultado == -1);
	}
	
	@Test
	public void testProveedorNoEncontrado() {
		while(saComponente.baja(componente.getId()) == -4);
		while(saComponente.baja(componenteModificado.getId()) == -4);
		while(saProveedor.baja(idProveedor) == -4);

		int resultado = saComponente.modificar(componente);


		String message = "";
		
		if (resultado == 0)
			message = "Datos incorrectos";
		else if (resultado == -1)
			message = "Componente con ID " + resultado + " no encontrado";
		else if (resultado == -2)
			message = "Componente repetido";
		else if (resultado == -4)
			message = "Error en la base de datos";

		while(saProveedor.alta(new TProveedor(DNI, "111111111", TESTEO)) == -4);
		while(saComponente.alta(componente) == -4);
		while(saComponente.alta(componenteModificado) == -4);

		
		assertTrue(message, resultado == -3);
	}

	@Test
	public void testRepetido() {

		componente.setMarca(componenteModificado.getMarca());
		componente.setModelo(componenteModificado.getModelo());
		int res = saComponente.modificar(componente);
		componente.setMarca(MARCA);
		componente.setModelo(MODELO);
		

		String message = "";
		if (res > 0)
			message = "Datos correctos";
		else if (res == 0)
			message = "Datos incorrectos";
		else if (res == -1)
			message = "Componente con ID " + res + " no encontrado";
		else if (res == -4)
			message = "Error en la base de datos";

		while(saComponente.modificar(componente) == -4);
		assertTrue(message, res == -2);
	}
}
