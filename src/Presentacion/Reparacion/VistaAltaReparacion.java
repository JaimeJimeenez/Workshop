package Presentacion.Reparacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaAltaReparacion extends JFrame implements Vista{
	
	public VistaAltaReparacion(){
		super("Alta reparacion");
		JPanel panel = new JPanel();
		JLabel lIDVehiculo = new JLabel("ID vehículo:");
		final JTextField tIDVehiculo = new JTextField(20);
		JLabel laveria = new JLabel("Avería:");
		final JTextField tAveria= new JTextField(20);
		JLabel lpresupuesto = new JLabel("Presupuesto:");
		final JTextField tpresupuesto= new JTextField(20);
		JLabel lfechaInicio = new JLabel("Fecha inicio:");
		JLabel lfechaFin= new JLabel("Fecha fin:");
		try{
			MaskFormatter mask = new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter('_');
			JFormattedTextField tFechaInicio = new JFormattedTextField(mask);
			JFormattedTextField tFechaFin = new JFormattedTextField(mask);
			panel.add(lIDVehiculo);
			panel.add(tIDVehiculo);
			panel.add(laveria);
			panel.add(tAveria);
			panel.add(lpresupuesto);
			panel.add(tpresupuesto);
			panel.add(lfechaInicio);
			panel.add(tFechaInicio);
			panel.add(lfechaFin);
			panel.add(tFechaFin);
			JButton aceptar= new JButton("Aceptar");
			panel.add(aceptar);
			
			aceptar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e)
			{
					setVisible(false);
					
					try {
						comprobarFecha(tFechaInicio.getText().split("/"));
						comprobarFecha(tFechaFin.getText().split("/"));
						TReparacion tReparacion = new TReparacion(0, Integer.valueOf(tIDVehiculo.getText()),
								tFechaInicio.getText(), tFechaFin.getText(), tAveria.getText(), Float.valueOf(tpresupuesto.getText()));
						Controlador.obtenerInstancia().accion(Eventos.ALTA_REPARACION, tReparacion);
						
					} catch (NumberFormatException | DateTimeException exc){
						TReparacion reparacion = new TReparacion(-1);
						Controlador.obtenerInstancia().accion(Eventos.ALTA_REPARACION, reparacion);
					}
			}
			});
		}catch(Exception e){
			
		}
		JButton cancelar= new JButton("Cancelar");
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_ALTA_REPARACION_OK:
			TEmplea tEmplea= (TEmplea) datos;
			JOptionPane.showMessageDialog(null, "Reparacion dada de alta con ID: " + tEmplea.getIdReparacion());
			break;
		case Eventos.RES_ALTA_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta la reparación: datos incorrectos");
			break;
		case Eventos.RES_ALTA_REPARACION_RE:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta la reparación: datos repetidos");
			break;
		}
	}
	
	private void comprobarFecha(String[] fecha) 
	{
		int yearIni = Integer.valueOf(fecha[2]);
		int monthIni = Integer.valueOf(fecha[1]);
		int dayIni = Integer.valueOf(fecha[0]);
		LocalDate.of(yearIni, monthIni, dayIni);	
	}

}
