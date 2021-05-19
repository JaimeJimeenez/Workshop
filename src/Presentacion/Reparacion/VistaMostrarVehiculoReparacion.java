package Presentacion.Reparacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarVehiculoReparacion extends JFrame implements Vista {

	public VistaMostrarVehiculoReparacion() {
		super("Mostrar Vehiculo Reparación");
		JPanel panel = new JPanel();
		JLabel lIDvehiculo = new JLabel("ID vehiculo:");
		final JTextField tIDvehiculo = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lIDvehiculo);
		panel.add(tIDvehiculo);
		panel.add(aceptar);
		panel.add(cancelar);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_REPARACION_VEHICULO, tIDvehiculo.getText());
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_MOSTRAR_REPARACION_VEHICULO_OK:// TR, TE, TT
			Collection<TReparacion> reparaciones = (Collection<TReparacion>) datos;
			String mensaje = "";

			for (TReparacion tReparacion : reparaciones) {
				mensaje += "Reparacion: \nID: " + tReparacion.getId() + "\nAveria: " + tReparacion.getAveria()
						+ "\nFecha de inicio: " + tReparacion.getFechaInicio() + "\nFecha de Salida: "
						+ tReparacion.getFechaSalida() + "\nPresupuesto: " + tReparacion.getPresupuesto()
						+ "\nID Vehiculo: " + tReparacion.getIdVehiculo() + "\n\n";
			}

			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case Eventos.RES_MOSTRAR_REPARACION_VEHICULO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la reparación: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_REPARACION_VEHICULO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la reparación: ID no encontrado");
			break;
		}
	}
}
