package Presentacion.Reparacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Reparacion.TTrabaja;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaBorrarMecanicoReparacion extends JFrame implements Vista{

	public VistaBorrarMecanicoReparacion()
	{
		super("Borrar mecánico reparación");
		JPanel panel = new JPanel();
		JLabel lIDreparacion = new JLabel("ID reparación:");
		final JTextField tIDreparacion= new JTextField(20);
		JLabel lIDmecanico = new JLabel("ID mecánico:");
		final JTextField tIDmecanico= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lIDreparacion);
		panel.add(tIDreparacion);
		panel.add(lIDmecanico);
		panel.add(tIDmecanico);
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
					TTrabaja tTrabaja = new TTrabaja(Integer.parseInt(tIDreparacion.getText()), Integer.parseInt(tIDmecanico.getText()));
					Controlador.obtenerInstancia().accion(Eventos.BORRAR_MECANICO_REPARACION, tTrabaja);
				} catch (NumberFormatException nfe){
					Controlador.obtenerInstancia().accion(Eventos.BORRAR_MECANICO_REPARACION, -1);
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
		case Eventos.RES_BORRAR_MECANICO_REPARACION_OK:
			TTrabaja tTrabaja= (TTrabaja) datos;
			JOptionPane.showMessageDialog(null, "Mecánico eliminado con ID: " + tTrabaja.getIdMecanico()
			                                    + ", de la reparación con ID: " + tTrabaja.getIdReparacion());
			break;
		case Eventos.RES_BORRAR_MECANICO_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el mecánico: datos incorrectos");
			break;
		case Eventos.RES_BORRAR_MECANICO_REPARACION_NR:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el mecánico: ID de la reparación no encontrado");
			break;
		case Eventos.RES_BORRAR_MECANICO_REPARACION_NM:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el mecánico: ID del mecánico no encontrado");
			break;
		}
		
	}

}
