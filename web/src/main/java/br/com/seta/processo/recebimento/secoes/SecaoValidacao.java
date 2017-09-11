package br.com.seta.processo.recebimento.secoes;

import static br.com.seta.processo.cadastrofornecedores.dominios.SimNao.NAO;
import static br.com.seta.processo.dominios.TipoDivergencia.PARCIAL;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.cadastrofornecedores.dominios.SimNao;
import br.com.seta.processo.constant.ConstantesRecebimento;
import br.com.seta.processo.dominios.TipoDivergencia;
import br.com.seta.processo.dominios.TipoDocumentoRecebimento;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.pagecomponentes.UploadArquivo;
import br.com.seta.processo.pagecomponentes.modal.Modal;

public class SecaoValidacao extends Panel {
	private static final long serialVersionUID = 1L;
	
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	private SolicitacaoIntencaoCompra solicitacao;
	private OrRequisicao requisicao;
	private Recebimento recebimento = new Recebimento();
	private long caseId;
	private String necessidadeAjusteImpostos = SimNao.NAO;
	
	private SecaoDadosSolicitacao secaoDadosSolicitacao;
	private SecaoGuiaCega secaoGuiaCega;
	private SecaoNotaFiscalSerie secaoNotaFiscalSerie;
	private SecaoTipoDocumento secaoTipoDocumento;
	private SecaoMensagem secaoMensagem;
	private SecaoDivergencias secaoDivergencias;
	private SecaoAnexoDocRecebimento secaoAnexoDocRecebimento;
	private SecaoAnexoDocDivergencia secaoAnexoDocDivergencia;
	private SecaoAnexoDocEscaneado secaoAnexoDocEscaneado;
	private SecaoAjusteDeImpostos secaoAjusteDeImpostos;
	private WebMarkupContainer secaoDadosParaValidacao;
	
	private UploadArquivo uploadDocRecebimento;
	private UploadArquivo uploadDocDivergencia;
	private UploadArquivo uploadDocEscaneado;
	
	public SecaoValidacao(String id, long caseId, SolicitacaoIntencaoCompra solicitacao, OrRequisicao requisicao, Recebimento recebimento) {
		super(id);
		
		this.solicitacao = solicitacao;
		this.requisicao = requisicao;
		this.recebimento = recebimento;
		this.caseId = caseId;
		
		secaoDadosSolicitacao = new SecaoDadosSolicitacao("secaoDadosSolicitacao");
		secaoDadosSolicitacao.setEnabled(false);
		
		secaoDadosParaValidacao = new WebMarkupContainer("secaoDadosParaValidacao");
		secaoGuiaCega = new SecaoGuiaCega("secaoGuiaCega");
		secaoNotaFiscalSerie = new SecaoNotaFiscalSerie("secaoNotaFiscalSerie");
		secaoTipoDocumento = new SecaoTipoDocumento("secaoTipoDocumento");
		secaoMensagem = new SecaoMensagem("secaoMensagem");
		secaoDivergencias = new SecaoDivergencias("secaoDivergencias");
		secaoAnexoDocRecebimento = new SecaoAnexoDocRecebimento();
		secaoAnexoDocDivergencia = new SecaoAnexoDocDivergencia();		
		secaoAnexoDocEscaneado = new SecaoAnexoDocEscaneado("secaoAnexoDocEscaneado");
		secaoAjusteDeImpostos = new SecaoAjusteDeImpostos("secaoAjusteDeImpostos");
		
		secaoDadosParaValidacao.add(secaoGuiaCega, secaoNotaFiscalSerie, secaoTipoDocumento, secaoMensagem, secaoAnexoDocRecebimento, secaoDivergencias, secaoAnexoDocDivergencia, secaoAnexoDocEscaneado, secaoAjusteDeImpostos);
		add(secaoDadosSolicitacao, secaoDadosParaValidacao);
		
		secaoGuiaCega.setVisible(false);
		secaoNotaFiscalSerie.setVisible(false);
		secaoTipoDocumento.setVisible(false);
		secaoMensagem.setVisible(false);
		secaoDivergencias.setVisible(false);
		secaoAnexoDocRecebimento.setVisible(false);
		secaoAnexoDocDivergencia.setVisibilidade(false);
		secaoAnexoDocEscaneado.setVisible(false);
		secaoAjusteDeImpostos.setVisible(false);
	}
	
