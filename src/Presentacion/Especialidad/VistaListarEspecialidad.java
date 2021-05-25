package Presentacion.Especialidad;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Especialidad.TEspecialidad;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarEspecialidad extends JFrame implements Vista {
	
	public VistaListarEspecialidad()
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						Controlador.obtenerInstancia().accion(Eventos.LISTAR_ESPECIALIDAD, null);
					}
				});
	}
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_ESPECIALIDAD_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TEspecialidad>) datos), "Listar Especialidades", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo listar las especialidades: se ha producido un fallo en la base de datos");
			break;
		case Eventos.RES_LISTAR_ESPECIALIDAD_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar las especialidades: no existe ninguna especialidad");
			break;
			
		}
	}
	private JScrollPane crearTabla(Collection<TEspecialidad> lista) {
		String[] colNames = {"ID", "Tipo"};
		String[][] aux = new String[lista.size()][colNames.length];		
		int i = 0;
		for(TEspecialidad especialidad : lista) {
			aux[i][0] = Integer.toString(especialidad.getId()); 
			aux[i][1] = especialidad.getTipo();
			i++;
		}
		JTable tabla = new JTable(new DefaultTableModel(aux, colNames));
		return new JScrollPane(tabla);
	}
}