package Presentacion.Cliente;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TParticular;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

public class VistaListarCliente extends JFrame implements Vista {
	
	public VistaListarCliente() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				Controlador.obtenerInstancia().accion(Eventos.LISTAR_CLIENTE, null);
			}
		});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_CLIENTE_OK:
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TCliente>)datos), "Listar Cliente", 
					JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo listar las especialidades: se ha producido un fallo en la base de datos");
			break;
		case Eventos.RES_LISTAR_CLIENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los clientes: no existe ningun cliente");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TCliente> lista) {
		String[] colNames = {"ID", "Tipo", "DNI / NIF","Nombre","Direccion / Web","Telefono"};
		int i = 0;
		String[][] aux = new String[lista.size()][colNames.length];
		for (TCliente cliente : lista) {
			
			aux[i][0] = Integer.toString(cliente.getId());
			if (cliente instanceof TEmpresa) {
				aux[i][1] = "E";
				TEmpresa t = (TEmpresa) cliente;
				aux[i][4] = t.getPaginaWeb();
			}
			else {
				aux[i][1] = "P";
				TParticular p = (TParticular) cliente;
				aux[i][4] = p.getDireccion();
			}
			aux[i][2] = cliente.getNif();
			aux[i][3] = cliente.getNombre();
			aux[i][5] = cliente.getTelefono();
			i++;
		}
		JTable tabla = new JTable(new DefaultTableModel(aux, colNames));
		tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(175);
		JScrollPane p = new JScrollPane(tabla);
		p.setPreferredSize(new Dimension(750, 300));
		return p;
	}
	
}
