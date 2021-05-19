package Negocio.Cliente;

public class TCliente {
	private String nombre;
	private int id;
	private String telefono;
	private String nif;
	private boolean activo;
	
	public TCliente(String nombre, int id, String telefono, boolean activo, String nif) {
		this.nombre = nombre;
		this.id = id;
		this.telefono = telefono;
		this.activo = activo;
		this.nif = nif;
	}
	
	public TCliente(String nombre, String telefono, String nif) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.nif = nif;
	}
	
	public TCliente(String nombre, int id, String telefono) {
		this.nombre = nombre;
		this.id = id;
		this.telefono = telefono;
	}
	
	public TCliente(int id) {
		this.id = id;
	}
	
	public TCliente(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getNif()
	{
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
}
