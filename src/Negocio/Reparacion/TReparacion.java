package Negocio.Reparacion;

public class TReparacion {
	
	private int id;
	private int idVehiculo;
	private String fechaInicio;
	private String fechaSalida;
	private String averia;
	private float presupuesto;
	private boolean activo;
	
	public TReparacion(int id, int idVehiculo, String fechaInicio, String fechaSalida, String averia, float presupuesto, boolean activo) {
		this.id = id;
		this.idVehiculo = idVehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaSalida = fechaSalida;
		this.averia = averia;
		this.presupuesto = presupuesto;
		this.activo = activo;
	}
	
	public TReparacion(int id, int idVehiculo, String fechaInicio, String fechaSalida, String averia, float presupuesto) {
		this.id = id;
		this.idVehiculo = idVehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaSalida = fechaSalida;
		this.averia = averia;
		this.presupuesto = presupuesto;
	}
	
	public TReparacion(int idVehiculo, String fechaInicio, String fechaSalida, String averia, float presupuesto) {
		this.idVehiculo = idVehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaSalida = fechaSalida;
		this.averia = averia;
		this.presupuesto = presupuesto;
	}
	
	public TReparacion(int id) {
		this.id = id;
	}
	
	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public int getIdVehiculo() { return idVehiculo; }

	public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

	public String getFechaInicio() { return fechaInicio; }

	public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

	public String getFechaSalida() { return fechaSalida; }

	public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }

	public String getAveria() { return averia; }

	public void setAveria(String averia) { this.averia = averia; }

	public float getPresupuesto() { return presupuesto; }
	
	public void setPresupuesto(float presupuesto) { this.presupuesto = presupuesto; }
	
	public boolean isActivo() { return activo; }

	public void setActivo(boolean activo) { this.activo = activo; }
	
}
