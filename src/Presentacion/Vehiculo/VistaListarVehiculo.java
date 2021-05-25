package Presentacion.Vehiculo;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Especialidad.TEspecialidad;
import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarVehiculo extends JFrame implements Vista {
	
	public VistaListarVehiculo() {
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				Controlador.obtenerInstancia().accion(Eventos.LISTAR_VEHICULO, null);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_VEHICULO_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TVehiculo>) datos), "Listar Vehiculos", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.RES_LISTAR_VEHICULO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los vehiculos: no existe ningun vehiculo");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TVehiculo> lista) {
		String[] colNames = {"ID", "ID Cliente", "Modelo", "Matricula"};
		int i = 0;
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		String[][] aux = new String[lista.size()][colNames.length];		
		for(TVehiculo vehiculo : lista) {
			int j = 0;
			aux[i][j] = Integer.toString(vehiculo.getId()); j++;
			aux[i][j] = Integer.toString(vehiculo.getIdCliente()); j++;
			aux[i][j] = vehiculo.getModelo(); j++;
			aux[i][j] = vehiculo.getMatricula(); j++;
			i++;
		}
		JTable tabla = new JTable(new DefaultTableModel(aux, colNames));
		tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
		JScrollPane p = new JScrollPane(tabla);
		p.setPreferredSize(new Dimension(500, 300));
		return p;
	}

}
