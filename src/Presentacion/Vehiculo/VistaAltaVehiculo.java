package Presentacion.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Componente.TComponente;
import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaAltaVehiculo extends JFrame implements Vista{

	public VistaAltaVehiculo() {
		super("Alta Vehiculo");
		JPanel panel = new JPanel();
		JLabel lIdC = new JLabel("ID Cliente:");
		JLabel lModelo = new JLabel("Modelo:");
		JLabel lMatricula = new JLabel("Matricula:");
		
		final JTextField tIdC = new JTextField(20);
		final JTextField tModelo = new JTextField(20);
		final JTextField tMatricula = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panel.add(lIdC);
		panel.add(tIdC);
		panel.add(lModelo);
		panel.add(tModelo);
		panel.add(lMatricula);
		panel.add(tMatricula);
		panel.add(aceptar);
		panel.add(cancelar);
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TVehiculo tVehiculo = new TVehiculo(tMatricula.getText(), tModelo.getText(), Integer.parseInt(tIdC.getText()));
				Controlador.obtenerInstancia().accion(Eventos.ALTA_VEHICULO, tVehiculo);
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
		case Eventos.RES_ALTA_VEHICULO_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Vehiculo creado con ID: " + id.intValue());
			break;
		case Eventos.RES_ALTA_VEHICULO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo crear el vehiculo: datos incorrectos");
			break;
		case Eventos.RES_ALTA_VEHICULO_RE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el vehiculo: datos repetidos");
			break;
		case Eventos.RES_ALTA_VEHICULO_CNE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el vehiculo: cliente no encontrado");
			break;
		}
		
	}

}
