package Presentacion.Proveedor;

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

public class VistaBajaProveedor extends JFrame implements Vista {

	public VistaBajaProveedor() {
		super("Baja proveedor");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.BAJA_PROVEEDOR, tID.getText());		
		}
		});
		cancelar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			FactoriaVistas.obtenerInstancia().crearVista(Eventos.PROVEEDOR);
	}
	});
	}
	
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_BAJA_PROVEEDOR_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null, "Proveedor eliminado con ID: " + id.intValue());
			break;
		case Eventos.RES_BAJA_PROVEEDOR_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor: datos incorrectos");
			break;
		case Eventos.RES_BAJA_PROVEEDOR_NE:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor: ID no encontrado");
			break;
		case Eventos.RES_BAJA_PROVEEDOR_OA:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor: componentes activos");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
			
		}
		
		
		
	}
}