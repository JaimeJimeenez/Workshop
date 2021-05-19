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

public class VistaModificarMecanico extends JFrame implements Vista {

	public VistaModificarMecanico() {
		super("Modificar mecánico");
		JPanel panel = new JPanel();

		JLabel lID = new JLabel("ID:");
		final JTextField tID = new JTextField(20);

		JLabel ldni = new JLabel("DNI:");
		final JTextField tdni = new JTextField(9);

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

		panel.add(lID);
		panel.add(tID);

		panel.add(ldni);
		panel.add(tdni);

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

				String cuenta = tcuenta.getText();
				String tel = ttelef.getText();
				TMecanico tMecanico;

				try {
					int id = Integer.parseInt(tID.getText());
					float sueldo = tsueldo.getText().equals("") ? 0 : Float.parseFloat(tsueldo.getText());
					int idEsp = tidEsp.getText().equals("") ? 0 : Integer.parseInt(tidEsp.getText());

					tMecanico = new TMecanico(id, tdni.getText().toUpperCase(), sueldo, tnombre.getText().toLowerCase(), idEsp, tel, cuenta);
				} catch (NumberFormatException nfex) {
					tMecanico = new TMecanico(-1);
				}
				Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_MECANICO, tMecanico);
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
		case Eventos.RES_MODIFICAR_MECANICO_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Mecánico modificado con ID: " + id.intValue());
			break;
		case Eventos.RES_MODIFICAR_MECANICO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el mecánico: datos incorrectos");
			break;
		case Eventos.RES_MODIFICAR_MECANICO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el mecánico: ID no encontrado");
			break;
		case Eventos.RES_MODIFICAR_MECANICO_RE:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el mecánico: datos repetidos");
			break;
		case Eventos.RES_MODIFICAR_MECANICO_IE:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el mecánico: ID especialidad no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
}
