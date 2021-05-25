package Presentacion.Componente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Componente.TComponente;
import Negocio.Especialidad.TEspecialidad;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaAltaComponente extends JFrame implements Vista{

	public VistaAltaComponente() {
		super("Alta Componente");
		JPanel panel = new JPanel();
		JLabel lIdProveedor = new JLabel("ID Proveedor:");
		JLabel lMarca = new JLabel("Marca:");
		JLabel lPrecio = new JLabel("Precio:");
		JLabel lModelo = new JLabel("Modelo:");
		JLabel lStock = new JLabel("Stock:");
		
		final JTextField tIdProveedor = new JTextField(20);
		final JTextField tMarca = new JTextField(20);
		final JTextField tPrecio = new JTextField(20);
		final JTextField tModelo = new JTextField(20);
		final JTextField tStock = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panel.add(lIdProveedor);
		panel.add(tIdProveedor);
		panel.add(lMarca);
		panel.add(tMarca);
		panel.add(lPrecio);
		panel.add(tPrecio);
		panel.add(lModelo);
		panel.add(tModelo);
		panel.add(lStock);
		panel.add(tStock);
		panel.add(aceptar);
		panel.add(cancelar);
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int idP = Integer.parseInt(tIdProveedor.getText());
				String marca = tMarca.getText();
				float precio = Float.parseFloat(tPrecio.getText());
				String modelo = tModelo.getText();
				int stock = Integer.parseInt(tStock.getText());
			
				TComponente tComponente = new TComponente(marca, idP, precio, modelo, stock);
				Controlador.obtenerInstancia().accion(Eventos.ALTA_COMPONENTE, tComponente);
			}
		});
		cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.COMPONENTE);
			}
		});
	}
	
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_ALTA_COMPONENTE_OK:
			Integer id = (Integer) datos;
			JOptionPane.showMessageDialog(null, "Componente creado con ID: " + id.intValue());
			break;
		case Eventos.RES_ALTA_COMPONENTE_DI:
			JOptionPane.showMessageDialog(null, "No se pudo crear el componente: datos incorrectos");
			break;
		case Eventos.RES_ALTA_COMPONENTE_RE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el componente: datos repetidos");
			break;
		case Eventos.RES_ALTA_COMPONENTE_IE:
			JOptionPane.showMessageDialog(null, "No se pudo crear el componente: ID proveedor no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo crear el componente: se ha producido un fallo en la base de datos");
			break;
		}
	}

}
