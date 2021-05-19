package Presentacion.Reparacion;

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

public class VistaBajaReparacion extends JFrame implements Vista{

	public VistaBajaReparacion()
	{
		super("Baja reparación");
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
				try {
					Controlador.obtenerInstancia().accion(Eventos.BAJA_REPARACION, tID.getText());
				} catch (NumberFormatException nfe){
					Controlador.obtenerInstancia().accion(Eventos.BAJA_REPARACION, -1);
				}
		}
		});
		cancelar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
			FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
	}
	});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_BAJA_REPARACION_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null, "Reparación eliminado con ID: " + id.intValue());
			break;
		case Eventos.RES_BAJA_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar la reparación: datos incorrectos");
			break;
		case Eventos.RES_BAJA_REPARACION_NE:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar la reparación: ID no encontrado");
			break;
		}
		
	}
}
