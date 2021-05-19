package Integracion.Reparacion;

import java.util.Collection;

import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;

public interface DAOReparacion {
	public int alta(TReparacion tReparacion);

	public int reactivar(TReparacion tReparacion);

	public int baja(int idReparacion);

	public TEmplea altaComponente(TEmplea tEmplea);

	public TTrabaja altaMecanico(TTrabaja tTrabaja);

	public TEmplea bajaComponente(TEmplea tEmplea);

	public TTrabaja bajaMecanico(TTrabaja tTrabaja);

	public int modificar(TReparacion tReparacion);

	public TEmplea modificarComponente(TEmplea tEmplea);

	public TTrabaja modificarMecanico(TTrabaja tTrabaja);

	public Collection<Object> mostrar(int idReparacion);

	public Collection<TReparacion> listar();

	public Collection<TReparacion> mostrarReparacionesVehiculo(int idVehiculo);

	public TReparacion leerPorDatos(TReparacion tReparacion);
}