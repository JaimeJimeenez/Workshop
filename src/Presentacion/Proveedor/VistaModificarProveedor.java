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

public class VistaModificarProveedor extends JFrame implements Vista {

	public VistaModificarProveedor() {
		super("Modificar proveedor");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID= new JTextField(20);
		JLabel ldireccion = new JLabel("Dirección:");
		final JTextField tdireccion= new JTextField(20);
		JLabel ltelef= new JLabel("Teléfono:");
		final JTextField ttelef= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(ldireccion);
		panel.add(tdireccion);
		panel.add(ltelef);
		panel.add(ttelef);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				TProveedor proveedor;
				try {
					proveedor = new TProveedor(Integer.parseInt(tID.getText()), ttelef.getText(), tdireccion.getText());
				} catch (NumberFormatException nfe){
					proveedor = new TProveedor(-1);
				}
				Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_PROVEEDOR, proveedor);
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
		switch(evento) 
		{
		case Eventos.RES_MODIFICAR_PROVEEDOR_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Proveedor modificado con ID: " + id.intValue());
		break;
		case Eventos.RES_MODIFICAR_PROVEEDOR_DI:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el proveedor: datos incorrectos");
		break;
		case Eventos.RES_MODIFICAR_PROVEEDOR_NE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el proveedor: ID no encontrado");
		break;
		case Eventos.RES_MODIFICAR_PROVEEDOR_RE:
			JOptionPane.showMessageDialog(null,"No se pudo crear el proveedor: datos repetidos");
		break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
		break;
		}
	}
}