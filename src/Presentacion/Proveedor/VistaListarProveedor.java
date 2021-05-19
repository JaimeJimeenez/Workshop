package Presentacion.Proveedor;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Proveedor.TProveedor;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarProveedor extends JFrame implements Vista {

	public VistaListarProveedor()
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						Controlador.obtenerInstancia().accion(Eventos.LISTAR_PROVEEDOR, null);
					}
				});
	}

	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_PROVEEDOR_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TProveedor>) datos), "Listar Proveedores", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.RES_LISTAR_PROVEEDOR_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los proveedores: no existe ningún proveedor");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TProveedor> lista) {
		String[] colNames = {"ID", "NIF", "Direccion", "Telefono"};
		String[][] aux = new String[lista.size()][colNames.length];		
		int i = 0;
		for(TProveedor proveedor : lista) {
			aux[i][0] = Integer.toString(proveedor.getId());
			aux[i][1] = proveedor.getNIF(); 
			aux[i][2] = proveedor.getDireccion(); 
			aux[i][3] = proveedor.getTelefono();
			i++;
		}
		JTable tabla = new JTable(new DefaultTableModel(aux, colNames));
		return new JScrollPane(tabla);
	}
	
}
