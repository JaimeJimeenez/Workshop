package Presentacion.Mecanico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMecanico extends JFrame implements Vista{
	
	Vista vistaAlta;
	Vista vistaBaja;
	Vista vistaMostrar;
	Vista vistaModificar;
	Vista vistaListar;
	Vista vistaMostrarMecanicosEspecialidad;
	
	public VistaMecanico() {
		super("Menú Mecánico");
		JPanel panel = new JPanel();
		JButton alta = new JButton("Alta");
		JButton baja = new JButton("Baja");
		JButton modificar = new JButton("Modificar");
		JButton mostrar = new JButton("Mostrar");
		JButton listar = new JButton("Listar");
		JButton mostrarMecanicosEspecialidad = new JButton("Mostrar Mecanicos Especialidad");
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBackground(Color.RED);
		panel.add(alta);
		panel.add(baja);
		panel.add(modificar);
		panel.add(mostrar);
		panel.add(listar);
		panel.add(mostrarMecanicosEspecialidad);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaAlta = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ALTA_MECANICO);
			}
		});

		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaBaja = FactoriaVistas.obtenerInstancia().crearVista(Eventos.BAJA_MECANICO);
			}
		});
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaModificar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MODIFICAR_MECANICO);
			}
		});
		
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_MECANICO);
			}
		});
		
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaListar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.LISTAR_MECANICO);
			}
		});
		
		mostrarMecanicosEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrarMecanicosEspecialidad = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_MECANICO_ESPECIALIDAD);
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
			vistaAlta.actualizar(evento, datos);
		else if (vistaBaja != null)
			vistaBaja.actualizar(evento, datos);
		else if (vistaMostrar != null)
			vistaMostrar.actualizar(evento, datos);
		else if (vistaModificar != null)
			vistaModificar.actualizar(evento, datos);
		else if (vistaListar != null)
			vistaListar.actualizar(evento, datos);
		else if (vistaMostrarMecanicosEspecialidad != null)
			vistaMostrarMecanicosEspecialidad.actualizar(evento, datos);
	}

}
