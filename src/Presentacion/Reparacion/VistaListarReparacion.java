package Presentacion.Reparacion;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Reparacion.TReparacion;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

public class VistaListarReparacion extends JFrame implements Vista{

	private static final long serialVersionUID = 1L;

	public VistaListarReparacion()
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						Controlador.obtenerInstancia().accion(Eventos.LISTAR_REPARACION, null);
					}
				});
	}

	
	
	
	public void actualizar (int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_REPARACION_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TReparacion>) datos), "Listar Reparacion", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo listar las reparaciones: se ha producido un fallo en la base de datos");
		case Eventos.RES_LISTAR_REPARACION_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los vehiculos: no existe ningun vehiculo");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TReparacion> lRepara) {
		String[] colNames = {"ID", "ID Vehiculo", "Fecha Inicio", "Fecha Salida", "Averia", "Presupuesto"};
		int i = 0;
		
		String[][] aux = new String[lRepara.size()][colNames.length];		
		
		for(TReparacion reparacion : lRepara) {
			aux[i][0] = Integer.toString(reparacion.getId());
			aux[i][1] = Integer.toString(reparacion.getIdVehiculo());
			aux[i][2] = reparacion.getFechaInicio(); 
			aux[i][3] = reparacion.getFechaSalida(); 
			aux[i][4] = reparacion.getAveria();
			aux[i][5] =  Float.toString(reparacion.getPresupuesto());
			i++;
		}
		
		JTable tabla = new JTable( new DefaultTableModel(aux, colNames));
		return new JScrollPane(tabla);
	}


}
