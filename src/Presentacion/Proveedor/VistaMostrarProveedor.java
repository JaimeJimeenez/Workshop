package Presentacion.Proveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Proveedor.TProveedor;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarProveedor extends JFrame implements Vista {

	public VistaMostrarProveedor() {
		// Todo basado en la diapositiva 41
		super("Mostrar proveedor");
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
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_PROVEEDOR, tID.getText());
				
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.PROVEEDOR);
			}
		});
	}

	public void actualizar(int evento, Object datos) {

		switch (evento) {
		case Eventos.RES_MOSTRAR_PROVEEDOR_OK:
			TProveedor proveedor = (TProveedor) datos;
			JOptionPane.showMessageDialog(null, "Proveedor:\n\nID: " + proveedor.getId() + "\nNIF: " + proveedor.getNIF() 
						+ "\nDireccion: " + proveedor.getDireccion() + "\nTeléfono: " + proveedor.getTelefono());
			break;
		case Eventos.RES_MOSTRAR_PROVEEDOR_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el proveedor: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_PROVEEDOR_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el proveedor: ID no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
}