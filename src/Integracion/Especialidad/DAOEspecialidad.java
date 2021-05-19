package Integracion.Especialidad;

import Negocio.Especialidad.TEspecialidad;
import java.util.Collection;

public interface DAOEspecialidad {

	public int alta(TEspecialidad tEspecialidad);

	public int baja(int id);

	public int modificar(TEspecialidad tEspecialidad);

	public TEspecialidad mostrar(int id);

	public Collection<TEspecialidad> listar();

	public TEspecialidad leerPorTipo(String tipo);

	public int reactivar(int id);

}