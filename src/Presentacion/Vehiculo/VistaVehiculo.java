package Presentacion.Vehiculo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaVehiculo extends JFrame implements Vista{
	Vista vistaAlta;
	Vista vistaBaja;
	Vista vistaMostrar;
	Vista vistaMostrarVehiculosCliente;
	Vista vistaModificar;
	Vista vistaListar;
	
	public VistaVehiculo() {
		super("Menú Vehiculo");
		JPanel panel = new JPanel();
		JButton alta = new JButton("Alta");
		JButton baja = new JButton("Baja");
		JButton modificar = new JButton("Modificar");
		JButton mostrar = new JButton("Mostrar");
		JButton mostrarXCliente = new JButton("Mostrar vehiculos de un Cliente");
		JButton listar = new JButton("Listar");
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBackground(Color.RED);

		panel.add(alta);
		panel.add(baja);
		panel.add(modificar);
		panel.add(mostrar);
		panel.add(mostrarXCliente);
		panel.add(listar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaAlta = FactoriaVistas.obtenerInstancia().crearVista(Eventos.ALTA_VEHICULO);
			}
		});
		
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);;
				vistaBaja = FactoriaVistas.obtenerInstancia().crearVista(Eventos.BAJA_VEHICULO);
			}
		});
		
		mostrarXCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrarVehiculosCliente = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_VEHICULO_CLIENTE);
			}
		});
		
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaMostrar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MOSTRAR_VEHICULO);
				
			}
		});
		
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.LISTAR_VEHICULO, null);
			}
			
		});
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				vistaListar = FactoriaVistas.obtenerInstancia().crearVista(Eventos.MODIFICAR_VEHICULO);
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.TALLER);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		if (vistaAlta != null)
			vistaAlta.actualizar(evento, datos);
		else if (vistaBaja != null)
			vistaBaja.actualizar(evento, datos);
		else if (vistaMostrarVehiculosCliente != null)
			vistaMostrarVehiculosCliente.actualizar(evento, datos);
		else if (vistaMostrar != null)
			vistaMostrar.actualizar(evento, datos);
		else if (vistaModificar != null)
			vistaModificar.actualizar(evento, datos);
	}

}
