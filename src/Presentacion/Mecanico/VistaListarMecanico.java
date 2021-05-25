package Presentacion.Mecanico;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Mecanico.TMecanico;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

public class VistaListarMecanico extends JFrame implements Vista {

	public VistaListarMecanico() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controlador.obtenerInstancia().accion(Eventos.LISTAR_MECANICO, null);
			}
		});
	}

	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_MECANICO_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TMecanico>) datos), "Listar Mecanicos",
					JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.RES_LISTAR_MECANICO_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los mecánicos: no existe ningún mecánico");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la conexión a la base de datos.");
			break;
		}
	}

	private JScrollPane crearTabla(Collection<TMecanico> lista) {

		String[] colNames = { "ID", "DNI", "Nombre", "Telefono", "Sueldo", "Cuenta", "ID Especialidad" };
		String[][] aux = new String[lista.size()][colNames.length];
		int i = 0;
		for (TMecanico mecanico : lista) {
			aux[i][0] = Integer.toString(mecanico.getId());
			aux[i][1] = mecanico.getDNI();
			aux[i][2] = mecanico.getNombre();
			aux[i][3] = mecanico.getTelefono();
			aux[i][4] = Float.toString(mecanico.getSueldo());
			aux[i][5] = mecanico.getCuenta();
			aux[i][6] = Integer.toString(mecanico.getIdEspecialidad());
			i++;
		}
		JTable tabla = new JTable(new DefaultTableModel(aux, colNames));
		tabla.getColumnModel().getColumn(5).setPreferredWidth(175);
		JScrollPane p = new JScrollPane(tabla);
		p.setPreferredSize(new Dimension(750, 300));
		return p;
	}

}