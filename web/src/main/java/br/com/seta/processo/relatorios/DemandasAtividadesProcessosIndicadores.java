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


public class DemandasAtividadesProcessosIndicadores extends Templete {
	private static final long serialVersionUID = 1L;
	
	@Inject private ProcessoService processoService;
	@Inject private RelatorioGraficosService relatorioGraficosService;
	
	private Label scriptGrfTres = new Label("scriptGrfTres", new Model<String>());
	private Label scriptGrfCinco = new Label("scriptGrfCinco", new Model<String>());
	
	public DemandasAtividadesProcessosIndicadores(){
		setTituloPagina("Demandas");
		
		try {
			processos = processoService.listaProcessos(getUser());
			if(processos.size() > 0)
				processoName = processos.get(0);
		} catch (BonitaException e) {}
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		
		Date de = cal.getTime();
		
		setDataDe(DateUtils.inicioDoDiaDe(de));
		setDataAte(DateUtils.fimDoDiaDe(new Date()));
		
		scriptGrfTres.setOutputMarkupId(true);
		scriptGrfTres.setEscapeModelStrings(false);
		
		scriptGrfCinco.setOutputMarkupId(true);
		scriptGrfCinco.setEscapeModelStrings(false);
		
		scriptGrfTres.setDefaultModelObject(relatorioGraficosService.demandasDemandaSemanal("grfTres", dataDe, dataAte, processoName.getName()));
		scriptGrfCinco.setDefaultModelObject(relatorioGraficosService.demandasStatusIntencoesEvolucao("grfCinco", dataDe, dataAte, processoName.getName()));
		
		Form<Object> form = new Form<Object>("form");
		form.add(new ModalFiltro("modalFiltro"));
		
		add(form, scriptGrfTres, scriptGrfCinco);
	}
	
	
	
	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public ModalFiltro(String id) {
			super(id);
			
			DateTextField txtDataDe = new DateTextField("txtDataDe", new PropertyModel<Date>(DemandasAtividadesProcessosIndicadores.this, "dataDe"), "dd/MM/yyyy");
			DateTextField txtDataAte = new DateTextField("txtDataAte", new PropertyModel<Date>(DemandasAtividadesProcessosIndicadores.this, "dataAte"), "dd/MM/yyyy");
			
			DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",  
						new PropertyModel<TaskProcess> (DemandasAtividadesProcessosIndicadores.this, "processoName"), 
						processos,
						new ChoiceRenderer<TaskProcess>("name"));
						
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					dataDe = DateUtils.inicioDoDiaDe(dataDe);
					dataAte = DateUtils.fimDoDiaDe(dataAte);
					
					scriptGrfTres.setDefaultModelObject(relatorioGraficosService.demandasDemandaSemanal("grfTres", dataDe, dataAte, processoName.getName()));
					scriptGrfCinco.setDefaultModelObject(relatorioGraficosService.demandasStatusIntencoesEvolucao("grfCinco", dataDe, dataAte, processoName.getName()));
					
					target.appendJavaScript("$('#demandaSemanal').text('Demanda Semanal - " + processoName.getName() + "')");
					target.appendJavaScript("$('#evolucaoMensal').text('Evolução Semanal - " + processoName.getName() + "')");
					target.add(scriptGrfTres, scriptGrfCinco);
					ocultaCarregamento(target);
				}
			};
			
			add(txtDataDe, txtDataAte, cmbProcesso, btnPesquisar);
		}	
	}
	
	
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/fusioncharts.js"));
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/themes/fusioncharts.theme.fint.js"));
	}
	
	private Date dataDe;
	private Date dataAte;
	private TaskProcess processoName;
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();

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
	public TaskProcess getProcessoName() {
		return processoName;
	}
	public void setProcessoName(TaskProcess processoName) {
		this.processoName = processoName;
	}
	
}
