package Presentacion.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Cliente.TEmpresa;
import Negocio.Cliente.TParticular;
import Negocio.Especialidad.TEspecialidad;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarCliente extends JFrame implements Vista{

	public VistaMostrarCliente() {
		super("Mostrar Cliente");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_CLIENTE, tID.getText());
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.CLIENTE);
			}
		});
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_MOSTRAR_CLIENTE_OK:
			if(datos instanceof TParticular) {
				TParticular cliente = (TParticular) datos;
				JOptionPane.showMessageDialog(null, "Cliente:\n\nID: " + cliente.getId() + "\nDNI: " + cliente.getNif() + "\nNombre: " + 
				cliente.getNombre() + "\nDireccion: " + cliente.getDireccion() + "\nTelefono: " + cliente.getTelefono());
			}
			else { 
				TEmpresa cliente = (TEmpresa) datos;
				JOptionPane.showMessageDialog(null, "Cliente:\n\nID: " + cliente.getId() + "\nNIF: " + cliente.getNif() + "\nNombre: " + 
						cliente.getNombre() + "\nPagina Web: " + cliente.getPaginaWeb() + "\nTelefono: " + cliente.getTelefono());
			}
			break;
		case Eventos.RES_MOSTRAR_CLIENTE_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el cliente: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_CLIENTE_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar el cliente: ID no encontrado");
			break;
		}
		
	}

}
