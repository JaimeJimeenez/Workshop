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

public class VistaAnyadirMecanico extends JFrame implements Vista{

	public VistaAnyadirMecanico()
	{
		super("Añadir Mecanico");
		JPanel panel = new JPanel();
		JLabel lIDrepracion = new JLabel("ID reparación:");
		final JTextField tIDreparacion= new JTextField(20);
		JLabel lIDmecanico = new JLabel("ID mecanico:");
		final JTextField tIDmecanico= new JTextField(20);
		JLabel lhora = new JLabel("Horas:");
		final JTextField thora= new JTextField(4);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lIDrepracion);
		panel.add(tIDreparacion);
		panel.add(lIDmecanico);
		panel.add(tIDmecanico);
		panel.add(lhora);
		panel.add(thora);
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
					TTrabaja tTrabaja = new TTrabaja(Integer.parseInt(tIDreparacion.getText()), Integer.parseInt(tIDmecanico.getText()), Integer.parseInt(thora.getText()));
					Controlador.obtenerInstancia().accion(Eventos.ANYADIR_MECANICO_REPARACION, tTrabaja);
				} catch (NumberFormatException nfe){
					Controlador.obtenerInstancia().accion(Eventos.ANYADIR_MECANICO_REPARACION, -1);
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
		case Eventos.RES_ANYADIR_MECANICO_REPARACION_OK:
			TTrabaja tTrabaja = (TTrabaja) datos;
			JOptionPane.showMessageDialog(null, "Mecanico añadido con ID: " + tTrabaja.getIdMecanico()
			                                    + ", de la reparación con ID: " + tTrabaja.getIdReparacion());
			break;
		case Eventos.RES_ANYADIR_MECANICO_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo añadir el mecánico: datos incorrectos");
			break;
		case Eventos.RES_ANYADIR_MECANICO_REPARACION_NM:
			JOptionPane.showMessageDialog(null, "No se pudo añadir el mecánico: ID del mecánico no encontrado");
			break;
		case Eventos.RES_ANYADIR_MECANICO_REPARACION_NR:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el mecánico: ID de la reparación no encontrado");
			break;
		case Eventos.RES_ANYADIR_MECANICO_REPARACION_RE:
			JOptionPane.showMessageDialog(null, "No se pudo añadir el mecánico a la reparación: Mecánico repetido");
			break;
		}
		
	}

}
