package Presentacion.Componente;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Componente.TComponente;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarComponentesProveedor extends JFrame implements Vista{

	public VistaMostrarComponentesProveedor() {
		setTitle("Listar Componente por Proveedor");
		JPanel panel = new JPanel();
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		JLabel lID = new JLabel("ID:");
		final JTextField tID = new JTextField(20);
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		panel.setPreferredSize(new Dimension(275, 70));
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				panel.removeAll();
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_COMPONENTE_PROVEEDOR, tID.getText());
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.COMPONENTE);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_MOSTRAR_COMPONENTE_PROVEEDOR_OK:
			Collection<TComponente> lista = (Collection<TComponente>) datos;
			crearTabla(lista, datos);
			break;
		case Eventos.RES_MOSTRAR_COMPONENTE_PROVEEDOR_NE:
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
		add(p);
		panel.add(p);
		this.setSize(600,450);		
		pack();
		this.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, p, "Listar Componentes de un Proveedor", JOptionPane.DEFAULT_OPTION);
	}

}
