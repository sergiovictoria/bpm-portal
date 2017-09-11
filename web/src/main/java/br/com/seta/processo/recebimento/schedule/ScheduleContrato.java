package br.com.seta.processo.recebimento.schedule;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.bean.ScheduleContratoService;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.model.ScheduleModel;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.recebimento.schedule.dominio.ScheduleContratoDominio;

public class ScheduleContrato extends BonitaPage {
	
	private static final long serialVersionUID = 1L;
	
	@Inject private ScheduleContratoService scheduleContratoService;
	private ScheduleModel model = new ScheduleModel();
	
	private ScheduleContratoForm form;
	
	private DropDownChoice<String> cmbDiasSemanaInicio, cmbDiasSemanaFim, cmbHora, cmbMinuto;

	public ScheduleContrato(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception,
																  ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		form = new ScheduleContratoForm("form");
				
		add(form);
	}

	private class ScheduleContratoForm extends Form<Void> {
		private static final long serialVersionUID = 1L;
		
		ScheduleContratoDominio dominio = new ScheduleContratoDominio();
		
		public ScheduleContratoForm(String id) {
			super(id);
			
			cmbDiasSemanaInicio = new DropDownChoice<String>("cmbDiasSemanaInicio", new PropertyModel<String>(model, "cmbDiasSemanaInicio"), dominio.getDIAS_SEMANA());
			cmbDiasSemanaFim = new DropDownChoice<String>("cmbDiasSemanaFim", new PropertyModel<String>(model, "cmbDiasSemanaFim"), dominio.getDIAS_SEMANA());
			cmbHora = new DropDownChoice<String>("cmbHora", new PropertyModel<String>(model, "cmbHora"), dominio.getHORAS_DIA());
			cmbMinuto = new DropDownChoice<String>("cmbMinuto", new PropertyModel<String>(model, "cmbMinuto"), dominio.getMINUTOS_SEGUNDOS());
			
			add(new AjaxButton("btnSalvar"){
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					
					boolean dadosOK = validaCamposPreenchidos(cmbDiasSemanaInicio.getModelValue(), cmbDiasSemanaFim.getModelValue(), cmbHora.getModelValue(), cmbMinuto.getModelValue());
					if (!dadosOK) {
						exibeMensagemAdvertencia("Schedule", "Existem DADOS INCOMPLETOS NO SCHEDULE, por favor VERIFIQUE.", target);
					} else {
						scheduleContratoService.save(model.getCmbHora(),model.getCmbMinuto(), model.getCmbDiasSemanaInicio(),model.getCmbDiasSemanaFim());
						scheduleContratoService.stop("ContratosServicos");
						scheduleContratoService.agendar();
						exibeMensagemSucesso("Schedule", "Os parâmetros do SCHEDULE foram salvos com sucesso", target);						
					}

				}
			});	
			add(cmbDiasSemanaInicio, cmbDiasSemanaFim, cmbHora, cmbMinuto);
		}
		
		private boolean validaCamposPreenchidos(String cmbDiasSemanaInicio, String cmbDiasSemanaFim, String cmbHora, String cmbMinuto) {
			boolean validado = true;
			if("".equals(cmbDiasSemanaInicio)) {
				validado = false;
			}
			if("".equals(cmbDiasSemanaFim)) {
				validado = false;
			}
			if("".equals(cmbHora)) {
				validado = false;
			}
			if("".equals(cmbMinuto)) {
				validado = false;
			}
			return validado;
		}
		
	}


}
