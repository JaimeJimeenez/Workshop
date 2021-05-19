package Negocio.Mecanico;

import java.util.Collection;

public interface SAMecanico {
	
	public int alta(TMecanico e);

	public int baja(int id);

	public int modificar(TMecanico e);

	public TMecanico mostrar(int id);

	public Collection<TMecanico> listar();
	
	public Collection<TMecanico> mostrarMecanicosEspecialidad(int idEspecialidad);
	
}
