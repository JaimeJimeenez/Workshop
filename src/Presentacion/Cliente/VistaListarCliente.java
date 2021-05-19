package Presentacion.Cliente;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TParticular;
import Presentacion.Vista;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarCliente extends JFrame implements Vista {
		
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_CLIENTE_OK:
			Collection<TCliente> lista = (Collection<TCliente>) datos;
			crearTabla(lista, datos);
			FactoriaVistas.obtenerInstancia().crearVista(Eventos.TALLER);
			break;
		case Eventos.RES_LISTAR_CLIENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los clientes: no existe ningun cliente");
			break;
		}
	}
	
	private void crearTabla(Collection<TCliente> lista, Object datos) {
		String[] colNames = {"ID", "Tipo", "DNI / NIF","Nombre","Direccion / Web","Telefono"};
		int i = 0;
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		JTable tabla = new JTable();
		String[][] aux = new String[lista.size()][colNames.length];		
		for(TCliente cliente : lista) {
			int j = 0;
			aux[i][j] = Integer.toString(cliente.getId()); j++;
			if(cliente instanceof TParticular) aux[i][j] = "Particular"; 
			else aux[i][j] = "Empresa";
			j++;
			aux[i][j] = cliente.getNif(); j++;
			aux[i][j] = cliente.getNombre(); j++;
			if(cliente instanceof TParticular) aux[i][j] = ((TParticular) cliente).getDireccion(); 
			else ((TEmpresa) cliente).getPaginaWeb(); 
			j++;
			aux[i][j] = cliente.getTelefono();
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
		tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
		JScrollPane p = new JScrollPane(tabla);
		add(p);
		panel.add(p);
		p.setPreferredSize(new Dimension(800, 300));		
		pack();
		this.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, p, "Listar Clientes", JOptionPane.DEFAULT_OPTION);
	}
	
}
