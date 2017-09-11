package br.com.seta.processo.page;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.markup.html.panel.Fragment;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.pagecomponentes.modal.AbstractModal;

public class TestePage extends Templete {

	private static final long serialVersionUID = 1L;	
	
	public TestePage() throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException{
		MyModal myModal = new MyModal();
		add(myModal);
	}
	
	private class MyModal extends AbstractModal{
		private static final long serialVersionUID = 1L;

		public MyModal() {
			super("myModal", "My Modal", new CorpoModal(), new RodapeModal() , AbstractModal.ATENCAO);
		}
		
	}
	
	private class CorpoModal extends Fragment{
		private static final long serialVersionUID = 1L;

		public CorpoModal() {
			super("corpoModal", "corpoModalFragment", TestePage.this);
		}
		
	}
	
	private class RodapeModal extends Fragment{
		private static final long serialVersionUID = 1L;

		public RodapeModal() {
			super("rodapeModal", "rodapeModalFragment", TestePage.this);
		}
		
	}
}
