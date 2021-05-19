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

public class VistaBorrarComponenteReparacion extends JFrame implements Vista{

	public VistaBorrarComponenteReparacion()
	{
		super("Borrar componente reparación");
		JPanel panel = new JPanel();
		JLabel lIDrepracion = new JLabel("ID reparación:");
		final JTextField tIDreparacion= new JTextField(20);
		JLabel lIDcomponente = new JLabel("ID componente:");
		final JTextField tIDcomponente= new JTextField(20);
		JButton aceptar= new JButton("Aceptar");
		JButton cancelar= new JButton("Cancelar");
		panel.add(lIDrepracion);
		panel.add(tIDreparacion);
		panel.add(lIDcomponente);
		panel.add(tIDcomponente);
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
					TEmplea tEmplea = new TEmplea(Integer.parseInt(tIDreparacion.getText()), Integer.parseInt(tIDcomponente.getText()));
					Controlador.obtenerInstancia().accion(Eventos.BORRAR_COMPONENTE_REPARACION, tEmplea);
				} catch (NumberFormatException nfe){
					Controlador.obtenerInstancia().accion(Eventos.BORRAR_COMPONENTE_REPARACION, -1);
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
		case Eventos.RES_BORRAR_COMPONENTE_REPARACION_OK:
			TEmplea tEmplea= (TEmplea) datos;
			JOptionPane.showMessageDialog(null, "Componente eliminado con ID: " + tEmplea.getIdComponente()
			                                    + ", de la reparación con ID: " + tEmplea.getIdReparacion());
			break;
		case Eventos.RES_BORRAR_COMPONENTE_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el componente: datos incorrectos");
			break;
		case Eventos.RES_BORRAR_COMPONENTE_REPARACION_NR:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el componente: ID de la reparación no encontrado");
			break;
		case Eventos.RES_BORRAR_COMPONENTE_REPARACION_NC:
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el componente: ID del componente no encontrado");
			break;
		}
		
	}

}
