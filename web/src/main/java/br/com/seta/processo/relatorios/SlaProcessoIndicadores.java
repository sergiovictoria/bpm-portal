package br.com.seta.processo.relatorios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.ProcessoService;
import br.com.seta.processo.service.interfaces.RelatorioGraficosService;
import br.com.seta.processo.utils.DateUtils;


public class SlaProcessoIndicadores extends Templete {
	private static final long serialVersionUID = 1L;
	
	@Inject private ProcessoService processoService;
	@Inject private RelatorioGraficosService relatorioGraficosService;
	
	private Label scriptGrfUm = new Label("scriptGrfUm", new Model<String>());
	
	public SlaProcessoIndicadores(){
		setTituloPagina("SLA por Processo");
		
		try {
			processos = processoService.listaProcessos(getUser());
			if(processos.size() > 0)
				processoSelecionado = processos.get(0);
		} catch (BonitaException e) {}
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		
		Date de = cal.getTime();		
		
		setDataDe(DateUtils.inicioDoDiaDe(de));
		setDataAte(DateUtils.fimDoDiaDe(new Date()));
		
		scriptGrfUm.setOutputMarkupId(true);
		scriptGrfUm.setEscapeModelStrings(false);
		
		scriptGrfUm.setDefaultModelObject(relatorioGraficosService.slaEvolucaoMensal("grfUm", processoSelecionado.getName(), dataDe, dataAte));
		
		Form<Object> form = new Form<Object>("form");
		form.add(new ModalFiltro("modalFiltro"));
		
		add(form, scriptGrfUm);
	}
	
	
	
	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public ModalFiltro(String id) {
			super(id);
			
			DateTextField txtDataDe = new DateTextField("txtDataDe", new PropertyModel<Date>(SlaProcessoIndicadores.this, "dataDe"), "dd/MM/yyyy");
			DateTextField txtDataAte = new DateTextField("txtDataAte", new PropertyModel<Date>(SlaProcessoIndicadores.this, "dataAte"), "dd/MM/yyyy");
			
			DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",  
						new PropertyModel<TaskProcess> (SlaProcessoIndicadores.this, "processoSelecionado"), 
						processos,
						new ChoiceRenderer<TaskProcess>("name"));
						
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					dataDe = DateUtils.inicioDoDiaDe(dataDe);
					dataAte = DateUtils.fimDoDiaDe(dataAte);					
					
					scriptGrfUm.setDefaultModelObject(relatorioGraficosService.slaEvolucaoMensal("grfUm", processoSelecionado.getName(), dataDe, dataAte));
					
					target.appendJavaScript("$('#lblTituloGrfUm').text('SLA Evolução Mensal - " + processoSelecionado.getName() + "')");
					target.add(scriptGrfUm);
					ocultaCarregamento(target);
				}
			};
			
			add(txtDataDe, txtDataAte, cmbProcesso, btnPesquisar);
		}	
	}
	
	private Date dataDe;
	private Date dataAte;
	private TaskProcess processoSelecionado;
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/fusioncharts.js"));
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/themes/fusioncharts.theme.fint.js"));
	}

	public Date getDataDe() {
		return dataDe;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public TaskProcess getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(TaskProcess processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}
	
	
}
