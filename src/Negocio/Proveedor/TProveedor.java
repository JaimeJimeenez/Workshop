package Negocio.Proveedor;

public class TProveedor {
	private String NIF;
	private int id;
	private boolean activo;
	private String telefono;
	private String direccion;

	public TProveedor(String NIF, int id, String telefono, String direccion, boolean activo) {
		this.NIF = NIF;
		this.activo = activo;
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public TProveedor(String NIF, int id, String telefono, String direccion) {
		this.NIF = NIF;
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public TProveedor(String NIF, String telefono, String direccion) {
		this.NIF = NIF;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public TProveedor(int id, String telefono, String direccion) {
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	public TProveedor(int id) {
		this.id = id;
	}
	
	public TProveedor(String NIF) {
		this.NIF = NIF;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
