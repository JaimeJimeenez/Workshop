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

public class VistaAltaProveedor extends JFrame implements Vista{

	public VistaAltaProveedor() {

		super("Alta proveedor");
		JPanel panel = new JPanel();
		JLabel lNIF = new JLabel("NIF:");
		final JTextField tNIF= new JTextField(20);
		JLabel ldireccion = new JLabel("Dirección:");
		final JTextField tdireccion= new JTextField(20);
		JLabel ltelef= new JLabel("Teléfono:");
		final JTextField ttelef= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lNIF);
		panel.add(tNIF);
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

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				setVisible(false);
				String NIF = tNIF.getText();
				String direc = tdireccion.getText();
				String tel = ttelef.getText();
				TProveedor tProveedor = new TProveedor(NIF, tel, direc);
				Controlador.obtenerInstancia().accion(Eventos.ALTA_PROVEEDOR, tProveedor);
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
		case Eventos.RES_ALTA_PROVEEDOR_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Proveedor creado con ID: " + id.intValue());
			break;
		case Eventos.RES_ALTA_PROVEEDOR_DI:
			JOptionPane.showMessageDialog(null, "No se pudo crear el proveedor: datos incorrectos");
			break;
		case Eventos.RES_ALTA_PROVEEDOR_RE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el proveedor: datos repetidos");
			break;
		}
	}

}
