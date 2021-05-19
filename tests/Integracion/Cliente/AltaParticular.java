package Integracion.Cliente;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TParticular;

@RunWith(value = Parameterized.class)
public class AltaParticular {
	@Parameters
	public static Iterable<TParticular[]> getData( {
		return Arrays.asList(new TParticular[][]) {
			{ 
				//TODO
			}
		});
	}
	
	private TParticular tParticularCorrecto;
	private TParticular tParticularIncorrecto;
	private DAOCliente daoParticular;
	
	public AltaParticular(TParticular tParticularCorrecto, TParticular tParticularIncorrecto) {
		this.tParticularCorrecto = tParticularCorrecto;
		this.tParticularIncorrecto = tParticularIncorrecto;
	}
	
	@Before
	public void init() { daoParticular = FactoriaIntegracion.obtenerInstancia().crearCliente(); }
	
	@Test
	public void testCorrecto() {
		int resultado = daoParticular.alta(tParticularCorrecto);
		assertTrue(resultado > 0);
	}
	
	@Test
	public void testIncorrecto() {
		int resultado = daoParticular.alta(tParticularIncorrecto);
		assertTrue(resultado == 0);
	}
}
