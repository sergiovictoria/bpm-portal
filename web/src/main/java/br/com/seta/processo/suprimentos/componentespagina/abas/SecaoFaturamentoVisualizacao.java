package br.com.seta.processo.suprimentos.componentespagina.abas;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.helper.DocumentoHelper;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.pagecomponentes.Anexos;
import br.com.seta.processo.provider.AnexosProvider;
import static br.com.seta.processo.constant.ConstantesSolicitacaoIntencaoCompra.FORMULARIO_ORCAMENTO;

public class SecaoFaturamentoVisualizacao extends Panel {
	private static final long serialVersionUID = 1L;
	
	private Secao secaoFaturamento;
	private AnexarOrcamentoDialog anexarOrcamentoDialog;
	private Documento orcamento;
	private Anexos secaoAnexoOrcamento;
	
	public SecaoFaturamentoVisualizacao(String id, OrRequisicao requisicao, BonitaPage parentPage){
		super(id);
		
		setOutputMarkupId(true);
		
		secaoFaturamento = (Secao) new Secao("secaoFaturamento", requisicao).setOutputMarkupId(true);
		anexarOrcamentoDialog = new AnexarOrcamentoDialog("anexarOrcamentoDialog");
		
		AnexosProvider provider = new AnexosProvider(parentPage.getCaseId(), ConstantesSolicitacaoIntencaoCompra.FORMULARIO_ORCAMENTO);
		secaoAnexoOrcamento = new Anexos("secaoAnexoOrcamento", provider);	
		if(provider.size() == 0) 
			secaoAnexoOrcamento.setVisible(false);		
		
		AnexosProvider cadastroFornecedorprovider = new AnexosProvider(parentPage.getCaseId(), ConstantesSolicitacaoIntencaoCompra.FORMULARIO_CADASTRO_FORNECEDOR);
		Anexos secaoAnexoCadastroFornecedor = new Anexos("secaoAnexoCadastroFornecedor", cadastroFornecedorprovider);
		if(cadastroFornecedorprovider.size() == 0){
			secaoAnexoCadastroFornecedor.setVisible(false);
		}
		
		add(secaoFaturamento, anexarOrcamentoDialog, secaoAnexoOrcamento, secaoAnexoCadastroFornecedor);
	}
	
	public void addSecaoAnexos(Panel secaoAnexos){
		addOrReplace(secaoAnexos);
	}
	
	public SecaoFaturamentoVisualizacao visibilidadeSecaoAnexoOrcamento(boolean visibilidade){
		secaoAnexoOrcamento.setVisible(visibilidade);
		return this;
	}
	
	public SecaoFaturamentoVisualizacao visibilidadeAbrirModalAnexoOrcamentoBtn(boolean visibilidade){
		secaoFaturamento.getAbrirModalAnexoOrcamentoBtn().setVisible(visibilidade);
		return this;
	}
	
	public SecaoFaturamentoVisualizacao visibilidadeAbrirModalAnexoCadFornecedorBtn(boolean visibilidade){
		secaoFaturamento.getAbrirModalAnexoCadFornecedorBtn().setVisible(visibilidade);
		return this;
	}
	
	public SecaoFaturamentoVisualizacao visibilidadeDownloadFormularioCadFornecedor(boolean visibilidade){
		secaoFaturamento.getCadastroFornecedorDownload().setVisible(visibilidade);
		return this;
	}
	
	public void habilitaAbrirModalAnexoOrcamentoBtn(boolean habilitado){
		secaoFaturamento.getAbrirModalAnexoOrcamentoBtn().setEnabled(habilitado);
	}
	
	public void habilitaAbrirModalAnexoCadFornecedorBtn(boolean habilitado){
		secaoFaturamento.getAbrirModalAnexoCadFornecedorBtn().setEnabled(habilitado);
	}
	
	public void habilitaDownloadFormularioCadFornecedor(boolean habilitado){
		secaoFaturamento.getCadastroFornecedorDownload().setEnabled(habilitado);
	}
	
	public Documento getOrcamento(){
		return this.orcamento;
	}
	
	private class Secao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		
		private TextField<Long> nroIntencao;
		private TextField<String> tipoDespesa;
		private DateTextField dataHora;
		private TextField<String> fornecedor, nomeEmpresa;	
		private Button abrirModalAnexoOrcamentoBtn, abrirModalAnexoCadFornecedorBtn, cadastroFornecedorDownload;
				
		@SuppressWarnings("unchecked")
		public Secao(String id, OrRequisicao requisicao) {
			super(id, new CompoundPropertyModel<OrRequisicao>(requisicao));
			
			nroIntencao = (TextField<Long>) new TextField<Long>("numeroIntencaoSolicitacaoCompra").setEnabled(false);
			tipoDespesa = (TextField<String>) new TextField<String>("tipoDespesa").setEnabled(false);
			dataHora = (DateTextField) new DateTextField("dtainclusao").setEnabled(false);
			fornecedor = (TextField<String>) new TextField<String>("nomeFornecedor").setEnabled(false);
			nomeEmpresa = (TextField<String>) new TextField<String>("nomeEmpresa").setEnabled(false);
			abrirModalAnexoOrcamentoBtn = new Button("abrirModalAnexoOrcamentoBtn");
			abrirModalAnexoCadFornecedorBtn = new Button("abrirModalAnexoCadFornecedorBtn");
			cadastroFornecedorDownload = new Button("cadastroFornecedorDownload");
			
			add(nroIntencao, tipoDespesa, dataHora, fornecedor, nomeEmpresa, abrirModalAnexoOrcamentoBtn, abrirModalAnexoCadFornecedorBtn, cadastroFornecedorDownload);
		}
		
		public Button getAbrirModalAnexoOrcamentoBtn(){
			return this.abrirModalAnexoOrcamentoBtn;
		}
		
		public Button getAbrirModalAnexoCadFornecedorBtn(){
			return this.abrirModalAnexoCadFornecedorBtn;
		}
		
		public Button getCadastroFornecedorDownload(){
			return this.cadastroFornecedorDownload;
		}
	}	
	
	private class AnexarOrcamentoDialog extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private FileUploadField anexoOrcamento;
		private AjaxButton anexoBtn;
		
		public AnexarOrcamentoDialog(String id) {
			super(id);
			
			anexoOrcamento = new FileUploadField("anexoOrcamento");
			anexoBtn = new AjaxButton("anexoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					FileUpload fileUpload = anexoOrcamento.getFileUpload();
					
					if(fileUpload != null){
						orcamento = DocumentoHelper.criaDocumento(fileUpload, FORMULARIO_ORCAMENTO);
					}
					
					target.appendJavaScript("$('#anexarOrcamentoDialog').modal('hide');");
				}
			};			
			add(anexoOrcamento, anexoBtn);
			
		}		
	}
	
	public void habilitaDadosSecaoFaturamento(boolean habilitado){
		this.secaoFaturamento.setEnabled(habilitado);
	}
	
}
