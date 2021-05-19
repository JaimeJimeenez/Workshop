package Negocio.Reparacion;

public class TTrabaja {

	private int idReparacion;
	private int idMecanico;
	private int horas;
	
	public TTrabaja(int idReparacion, int idMecanico, int horas) {
		this.idReparacion = idReparacion;
		this.idMecanico = idMecanico;
		this.horas = horas;
	}
	
	public TTrabaja(int idReparacion, int idMecanico) {
		this.idReparacion = idReparacion;
		this.idMecanico = idMecanico;
	}
	
	public TTrabaja(int idReparacion) {
		this.idReparacion = idReparacion;
	}
	
	public int getIdReparacion() { return idReparacion; }

	public void setIdReparacion(int idReparacion) { this.idReparacion = idReparacion; }

	public int getIdMecanico() { return idMecanico; }

	public void setIdMecanico(int idMecanico) { this.idMecanico = idMecanico; }

	public int getHora() { return horas; }

	public void setHora(int hora) { this.horas = hora; }
	
}
