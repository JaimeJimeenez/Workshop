package Negocio.Reparacion;

import java.util.Collection;

public interface SAReparacion {
	
	public int alta(TReparacion tReparacion, Collection<TEmplea> tEmplea, Collection<TTrabaja> tTrabaja);
	
	public int baja(int id);
	
	public Collection<Object> mostrar(int id);
	
	public int modificar(TReparacion tReparacion);
	
	public Collection<TReparacion> listar();
	
	public TEmplea anyadirComponente(TEmplea tEmplea);
	
	public TEmplea borrarComponenteReparacion(TEmplea tEmplea);
	
	public TTrabaja anyadirMecanicoReparacion(TTrabaja tTrabaja);
	
	public TTrabaja borrarMecanicoReparacion(TTrabaja tTrabaja);
	
	public TEmplea modificarComponenteReparacion(TEmplea tEmplea);
	
	public TTrabaja modificarMecanicoReparacion(TTrabaja tTrabaja);
	
	public Collection<TReparacion> mostrarReparacionVehiculo(int idVehiculo);
	
}