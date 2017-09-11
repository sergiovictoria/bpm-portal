package br.com.seta.processo.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.bean.dao.ConfiguracoesSolicitacaoPagamentoDao;
import br.com.seta.processo.dto.ConfiguracaoSolicitacaoPagamento;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name="ConfiguracoesSolicitacaoPagamento")
@LocalBean
//@Interceptors({LogInterceptor.class})

public class ConfiguracoesSolicitacaoPagamentoService extends AbstractValidateBeanService {
	
	@Inject
	ConfiguracoesSolicitacaoPagamentoDao dao;
	
	public ConfiguracaoSolicitacaoPagamento buscaConfiguracaoSolicitacaoPagamento(){
		ConfiguracaoSolicitacaoPagamento configuracao = dao.buscaConfiguracaoSolicitacaoPagamento();
		
		return configuracao != null ? configuracao : criaNullObject();
	}
	
	public void salvaConfiguracaoSolicitacaoPagamento(ConfiguracaoSolicitacaoPagamento configuracao) throws ValidacaoBeanException{
		valida(configuracao, null);		
		dao.save(configuracao);
	}
	
	private ConfiguracaoSolicitacaoPagamento criaNullObject(){
		ConfiguracaoSolicitacaoPagamento nullObject = new ConfiguracaoSolicitacaoPagamento();
		nullObject.setEmailNotificacao("");
		
		return nullObject;
	}
}
