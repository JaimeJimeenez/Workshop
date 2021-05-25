package Presentacion.Especialidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Especialidad.TEspecialidad;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaAltaEspecialidad extends JFrame implements Vista {
	public VistaAltaEspecialidad() {
		// Todo basado en la diapositiva 41

		super("Alta especialidad");
		JPanel panel = new JPanel();
		JLabel lTipo = new JLabel("Tipo:");
		final JTextField tTipo = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lTipo);
		panel.add(tTipo);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				TEspecialidad tEspecialidad = new TEspecialidad(tTipo.getText().toLowerCase());
				Controlador.obtenerInstancia().accion(Eventos.ALTA_ESPECIALIDAD, tEspecialidad);
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.ESPECIALIDAD);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_ALTA_ESPECIALIDAD_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Especialidad creada con ID: " + id.intValue());
			break;
		case Eventos.RES_ALTA_ESPECIALIDAD_DI:
			JOptionPane.showMessageDialog(null, "No se pudo crear la especialidad: datos incorrectos");
			break;
		case Eventos.RES_ALTA_ESPECIALIDAD_RE:
			JOptionPane.showMessageDialog(null, "No se pudo crear la especialidad: datos repetidos");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
}