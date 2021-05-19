package Presentacion.Controlador;

import java.util.Collection;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.Componente.SAComponente;
import Negocio.Componente.TComponente;
import Negocio.Especialidad.SAEspecialidad;
import Negocio.Especialidad.TEspecialidad;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.Mecanico.SAMecanico;
import Negocio.Mecanico.TMecanico;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.TProveedor;
import Negocio.Reparacion.SAReparacion;
import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.SAVehiculo;
import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Cliente.VistaListarCliente;
import Presentacion.Componente.VistaListarComponente;
import Presentacion.Taller.VistaTaller;
import Presentacion.Vehiculo.VistaListarVehiculo;

public class ControladorImp extends Controlador {
	private SAEspecialidad saEspecialidad;
	private SAProveedor saProveedor;
	private SACliente saCliente;
	private SAMecanico saMecanico;
	private SAComponente saComponente;
	private SAVehiculo saVehiculo;
	private SAReparacion saReparacion;
	private Vista vista;

	private VistaListarCliente vistaCliente;
	private VistaListarComponente vistaComponente;
	private VistaListarVehiculo vistaVehiculo;

	public ControladorImp() {
		saEspecialidad = FactoriaSA.obtenerInstancia().crearSAEspecialidad();
		saProveedor = FactoriaSA.obtenerInstancia().crearSAProveedor();
		saCliente = FactoriaSA.obtenerInstancia().crearSACliente();
		saMecanico = FactoriaSA.obtenerInstancia().crearSAMecanico();
		saComponente = FactoriaSA.obtenerInstancia().crearSAComponente();
		saVehiculo = FactoriaSA.obtenerInstancia().crearSAVehiculo();
		saReparacion = FactoriaSA.obtenerInstancia().crearSAReparacion();
		this.vista = new VistaTaller();
	}

