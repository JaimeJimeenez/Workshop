package Negocio.Reparacion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Negocio.FactoriaSA.FactoriaSA;

@RunWith(value = Parameterized.class)
public class Mostrar {
	
	private int idCorrecto;
	private int idIncorrecto;
	private int idNoEncontrado;
	private SAReparacion saReparacion;
	
	@Parameters
	public static Iterable<Integer[]> getData() {
		return Arrays.asList(new Integer[][]{{5, 0 , 50}});
	}
	public Mostrar(int idCorrecto, int idIncorrecto, int idNoEncontrado) {
		this.idCorrecto = idCorrecto;
		this.idIncorrecto = idIncorrecto;
		this.idNoEncontrado = idNoEncontrado; 
	}
	
	@Before
	public void init() {
		saReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion();	
	}
	
	@Test
	public void correcto() {
		Collection<Object> resultado = saReparacion.mostrar(idCorrecto);
		TReparacion reparacion = (TReparacion) resultado.toArray()[0];
		assertTrue(reparacion.getId() > 0);
	}
	
	@Test
	public void noEncontrado() {
		Collection<Object> resultado = saReparacion.mostrar(idNoEncontrado);
		TReparacion reparacion = (TReparacion) resultado.toArray()[0];
		assertTrue(reparacion.getId() == -1);
	}
	
	@Test
	public void incorrecto() {
		Collection<Object> resultado = saReparacion.mostrar(idIncorrecto);
		TReparacion reparacion = (TReparacion) resultado.toArray()[0];
		assertTrue(reparacion.getId() == 0);
	}

}