package Negocio.Reparacion;

import java.util.ArrayList;
import java.util.Collection;

import Integracion.Componente.DAOComponente;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Mecanico.DAOMecanico;
import Integracion.Reparacion.DAOReparacion;
import Negocio.DataCorrect;
import Negocio.Componente.TComponente;
import Negocio.Mecanico.TMecanico;

public class SAReparacionImp implements SAReparacion {

	@Override
	public int alta(TReparacion tReparacion, Collection<TEmplea> tEmplea, Collection<TTrabaja> tTrabaja) {

		if (tReparacion.getId() <= 0)
			return 0;
		
		if (!DataCorrect.stringCorrecto(tReparacion.getAveria()))
			return 0;
		
		if (!DataCorrect.stringCorrecto(tReparacion.getFechaInicio()))
			return 0;
		
		if (!DataCorrect.stringCorrecto(tReparacion.getFechaSalida()))
			return 0;
		
		if (tReparacion.getIdVehiculo() <= 0)
			return 0;
		
		if (tReparacion.getPresupuesto() <= 0)
			return 0;
		
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		TReparacion leido = dao.leerPorDatos(tReparacion);
		int resultado = -1;
		if (leido == null)
			resultado = dao.alta(tReparacion);
		else if (!leido.isActivo())
			resultado = dao.reactivar(tReparacion);
		
		for (TTrabaja t: tTrabaja){
			anyadirMecanicoReparacion(t);
		}
		
		for (TEmplea t: tEmplea){
			anyadirComponente(t);
		}
		
		return resultado;
		
	}

	@Override
	public int baja(int id) {
		if (id <= 0)
			return 0;

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(id);

		if (leido == null)
			return -1;

		TReparacion rep = (TReparacion) leido.toArray()[0];

		if (!rep.isActivo())
			return -1;

		return dao.baja(id);
	}

	@Override
	public Collection<Object> mostrar(int id) {
		ArrayList<Object> result = new ArrayList<Object>();

		if (id <= 0) {
			result.add(new TReparacion(0));

			return result;
		}

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(id);

		if (leido == null) {
			result.add(new TReparacion(-1));

			return result;
		}

		Object[] rep = leido.toArray();
		TReparacion tReparacion = (TReparacion) rep[0];

		if (!tReparacion.isActivo()) {
			result.add(new TReparacion(-1));

			return result;
		}

		return leido;
	}

	@Override
	public int modificar(TReparacion tReparacion) {
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tReparacion.getId());

		if (leido == null)
			return -1;

		Object[] rep = leido.toArray();
		int resultado;
		TReparacion reparacion = (TReparacion) rep[0];

		if (!reparacion.isActivo())
			resultado = -1;
		else
			resultado = dao.modificar(tReparacion);

