package Negocio.Mecanico;

public class TMecanico {

	private String DNI;
	private int id;
	private float sueldo;
	private boolean activo;
	private String nombre;
	private int idEspecialidad;
	private String telefono;
	private String cuenta;

	public TMecanico(String DNI, int id, float sueldo, boolean activo, String nombre, int idEspecialidad,
			String telefono, String cuenta) {

		this.DNI = DNI;
		this.id = id;
		this.sueldo = sueldo;
		this.activo = activo;
		this.nombre = nombre;
		this.idEspecialidad = idEspecialidad;
		this.telefono = telefono;
		this.cuenta = cuenta;

	}

	public TMecanico(int id, String DNI, float sueldo, String nombre, int idEspecialidad, String telefono,
			String cuenta) {

		this.DNI = DNI;
		this.id = id;
		this.sueldo = sueldo;
		this.nombre = nombre;
		this.idEspecialidad = idEspecialidad;
		this.telefono = telefono;
		this.cuenta = cuenta;

	}

	public TMecanico(String DNI, float sueldo, String nombre, int idEspecialidad, String telefono, String cuenta) {

		this.DNI = DNI;
		this.sueldo = sueldo;
		this.nombre = nombre;
		this.idEspecialidad = idEspecialidad;
		this.telefono = telefono;
		this.cuenta = cuenta;

	}

	public TMecanico(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
