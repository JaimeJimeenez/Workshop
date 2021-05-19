package Negocio.Reparacion;

public class TEmplea {
	
	private int idReparacion;
	private int idComponente;
	private float precio;
	private int cantidad;
	
	public TEmplea(int idReparacion, int idComponente, float precio, int cantidad) {
		this.idReparacion = idReparacion;
		this.idComponente = idComponente;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public TEmplea(int idReparacion, int idComponente) {
		this.idReparacion = idReparacion;
		this.idComponente = idComponente;
	}
	
	public TEmplea(int idReparacion) {
		this.idReparacion = idReparacion;
	}
	
	public int getIdReparacion() { return idReparacion; }
	
	public void setIdReparacion(int idReparacion) { this.idReparacion = idReparacion; }
	
	public int getIdComponente() { return idComponente; }
	
	public void setIdComponente(int idComponente) { this.idComponente = idComponente; }
	
	public float getPrecio() { return precio; }

	public void setPrecio(float precio) { this.precio = precio; }

	public int getCantidad() { return cantidad; }

	public void setCantidad(int cantidad) { this.cantidad = cantidad; }

}
