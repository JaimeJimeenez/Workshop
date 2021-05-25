package Presentacion.Vehiculo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarVehiculosCliente extends JFrame implements Vista{

	public VistaMostrarVehiculosCliente() {
		setTitle("Listar Vehiculos de un Cliente");
		JPanel panel = new JPanel();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		JLabel lID = new JLabel("ID Cliente:");
		final JTextField tID = new JTextField(20);
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		panel.setPreferredSize(new Dimension(275, 70));
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				panel.removeAll();
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_VEHICULO_CLIENTE, tID.getText());
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
		switch (evento) {
		case Eventos.RES_MOSTRAR_VEHICULO_CLIENTE_OK:
			Collection<TVehiculo> lista = (Collection<TVehiculo>) datos;
			crearTabla(lista, datos);
			break;
		case Eventos.RES_MOSTRAR_VEHICULO_CLIENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los vehiculos: no existe ningun vehiculo.");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
	
	private void crearTabla(Collection<TVehiculo> lista, Object datos) {
		String[] colNames = {"ID", "ID Cliente", "Modelo", "Matricula"};
		int i = 0;
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		JTable tabla = new JTable();
		String[][] aux = new String[lista.size()][colNames.length];		
		for(TVehiculo vehiculo : lista) {
			int j = 0;
			aux[i][j] = Integer.toString(vehiculo.getId()); j++;
			aux[i][j] = Integer.toString(vehiculo.getIdCliente()); j++;
			aux[i][j] = vehiculo.getModelo(); j++;
			aux[i][j] = vehiculo.getMatricula(); j++;
			i++;
		}
		DefaultTableModel tmodel = new DefaultTableModel(aux, colNames) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tabla.setModel(tmodel);	
		JScrollPane p = new JScrollPane(tabla);
		add(p);
		//p.setPreferredSize(new Dimension(750, 400));
		add(p);
		panel.add(p);
		this.setSize(600,450);		
		pack();
		this.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, p, "Listar Vehiculos de un Cliente", JOptionPane.DEFAULT_OPTION);
	}


}
