package Presentacion.Reparacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaReparacion extends JFrame implements Vista {
	
	Vista vistaAlta;
	Vista vistaBaja;
	Vista vistaMostrar;
	Vista vistaModificar;
	Vista vistaListar;
	Vista vistaAnyadirComponente;
	Vista vistaAnyadirMecanico;
	Vista vistaBorrarComponente;
	Vista vistaBorrarMecanico;
	Vista vistaModificarComponente;
	Vista vistaModificarMecanico;
	Vista vistaMostrarReparacionVehiculo;
	public VistaReparacion() {
		super("Menú Reparacion");
		JPanel panel = new JPanel();
		JButton alta = new JButton("Alta");
		JButton baja = new JButton("Baja");
		JButton modificar = new JButton("Modificar");
		JButton mostrar = new JButton("Mostrar");
		JButton listar = new JButton("Listar");
		JButton anyadirComponente = new JButton("Añadir Componente");
		JButton anyadirMecanico = new JButton("Añadir Mecanico");
		JButton borrarComponente = new JButton("Borrar Componente");
		JButton borrarMecanico = new JButton("Borrar Mecanico");
		JButton modificarComponente = new JButton("Modificar Componente");
		JButton modificarMecanico = new JButton("Modificar Mecanico");
		JButton mostrarReparacionVehiculo = new JButton("Mostrar Reparacion Vehiculo");
		JButton cancelar = new JButton("Cancelar");
		
		cancelar.setBackground(Color.RED);
		panel.add(alta);
		panel.add(baja);
		panel.add(modificar);
		panel.add(mostrar);
		panel.add(listar);
		panel.add(anyadirComponente);
		panel.add(anyadirMecanico);
		panel.add(borrarComponente);
		panel.add(borrarMecanico);
		panel.add(modificarComponente);
		panel.add(modificarMecanico);
		panel.add(mostrarReparacionVehiculo);
		panel.add(cancelar);
		this.add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaAlta = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ALTA_REPARACION);
			}
		});

		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaBaja = FactoriaVistas.obtenerInstancia().crearVista(Eventos.BAJA_REPARACION);
			}
		});
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaModificar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MODIFICAR_REPARACION);
			}
		});
		
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_REPARACION);
			}
		});
		
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaListar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.LISTAR_REPARACION);
			}
		});
		anyadirComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaAnyadirComponente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ANYADIR_COMPONENTE_REPARACION);
			}
		});
		anyadirMecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaAnyadirMecanico = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ANYADIR_MECANICO_REPARACION);
			}
		});
		borrarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaBorrarComponente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.BORRAR_COMPONENTE_REPARACION);
			}
		});
		borrarMecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaBorrarMecanico = FactoriaVistas.obtenerInstancia().crearVista(Eventos.BORRAR_MECANICO_REPARACION);
			}
		});
		modificarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaModificarComponente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MODIFICAR_COMPONENTE_REPARACION);
			}
		});
		modificarMecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaModificarMecanico = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MODIFICAR_MECANICO_REPARACION);
			}
		});
		mostrarReparacionVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrarReparacionVehiculo = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_REPARACION_VEHICULO);
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.TALLER);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (vistaAlta != null)
		{
			vistaAlta.actualizar(evento, datos);	
			vistaAlta = null;
		}
		else if (vistaBaja != null)
		{
			vistaBaja.actualizar(evento, datos);
			vistaBaja = null;
		}
		else if (vistaMostrar != null)
		{
			vistaMostrar.actualizar(evento, datos);	
			vistaMostrar = null;
		}
		else if (vistaModificar != null)
		{
			vistaModificar.actualizar(evento, datos);	
			vistaModificar = null;
		}
		else if (vistaAnyadirComponente != null)
		{
			vistaAnyadirComponente.actualizar(evento, datos);
			vistaAnyadirComponente = null;
		}
		else if (vistaAnyadirMecanico != null)
		{
			vistaAnyadirMecanico.actualizar(evento, datos);		
			vistaAnyadirMecanico = null;
		}
		else if (vistaBorrarComponente != null)
		{
			vistaBorrarComponente.actualizar(evento, datos);	
			vistaBorrarComponente = null;
		}
		
		else if (vistaBorrarMecanico != null)
		{
			vistaBorrarMecanico.actualizar(evento, datos);
			vistaBorrarMecanico = null;
		}
		else if (vistaModificarComponente != null)
		{
			vistaModificarComponente.actualizar(evento, datos);
			vistaModificarComponente = null;
		}
		else if (vistaModificarMecanico != null)
		{
			vistaModificarMecanico.actualizar(evento, datos);
			vistaModificarMecanico = null;
		}
		else if (vistaMostrarReparacionVehiculo != null)
		{
			vistaMostrarReparacionVehiculo.actualizar(evento, datos);
			vistaMostrarReparacionVehiculo = null;
		}
		else if (vistaListar != null)
		{
			vistaListar.actualizar(evento, datos);
			vistaListar = null;	
		}
	}

}
