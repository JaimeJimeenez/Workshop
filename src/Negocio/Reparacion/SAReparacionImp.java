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

		if (!DataCorrect.stringCorrecto(tReparacion.getAveria()))
			return 0;
		
		if (!DataCorrect.fechaCorrecta(tReparacion.getFechaInicio()))
			return 0;
		
		if (!DataCorrect.fechaCorrecta(tReparacion.getFechaSalida()))
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
		
		if (leido.getId() == -4)
			return -4;
		
		if (resultado == -4)
			return -4;
		
		if (leido != null && !leido.isActivo())
			resultado = dao.reactivar(leido);
		
		if (resultado == -4)
			return -4;
		
		if (tTrabaja != null){
			for (TTrabaja t: tTrabaja){
				anyadirMecanicoReparacion(new TTrabaja(resultado, t.getIdMecanico(), t.getHora()));
			}
		}
		
		if (tEmplea != null){
			for (TEmplea t: tEmplea){
				anyadirComponente(new TEmplea(resultado, t.getIdComponente(), t.getPrecio(), t.getCantidad()));
			}
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
		
		if (rep.getId() == -4){
			return -4;
		}

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
		
		if (tReparacion.getId() == -4){
			Collection<Object> res = null;
			res.add(new TReparacion(-4));
			return res;
		}
		
		if (!tReparacion.isActivo()) {
			result.add(new TReparacion(-1));

			return result;
		}

		return leido;
	}

	@Override
	public int modificar(TReparacion tReparacion) {
		
		if (tReparacion.getIdVehiculo() <= 0)
			return 0;
		
		if (tReparacion.getId() <= 0)
			return 0;
		
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tReparacion.getId());

		if (leido == null)
			return -1;

		Object[] rep = leido.toArray();
		int resultado;
		TReparacion reparacion = (TReparacion) rep[0];
		
		if (reparacion.getId() == -4){
			return -4;
		}
		
		if (!reparacion.isActivo())
			resultado = -1;
		else{
			
			if (tReparacion.getAveria() == ""){
				tReparacion.setAveria(reparacion.getAveria());
			}
			if (tReparacion.getFechaInicio() == ""){
				tReparacion.setFechaInicio(reparacion.getFechaInicio());
			}
			if (tReparacion.getFechaSalida() == ""){
				tReparacion.setFechaSalida(reparacion.getFechaSalida());
			}
			if (tReparacion.getPresupuesto() == 0){
				tReparacion.setPresupuesto(reparacion.getPresupuesto());
			}
			
			if (!DataCorrect.stringCorrecto(tReparacion.getAveria()))
				return 0;
			
			if (!DataCorrect.fechaCorrecta(tReparacion.getFechaInicio()))
				return 0;
			
			if (!DataCorrect.fechaCorrecta(tReparacion.getFechaSalida()))
				return 0;
			
			if (tReparacion.getPresupuesto() <= 0)
				return 0;
			
			
			resultado = dao.modificar(tReparacion);
		}
			

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

		if (componente == null )
			return new TEmplea(-1);
		
		if (componente.getId() == -4){
			return new TEmplea(-4);
		}
		
		if (!componente.isActivo())
			return new TEmplea(-1);
		
		componente.setStock(componente.getStock() - tEmplea.getCantidad());
		
		if (componente.getStock() < 0)
			return new TEmplea(0);

		int mod = daoComponente.modificar(componente);
		
		if (mod == -4)
			return new TEmplea(-4);
		
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null)
			return new TEmplea(-2);

		Object[] leidoArray = leido.toArray();
		TReparacion repara = (TReparacion) leidoArray[0];
		
		if (repara.getId() == -4)
			return new TEmplea(-4);
		
		if (!repara.isActivo())
			return new TEmplea(-2);
			
		Collection<TEmplea> componentes = (Collection<TEmplea>) leidoArray[1];
			
		for (TEmplea c : componentes) {
			if (tEmplea.getIdComponente() == c.getIdComponente() && tEmplea.getIdReparacion() == c.getIdReparacion()) 
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
		
		if (componente.getId() == -4)
			return new TEmplea(-4);
		
		if (!componente.isActivo())
			return new TEmplea(-1);

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null)
			return new TEmplea(-2);

		Object[] leidoArray = leido.toArray();
		TReparacion r = (TReparacion) leidoArray[0];
		if (r.getId() == -4)
			return new TEmplea(-4);
		
		if (!r.isActivo())
			return new TEmplea(-2);

		Collection<TEmplea> componentes = (Collection<TEmplea>) leidoArray[1];
		
		for (TEmplea c : componentes) {
			if (tEmplea.getIdComponente() == c.getIdComponente() && tEmplea.getIdReparacion() == c.getIdReparacion())															// equals
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

		if (leidoM == null)
			return new TTrabaja(-1);
		
		if (leidoM.getId() == -4)
			return new TTrabaja(-4);
		
		if (!leidoM.isActivo())
			return new TTrabaja(-1);

		DAOReparacion daoR = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leidoT = daoR.mostrar(tTrabaja.getIdReparacion());

		if (leidoT == null)
			return new TTrabaja(-2);

		Object[] rep = leidoT.toArray();
		Collection<TTrabaja> t = null;
		
		if (rep.length > 1)
			t = (Collection<TTrabaja>) rep[2];
		
		TReparacion repara = (TReparacion) rep[0];
		
		if (repara.getId() == -4)
			return new TTrabaja(-4);
		
		if (!repara.isActivo())
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

		if (mecanico == null)
			return new TTrabaja(-1);
		
		if (mecanico.getId() == -4)
			return new TTrabaja(-4);
		
		if (!mecanico.isActivo())
			return new TTrabaja(-4);

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tTrabaja.getIdReparacion());

		if (leido == null)
			return new TTrabaja(-2);

		Object[] leidoArray = leido.toArray();
		TReparacion repara = (TReparacion) leidoArray[0];
		
		if (repara.getId() == -4)
			return new TTrabaja(-4);
		
		if (!repara.isActivo())
			return new TTrabaja(-2);
		Collection<TTrabaja> mecanicos = null;
		if (leidoArray.length > 1){
			mecanicos = (Collection<TTrabaja>) leidoArray[2];
		}
		
		for (TTrabaja leidoM : mecanicos)
			if (leidoM.getIdMecanico() == tTrabaja.getIdMecanico() && leidoM.getIdReparacion() == tTrabaja.getIdReparacion())
				return dao.bajaMecanico(tTrabaja);

		return new TTrabaja(-1);
	}

	@Override
	public TEmplea modificarComponenteReparacion(TEmplea tEmplea) {

		if (tEmplea.getIdReparacion() <= 0 || tEmplea == null)
			return new TEmplea(0);
		
		if (tEmplea.getIdComponente() <= 0){
			return new TEmplea(0);
		}
		if (tEmplea.getCantidad() < 0){
			return new TEmplea(0);
		}
		
		if (tEmplea.getPrecio() < 0){
			return new TEmplea(0);
		}
		
		DAOComponente daoC = FactoriaIntegracion.obtenerInstancia().crearComponente();
		TComponente componente = daoC.mostrar(tEmplea.getIdComponente());

		if (componente == null) {
			return new TEmplea(-1);
		}
		
		if (componente.getId() == -4)
			return new TEmplea(-4);
		
		if (!componente.isActivo())
			return new TEmplea(-1);
		
		if (tEmplea.getPrecio() == 0){
			tEmplea.setPrecio(componente.getPrecio());
		}

		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leido = dao.mostrar(tEmplea.getIdReparacion());

		if (leido == null) {
			return new TEmplea(-2);
		}

		Object[] reparacion = leido.toArray();
		Collection<TEmplea> rep = null;
		if (reparacion.length > 1)
			rep = (Collection<TEmplea>) reparacion[1];
		TReparacion repara = (TReparacion) reparacion[0];
		
		if (repara.getId() == -4)
			return new TEmplea(-4);
		
		if (!repara.isActivo()) {
			return new TEmplea(-1);
		}
		
		for (TEmplea l : rep)
			if (l.getIdReparacion() == tEmplea.getIdReparacion() && l.getIdComponente() == tEmplea.getIdComponente())
				return dao.modificarComponente(tEmplea);
		
		return new TEmplea(-3);
	}

	@Override
	public TTrabaja modificarMecanicoReparacion(TTrabaja tTrabaja) {
		if (tTrabaja.getHora() < 0 || tTrabaja.getHora() >= 80)
			return new TTrabaja(0);
		
		if (tTrabaja.getIdMecanico() <= 0)
			return new TTrabaja(0);
		
		if (tTrabaja.getIdReparacion() <= 0)
			return new TTrabaja(0);
		
		DAOMecanico daoM = FactoriaIntegracion.obtenerInstancia().crearMecanico();
		TMecanico leidoM = daoM.mostrar(tTrabaja.getIdMecanico());

		if (leidoM == null)
			return new TTrabaja(-1);
		
		if (leidoM.getId() == -4)
			return new TTrabaja(-4);
		
		if (!leidoM.isActivo())
			return new TTrabaja(-1);
		
		DAOReparacion daoR = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		Collection<Object> leidoT = daoR.mostrar(tTrabaja.getIdReparacion());

		if (leidoT == null)
			return new TTrabaja(-2);

		Object[] rep = leidoT.toArray();
		TReparacion repara = (TReparacion) rep[0];
		Collection<TTrabaja> t = null;
		if (rep.length > 1)
			t = (Collection<TTrabaja>) rep[2];
		
		if (repara.getId() == -4)
			return new TTrabaja(-4);
		
		if (!repara.isActivo())
			return new TTrabaja(-2);

		for (TTrabaja leido : t)
			if (leido.getIdMecanico() == tTrabaja.getIdMecanico() && leido.getIdReparacion() == tTrabaja.getIdReparacion()){
				if (tTrabaja.getHora() == 0) tTrabaja.setHora(leido.getHora());
				return daoR.modificarMecanico(tTrabaja);
			}
				

		return new TTrabaja(-3);
	}

	@Override
	public Collection<TReparacion> mostrarReparacionVehiculo(int idVehiculo) {
		DAOReparacion dao = FactoriaIntegracion.obtenerInstancia().crearReparacion();
		return dao.mostrarReparacionesVehiculo(idVehiculo);
	}

}
