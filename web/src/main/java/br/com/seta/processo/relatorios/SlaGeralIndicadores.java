package br.com.seta.processo.relatorios;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.interfaces.RelatorioGraficosService;
import br.com.seta.processo.utils.DateUtils;


public class SlaGeralIndicadores extends Templete {
	private static final long serialVersionUID = 1L;
	
	@Inject private RelatorioGraficosService relatorioGraficosService;
	
	private Label scriptGrfUm = new Label("scriptGrfUm", new Model<String>());
	private Label scriptGrfDois = new Label("scriptGrfDois", new Model<String>());
	
	public SlaGeralIndicadores(){
		setTituloPagina("SLA Geral");
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		
		Date de = cal.getTime();
		
		setDataDe(DateUtils.inicioDoDiaDe(de));
		setDataAte(DateUtils.fimDoDiaDe(new Date()));
		
		scriptGrfUm.setOutputMarkupId(true);
		scriptGrfUm.setEscapeModelStrings(false);
		
		scriptGrfDois.setOutputMarkupId(true);
		scriptGrfDois.setEscapeModelStrings(false);
				
		scriptGrfUm.setDefaultModelObject(relatorioGraficosService.slaGeralConsolidado("grfUm", dataDe, dataAte));
		scriptGrfDois.setDefaultModelObject(relatorioGraficosService.slaProcessoConsolidado("grfDois", dataDe, dataAte));
		
		Form<Object> form = new Form<Object>("form");
		form.add(new ModalFiltro("modalFiltro"));
		
		add(form, scriptGrfUm, scriptGrfDois);
	}
	
	
	
	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public ModalFiltro(String id) {
			super(id);
			
			DateTextField txtDataDe = new DateTextField("txtDataDe", new PropertyModel<Date>(SlaGeralIndicadores.this, "dataDe"), "dd/MM/yyyy");
			DateTextField txtDataAte = new DateTextField("txtDataAte", new PropertyModel<Date>(SlaGeralIndicadores.this, "dataAte"), "dd/MM/yyyy");
			
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					dataDe = DateUtils.inicioDoDiaDe(dataDe);
					dataAte = DateUtils.fimDoDiaDe(dataAte);
					
					scriptGrfUm.setDefaultModelObject(relatorioGraficosService.slaGeralConsolidado("grfUm", dataDe, dataAte));
					scriptGrfDois.setDefaultModelObject(relatorioGraficosService.slaProcessoConsolidado("grfDois", dataDe, dataAte));
					target.add(scriptGrfUm, scriptGrfDois);
					ocultaCarregamento(target);
				}
			};
			
			add(txtDataDe, txtDataAte, btnPesquisar);
		}	
	}
	
	private Date dataDe;
	private Date dataAte;
	
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
	
}
