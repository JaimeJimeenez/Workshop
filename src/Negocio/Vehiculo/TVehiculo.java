package Negocio.Vehiculo;

public class TVehiculo {
	private int id;
	private String matricula;
	private String modelo;
	private boolean actividad;
	private int idCliente;
	
	public TVehiculo(int id, String matricula, String modelo, boolean actividad, int idCliente){
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.actividad = actividad;
		this.idCliente = idCliente;
	}
	
	public TVehiculo(int id, String matricula, String modelo, int idCliente){
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.idCliente = idCliente;
	}
	
	public TVehiculo(String matricula, String modelo, int idCliente){
		this.matricula = matricula;
		this.modelo = modelo;
		this.idCliente = idCliente;
	}
	
	public TVehiculo(int id){
		this.id = id;
	}


	public TVehiculo(int idCliente, String modelo, String matricula) {
		this.idCliente = idCliente;
		this.modelo = modelo;
		this.matricula = matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isActividad() {
		return actividad;
	}

	public void setActividad(boolean atividad) {
		this.actividad = atividad;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
