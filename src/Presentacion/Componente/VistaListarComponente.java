package Presentacion.Componente;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Componente.TComponente;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

public class VistaListarComponente extends JFrame implements Vista {
	
	public VistaListarComponente()
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() {
						Controlador.obtenerInstancia().accion(Eventos.LISTAR_COMPONENTE, null);
					}
				});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_COMPONENTE_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TComponente>) datos), "Listar Componente", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: se ha producido un fallo en la base de datos");
			break;
		case Eventos.RES_LISTAR_COMPONENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: no existe ningun componente");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TComponente> lista) {
		String[] colNames = {"ID", "ID Proveedor", "Marca", "Precio","Modelo","Stock"};
		int i = 0;
		String[][] aux = new String[lista.size()][colNames.length];		
		for(TComponente componente : lista) {
			aux[i][0] = Integer.toString(componente.getId()); 
			aux[i][1] = Integer.toString(componente.getIdProveedor()); 
			aux[i][2] = componente.getMarca(); 
			aux[i][3] = Float.toString(componente.getPrecio()); 
			aux[i][4] = componente.getModelo();
			aux[i][5] = Integer.toString(componente.getStock()); 
			i++;
		}
		DefaultTableModel tmodel = new DefaultTableModel(aux, colNames);
		JTable tabla = new JTable(tmodel);
		return new JScrollPane(tabla);
	}

}

	
