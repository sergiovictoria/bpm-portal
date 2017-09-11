package br.com.seta.processo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.bean.Categorias;
import br.com.seta.processo.dto.Categoria;

public class CategoriaModel implements IClusterable, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categoriaService;

	private Categoria categoriaSelecionada;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public CategoriaModel() {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.categorias = this.categoriaService.getCategorias();
		this.categoriaSelecionada = this.categorias.get(0);
	}

	/**
	 * @return the categoriaSelecionada
	 */
	public final Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	/**
	 * @param categoriaSelecionada
	 *            the categoriaSelecionada to set
	 */
	public final void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	/**
	 * @return the categorias
	 */
	public final List<Categoria> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias
	 *            the categorias to set
	 */
	public final void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
