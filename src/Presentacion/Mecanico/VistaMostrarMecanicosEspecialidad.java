package Presentacion.Mecanico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Mecanico.TMecanico;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarMecanicosEspecialidad extends JFrame implements Vista {

	public VistaMostrarMecanicosEspecialidad() {
		super("Mostrar Mecanicos Especialidad");
		JPanel panel = new JPanel();
		JLabel lIDespecialidad = new JLabel("ID especialidad:");
		final JTextField tIDespecialidad = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lIDespecialidad);
		panel.add(tIDespecialidad);
		panel.add(aceptar);
		panel.add(cancelar);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try{
					Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_MECANICO_ESPECIALIDAD, Integer.parseInt(tIDespecialidad.getText()));					
				}
				catch(NumberFormatException ex){
					Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_MECANICO_ESPECIALIDAD, 0);
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.MECANICO);
			}
		});
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_OK:
			Collection<TMecanico> mecanicos = (Collection<TMecanico>) datos;
			String mensaje = "";
			Iterator<TMecanico> it = mecanicos.iterator();

			if (it.hasNext())
				mensaje += "ID Especialidad: " + it.next().getIdEspecialidad();

			for (TMecanico tMecanico : mecanicos) {
				mensaje += "\n\nID Mecanico: " + tMecanico.getId() + "\nDNI: " + tMecanico.getDNI() + "\nNombre: "
						+ tMecanico.getNombre() + "\nSueldo: " + tMecanico.getSueldo() + "\nTelefono: "
						+ tMecanico.getTelefono() + "\nCuenta: " + tMecanico.getCuenta();
			}

			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_DI:
			JOptionPane.showMessageDialog(null,
					"No se pudo mostrar los mecanicos de la especialidad: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_NE:
			JOptionPane.showMessageDialog(null,
					"No se pudo mostrar los mecanicos de la especialidad: no existe ningún mecánico con la especialidad especificada");
			break;
		case Eventos.RES_MOSTRAR_MECANICO_ESPECIALIDAD_IE:
			JOptionPane.showMessageDialog(null,
					"No se pudo mostrar los mecánicos de la especialidad: ID especialidad no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
}
