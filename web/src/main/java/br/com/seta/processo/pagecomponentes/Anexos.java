package br.com.seta.processo.pagecomponentes;

import java.text.SimpleDateFormat;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.resourceprovider.MongoDBDocumentoResourceProvider;

public class Anexos extends Panel {
	private static final long serialVersionUID = 1L;
	
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy  HH:mm");
	
	private AnexosDV anexosDV;
	private WebMarkupContainer tabelaAnexos;
	
	public Anexos(String id, IDataProvider<Documento> provider) {
		super(id);
		this.anexosDV = new AnexosDV("anexos", provider); 
		
		this.tabelaAnexos = new WebMarkupContainer("tabelaAnexos");
		this.tabelaAnexos.add(anexosDV);
		add(tabelaAnexos);
	}
	
	private class AnexosDV extends DataView<Documento>{
		private static final long serialVersionUID = 1L;

		protected AnexosDV(String id, IDataProvider<Documento> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(Item<Documento> item) {
			Documento documento = (Documento) item.getDefaultModelObject();
			String data = documento.getDataCriacao() != null ? dateFormater.format(documento.getDataCriacao()) : "";
			Label dataCriacao = new Label("dataCriacao", data);
			Label nomeArquivo = new Label("nomeArquivo", documento.getNome());
			Label descricao = new Label("descricao", documento.getDescricao());
			MongoDBDocumentoResourceProvider resourceProvider = new MongoDBDocumentoResourceProvider(documento.getId());
			ResourceLink downloadBtn = new ResourceLink("downloadBtn", resourceProvider);
			
			item.add(dataCriacao, nomeArquivo, descricao, downloadBtn);
		}
		
	}
}
