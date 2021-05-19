package Negocio.Cliente;

public class TParticular extends TCliente {

	private String direccion;
	
	public TParticular(String nombre, int id, String telefono, String dni, String direccion, boolean activo) {
		super(nombre, id, telefono, activo, dni);
		this.direccion = direccion;
	}
	
	public TParticular(String nombre, String telefono, String dni, String direccion) {
		super(nombre, telefono, dni);
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
