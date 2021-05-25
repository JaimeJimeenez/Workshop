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

public class VistaMostrarComponente extends JFrame implements Vista {

	public VistaMostrarComponente() {
		super("Mostrar Componente");
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
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_COMPONENTE, tID.getText());
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
		case Eventos.RES_MOSTRAR_COMPONENTE_OK:
			TComponente componente = (TComponente) datos;
			JOptionPane.showMessageDialog(null, "Componente:\n\nID: " + componente.getId() + "\nMarca: " + componente.getMarca() + "\nPrecio: " + componente.getPrecio() +"\nModelo " + componente.getModelo() +"\nStock: " + componente.getStock());
			break;
		case Eventos.RES_MOSTRAR_COMPONENTE_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el componente: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_COMPONENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el componente: ID no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el componente: se ha producido un fallo en la base de datos");
			break;
		}
	}

}
