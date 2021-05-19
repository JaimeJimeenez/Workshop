package Presentacion.Mecanico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Mecanico.TMecanico;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarMecanico extends JFrame implements Vista {

	public VistaMostrarMecanico() {
		super("Mostrar mecánico");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_MECANICO, tID.getText());
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.MECANICO);
			}
		});
	}

	public void actualizar(int evento, Object datos) {

		switch (evento) {
		case Eventos.RES_MOSTRAR_MECANICO_OK:
			TMecanico mecanico = (TMecanico) datos;
			String mensaje = mecanico.getId() + "\nDNI: " + mecanico.getDNI() + "\nTeléfono: " + mecanico.getTelefono()
					+ "\nSueldo: " + mecanico.getSueldo() + "\nNombre: " + mecanico.getNombre() + "\nCuenta: "
					+ mecanico.getCuenta() + "\nID Especialidad: " + mecanico.getIdEspecialidad();
			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case Eventos.RES_MOSTRAR_MECANICO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el mecánico: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_MECANICO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el mecánico: ID no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
}
