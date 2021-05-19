package Presentacion.Reparacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Reparacion.TEmplea;
import Negocio.Reparacion.TReparacion;
import Negocio.Reparacion.TTrabaja;
import Negocio.Vehiculo.TVehiculo;
import Presentacion.Vista;
import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;
import Presentacion.FactoriaVistas.FactoriaVistas;

public class VistaMostrarReparacion  extends JFrame implements Vista{

	public VistaMostrarReparacion()
	{
		super("Mostrar Reparacion");
		JPanel panel = new JPanel();
		JLabel lID = new JLabel("ID:");
		final JTextField tID = new JTextField(20);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		panel.add(lID);
		panel.add(tID);
		panel.add(aceptar);
		panel.add(cancelar);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controlador.obtenerInstancia().accion(Eventos.MOSTRAR_REPARACION, tID.getText());
			}
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaVistas.obtenerInstancia().crearVista(Eventos.REPARACION);
			}
		});
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Eventos.RES_MOSTRAR_REPARACION_OK://TR, TE, TT
			ArrayList<Object> reparacion = (ArrayList<Object>) datos;
			TReparacion tReparacion = (TReparacion) reparacion.get(0);
			ArrayList<TEmplea>tEmpleaList = (ArrayList<TEmplea>) reparacion.get(1);
			ArrayList<TTrabaja> tTrabajaList = (ArrayList<TTrabaja>) reparacion.get(2);
			String mensaje = "Reparacion: \n\nID: " + tReparacion.getId()
			+ "\nAveria: " + tReparacion.getAveria()
			+ "\nFecha de inicio: " + tReparacion.getFechaInicio()
			+ "\nFecha de Salida: " + tReparacion.getFechaSalida()
			+ "\nPresupuesto: " + tReparacion.getPresupuesto()
			+ "\n\nID Vehiculo: " + tReparacion.getIdVehiculo()
			+ "\n\nMecanicos:\n";
			for(TTrabaja tTrabaja:tTrabajaList )
			{
				mensaje += "\nID: " + tTrabaja.getIdMecanico() + "\nHoras: " + tTrabaja.getHora();
			}
			mensaje += "\n\nComponentes:\n";
			int suma = 0;
			for(TEmplea tEmplea: tEmpleaList)
			{
				suma +=tEmplea.getCantidad()*tEmplea.getPrecio();
				mensaje += "\nID: " + tEmplea.getIdComponente() + "\nCantidad: " + tEmplea.getCantidad() + "\nPrecio Unitario: " + tEmplea.getPrecio()
				+ "\nPrecio Total: " + tEmplea.getCantidad()*tEmplea.getPrecio();
			}
			mensaje += "\n\nPrecio de todos los componentes: " + suma;
			JOptionPane.showMessageDialog(null, mensaje);
			break;
		case Eventos.RES_MOSTRAR_REPARACION_DI:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la reparación: datos incorrectos");
			break;
		case Eventos.RES_MOSTRAR_REPARACION_NE:
			JOptionPane.showMessageDialog(null, "No se pudo mostrar la reparación: ID no encontrado");
			break;
		}
		
	}

}
