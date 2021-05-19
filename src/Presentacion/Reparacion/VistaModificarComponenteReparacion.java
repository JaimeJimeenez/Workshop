package Presentacion.Reparacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Reparacion.TEmplea;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaModificarComponenteReparacion extends JFrame implements Vista{

	public VistaModificarComponenteReparacion()
	{
		super("Modificar componente reparación");
		JPanel panel = new JPanel();
		JLabel lIDrepracion = new JLabel("ID reparación:");
		final JTextField tIDreparacion= new JTextField(20);
		JLabel lIDcomponente = new JLabel("ID componente:");
		final JTextField tIDcomponente= new JTextField(20);
		JLabel lPrecio = new JLabel("Precio:");
		final JTextField tPrecio = new JTextField(20);
		JLabel lCantidad = new JLabel("Cantidad:");
		final JTextField tCantidad = new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lIDrepracion);
		panel.add(tIDreparacion);
		panel.add(lIDcomponente);
		panel.add(tIDcomponente);
		panel.add(lPrecio);
		panel.add(tPrecio);
		panel.add(lCantidad);
		panel.add(tCantidad);
		panel.add(aceptar);
		panel.add(cancelar);
		this.add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
				setVisible(false);
				try {
					TEmplea tEmplea = new TEmplea(Integer.parseInt(tIDreparacion.getText()), Integer.parseInt(tIDcomponente.getText()),
							Float.parseFloat(tPrecio.getText()), Integer.parseInt(tCantidad.getText()));
					Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_COMPONENTE_REPARACION, tEmplea);
				} catch (NumberFormatException nfe){
					Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_COMPONENTE_REPARACION, -1);
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
		case Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_OK:
			TEmplea tEmplea= (TEmplea) datos;
			JOptionPane.showMessageDialog(null, "Componente modificado con ID: " + tEmplea.getIdComponente()
			                                    + ", a la reparación con ID: " + tEmplea.getIdReparacion());
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el componente a la reparación: datos incorrectos");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_NR:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el componente a la reparación: ID de la reparación no encontrado");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_REPARACION_NC:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el componente a la reparación: ID del componente no encontrado");
			break;
		}
	}
}
