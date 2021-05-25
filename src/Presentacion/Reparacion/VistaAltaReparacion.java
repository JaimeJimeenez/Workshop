package Presentacion.Reparacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaAltaReparacion extends JFrame implements Vista{

	private static final long serialVersionUID = 1L;

	private TReparacion tReparacion;
	private Collection<TEmplea> cEmplea;
	private Collection<TTrabaja> cTrabaja;
	
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
		cEmplea = new LinkedList<TEmplea>();
		cTrabaja = new LinkedList<TTrabaja>();
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
					String fechaIni = comprobarFecha(tFechaInicio.getText().split("/"));
					String fechaFin = comprobarFecha(tFechaFin.getText().split("/"));
					tReparacion = new TReparacion(0, Integer.valueOf(tIDVehiculo.getText()),
							fechaIni, fechaFin, tAveria.getText(), Float.valueOf(tpresupuesto.getText()));
					
					tablaComponentes();
					//Controlador.obtenerInstancia().accion(Eventos.ALTA_REPARACION, tReparacion);
					
				} catch (NumberFormatException | DateTimeException exc){
					tReparacion = new TReparacion(-1);
					Collection<Object> rep = new LinkedList<Object>();
					rep.add(tReparacion);
					rep.add(cEmplea);
					rep.add(cTrabaja);
					Controlador.obtenerInstancia().accion(Eventos.ALTA_REPARACION, rep);
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
			int tReparacion= (int) datos;
			//int id = tReparacion.getId();
			JOptionPane.showMessageDialog(null, "Reparacion dada de alta con ID: " + tReparacion);
			//VistaAltaReparacionTabla h = new VistaAltaReparacionTabla(id);
			break;
		case Eventos.RES_ALTA_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta la reparación: datos incorrectos");
			break;
		case Eventos.RES_ALTA_REPARACION_RE:
			JOptionPane.showMessageDialog(null, "No se pudo dar de alta la reparación: datos repetidos");
			break;
		}
	}
	
	private String comprobarFecha(String[] fecha) 
	{
		int yearIni = Integer.valueOf(fecha[2]);
		int monthIni = Integer.valueOf(fecha[1]);
		int dayIni = Integer.valueOf(fecha[0]);
		LocalDate.of(yearIni, monthIni, dayIni);
		
		String sol = null;
		sol = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
		return sol;
	}
	
	private void tablaComponentes(){
		
		JFrame f = new JFrame("Añadir Componente");
		//JPanel panel = new JPanel();
		JLabel lIDComponente = new JLabel("ID componente:");
		JTextField tIDComponente = new JTextField(20);
		JLabel lprecio = new JLabel("Precio:");
		JTextField tprecio= new JTextField(20);
		JLabel lcantidad = new JLabel("Cantidad:");
		JTextField tcantidad= new JTextField(20);
				
		JPanel contentPane = new JPanel(new BorderLayout(10,20));
		JPanel tabla = new JPanel();
		tabla.setBorder(new TitledBorder("Componentes"));
		contentPane.add(tabla);
		
		JPanel lineStart = new JPanel(new GridLayout(0, 1, 5, 5));
		lineStart.setBorder(new TitledBorder("Datos"));
		contentPane.add(lineStart, BorderLayout.LINE_START);
		lineStart.add(lIDComponente);
		lineStart.add(tIDComponente);
		lineStart.add(lprecio);
		lineStart.add(tprecio);
		lineStart.add(lcantidad);
		lineStart.add(tcantidad);
		JButton ayadirC= new JButton("Añadir");
		lineStart.add(ayadirC);
		JButton aceptarC= new JButton("Aceptar");
		lineStart.add(aceptarC);
		JButton cancelarC= new JButton("Cancelar");
		lineStart.add(cancelarC);
		
		JTable tbl = new JTable();
		tabla.add(new JScrollPane(tbl));
		DefaultTableModel dtm = new DefaultTableModel(0, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		String header[] = new String[] { "IDComponente: ", "Precio: ", "Cantidad: " };
		dtm.setColumnIdentifiers(header);
		tbl.setModel(dtm);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(240);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(240);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(240);
		
		        
		f.setContentPane(contentPane);
		f.pack();
		f.setMinimumSize(f.getSize());
		f.setSize(900, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		ayadirC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				//SAComponente sa = FactoriaSA.obtenerInstancia().crearSAComponente();
				//SAReparacion saR = FactoriaSA.obtenerInstancia().crearSAReparacion();
				//TComponente tComponente = sa.mostrar(Integer.valueOf(tIDComponente.getText()));
				//Collection<Object> list = saR.mostrar(reparacion);
				//Collection<TEmplea> emplea = (Collection<TEmplea>) list.toArray()[2];
				TEmplea tEmplea;
				try {
					tEmplea = new TEmplea(
							0, 
							Integer.valueOf(tIDComponente.getText()), 
							Float.valueOf(tprecio.getText()), 
							Integer.valueOf(tcantidad.getText()));
					
					cEmplea.add(tEmplea);
					
					dtm.addRow(new Object[]{
							tIDComponente.getText(),
							tprecio.getText(),
							tcantidad.getText()
					});
					
				} catch (NumberFormatException ex){
					tEmplea = new TEmplea(-1);
							 
				}		

				
			}
		});
				
		aceptarC.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				//FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
				tablaMecanicos();
			}
		});		
		cancelarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
		
	}
	
	
	private void tablaMecanicos(){
		JFrame f = new JFrame("Añadir Mecánicos");
		//JPanel panel = new JPanel();
		JLabel lIDComponente = new JLabel("ID mecánico:");
		JTextField tIDComponente = new JTextField(20);
		JLabel lhora = new JLabel("Horas:");
		JTextField thora= new JTextField(20);
				
		JPanel contentPane = new JPanel(new BorderLayout(10,20));
		JPanel tabla = new JPanel();
		tabla.setBorder(new TitledBorder("Componentes"));
		contentPane.add(tabla);
		
		JPanel lineStart = new JPanel(new GridLayout(0, 1, 5, 5));
		lineStart.setBorder(new TitledBorder("Datos"));
		contentPane.add(lineStart, BorderLayout.LINE_START);
		lineStart.add(lIDComponente);
		lineStart.add(tIDComponente);
		lineStart.add(lhora);
		lineStart.add(thora);
		JButton ayadirM= new JButton("Añadir");
		lineStart.add(ayadirM);
		JButton aceptarM= new JButton("Aceptar");
		lineStart.add(aceptarM);
		JButton cancelarM= new JButton("Cancelar");
		lineStart.add(cancelarM);
		
		JTable tbl = new JTable();
		tabla.add(new JScrollPane(tbl));
		DefaultTableModel dtm = new DefaultTableModel(0, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		String header[] = new String[] { "IDMecanico: ", "Horas: "};
		dtm.setColumnIdentifiers(header);
		tbl.setModel(dtm);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(240);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(240);
		
		        
		f.setContentPane(contentPane);
		f.pack();
		f.setMinimumSize(f.getSize());
		f.setSize(900, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		ayadirM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				//SAComponente sa = FactoriaSA.obtenerInstancia().crearSAComponente();
				//SAReparacion saR = FactoriaSA.obtenerInstancia().crearSAReparacion();
				//TComponente tComponente = sa.mostrar(Integer.valueOf(tIDComponente.getText()));
				//Collection<Object> list = saR.mostrar(reparacion);
				//Collection<TEmplea> emplea = (Collection<TEmplea>) list.toArray()[2];
				TTrabaja tTrabaja;
				try {
					tTrabaja = new TTrabaja(
							0, 
							Integer.valueOf(tIDComponente.getText()), 
							Integer.valueOf(thora.getText()));
					cTrabaja.add(tTrabaja);
					
					dtm.addRow(new Object[]{
							tIDComponente.getText(),
							thora.getText()
					});
				} catch (NumberFormatException ex){
					tTrabaja = new TTrabaja(-1);
				}

				
			}
		});
				
		aceptarM.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				//FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
				Collection<Object> rep = new LinkedList<Object>();;
				rep.add(tReparacion);
				rep.add(cEmplea);
				rep.add(cTrabaja);
				Controlador.obtenerInstancia().accion(Eventos.ALTA_REPARACION, rep);
			}
		});		
		cancelarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}

}
