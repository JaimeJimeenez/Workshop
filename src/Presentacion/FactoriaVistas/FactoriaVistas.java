/**
 * 
 */
package Presentacion.FactoriaVistas;

import Presentacion.Vista;

public abstract class FactoriaVistas {

	private static FactoriaVistas instancia;

	public abstract Vista crearVista(int tipo);
	
	public static FactoriaVistas obtenerInstancia() {
		if(instancia == null)
		{
			instancia = new FactoriaVistasImp();
		}
		return instancia;
	}
}