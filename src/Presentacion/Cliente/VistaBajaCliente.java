package Presentacion.Cliente;

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

public class VistaBajaCliente extends JFrame implements Vista{

	public VistaBajaCliente() {
		super("Baja Cliente");
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
				Controlador.obtenerInstancia().accion(Eventos.BAJA_CLIENTE, tID.getText());
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.CLIENTE);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_BAJA_CLIENTE_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Cliente eliminado con ID: " + id.intValue());
			break;
		case Eventos.RES_BAJA_CLIENTE_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente: datos incorrectos");
			break;
		case Eventos.RES_BAJA_CLIENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente: ID no encontrado");
			break;
		case Eventos.RES_BAJA_CLIENTE_OA:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente: mecánicos activos");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente: No se pudo conectar con la base de datos");
			break;
		}
		
	}

}
