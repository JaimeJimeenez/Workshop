package Presentacion.Controlador;

public abstract class Controlador {
	private static Controlador instancia;
	public abstract void accion(int e, Object d);

	public static Controlador obtenerInstancia() {
		if(instancia ==null)
		{
			instancia = new ControladorImp();
		}
		
		return instancia;
	}
}