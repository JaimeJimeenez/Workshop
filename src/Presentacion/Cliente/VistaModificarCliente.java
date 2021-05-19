package Presentacion.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TParticular;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaModificarCliente extends JFrame implements Vista {

	public VistaModificarCliente() {
		JFrame f = new JFrame("Alta Cliente");
        JButton particular = new JButton("Particular");
        JButton empresa = new JButton("Empresa");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        JTextField id = new JTextField(20);
        JTextField nombre = new JTextField(35);
        JTextField direccion = new JTextField(60);
        JTextField web = new JTextField(60);
        JTextField nif = new JTextField(9);
        JTextField dni = new JTextField(9);
        JTextField telf = new JTextField(9);
        JLabel lId = new JLabel("ID:");
        JLabel lNombre = new JLabel("Nombre:");
        JLabel lDir = new JLabel("Direccion:");
        JLabel lWeb = new JLabel("Pagina Web:");
        JLabel lNif = new JLabel("NIF:");
        JLabel lDni = new JLabel("DNI:");
        JLabel lTelf = new JLabel("Telefono:");
        JPanel cards = new JPanel();
        
        f.setContentPane(crearPanel(particular, empresa, cards));
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setSize(800, 200);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        particular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();
				cards.add(lId);
				cards.add(id);
				cards.add(lNombre);
				cards.add(nombre);
				cards.add(lTelf);
				cards.add(telf);
				cards.add(lDni);
				cards.add(dni);
				cards.add(lDir);
				cards.add(direccion);
				cards.add(aceptar);
				cards.add(cancelar);
				SwingUtilities.updateComponentTreeUI(f);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						TCliente tParticular = new TParticular(nombre.getText(), Integer.parseInt(id.getText()), telf.getText(), dni.getText(), direccion.getText(), true);
						Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_CLIENTE, tParticular);
					}
				});
				
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						FactoriaVistas.obtenerInstancia().crearVista(Eventos.CLIENTE);
					}
				});
			}
        });
        
        empresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.removeAll();
				cards.add(lId);
				cards.add(id);
				cards.add(lNombre);
				cards.add(nombre);
				cards.add(lTelf);
				cards.add(telf);
				cards.add(lNif);
				cards.add(nif);
				cards.add(lWeb);
				cards.add(web);
				cards.add(aceptar);
				cards.add(cancelar);
				SwingUtilities.updateComponentTreeUI(f);
				
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						TCliente tEmpresa = new TEmpresa(nombre.getText(), telf.getText(), Integer.parseInt(lId.getText()), nif.getText(), web.getText(), true);
						Controlador.obtenerInstancia().accion(Eventos.MODIFICAR_CLIENTE, tEmpresa);
					}
				});
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.setVisible(false);
						FactoriaVistas.obtenerInstancia().crearVista(Eventos.CLIENTE);
					}
				});
			}
        });
	}
		
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Eventos.RES_MODIFICAR_COMPONENTE_OK:
			Integer id= (Integer) datos;
			JOptionPane.showMessageDialog(null,"Cliente modificado con ID: " + id.intValue());
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_DI:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el cliente: datos incorrectos");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_NE:
			JOptionPane.showMessageDialog(null,"No se pudo modificar el cliente: ID no encontrado");
			break;
		case Eventos.RES_MODIFICAR_COMPONENTE_RE:
			JOptionPane.showMessageDialog(null,"No se pudo crear el cliente: datos repetidos");
			break;
		}
	}
	
	private JPanel crearPanel(JButton particular, JButton empresa, JPanel cards) {
        JPanel vista = new JPanel(new BorderLayout(10,20));
        vista.setBorder(new TitledBorder("Seleccion de Tipo de Cliente para su moficacion"));
        cards.setBorder(new TitledBorder("Datos"));
        vista.add(cards);

        JPanel lineStart = new JPanel(new GridLayout(0, 1, 5, 5));
        lineStart.setBorder(new TitledBorder("Tipo de Cliente"));
        vista.add(lineStart, BorderLayout.LINE_START);
        lineStart.add(particular);
        lineStart.add(empresa);

        return vista;
    }
	
}
