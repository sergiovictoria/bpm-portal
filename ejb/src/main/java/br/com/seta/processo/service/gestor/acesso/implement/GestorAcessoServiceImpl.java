package br.com.seta.processo.service.gestor.acesso.implement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.jboss.logging.Logger;



import static br.com.seta.processo.constant.GestorAcesso.CONSULTA_PERMISSAO;
import br.com.seta.processo.service.gestor.acesso.interfaces.GestorAcessoService;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ConsultaPermissaoWSV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.DadosUsuarioDTOV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListaPermissaoDTORequest;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissao;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissaoResponse;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTO;
import br.com.seta.processo.utils.PropertiesEJBUtils;

@Stateless
@Local(GestorAcessoService.class)




public class GestorAcessoServiceImpl implements GestorAcessoService {

	@Inject private Logger logger;

	private static HashMap<String,ConsultaPermissaoWSV2> servicos = new  HashMap<String, ConsultaPermissaoWSV2>();

	@Override
	public ConsultaPermissaoWSV2 getServicoConsulta() throws MalformedURLException {
		ConsultaPermissaoWSV2 servico = null;
		URL url = null;
		
		try {
			servico = servicos.get("service");
		} catch (Exception e) {
			logger.error("erro ao estabelecer conexao com o WS de Consulta do Gestor");
		}

		if(servico == null){

			String ip =      PropertiesEJBUtils.getInstance().getValues("urlGestorHost");
			String port =    PropertiesEJBUtils.getInstance().getValues("utlGestoPort");
			String context = PropertiesEJBUtils.getInstance().getValues("gestorContext");
			String urlContext = ip + ":" + port + context + CONSULTA_PERMISSAO;
			logger.info("Fazendo conexão ao gestor [ "+urlContext+ " ]");
			url = new URL(urlContext);
			

			QName qname = new QName("http://webservice.portal.seta.com.br/", "ConsultaPermissaoWS_V2");

			Service service = Service.create(url, qname);
			servico = service.getPort(ConsultaPermissaoWSV2.class);

			((BindingProvider) servico).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 7000);
			((BindingProvider) servico).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 7000);

			System.setProperty("sun.net.client.defaultConnectTimeout", "7000");
			System.setProperty("sun.net.client.defaultReadTimeout", "7000");

			((BindingProvider) servico).getRequestContext().put("javax.xml.ws.client.connectionTimeout", 7000);
			((BindingProvider) servico).getRequestContext().put("javax.xml.ws.client.receiveTimeout", 7000);

			servicos.put("service", servico);
		}

		return servico;
	}

	@Override
	public DadosUsuarioDTOV2 login(String username, String password, String nomeAplicacao) throws Exception {
		ConsultaPermissaoWSV2 inter = null;
		try {
			inter = this.getServicoConsulta();
		} catch (Exception e) {
			throw new Exception("Erro ao estabelecer conexão com o Gestor de Acessos. " + e.getMessage());
		}

		AcessoDTO acessoDto = new AcessoDTO();
		acessoDto.setLogin(username);
		acessoDto.setSenha(password);

		try {
			ListarPorPermissao lpp = new ListarPorPermissao();
			PermissaoTagDTO permissao = new PermissaoTagDTO();
			ListaPermissaoDTORequest lpr = new ListaPermissaoDTORequest();

			permissao.setSistema(nomeAplicacao);
			lpr.getPermissao().add(permissao);
			lpp.setPermissoes(lpr);

			ListarPorPermissaoResponse lppr = inter.listarPorPermissao(lpp, acessoDto);
			System.out.println(lppr.getAcesso().getUsuario());
			return lppr.getAcesso().getUsuario();

		} catch (Exception e) {
			throw new Exception("Erro ao estabelecer conexão com o Gestor de Acessos. " + e.getMessage());
		}
	}

}
