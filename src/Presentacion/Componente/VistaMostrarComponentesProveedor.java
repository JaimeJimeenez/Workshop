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
			JOptionPane.showMessageDialog(null, crearTabla((Collection<TComponente>) datos), "Listar Componentes de un Proveedor", JOptionPane.DEFAULT_OPTION);
			break;
		case Eventos.RES_MOSTRAR_COMPONENTE_PROVEEDOR_NE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: el proveedor no tiene ningun componente");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_IE:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: ID proveedor no encontrado");
			break;
		case Eventos.EXCEPCION_SQL:
			JOptionPane.showMessageDialog(null, "No se pudo listar los componentes: se ha producido un fallo en la base de datos");
			break;
		}
	}
	
	private JScrollPane crearTabla(Collection<TComponente> lista) {
		String[] colNames = {"ID", "ID Proveedor", "Marca", "Precio","Modelo","Stock"};
		int i = 0;
		JPanel panel = new JPanel();
		this.setContentPane(panel);
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
