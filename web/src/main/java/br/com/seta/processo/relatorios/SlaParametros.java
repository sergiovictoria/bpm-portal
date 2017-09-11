package br.com.seta.processo.relatorios;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.SlaProcessoService;
import br.com.seta.processo.entity.SlaProcesso;
import br.com.seta.processo.entity.SlaProcesso.UnidadeTempo;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.provider.SlaProcessoProvider;


public class SlaParametros extends Templete {
	private static final long serialVersionUID = 1L;
	
	private SlaProcessoProvider provider;
	
	@Inject private SlaProcessoService processoService;
	
	public SlaParametros(){
		setTituloPagina("SLA");
		
		provider = new SlaProcessoProvider(getUser());
		
		Form<Object> form = new Form<Object>("form");
		
		DataView<SlaProcesso> dtvProcessos = new DataView<SlaProcesso>("dtvProcessos", provider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(Item<SlaProcesso> item) {
				SlaProcesso sla = (SlaProcesso) item.getDefaultModelObject();
				item.add(new Label("descricao", sla.getProcesso()));
				item.add(new TextField<Integer>("txtTempo", new PropertyModel<Integer>(sla, "tempo")));
				
				List<UnidadeTempo> unidades = Arrays.asList(UnidadeTempo.values());
				
				item.add(new DropDownChoice<UnidadeTempo>("cmbTempoTipo",  
						new PropertyModel<UnidadeTempo> (sla, "unidadeTempo"), 
						unidades));
			}
		};
		
		WebMarkupContainer rptProcessos = new WebMarkupContainer("rptProcessos");
		rptProcessos.setOutputMarkupId(true);
		
		rptProcessos.add(dtvProcessos);
		
		form.add(new AjaxButton("btnSalvar"){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				processoService.salva(provider.getSlas());
				
				exibeMensagemSucesso("Sucesso", "Os parámetros das SLAs foram salvas com sucesso", target);
			}
		});	
		
		form.add(rptProcessos);
		
		add(form);
	}
	
}
