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

public class VistaModificarMecanicoReparacion extends JFrame implements Vista {
	
	public VistaModificarMecanicoReparacion() {
		// Todo basado en la diapositiva 41
		
		super("Modificar mecánico");
		JPanel panel = new JPanel();
		
		JLabel lIdMecanico = new JLabel("ID Mecanico:");
		final JTextField tIdMecanico = new JTextField(20);

		JLabel lIdReparacion = new JLabel("ID Reparacion:");
		final JTextField tIdReparacion = new JTextField(20);
		
		JLabel lHoras = new JLabel("Horas:");
		final JTextField tHoras = new JTextField(20);
		
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		
		panel.add(lIdMecanico);
		panel.add(tIdMecanico);

		panel.add(lIdReparacion);
		panel.add(tIdReparacion);
		
		panel.add(lHoras);
		panel.add(tHoras);
		
		panel.add(aceptar);
		panel.add(cancelar);
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
				setVisible(false);
				int id;
				try {
					id = Integer.parseInt(tIdReparacion.getText());
				} catch (NumberFormatException nfexc) {
					id = -1;
				}
				int idMecanico;
				try {
					idMecanico = Integer.parseInt(tIdMecanico.getText());
				}catch (NumberFormatException nfex){
					idMecanico = -1;
				}
				int horas = -1;
				TTrabaja tMecanico;
				try {
					horas = Integer.parseInt(tHoras.getText());
					tMecanico = new TTrabaja(id, idMecanico, horas);
				} catch (NumberFormatException nfex) {
					tMecanico = new TTrabaja(id, idMecanico, horas);
				}
				Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_MECANICO_REPARACION, tMecanico);
		}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		switch(evento) 
		{
		case Eventos.RES_MODIFICAR_MECANICO_REPARACION_OK:
			TTrabaja tTrabaja = (TTrabaja) datos;
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Mecánico modificado con ID: " + tTrabaja.getIdMecanico() + 
												", a la reparacion con ID: " + tTrabaja.getIdReparacion());
		break;
		case Eventos.RES_MODIFICAR_MECANICO_REPARACION_DI:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el mecánico: datos incorrectos");
		break;
		case Eventos.RES_MODIFICAR_MECANICO_REPARACION_NM:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el mecánico: ID del mecanico no encontrado");
		break;
		case Eventos.RES_MODIFICAR_MECANICO_REPARACION_NR:
			JOptionPane.showMessageDialog(null,"No se pudo crear el mecánico: ID de la reparacion no encotrado");
		break;
		}
	}
}
