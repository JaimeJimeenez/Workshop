package Presentacion.Componente;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Componente.TComponente;
import Presentacion.Vista;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaListarComponente extends JFrame implements Vista {
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_LISTAR_COMPONENTE_OK:
			Collection<TComponente> lista = (Collection<TComponente>) datos;
			crearTabla(lista, datos);
			FactoriaVistas.obtenerInstancia().crearVista(Eventos.TALLER);
			break;
		case Eventos.RES_LISTAR_COMPONENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: no existe ningun componente");
			break;
		}
	}
	
	private void crearTabla(Collection<TComponente> lista, Object datos) {
		String[] colNames = {"ID", "ID Proveedor", "Marca", "Precio","Modelo","Stock"};
		int i = 0;
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		JTable tabla = new JTable();
		String[][] aux = new String[lista.size()][colNames.length];		
		for(TComponente componente : lista) {
			int j = 0;
			aux[i][j] = Integer.toString(componente.getId()); j++;
			aux[i][j] = Integer.toString(componente.getIdProveedor()); j++;
			aux[i][j] = componente.getMarca(); j++;
			aux[i][j] = Float.toString(componente.getPrecio()); j++;
			aux[i][j] = componente.getModelo(); j++;
			aux[i][j] = Integer.toString(componente.getStock()); j++;
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
		tabla.getColumnModel().getColumn(1).setPreferredWidth(90);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(250);
		JScrollPane p = new JScrollPane(tabla);
		add(p);
		p.setPreferredSize(new Dimension(750, 400));
		panel.add(p);
		this.setSize(650,450);		
		pack();
		this.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, p, "Listar Componente", JOptionPane.DEFAULT_OPTION);
	
	}

}

	
