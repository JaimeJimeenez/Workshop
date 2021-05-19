package Integracion.Mecanico;

import java.util.Collection;

import Negocio.Mecanico.TMecanico;


public interface DAOMecanico {
	
	public int alta(TMecanico tMecanico);

	public int baja(int id);

	public int modificar(TMecanico e);

	public TMecanico mostrar(int id);

	public Collection<TMecanico> listar();

	public TMecanico leerPorNif(String DNI);
	
	public int reactivar(int id);
	
	public Collection<TMecanico> mostrarMecanicosEspecialidad(int idEspecialidad);
}
