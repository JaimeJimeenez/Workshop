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

public class VistaAltaMecanico extends JFrame implements Vista {

	public VistaAltaMecanico() {
		super("Alta mecánico");
		JPanel panel = new JPanel();

		JLabel lNIF = new JLabel("DNI:");
		final JTextField tNIF = new JTextField(20);

		JLabel lnombre = new JLabel("Nombre:");
		final JTextField tnombre = new JTextField(20);

		JLabel ltelef = new JLabel("Teléfono:");
		final JTextField ttelef = new JTextField(20);

		JLabel lsueldo = new JLabel("Sueldo:");
		final JTextField tsueldo = new JTextField(20);

		JLabel lcuenta = new JLabel("Cuenta:");
		final JTextField tcuenta = new JTextField(20);

		JLabel lidEsp = new JLabel("ID Especialidad:");
		final JTextField tidEsp = new JTextField(20);

		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");

		panel.add(lNIF);
		panel.add(tNIF);

		panel.add(lnombre);
		panel.add(tnombre);

		panel.add(lsueldo);
		panel.add(tsueldo);

		panel.add(lcuenta);
		panel.add(tcuenta);

		panel.add(lidEsp);
		panel.add(tidEsp);

		panel.add(ltelef);
		panel.add(ttelef);

		panel.add(aceptar);
		panel.add(cancelar);

		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String NIF = tNIF.getText();
				String nombre = tnombre.getText();
				Float sueldo;
				String cuenta = tcuenta.getText();
				String tel = ttelef.getText();
				TMecanico tMecanico;

				try {
					sueldo = Float.parseFloat(tsueldo.getText());
					int idEsp = Integer.parseInt(tidEsp.getText());
					tMecanico = new TMecanico(NIF.toUpperCase(), sueldo, nombre.toLowerCase(), idEsp, tel, cuenta);
				} catch (NumberFormatException nfex) {
					tMecanico = new TMecanico(-1);
				}

				Controlador.obtenerInstancia().accion(Eventos.ALTA_MECANICO, tMecanico);
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
		case Eventos.RES_ALTA_MECANICO_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Mecánico creado con ID: " + id.intValue());
			break;
		case Eventos.RES_ALTA_MECANICO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo crear el mecánico: datos incorrectos");
			break;
		case Eventos.RES_ALTA_MECANICO_RE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el mecánico: datos repetidos");
			break;
		case Eventos.RES_ALTA_MECANICO_IE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el mecánico: ID especialidad no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}

}
