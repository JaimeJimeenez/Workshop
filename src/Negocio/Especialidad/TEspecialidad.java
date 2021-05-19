/**
 * 
 */
package Negocio.Especialidad;


public class TEspecialidad {
	private String tipo;
	private int id;
	private boolean activo;
	
	public TEspecialidad(String tipo, int id, boolean activo) {
		this.tipo = tipo;
		this.activo = activo;
		this.id = id;
	}
	
	public TEspecialidad(String tipo, int id) {
		this.tipo = tipo;
		this.id = id;
	}
	
	public TEspecialidad(int id) {
		this.id = id;
	}
	
	public TEspecialidad(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}