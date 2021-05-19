package Presentacion.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaBajaVehiculo extends JFrame implements Vista {

	public VistaBajaVehiculo() {
		super("Baja Vehiculo");
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
				Controlador.obtenerInstancia().accion(Eventos.BAJA_VEHICULO, tID.getText());
			}
			});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.VEHICULO);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_BAJA_VEHICULO_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Vehiculo eliminado con ID: " + id.intValue());
			break;
		case Eventos.RES_BAJA_VEHICULO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el vehiculo: datos incorrectos");
			break;
		case Eventos.RES_BAJA_VEHICULO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el vehiculo: ID no encontrado");
			break;
		}
		
	}

}
