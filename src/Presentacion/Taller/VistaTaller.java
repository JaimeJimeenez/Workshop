package Presentacion.Taller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Vista;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaTaller extends JFrame implements Vista {
	private Vista vistaEspecialidad;
	private Vista vistaProveedor;
	private Vista vistaCliente;
	private Vista vistaMecanico;
	private Vista vistaComponente;
	private Vista vistaVehiculo;
	private Vista vistaReparacion;

	public VistaTaller() {
		super("Taller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JFrame aux = new JFrame();
		JButton especialidad = new JButton("Especialidad");
		JButton mecanico = new JButton("Mecánico");
		JButton proveedor = new JButton("Proveedor");
		JButton vehiculo = new JButton("Vehiculo");
		JButton compenente = new JButton("Componente");
		JButton reparacion = new JButton("Reparación");
		JButton cliente = new JButton("Cliente");
		panel.add(especialidad);
		panel.add(mecanico);
		panel.add(proveedor);
		panel.add(vehiculo);
		panel.add(compenente);
		panel.add(reparacion);
		panel.add(cliente);

		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		especialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaEspecialidad = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ESPECIALIDAD);
			}
		});

		proveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaProveedor = FactoriaVistas.obtenerInstancia().crearVista(Eventos.PROVEEDOR);
			}
		});

		vehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaVehiculo = FactoriaVistas.obtenerInstancia().crearVista(Eventos.VEHICULO);
			}
		});

		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaCliente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.CLIENTE);
			}
		});

		mecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMecanico = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MECANICO);
			}
		});

		compenente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaComponente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.COMPONENTE);
			}
		});
		reparacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaReparacion = FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (vistaEspecialidad != null) {
			vistaEspecialidad.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaProveedor != null) {
			vistaProveedor.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaCliente != null) {
			vistaCliente.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaMecanico != null) {
			vistaMecanico.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaComponente != null) {
			vistaComponente.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaVehiculo != null) {
			vistaVehiculo.actualizar(evento, datos);
			setVisible(true);
		}
		if (vistaReparacion != null) {
			vistaReparacion.actualizar(evento, datos);
			setVisible(true);
		}
	}
}