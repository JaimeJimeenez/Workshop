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
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaModificarComponente extends JFrame implements Vista {
	
	public VistaModificarComponente() {
		super("Modificar Componente");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		JLabel lIdProveedor = new JLabel("ID Proveedor:");
		JLabel lMarca = new JLabel("Marca:");
		JLabel lPrecio = new JLabel("Precio:");
		JLabel lModelo = new JLabel("Modelo:");
		JLabel lStock = new JLabel("Stock:");
		
		
		final JTextField tID = new JTextField(20);
		final JTextField tIdProveedor = new JTextField(20);
		final JTextField tMarca = new JTextField(20);
		final JTextField tPrecio = new JTextField(20);
		final JTextField tModelo = new JTextField(20);
		final JTextField tStock = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		panel.add(lID);
		panel.add(tID);
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
			
		aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			setVisible(false);
			String marca = tMarca.getText();
			String modelo = tModelo.getText();
			TComponente tComponente;
			try
			{
				int id = Integer.parseInt(tID.getText());
				int idP =  tIdProveedor.getText().equals("") ? 0: Integer.parseInt(tIdProveedor.getText());
				float precio = tPrecio.getText().equals("") ? 0 :Float.parseFloat(tPrecio.getText());
				int stock = tStock.getText().equals("") ? 0 : Integer.parseInt(tStock.getText());
				tComponente = new TComponente(id, idP, marca, precio, modelo, stock);
			}
			catch(NumberFormatException exp)
			{
				tComponente = new TComponente(-1);
			}

			Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_COMPONENTE,  tComponente);
		}});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.COMPONENTE);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.RES_MODIFICAR_COMPONENTE_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Componente modificado con ID: " + id.intValue());
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_DI:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el componente: datos incorrectos");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_NE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el componente: ID no encontrado");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_RE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el componente: datos repetidos");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_IE:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el componente: ID proveedor no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el componente: se ha producido un fallo en la base de datos");
			break;
		}
	}
}
