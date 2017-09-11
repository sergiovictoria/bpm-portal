package br.com.seta.processo.bean.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.GeFeriado;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless
@LocalBean
public class GeFeriadoDao extends GenericJpaDaoConSinco<GeFeriado> {

	private static final long serialVersionUID = 1L;
	private static final String SQL_TODOS_FERIADOS_NACIONAIS = "SELECT * FROM GE_FERIADO WHERE UF IS NULL";
	
	public List<GeFeriado> listaFeriadosNacionais(){
		List<GeFeriado> feriados = findFromNativeQuery(SQL_TODOS_FERIADOS_NACIONAIS);
		return feriados;
	}

}
