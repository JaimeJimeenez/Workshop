package Presentacion.Especialidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Especialidad.TEspecialidad;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaModificarEspecialidad extends JFrame implements Vista {

	public VistaModificarEspecialidad() {
		// Todo basado en la diapositiva 41
		
		super("Modificar especialidad");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID= new JTextField(20);
		JLabel lTipo = new JLabel("Tipo:");
		final JTextField tTipo= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(lTipo);
		panel.add(tTipo);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
		{
				setVisible(false);
				try{
					TEspecialidad especialidad = new TEspecialidad(tTipo.getText().toLowerCase(), Integer.parseInt(tID.getText().toLowerCase()));
					Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_ESPECIALIDAD, especialidad);
				}catch(NumberFormatException exception){
					System.out.println(exception.getMessage());
				}
		}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.ESPECIALIDAD);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		switch(evento) 
		{
		case Eventos.RES_MODIFICAR_ESPECIALIDAD_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Especialidad modificada con ID: " + id.intValue());
		break;
		case Eventos.RES_MODIFICAR_ESPECIALIDAD_DI:
			JOptionPane.showMessageDialog(null,"No se pudo modificar la especialidad: datos incorrectos");
		break;
		case Eventos.RES_MODIFICAR_ESPECIALIDAD_NE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar la especialidad: ID no encontrado");
		break;
		case Eventos.RES_MODIFICAR_ESPECIALIDAD_RE:
			JOptionPane.showMessageDialog(null,"No se pudo crear la especialidad: datos repetidos");
		break;
		}
	}
}