	public void accion(int evento, Object datos) {
		int res;
		Collection<Object> reparacion = null;
		TEspecialidad tResultado = null;
		TProveedor tProveedor = null;
		TMecanico tMecanico = null;
		TCliente tCliente = null;
		TComponente tComponente = null;
		TVehiculo tVehiculo = null;
		TReparacion tReparacion = null;
		TEmplea tEmplea = null;
		TTrabaja tTrabaja = null;
		Collection<TEspecialidad> listaEspecialidad = null;
		Collection<TProveedor> listaProveedor = null;
		Collection<TMecanico> listaMecanico = null;
		Collection<TCliente> listaCliente = null;
		Collection<TComponente> listaComponente = null;
		Collection<TVehiculo> listaVehiculo = null;
		Collection<TReparacion> listaReparacion = null;
		Collection<TEmplea> listaEmplea = null;
		Collection<TTrabaja> listaTrabaja = null;
		Collection<TReparacion> tReparaciones = null;

		switch (evento) {
		case Eventos.ALTA_ESPECIALIDAD:
			res = saEspecialidad.alta((TEspecialidad) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_ESPECIALIDAD_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_ESPECIALIDAD_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_ESPECIALIDAD_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_ESPECIALIDAD:

			try {
				int id = Integer.parseInt((String) datos);
				res = saEspecialidad.baja(id);
			} catch (NumberFormatException exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_ESPECIALIDAD_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_ESPECIALIDAD_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_ESPECIALIDAD_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_ESPECIALIDAD_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_ESPECIALIDAD:
			try {
				int id = Integer.parseInt((String) datos);
				tResultado = saEspecialidad.mostrar(id);
				res = tResultado.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_ESPECIALIDAD_OK, tResultado);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_ESPECIALIDAD_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_ESPECIALIDAD_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.LISTAR_ESPECIALIDAD:
			listaEspecialidad = saEspecialidad.listar();
			if (listaEspecialidad != null && !listaEspecialidad.isEmpty()
					&& listaEspecialidad.iterator().next().getId() == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			else if (listaEspecialidad != null)
				vista.actualizar(Eventos.RES_LISTAR_ESPECIALIDAD_OK, listaEspecialidad);
			else
				vista.actualizar(Eventos.RES_LISTAR_ESPECIALIDAD_NE, null);
			break;

		case Eventos.MODIFICAR_ESPECIALIDAD:
			res = saEspecialidad.modificar((TEspecialidad) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_ESPECIALIDAD_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_ESPECIALIDAD_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_ESPECIALIDAD_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_ESPECIALIDAD_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		// ----------------------------------

		case Eventos.ALTA_PROVEEDOR:
			res = saProveedor.alta((TProveedor) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_PROVEEDOR_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_PROVEEDOR_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_PROVEEDOR_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_PROVEEDOR:

			try {
				int id = Integer.parseInt((String) datos);
				res = saProveedor.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_PROVEEDOR_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_PROVEEDOR_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_PROVEEDOR_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_PROVEEDOR_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_PROVEEDOR:
			try {
				int id = Integer.parseInt((String) datos);
				tProveedor = saProveedor.mostrar(id);
				res = tProveedor.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_PROVEEDOR_OK, tProveedor);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_PROVEEDOR_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_PROVEEDOR_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.LISTAR_PROVEEDOR:
			listaProveedor = saProveedor.listar();

			if (listaProveedor != null && !listaProveedor.isEmpty() && listaProveedor.iterator().next().getId() == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			else if (listaProveedor != null)
				vista.actualizar(Eventos.RES_LISTAR_PROVEEDOR_OK, listaProveedor);
			else
				vista.actualizar(Eventos.RES_LISTAR_PROVEEDOR_NE, null);
			break;

		case Eventos.MODIFICAR_PROVEEDOR:
			res = saProveedor.modificar((TProveedor) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_PROVEEDOR_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		// -----------------------------------

		case Eventos.ALTA_CLIENTE:
			res = saCliente.alta((TCliente) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_CLIENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_CLIENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_CLIENTE_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_CLIENTE:

			try {
				int id = Integer.parseInt((String) datos);
				res = saCliente.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_CLIENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_CLIENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_CLIENTE_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_CLIENTE_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.LISTAR_CLIENTE:
			listaCliente = saCliente.listar();
			vistaCliente = new VistaListarCliente();

			if (listaCliente != null)
				vistaCliente.actualizar(Eventos.RES_LISTAR_CLIENTE_OK, listaCliente);
			else
				vista.actualizar(Eventos.RES_LISTAR_CLIENTE_NE, null);
			break;

		case Eventos.MOSTRAR_CLIENTE:
			try {
				int id = Integer.parseInt((String) datos);
				tCliente = saCliente.mostrar(id);
				res = tCliente.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_CLIENTE_OK, tCliente);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_CLIENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_CLIENTE_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MODIFICAR_CLIENTE:
			res = saCliente.modificar((TCliente) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_CLIENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_CLIENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_CLIENTE_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_CLIENTE_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		// -----------------------------------

		case Eventos.ALTA_MECANICO:

			if (((TMecanico) datos).getId() == -1)
				res = 0;
			else
				res = saMecanico.alta((TMecanico) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_MECANICO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_MECANICO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_MECANICO_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ALTA_MECANICO_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_MECANICO:

			try {
				int id = Integer.parseInt((String) datos);
				res = saMecanico.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_MECANICO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_MECANICO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_MECANICO_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_MECANICO_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_MECANICO:
			try {
				int id = Integer.parseInt((String) datos);
				tMecanico = saMecanico.mostrar(id);
				res = tMecanico.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_OK, tMecanico);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.LISTAR_MECANICO:
			listaMecanico = saMecanico.listar();
			if (listaMecanico != null) {
				res = ((TMecanico) listaMecanico.toArray()[0]).getId();
				if (res > 0)
					vista.actualizar(Eventos.RES_LISTAR_MECANICO_OK, listaMecanico);
				else
					vista.actualizar(Eventos.EXCEPCION_SQL, null);
			} else
				vista.actualizar(Eventos.RES_LISTAR_MECANICO_NE, null);
			break;

		case Eventos.MODIFICAR_MECANICO:
			res = saMecanico.modificar((TMecanico) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_MECANICO_ESPECIALIDAD:
			listaMecanico = saMecanico.mostrarMecanicosEspecialidad((int) datos);

			if (listaMecanico != null) {

				res = ((TMecanico) listaMecanico.toArray()[0]).getId();

				if (res > 0)
					vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_OK, listaMecanico);
				else if (res == 0)
					vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_DI, null);
				else if (res == -3)
					vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_IE, null);
				else if (res == -4)
					vista.actualizar(Eventos.EXCEPCION_SQL, null);

			} else
				vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_NE, null);

			break;

		// -------------------------------------------------------------------------------

		case Eventos.ALTA_COMPONENTE:
			res = saComponente.alta((TComponente) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_COMPONENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_COMPONENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_COMPONENTE_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ALTA_COMPONENTE_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_COMPONENTE:
			try {
				int id = Integer.parseInt((String) datos);
				res = saComponente.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_COMPONENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_COMPONENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_COMPONENTE_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_MECANICO_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_COMPONENTE:
			try {
				int id = Integer.parseInt((String) datos);
				tComponente = saComponente.mostrar(id);
				res = tComponente.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_COMPONENTE_OK, tComponente);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_COMPONENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_MECANICO_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_COMPONENTE_PROVEEDOR:
			int idProveedor = Integer.parseInt((String) datos);
			listaComponente = saComponente.mostrarComponentesProveedor(idProveedor);

			if (listaComponente != null)
				vista.actualizar(Eventos.RES_MOSTRAR_COMPONENTE_PROVEEDOR_OK, listaComponente);
			else
				vista.actualizar(Eventos.RES_MOSTRAR_COMPONENTE_PROVEEDOR_NE, null);
			break;

		case Eventos.LISTAR_COMPONENTE:
			listaComponente = saComponente.listar();
			vistaComponente = new VistaListarComponente();

			if (listaComponente != null)
				vistaComponente.actualizar(Eventos.RES_LISTAR_COMPONENTE_OK, listaComponente);
			else
				vista.actualizar(Eventos.RES_LISTAR_COMPONENTE_NE, null);
			break;

		case Eventos.MODIFICAR_COMPONENTE:
			res = saComponente.modificar((TComponente) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		// ---------------------------------------------------

		case Eventos.ALTA_VEHICULO:
			res = saVehiculo.alta((TVehiculo) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_VEHICULO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_VEHICULO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_VEHICULO_RE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_ALTA_VEHICULO_CNE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ALTA_VEHICULO_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BAJA_VEHICULO:
			try {
				int id = Integer.parseInt((String) datos);
				res = saVehiculo.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_VEHICULO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_VEHICULO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_VEHICULO_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_BAJA_VEHICULO_OA, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_VEHICULO:
			try {
				int id = Integer.parseInt((String) datos);
				tVehiculo = saVehiculo.mostrar(id);
				res = tVehiculo.getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_VEHICULO_OK, tVehiculo);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_VEHICULO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_VEHICULO_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_VEHICULO_CLIENTE:
			int idCliente = Integer.parseInt((String) datos);
			listaVehiculo = saVehiculo.mostrarVehiculoCliente(idCliente);

			if (listaVehiculo != null)
				vista.actualizar(Eventos.RES_MOSTRAR_VEHICULO_CLIENTE_OK, listaVehiculo);
			else
				vista.actualizar(Eventos.RES_MOSTRAR_VEHICULO_CLIENTE_NE, null);
			break;

		case Eventos.LISTAR_VEHICULO:
			listaVehiculo = saVehiculo.listar();
			vistaVehiculo = new VistaListarVehiculo();

			if (listaVehiculo != null)
				vistaVehiculo.actualizar(Eventos.RES_LISTAR_VEHICULO_OK, listaVehiculo);
			else
				vista.actualizar(Eventos.RES_LISTAR_VEHICULO_NE, null);
			break;

		case Eventos.MODIFICAR_VEHICULO:
			res = saVehiculo.modificar((TVehiculo) datos);

			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_VEHICULO_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_VEHICULO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_VEHICULO_NE, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_VEHICULO_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_MODIFICAR_VEHICULO_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		// ---------------------------------------------------------------------------------------
		// REPARACION

		case Eventos.ALTA_REPARACION:
			res = saReparacion.alta((TReparacion) datos, listaEmplea, listaTrabaja);
			if (res > 0)
				vista.actualizar(Eventos.RES_ALTA_REPARACION_OK, tEmplea);
			else if (res == 0)
				vista.actualizar(Eventos.RES_ALTA_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ALTA_REPARACION_RE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ALTA_REPARACION_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;
		case Eventos.MOSTRAR_REPARACION:
			try {
				int id = Integer.parseInt((String) datos);
				reparacion = saReparacion.mostrar(id);
				res = ((TReparacion) (reparacion.toArray()[0])).getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_OK, reparacion);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MOSTRAR_REPARACION_VEHICULO:
			try {
				int idVehiculo = Integer.parseInt((String) datos);
				tReparaciones = saReparacion.mostrarReparacionVehiculo(idVehiculo);
				res = ((TReparacion) (tReparaciones.toArray()[0])).getId();
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_VEHICULO_OK, tReparaciones);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_VEHICULO_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MOSTRAR_REPARACION_VEHICULO_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MODIFICAR_REPARACION:
			res = saReparacion.modificar((TReparacion) datos);
			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_REPARACION_OK, tEmplea);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_REPARACION_NE, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_MODIFICAR_REPARACION_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;
		case Eventos.BAJA_REPARACION:
			try {
				int id = Integer.parseInt((String) datos);
				res = saReparacion.baja(id);
			} catch (Exception exception) {
				res = 0;
			}

			if (res > 0)
				vista.actualizar(Eventos.RES_BAJA_REPARACION_OK, new Integer(res));
			else if (res == 0)
				vista.actualizar(Eventos.RES_BAJA_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_BAJA_REPARACION_NE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BORRAR_MECANICO_REPARACION:
			try {
				tTrabaja = saReparacion.borrarMecanicoReparacion((TTrabaja) datos);
			} catch (Exception exception) {
				tTrabaja = new TTrabaja(0);
			}
			if (tTrabaja.getIdReparacion() > 0)
				vista.actualizar(Eventos.RES_BORRAR_MECANICO_REPARACION_OK, tEmplea);
			else if (tEmplea.getIdReparacion() == 0)
				vista.actualizar(Eventos.RES_BORRAR_MECANICO_REPARACION_DI, null);
			else if (tEmplea.getIdReparacion() == -1)
				vista.actualizar(Eventos.RES_BORRAR_MECANICO_REPARACION_NM, null);
			else if (tEmplea.getIdReparacion() == -2)
				vista.actualizar(Eventos.RES_BORRAR_MECANICO_REPARACION_NR, null);
			else if (tEmplea.getIdReparacion() == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.BORRAR_COMPONENTE_REPARACION:
			try {
				tEmplea = saReparacion.borrarComponenteReparacion((TEmplea) datos);
			} catch (Exception exception) {
				tEmplea = new TEmplea(0);
			}
			if (tEmplea.getIdReparacion() > 0)
				vista.actualizar(Eventos.RES_BORRAR_COMPONENTE_REPARACION_OK, tEmplea);
			else if (tEmplea.getIdReparacion() == 0)
				vista.actualizar(Eventos.RES_BORRAR_COMPONENTE_REPARACION_DI, null);
			else if (tEmplea.getIdReparacion() == -1)
				vista.actualizar(Eventos.RES_BORRAR_COMPONENTE_REPARACION_NC, null);
			else if (tEmplea.getIdReparacion() == -2)
				vista.actualizar(Eventos.RES_BORRAR_COMPONENTE_REPARACION_NR, null);
			else if (tEmplea.getIdReparacion() == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.ANYADIR_COMPONENTE_REPARACION:
			tEmplea = saReparacion.anyadirComponente((TEmplea) datos);
			res = tEmplea.getIdReparacion();
			if (res > 0)
				vista.actualizar(Eventos.RES_ANYADIR_COMPONENTE_REPARACION_OK, tEmplea);
			else if (res == 0)
				vista.actualizar(Eventos.RES_ANYADIR_COMPONENTE_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ANYADIR_COMPONENTE_REPARACION_NC, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_ANYADIR_COMPONENTE_REPARACION_NR, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ANYADIR_COMPONENTE_REPARACION_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.ANYADIR_MECANICO_REPARACION:
			tTrabaja = saReparacion.anyadirMecanicoReparacion((TTrabaja) datos);
			res = tTrabaja.getIdReparacion();
			if (res > 0)
				vista.actualizar(Eventos.RES_ANYADIR_MECANICO_REPARACION_OK, tTrabaja);
			else if (res == 0)
				vista.actualizar(Eventos.RES_ANYADIR_MECANICO_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_ANYADIR_MECANICO_REPARACION_NM, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_ANYADIR_MECANICO_REPARACION_NR, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_ANYADIR_MECANICO_REPARACION_RE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MODIFICAR_MECANICO_REPARACION:
			tTrabaja = saReparacion.modificarMecanicoReparacion((TTrabaja) datos);
			res = tTrabaja.getIdReparacion();
			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_REPARACION_OK, tTrabaja);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_REPARACION_NM, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_REPARACION_NR, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.MODIFICAR_COMPONENTE_REPARACION:
			tEmplea = saReparacion.modificarComponenteReparacion((TEmplea) datos);
			res = tEmplea.getIdReparacion();
			if (res > 0)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_OK, tEmplea);
			else if (res == 0)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_DI, null);
			else if (res == -1)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_NC, null);
			else if (res == -2)
				vista.actualizar(Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_NR, null);
			else if (res == -3)
				vista.actualizar(Eventos.RES_MODIFICAR_MECANICO_IE, null);
			else if (res == -4)
				vista.actualizar(Eventos.EXCEPCION_SQL, null);
			break;

		case Eventos.LISTAR_REPARACION:
			listaReparacion = saReparacion.listar();

			if (listaReparacion != null)
				vista.actualizar(Eventos.RES_LISTAR_REPARACION_OK, listaReparacion);
			else
				vista.actualizar(Eventos.RES_LISTAR_REPARACION_NE, null);
			break;
		}

	}
}