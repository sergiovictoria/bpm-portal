package br.com.seta.processo.model;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.model.IModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Contatos;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.service.DadosUsuarioService;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ProdutoCompradorModel implements IModel<Usuario> {
	private static final long serialVersionUID = 1L;

	@Inject
	private DadosUsuarioService dadosUsuarioService;
	
	private transient User user;
	private FormularioProduto fp;
	
	public ProdutoCompradorModel(User user, FormularioProduto fp){
		CdiContainer.get().getNonContextualManager().inject(this);	
		this.user = user;
		this.fp = fp;
	}		
	
	@Override
	public void detach() {
		
	}

	@Override
	public Usuario getObject() {
		try {
			return dadosUsuarioService.recuperaUsuario(user, fp.getIdComprador());
		} catch (BonitaException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObject(Usuario usuario) {
		Contatos contatos;
		try {
			contatos = dadosUsuarioService.recuperaContatosProfissionais(this.user, usuario.getId());
		} catch (BonitaException e) {
			throw new RuntimeException(e);
		}
		fp.setComprador(usuario.getNomeUsuario());
		fp.setNomeComprador(usuario.getNomeCompleto());
		fp.setIdComprador(usuario.getId());					
		String email = contatos != null ? contatos.getEmail() : null;
		fp.setEmailComprador(email);		
	}
	
}


