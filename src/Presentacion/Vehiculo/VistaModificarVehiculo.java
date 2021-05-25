package Presentacion.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Componente.TComponente;
import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaModificarVehiculo extends JFrame implements Vista{

	public VistaModificarVehiculo() {
		// Todo basado en la diapositiva 41
				super("Modificar Vehiculo");
				JPanel panel = new JPanel();
				JLabel lID = new JLabel("ID:");
				JLabel lIdCliente = new JLabel("ID Cliente:");
				JLabel lModelo = new JLabel("Modelo:");
				JLabel lMatricula = new JLabel("Matricula:");
				
				final JTextField tID = new JTextField(20);
				final JTextField tIdCliente = new JTextField(20);
				final JTextField tModelo = new JTextField(20);
				final JTextField tMatricula = new JTextField(20);

				JButton aceptar = new JButton("Aceptar");
				JButton cancelar = new JButton("Cancelar");
				
				panel.add(lID);
				panel.add(tID);
				panel.add(lIdCliente);
				panel.add(tIdCliente);
				panel.add(lModelo);
				panel.add(tModelo);
				panel.add(lMatricula);
				panel.add(tMatricula);
				panel.add(aceptar);
				panel.add(cancelar);
				getContentPane().add(panel);
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
					
				aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
					setVisible(false);
					try {
						int id = Integer.parseInt(tID.getText());
						int idC = Integer.parseInt(tIdCliente.getText());
						String modelo = tModelo.getText();
						String matricula = tMatricula.getText();
					
						TVehiculo tVehiculo = new TVehiculo(id, matricula, modelo, idC);
						Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_VEHICULO, tVehiculo);
					} catch(NumberFormatException exception){
						System.out.println(exception.getMessage());
					}
				}
				});
				
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						FactoriaVistas.obtenerInstancia().crearVista(Eventos.VEHICULO);
					}
				});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.RES_MODIFICAR_VEHICULO_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null, "Vehiculo modificado con ID: " + id.intValue());
			break;
		case Eventos.RES_MODIFICAR_VEHICULO_DI:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el vehiculo: datos incorrectos");
			break;
		case Eventos.RES_MODIFICAR_VEHICULO_NE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el vehiculo: ID no encontrado");
			break;
		case Eventos.RES_MODIFICAR_VEHICULO_RE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el vehiculo: datos repetidos");
			break;
		case Eventos.RES_MODIFICAR_VEHICULO_IE:
			JOptionPane.showMessageDialog(null, "No se pudo modificar el vehiculo: cliente no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
		
	}

}
