package br.com.seta.processo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.bean.Classificacoes;
import br.com.seta.processo.dto.ClassificacaoComercial;

public class ClassificacaoComercialModel implements IClusterable, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Classificacoes classificacaoComercialService;
	
	private ClassificacaoComercial classificacaoComercialSelecionada;
	private List<ClassificacaoComercial> classificacoes = new ArrayList<ClassificacaoComercial>();
	
	
	public ClassificacaoComercialModel() {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.classificacoes = this.classificacaoComercialService.getClassificacaoComercial();
		this.classificacaoComercialSelecionada = this.classificacoes.get(0);
	}

	/**
	 * @return the classificacaoComercialSelecionada
	 */
	public final ClassificacaoComercial getClassificacaoComercialSelecionada() {
		return classificacaoComercialSelecionada;
	}

	/**
	 * @param classificacaoComercialSelecionada
	 *            the classificacaoComercialSelecionada to set
	 */
	public final void setClassificacaoComercialSelecionada(ClassificacaoComercial classificacaoComercialSelecionada) {
		this.classificacaoComercialSelecionada = classificacaoComercialSelecionada;
	}

	/**
	 * @return the classificacoes
	 */
	public final List<ClassificacaoComercial> getClassificacoes() {
		return classificacoes;
	}

	/**
	 * @param classificacoes
	 *            the classificacoes to set
	 */
	public final void setClassificacoes(List<ClassificacaoComercial> classificacoes) {
		this.classificacoes = classificacoes;
	}

}