		return resultado;
	}

	@Override
	public Collection<TReparacion> listar() {
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<TReparacion> resultado = dao.listar();
		
		return resultado;
	}

	@Override
	public TEmplea anyadirComponente(TEmplea tEmplea) {
		if (tEmplea.getIdComponente() <= 0 || tEmplea.getIdReparacion() <= 0)
			return new TEmplea(0);

		if (tEmplea.getPrecio() < 0 || tEmplea.getCantidad() <= 0)
			return new TEmplea(0);

		DAOComponente daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente componente = daoComponente.mostrar(tEmplea.getIdComponente());

		if (componente == null || !componente.isActivo())
			return new TEmplea(-1);

		componente.setStock(componente.getStock() - tEmplea.getCantidad());
		
		if (componente.getStock() < 0)
			return new TEmplea(0);

		daoComponente.modificar(componente);
		
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null)
			return new TEmplea(-2);

		Object[] leidoArray = leido.toArray();

		if (!((TReparacion) leidoArray[0]).isActivo())
			return new TEmplea(-2);
		
		Collection<TEmplea> componentes = (Collection<TEmplea>) leidoArray[1];

		for (TEmplea c : componentes) {
			if (tEmplea.getIdComponente() == c.getIdComponente() && tEmplea.getIdReparacion() == c.getIdReparacion()) // TODO Sobreescribir equals
				return new TEmplea(-3);
		}

		return dao.altaComponente(tEmplea);
	}

	@Override
	public TEmplea borrarComponenteReparacion(TEmplea tEmplea) {
		if (tEmplea.getIdReparacion() <= 0 || tEmplea == null)
			return new TEmplea(0);

		DAOComponente daoComponente = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente componente = daoComponente.mostrar(tEmplea.getIdComponente());

		if (componente == null)
			return new TEmplea(-1);

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null)
			return new TEmplea(-2);

		Object[] leidoArray = leido.toArray();

		if (!((TReparacion) leidoArray[0]).isActivo())
			return new TEmplea(-2);

		Collection<TEmplea> componentes = (Collection<TEmplea>) leidoArray[1];

		for (TEmplea c : componentes) {
			if (tEmplea.getIdComponente() == c.getIdComponente() && tEmplea.getIdReparacion() == c.getIdReparacion()) // TODO
																														// Sobreescribir
																														// equals
				return dao.bajaComponente(tEmplea);
		}

		return new TEmplea(-1);
	}

	@Override
	public TTrabaja anyadirMecanicoReparacion(TTrabaja tTrabaja) {
		if (tTrabaja.getIdMecanico() <= 0 || tTrabaja.getIdReparacion() <= 0 || tTrabaja.getHora() <= 0
				|| tTrabaja.getHora() >= 80)
			return new TTrabaja(0);

		DAOMecanico daoM = FactoriaIntegracion.obtenerInstancia().crearMecanico();
		TMecanico leidoM = daoM.mostrar(tTrabaja.getIdMecanico());

		if (leidoM == null || !leidoM.isActivo())
			return new TTrabaja(-1);

		DAOReparacion daoR = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leidoT = daoR.mostrar(tTrabaja.getIdReparacion());

		if (leidoT == null)
			return new TTrabaja(-2);

		Object[] rep = leidoT.toArray();
		Collection<TTrabaja> t = (Collection<TTrabaja>) rep[2];

		if (!((TReparacion) rep[0]).isActivo())
			return new TTrabaja(-2);

		for (TTrabaja leido : t)
			if (leido.getIdMecanico() == tTrabaja.getIdMecanico() && leido.getIdReparacion() == tTrabaja.getIdReparacion())
				return new TTrabaja(-3);

		return daoR.altaMecanico(tTrabaja);
	}

	@Override
	public TTrabaja borrarMecanicoReparacion(TTrabaja tTrabaja) {
		if (tTrabaja.getIdReparacion() <= 0 || tTrabaja == null)
			return new TTrabaja(0);

		DAOMecanico daoMecanico = FactoriaIntegracion.obtenerInstancia().crearMecanico();
		TMecanico mecanico = daoMecanico.mostrar(tTrabaja.getIdMecanico());

		if (mecanico == null || !mecanico.isActivo())
			return new TTrabaja(-1);

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tTrabaja.getIdReparacion());

		if (leido == null)
			return new TTrabaja(-2);

		Object[] leidoArray = leido.toArray();

		if (!((TReparacion) leidoArray[0]).isActivo())
			return new TTrabaja(-2);

		Collection<TTrabaja> mecanicos = (Collection<TTrabaja>) leidoArray[1];

		for (TTrabaja leidoM : mecanicos)
			if (leidoM.getIdMecanico() == tTrabaja.getIdReparacion() && leidoM.getIdReparacion() == tTrabaja.getIdMecanico())
				return dao.bajaMecanico(tTrabaja);

		return new TTrabaja(-1);
	}

	@Override
	public TEmplea modificarComponenteReparacion(TEmplea tEmplea) {

		if (tEmplea.getIdReparacion() <= 0 || tEmplea == null)
			return new TEmplea(0);

		DAOComponente daoC = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente componente = daoC.mostrar(tEmplea.getIdComponente());

		if (componente == null || !componente.isActivo()) {
			return new TEmplea(-1);
		}
		
		if (tEmplea.getPrecio() <= 0){
			tEmplea.setPrecio(componente.getPrecio());
		}

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null) {
			return new TEmplea(-2);
		}

		Object[] reparacion = leido.toArray();
		Collection<TEmplea> rep = (Collection<TEmplea>) reparacion[1];

		if (!((TReparacion) reparacion[0]).isActivo()) {
			return new TEmplea(-1);
		} else {
			return dao.modificarComponente(tEmplea);
		}
	}

	@Override
	public TTrabaja modificarMecanicoReparacion(TTrabaja tTrabaja) {
		if (tTrabaja.getHora() <= 0 || tTrabaja.getHora() >= 80)
			return new TTrabaja(0);

		DAOMecanico daoM = FactoriaIntegracion.obtenerInstancia().crearMecanico();
		TMecanico leidoM = daoM.mostrar(tTrabaja.getIdMecanico());

		if (leidoM == null || !leidoM.isActivo())
			return new TTrabaja(-1);

		DAOReparacion daoR = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leidoT = daoR.mostrar(tTrabaja.getIdReparacion());

		if (leidoT == null)
			return new TTrabaja(-2);

		Object[] rep = leidoT.toArray();
		Collection<TTrabaja> t = (Collection<TTrabaja>) rep[2];

		if (!((TReparacion) rep[0]).isActivo())
			return new TTrabaja(-2);

		for (TTrabaja leido : t)
			if (leido.getIdMecanico() == tTrabaja.getIdReparacion() && leido.getIdReparacion() == tTrabaja.getIdMecanico())
				return new TTrabaja(-3);

		return daoR.modificarMecanico(tTrabaja);
	}

	@Override
	public Collection<TReparacion> mostrarReparacionVehiculo(int idVehiculo) {
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		return dao.mostrarReparacionesVehiculo(idVehiculo);
	}

}
