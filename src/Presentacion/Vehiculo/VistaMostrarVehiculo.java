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

public class VistaMostrarVehiculo extends JFrame implements Vista {

	public VistaMostrarVehiculo() {
		super("Mostrar Vehiculo");
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
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_VEHICULO, tID.getText());
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
		case Eventos.RES_MOSTRAR_VEHICULO_OK:
			TVehiculo vehiculo = (TVehiculo) datos;
			JOptionPane.showMessageDialog(null, "Vehiculo:\n\nID: " + vehiculo.getId() + "\nID Cliente: " + vehiculo.getIdCliente() + "\nModelo: " + vehiculo.getModelo() + "\nMatriculo: " + vehiculo.getMatricula());
			break;
		case Eventos.RES_MOSTRAR_VEHICULO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el vehiculo: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_VEHICULO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el vehiculo: ID no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
		
		
	}

}
