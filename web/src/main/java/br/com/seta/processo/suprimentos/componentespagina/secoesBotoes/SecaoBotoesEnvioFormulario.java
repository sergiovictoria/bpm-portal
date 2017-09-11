package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamento;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens;
import br.com.seta.processo.validacao.ValidadorBean;

public class SecaoBotoesEnvioFormulario extends Panel {
	
	private static final long serialVersionUID = 1L;	
	
	private FormularioIntencaoCompraTemplate parentPage;
	
	@Inject
	private ValidadorBean validador;
	@Inject
	private DocumentoDao documentoDao;
	
	public SecaoBotoesEnvioFormulario(String id, final FormularioIntencaoCompraTemplate parentPage, final OrRequisicao requisicao, final SolicitacaoIntencaoCompra solicitacao) {
		super(id);	
		
		this.parentPage = parentPage;
		
		AjaxButton voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(parentPage.getPaginaAnterior().getPage());
			}
		};
		
		AjaxButton envioBtn = new AjaxButton("envioBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacao);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,requisicao);
				setFormularioCadastroItens();
				setOrcamento();
				setFormularioCadastroFornecedor();
				try {
					validador.valida(requisicao, parentPage);
					persistiFormularioCadastroItens();	
					persistiOrcamento();
					persistiFormularioCadastroFornecedor();
					
					parentPage.executeFlowAndUpdateVariables(processVariables);
					parentPage.redirecionaParaPaginaDeAtividades();
				}catch(ValidacaoBeanException e){
					target.appendJavaScript("$('#envioModal').modal('hide')");
					parentPage.setMensagensErro(e.getMessages(), target);
				}				
				catch (BonitaException e) {
					e.printStackTrace();
				}
			}

		};
		
		add(voltarBtn, envioBtn);
	}
	
	private void persistiFormularioCadastroItens(){
		Documento formularioCadastroItens = parentPage.getFormularioCadastroItens();
		if(formularioCadastroItens != null){
			documentoDao.salvaDocumento(formularioCadastroItens, parentPage.getCaseId());
		}
	}
	
	private void persistiOrcamento(){
		Documento orcamento = parentPage.getOrcamento();
		if(orcamento != null){
			documentoDao.salvaDocumento(orcamento, parentPage.getCaseId());
		}
	}
	
	private void persistiFormularioCadastroFornecedor(){
		Documento formularioCadastroFornecedor = parentPage.getFormularioCadastroFornecedor();
		if(formularioCadastroFornecedor != null){
			documentoDao.salvaDocumento(formularioCadastroFornecedor, parentPage.getCaseId());
		}
	}
	
	private void setFormularioCadastroItens(){
		SecaoItens secaoItens = (SecaoItens) this.parentPage.getSecaoItens();
		parentPage.setFormularioCadastroItens(secaoItens.getFormularioCadastroItens());
	}
	
	private void setOrcamento(){
		SecaoFaturamento secaoFaturamento = (SecaoFaturamento) this.parentPage.getSecaoFaturamento();
		parentPage.setOrcamento(secaoFaturamento.getOrcamento());
	}
	
	private void setFormularioCadastroFornecedor(){
		SecaoFaturamento secaoFaturamento = (SecaoFaturamento) this.parentPage.getSecaoFaturamento();
		parentPage.setFormularioCadastroFornecedor(secaoFaturamento.getFormularioCadFornecedor());
	}

}