	public SecaoValidacao adicionaSecaoAnexoDocDivergencia(Component secaoAnexo){
		this.addOrReplace(secaoAnexo);
		return this;
	}
	
	public SecaoValidacao exibeSecaoDadosParaValidacao(boolean visivel){
		secaoDadosParaValidacao.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoAjusteDeImpostos(boolean visivel){
		secaoAjusteDeImpostos.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoAnexoDocDivergencia(boolean visivel){
		secaoAnexoDocDivergencia.setVisibilidade(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoGuiaCega(boolean visivel){
		secaoGuiaCega.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoAnexoDocRecebimento(boolean visivel){
		secaoAnexoDocRecebimento.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao habilitaSecaoGuiaCega(boolean habilitado){
		secaoGuiaCega.setEnabled(habilitado);
		return this;
	}
	
	public SecaoValidacao exibeSecaoNotaFiscalSerie(boolean visivel){
		secaoNotaFiscalSerie.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoTipoDocumento(boolean visivel){
		secaoTipoDocumento.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoMensagem(boolean visivel){
		secaoMensagem.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao  exibeSecaoDivergencias(boolean visivel){
		secaoDivergencias.setVisible(visivel);
		return this;
	}
	
	public SecaoValidacao exibeSecaoAnexoDocEscaneado(boolean visivel){
		secaoAnexoDocEscaneado.setVisible(visivel);
		return this;
	}
	
	private class SecaoDadosSolicitacao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<Long> nroIntencaoTxt, requisicaoC5Txt;
		private TextField<String> tipoDespesaTxt;
		private DateTextField solicitacaoTxt, inclusaoC5Txt, compraC5Txt;
		
		public SecaoDadosSolicitacao(String id) {
			super(id);
			
			nroIntencaoTxt = new TextField<Long>("nroIntencaoTxt", new PropertyModel<Long>(solicitacao, "numeroIntencaoSolicitacaoCompra"));
			requisicaoC5Txt = new TextField<Long>("requisicaoC5Txt", new PropertyModel<Long>(requisicao, "nrorequisicao"));
			tipoDespesaTxt = new TextField<String>("tipoDespesaTxt", new PropertyModel<String>(solicitacao, "tipoDespesa"));
			solicitacaoTxt = new DateTextField("solicitacaoTxt", new PropertyModel<Date>(requisicao, "dataSolicitacaoIntencao"), DATE_PATTERN);
			inclusaoC5Txt = new DateTextField("inclusaoC5Txt", new PropertyModel<Date>(requisicao, "dtainclusao"), DATE_PATTERN);			
			compraC5Txt = new DateTextField("compraC5Txt", Model.of(new Date()) );
			
			add(nroIntencaoTxt, requisicaoC5Txt, tipoDespesaTxt, solicitacaoTxt, inclusaoC5Txt, compraC5Txt);
		}
		
	}
	
	private class SecaoGuiaCega extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<String> box, nroNota, conferentes, descarregadores;	
		
		public SecaoGuiaCega(String id) {
			super(id);
			
			box = new TextField<String>("box", new PropertyModel<String>(recebimento, "box"));
			nroNota = new TextField<String>("nroNota", new PropertyModel<String>(recebimento, "nroNota"));
			conferentes = new TextField<String>("conferentes", new PropertyModel<String>(recebimento, "conferentes"));
			descarregadores = new TextField<String>("descarregadores", new PropertyModel<String>(recebimento, "descarregadores"));			
			
			add(box, nroNota, conferentes, descarregadores);
		}		
	}
	
	private class SecaoNotaFiscalSerie extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private TextField<String> nfSerie;
		
		public SecaoNotaFiscalSerie(String id) {
			super(id);
			
			nfSerie = new TextField<String>("nfSerie", new PropertyModel<String>(recebimento, "nfSerie"));
			
			add(nfSerie);
		}		
	}
	
	private class SecaoTipoDocumento extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private DropDownChoice<String> tipoDocumento;
		private Label descTipoDocumentoLbl;
		private TextField<String> descTipoDocumento;
		
		public SecaoTipoDocumento(String id) {
			super(id);
			
			descTipoDocumentoLbl = new Label("descTipoDocumentoLbl", Model.of("Descrever"));
			descTipoDocumento = new TextField<String>("descTipoDocumento", new PropertyModel<String>(recebimento, "descTipoDocumento"));
			final WebMarkupContainer campoDescTipoDocumento = new WebMarkupContainer("campoDescTipoDocumento"){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onBeforeRender() {					
					super.onBeforeRender();
					if(!tipoDocumentoEhIgualaOutro()){
						descTipoDocumentoLbl.setVisible(false);
						descTipoDocumento.setVisible(false);
					}	
				}
			};
			campoDescTipoDocumento.add(descTipoDocumento, descTipoDocumentoLbl).setOutputMarkupId(true);
			
			tipoDocumento = new DropDownChoice<String>("tipoDocumento", new PropertyModel<String>(recebimento, "tipoDocumento"), TipoDocumentoRecebimento.getValores());
			AjaxFormComponentUpdatingBehavior onChange = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					if (tipoDocumentoEhIgualaOutro()) {
						descTipoDocumentoLbl.setVisible(true);
						descTipoDocumento.setVisible(true);
					} else {
						descTipoDocumentoLbl.setVisible(false);
						descTipoDocumento.setVisible(false);
						recebimento.setDescTipoDocumento(null);
					}

					target.add(campoDescTipoDocumento);
					
				}
			};			

			tipoDocumento.add(onChange);
			
			add(tipoDocumento, campoDescTipoDocumento);			
		}
		
		private boolean tipoDocumentoEhIgualaOutro() {
			return recebimento.getTipoDocumento() != null && recebimento.getTipoDocumento().equalsIgnoreCase("Outro");
		}		
	}	
	
	private class SecaoDivergencias extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private DropDownChoice<String> possuiDivergencias, categoria;
		private TextArea<String> motivo;
		
		public SecaoDivergencias(String id) {
			super(id);
			
			possuiDivergencias = new DropDownChoice<String>("IsDivergencia", new PropertyModel<String>(recebimento, "IsDivergencia"), SimNao.getValores());
			categoria = new DropDownChoice<String>("tipoDivergencia", new PropertyModel<String>(recebimento, "tipoDivergencia"), TipoDivergencia.getValores());
			motivo = new TextArea<String>("motivoOuJustificativaDivergencia", new PropertyModel<String>(recebimento, "motivoOuJustificativaDivergencia"));
						
			final MarkupContainer categoriaConteiner = (MarkupContainer) new WebMarkupContainer("categoriaConteiner"){
				private static final long serialVersionUID = 1L;

				protected void onBeforeRender() {
					super.onBeforeRender();
					if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO))
						this.setVisible(false);
				};
			}.add(categoria).setOutputMarkupId(true);
			
			categoria.add(new AjaxFormComponentUpdatingBehavior("change"){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					target.add(secaoAnexoDocRecebimento);
				}				
			});

			final MarkupContainer motivoContainer = (MarkupContainer) new WebMarkupContainer("motivoContainer"){
				private static final long serialVersionUID = 1L;
				
				protected void onBeforeRender() {
					super.onBeforeRender();
					if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO))
						this.setVisible(false);
				};
			}.add(motivo).setOutputMarkupId(true);
			
			final MarkupContainer motivoFormGroup = (MarkupContainer) new WebMarkupContainer("motivoFormGroup").add(motivoContainer).setOutputMarkupId(true);
			final MarkupContainer categoriaFormGroup = (MarkupContainer) new WebMarkupContainer("categoriaFormGroup").add(categoriaConteiner).setOutputMarkupId(true);
			
			possuiDivergencias.add(	new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO)){
						categoriaConteiner.setVisible(false);
						motivoContainer.setVisible(false);
						recebimento.setTipoDivergencia(null);
						recebimento.setMotivoOuJustificativaDivergencia(null);
						secaoAnexoDocDivergencia.setVisibilidade(false);
					}else{
						categoriaConteiner.setVisible(true);
						motivoContainer.setVisible(true);
						secaoAnexoDocDivergencia.setVisibilidade(true);
						recebimento.setTipoDivergencia(PARCIAL);
					}
					target.add(motivoFormGroup, categoriaFormGroup, secaoAnexoDocDivergencia, secaoAnexoDocRecebimento);

				}
			});
			
			add(possuiDivergencias, motivoFormGroup, categoriaFormGroup);
		}
		
	}
	
	private class SecaoAnexoDocRecebimento extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Button abrirModalAnexoDocRecebimento;
		private Modal anexoDocRecebimentoModal;
		private WebMarkupContainer containerAnexo;
		
		public SecaoAnexoDocRecebimento(){
			super("secaoAnexoDocRecebimento");			
			setOutputMarkupId(true);	
			
			abrirModalAnexoDocRecebimento = new Button("abrirModalAnexoDocRecebimento");
			anexoDocRecebimentoModal = new Modal("anexoDocRecebimentoModal", 
					"anexoDocRecebimentoModal", 
					Modal.CONFIRMACAO, 
					new CorpoModalAnexoDocRecebimento(), 
					"Anexar Documento de Recebimento", 
					new BtnFecharModalFragment());
			containerAnexo = new WebMarkupContainer("containerAnexo");
			containerAnexo.add(abrirModalAnexoDocRecebimento, anexoDocRecebimentoModal);
			add(containerAnexo);
		}
		
		@Override
		protected void onConfigure() {
			if(recebimento.getIsDivergencia() == null || recebimento.getIsDivergencia().equals(NAO)){
				containerAnexo.setVisible(true);
			}else{
				if(recebimento.getTipoDivergencia() == null || recebimento.getTipoDivergencia().equals(PARCIAL)){
					containerAnexo.setVisible(true);
				}else{
					containerAnexo.setVisible(false);
				}
			}
		}
	}
	
	private class CorpoModalAnexoDocRecebimento extends Fragment{
		private static final long serialVersionUID = 1L;

		public CorpoModalAnexoDocRecebimento() {
			super("corpoModal", "corpoModalAnexoDocRecebimento", SecaoValidacao.this);
			uploadDocRecebimento = new UploadArquivo("uploadDocRecebimento");
			add(uploadDocRecebimento);
		}
		
	}
	
	private class SecaoAnexoDocDivergencia extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Button abrirModalAnexoDocDivergencia;
		private Modal anexoDocDivergenciaModal;	
		private WebMarkupContainer containerAnexo;
		
		public SecaoAnexoDocDivergencia() {
			super("secaoAnexoDocDivergencia");			
			
			setOutputMarkupId(true);			
			
			abrirModalAnexoDocDivergencia = new Button("abrirModalAnexoDocDivergencia");
			anexoDocDivergenciaModal = new Modal("anexoDocDivergenciaModal", 
					"anexoDocDivergenciaModal", 
					Modal.CONFIRMACAO, 
					new CorpoModalAnexoDocDivergencia(), 
					"Anexar Documento de Divergência", 
					new BtnFecharModalFragment());
			
			containerAnexo = new WebMarkupContainer("containerAnexo");
			
			containerAnexo.add(abrirModalAnexoDocDivergencia, anexoDocDivergenciaModal);
			add(containerAnexo);
		}
		
		
		public void setVisibilidade(boolean visible){
			containerAnexo.setVisible(visible);
		}		
		
	}
	
	private class CorpoModalAnexoDocDivergencia extends Fragment{
		private static final long serialVersionUID = 1L;

		public CorpoModalAnexoDocDivergencia() {
			super("corpoModal", "corpoModalAnexoDocDivergencia", SecaoValidacao.this);
			uploadDocDivergencia = new UploadArquivo("uploadDocDivergencia");
			add(uploadDocDivergencia);
		}
		
	}
	
	private class SecaoAnexoDocEscaneado extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private Modal anexoDocEscaneadoModal;	
		
		public SecaoAnexoDocEscaneado(String id) {
			super(id);
			
			anexoDocEscaneadoModal = new Modal("anexoDocEscaneadoModal", 
					"anexoDocEscaneadoModal", 
					Modal.CONFIRMACAO, 
					new CorpoModalAnexoDocEscaneado(), 
					"Anexar Documento Escaneado", 
					new BtnFecharModalFragment());
			
			add(anexoDocEscaneadoModal);
		}
		
	}	
	
	private class CorpoModalAnexoDocEscaneado extends Fragment{
		private static final long serialVersionUID = 1L;

		public CorpoModalAnexoDocEscaneado() {
			super("corpoModal", "corpoModalAnexoDocEscaneado", SecaoValidacao.this);
			
			uploadDocEscaneado = new UploadArquivo("uploadDocEscaneado");
			
			add(uploadDocEscaneado);
		}
		
	}
	
	private class SecaoAjusteDeImpostos extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private DropDownChoice<String> ajusteDeImpostos;
		
		public SecaoAjusteDeImpostos(String id) {
			super(id);
			ajusteDeImpostos = new DropDownChoice<String>("ajusteDeImpostos", new PropertyModel<String>(SecaoValidacao.this, "necessidadeAjusteImpostos"), SimNao.getValores());
			add(ajusteDeImpostos);
		}		
	}
	
	private class SecaoMensagem extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Label mensagemLbl;
		
		public SecaoMensagem(String id) {
			super(id);
			setOutputMarkupId(true);
			
			mensagemLbl = new Label("mensagemLbl", "");
			mensagemLbl.setEscapeModelStrings(false);
			
			add(mensagemLbl);
		}
		
		public void setMensagem(String mensagem){
			mensagemLbl.setDefaultModelObject(mensagem);
		}
		
//		public void setMensagem(String mensagem, AjaxRequestTarget target){
//			setMensagem(mensagem);
//			target.add(this);
//		}
		
	}
	
	private class BtnFecharModalFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		public BtnFecharModalFragment() {
			super("botao", "botaoFecharModalFragment", SecaoValidacao.this);
		}
		
	}
	
	public String getNecessidadeAjusteImpostos(){
		return this.necessidadeAjusteImpostos;
	}

	public Documento getAnexoDocRecebimento(){
		return uploadDocRecebimento.getAnexo(caseId, ConstantesRecebimento.DOCUMENTO_RECEBIMENTO);
	}

	public Documento getAnexoDocDivergencias(){
		return uploadDocDivergencia.getAnexo(caseId, ConstantesRecebimento.DOCUMENTO_DE_DIVERGENCIAS);
	}

	public Documento getAnexoDocEscaneado(){
		return uploadDocEscaneado.getAnexo(caseId, ConstantesRecebimento.DOCUMENTO_ESCANEADO);
	}

	public SecaoValidacao setMensagem(String mensagem){
		secaoMensagem.setMensagem(mensagem);
		return this;
	}
	
}
