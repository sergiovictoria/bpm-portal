/**
 * 
 */
package br.com.seta.processo.provider;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.FornecedorIntencaoCompra;
import br.com.seta.processo.dto.GrupoRecebimentoIntencaoCompra;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitanteRequisicao;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.interfaces.RecebimentoService;

/**
 * @author Eliel Sobral
 * @param <T>
 *
 */
public class RecebimentosProvider extends SortableDataProvider<OrRequisicao, String> {


	private static final long serialVersionUID = 1L;

	@Inject
	private RecebimentoService recebimentoService;

	private List<OrRequisicao> orRequisicaoList = new ArrayList<OrRequisicao>();
	transient private final User user;

	/**
	 * Constructor
	 * @param user
	 */
	public RecebimentosProvider(User user) {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.user = user;
		try {	
			this.orRequisicaoList = (List<OrRequisicao>) recebimentoService.listaRequisicoes(this.user);
			getIndex(getSort());
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<? extends OrRequisicao> iterator(long first, long count) {
		getIndex(getSort());
		return this.orRequisicaoList.subList((int) first, (int) (first + count)).iterator();
	}

	@Override
	public long size() {
		return orRequisicaoList.size();
	}

	@Override
	public IModel<OrRequisicao> model(final OrRequisicao object) {
		return new LoadableDetachableModel<OrRequisicao>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected OrRequisicao load() {
				return object;
			}
		};
	}

	@Override
	public void detach() {
	}
	
	/**
	 * @return the orRequisicaoList
	 */
	public final List<OrRequisicao> getOrRequisicaoList() {
		return orRequisicaoList;
	}

	/**
	 * @param orRequisicaoList the orRequisicaoList to set
	 */
	public final void setOrRequisicaoList(List<OrRequisicao> orRequisicaoList) {
		this.orRequisicaoList = orRequisicaoList;
	}

	/**
	 * @param filtro
	 * @return
	 */
	public List<String> listaFornecedorRequisicao(String filtro) {
		List<FornecedorIntencaoCompra> listFornecedor = new ArrayList<FornecedorIntencaoCompra>();
		Set<FornecedorIntencaoCompra> ficList = new HashSet<FornecedorIntencaoCompra>();
		
		for (OrRequisicao or : this.orRequisicaoList) {
			if(null != filtro && !"".equals(filtro)) {
				if(filtro.equals(or.getNomeFornecedor())) {
					FornecedorIntencaoCompra fic = new FornecedorIntencaoCompra();
					fic.setNomeFornecedor(or.getNomeFornecedor());
					ficList.add(fic);
				}
			} else {
				FornecedorIntencaoCompra fic = new FornecedorIntencaoCompra();
				fic.setNomeFornecedor(or.getNomeFornecedor());
				ficList.add(fic);
			}
		}
		listFornecedor.addAll(ficList);
		List<String> listFornecedorRetorno = new ArrayList<String>();
		for (FornecedorIntencaoCompra fornecedorIntencaoCompra : ficList) {
			listFornecedorRetorno.add(fornecedorIntencaoCompra.getNomeFornecedor());
		}
		Collections.sort(listFornecedorRetorno, Collator.getInstance());
		return listFornecedorRetorno;
	}
	
	/**
	 * @param filtro
	 * @return
	 */
	public List<String> listaGrupoRecebimentoRequisicao(String filtro) {
		List<GrupoRecebimentoIntencaoCompra> listGrupoRecebimento = new ArrayList<GrupoRecebimentoIntencaoCompra>();
		Set<GrupoRecebimentoIntencaoCompra> gricList = new HashSet<GrupoRecebimentoIntencaoCompra>();	
		for (OrRequisicao or : this.orRequisicaoList) {
			if(null != filtro && !"".equals(filtro)) {
				if(filtro.equals(or.getGrupoRecebimento())) {
					GrupoRecebimentoIntencaoCompra gric = new GrupoRecebimentoIntencaoCompra();
					gric.setGrupoRecebimento(or.getGrupoRecebimento());
					gricList.add(gric);
				}
			} else {
				GrupoRecebimentoIntencaoCompra gric = new GrupoRecebimentoIntencaoCompra();
				gric.setGrupoRecebimento(or.getGrupoRecebimento());
				gricList.add(gric);
			}
		}
		listGrupoRecebimento.addAll(gricList);
		List<String> listGrupoRecebimentoRetorno = new ArrayList<String>();
		for (GrupoRecebimentoIntencaoCompra grupoRecebimentoIntencaoCompra : gricList) {
			listGrupoRecebimentoRetorno.add(grupoRecebimentoIntencaoCompra.getGrupoRecebimento());
		}
		Collections.sort(listGrupoRecebimentoRetorno, Collator.getInstance());
		return listGrupoRecebimentoRetorno;
	}

	/**
	 * @param filtro
	 * @return
	 */
	public List<String> listaSolicitantesRequisicao(String filtro) {
		List<SolicitanteRequisicao> listSolicitanteRequisicao = new ArrayList<SolicitanteRequisicao>();
		Set<SolicitanteRequisicao> srList = new HashSet<SolicitanteRequisicao>();	
		for (OrRequisicao or : this.orRequisicaoList) {
			if(null != filtro && !"".equals(filtro)) {
				if(filtro.equals(or.getNomeSolicitanteBPM())) {
					SolicitanteRequisicao sr = new SolicitanteRequisicao();
					sr.setNomeSolicitanteBPM(or.getNomeSolicitanteBPM());
					srList.add(sr);
				}
			} else {
				SolicitanteRequisicao sr = new SolicitanteRequisicao();
				sr.setNomeSolicitanteBPM(or.getNomeSolicitanteBPM());
				srList.add(sr);
			}
		}
		listSolicitanteRequisicao.addAll(srList);
		List<String> listSolicitanteRequisicaoRetorno = new ArrayList<String>();
		for (SolicitanteRequisicao solicitanteRequisicao : srList) {
			listSolicitanteRequisicaoRetorno.add(solicitanteRequisicao.getNomeSolicitanteBPM());
		}
		Collections.sort(listSolicitanteRequisicaoRetorno, Collator.getInstance());
		return listSolicitanteRequisicaoRetorno;
	}
	
	public final void refresh() {
		try {
			this.orRequisicaoList = (List<OrRequisicao>) recebimentoService.listaRequisicoes(this.user);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param tipoDespesa
	 * @param grupoRecebimento
	 * @param nomeFornecedor
	 * @param nomeSolicitanteBPM
	 * @param numeroIntencaoCompra
	 * @param numeroRequisicao
	 * @param emFilaDe
	 * @param emFilaAte
	 */
	public void executaFiltroRequisicao(String tipoDespesa, String grupoRecebimento, String nomeFornecedor, String nomeSolicitanteBPM, 
										Long numeroIntencaoCompra, Long numeroRequisicao, String emFilaDe, String emFilaAte) {
		Set<OrRequisicao> listFiltro = new HashSet<OrRequisicao>();
		this.refresh();

		if(null != tipoDespesa) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				if(tipoDespesa.equals(or.getTipoDespesa())) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		} 
		if(null != grupoRecebimento) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				if(grupoRecebimento.equals(or.getGrupoRecebimento())) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		} 
		if(null != nomeFornecedor) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				if(nomeFornecedor.equals(or.getNomeFornecedor())) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		} 
		if(null != nomeSolicitanteBPM) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				if(nomeSolicitanteBPM.equals(or.getNomeSolicitanteBPM())) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		}
		if(null != numeroIntencaoCompra && numeroIntencaoCompra != 0) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				boolean encontrou = numeroIntencaoCompra.equals(or.getNumeroIntencaoSolicitacaoCompra());
				if(encontrou) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		} 
		if(null != numeroRequisicao && numeroRequisicao != 0) {
			listFiltro.clear();
			for (OrRequisicao or : this.orRequisicaoList) {
				boolean encontrou = numeroRequisicao.equals(or.getNrorequisicao());
				if(encontrou) {
					listFiltro.add(or);
				}
			}
			this.orRequisicaoList.clear();
			this.orRequisicaoList.addAll(listFiltro);
		} 
		
	}
	
	public void getIndex(SortParam<?> sort) {
		if (sort == null) {
			    Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._TIPODESPESA);
		}else if (sort.getProperty().equals("TipoDespesa"))  {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._TIPODESPESA);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._TIPODESPESA));
			}		
		}else if (sort.getProperty().equals("IDRequisicao")) {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._REQUISICAO);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._REQUISICAO));
			}
		}else if (sort.getProperty().equals("LocalEntrega")) {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._LOCALENTREGA);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._LOCALENTREGA));
			}
		}else if (sort.getProperty().equals("Solicitante")) {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._SOLICITANTE);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._SOLICITANTE));
			}
		}else if (sort.getProperty().equals("Fornecedor")) {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._FORNECEDOR);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._FORNECEDOR));
			}
		}else if (sort.getProperty().equals("EmFila")) {
			if (sort.isAscending()) {
				Collections.sort(this.orRequisicaoList, OrRequisicao.Comparators._EMFILA);
			}else {
				Collections.sort(this.orRequisicaoList, Collections.reverseOrder(OrRequisicao.Comparators._EMFILA));
			}
		}		

	}
	
	public void receberPedidoSelecionado(OrRequisicao requisicao) {
		try {
			recebimentoService.receberPedidoSelecionado(requisicao);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	public List<OrRequisicao> getRequisicoesParaEnviar() {
		List<OrRequisicao> listaRequisicoesParaEnviar = new ArrayList<OrRequisicao>();
		for (OrRequisicao requisicao : this.orRequisicaoList) {
			if(requisicao.isSelecionado()) {
				listaRequisicoesParaEnviar.add(requisicao);
			}
		}
		return listaRequisicoesParaEnviar;
	}

}
