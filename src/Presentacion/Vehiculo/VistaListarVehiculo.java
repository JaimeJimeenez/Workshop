package Presentacion.Vehiculo;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarVehiculo extends JFrame implements Vista{
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_VEHICULO_OK:
			Collection<TVehiculo> lista = (Collection<TVehiculo>) datos;
			crearTabla(lista, datos);
			FactoriaVistas.obtenerInstancia().crearVista(Eventos.TALLER);
			break;
		case Eventos.RES_LISTAR_VEHICULO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los vehiculos: no existe ningun vehiculo");
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
		panel.add(p);
		this.setSize(650,450);		
		pack();
		this.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, p, "Listar Vehiculos", JOptionPane.DEFAULT_OPTION);
	}

}
