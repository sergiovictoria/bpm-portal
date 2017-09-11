package br.com.seta.processo.solicitacaopagamento.componentespagina;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.seta.processo.bean.SolicitacaoPagamentoService;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.RegraDeNegocioException;
import br.com.seta.processo.exceptions.SolicitacaoPagamentoServiceException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.solicitacaopagamento.SolicitacaoPagamentoTemplate;

public class AprovacaoSolicPagamentoPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private SolicitacaoPagamentoTemplate parentPage;
	Label codTitulo = (Label) new Label("codTitulo", Model.of(0L)).setOutputMarkupId(true);	
	private TextArea<String> comentariosRejeicao;
	
	@Inject
	private SolicitacaoPagamentoService solicitacaoService;
	
	public AprovacaoSolicPagamentoPanel(String id, SolicitacaoPagamentoTemplate parentPage) {
		super(id);		
		this.parentPage = parentPage;
		this.comentariosRejeicao  = new TextArea<String>("comentariosRejeicao", Model.of(""));
		
		add(new AprovarBtn("aprovarBtn"), new RejeitarBtn("rejeitarBtn"), new SalvarBtn("salvarBtn"),
				new SucessoOkBtn("sucessoOkBtn"), codTitulo, comentariosRejeicao);
	}
	
	private class AprovarBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public AprovarBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {			
			try {
				target.appendJavaScript("$('#confirmacaoAprovar').modal('hide')");
				long seqNota = solicitacaoService.aprovaSolicitacaoPagamento(getUser(), parentPage.getTaskId(), parentPage.getSolicitacaoPagamento());
				codTitulo.setDefaultModelObject(seqNota);
				target.appendJavaScript("$('#sucessoEnvioConsincoModal').modal('show')");
				target.add(codTitulo);
			}catch (ValidacaoBeanException e) {
				parentPage.setMensagensErro(e.getMessages(), "", target);
			}catch(RegraDeNegocioException e){
				parentPage.setMensagensAtencao(e.getMensagens(), "", target);
			}catch (SolicitacaoPagamentoServiceException e) {
				throw new RuntimeException(e);
			}			
		}
		
	}
	
	private class RejeitarBtn extends AjaxButton{	
		private static final long serialVersionUID = 1L;

		public RejeitarBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {			
			try {
				target.appendJavaScript("$('#confirmacaoRejeitar').modal('hide')");
				String comentarios = (String) comentariosRejeicao.getDefaultModelObject();
				solicitacaoService.rejeitaSolicitacaoPagamento(getUser(), parentPage.getTaskId(), parentPage.getSolicitacaoPagamento(), comentarios);
				parentPage.redirecionaParaPaginaDeAtividades();
			} catch (SolicitacaoPagamentoServiceException e) {
				throw new RuntimeException(e);
			}

		}		
	}
	
	private class SalvarBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;
		
		public SalvarBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			try {
				target.appendJavaScript("$('#confirmacaoSalvar').modal('hide')");
				solicitacaoService.salvaSolicitacaoPagamento(getUser(), parentPage.getTaskId(), parentPage.getSolicitacaoPagamento());				
			} catch (ValidacaoBeanException e) {
				parentPage.setMensagensErro(e.getMessages(), "", target);
			} catch (SolicitacaoPagamentoServiceException e) {
				throw new RuntimeException(e);
			}
		}

		
	}
	
	private class SucessoOkBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public SucessoOkBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			parentPage.redirecionaParaPaginaDeAtividades();
		}
		
	}
	
	private User getUser() {
		return (User) Session.get().getAttribute("user");
	}
}
