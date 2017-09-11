package br.com.seta.processo.bean;

import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.entity.FiForncontaEntity;
import br.com.seta.processo.entity.FiForncontaId;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="DadosFinanceiroFornecedor")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class DadosFinanceiroFornecedor extends GenericJpaDaoConSinco<FiForncontaEntity> {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - DadosFinanceiroFornecedor ");
	}


	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(FormularioFornecedor formularioFornecedor, Integer seqpessoa) {
		
		logger.info("Criando dados financeiros ");
		
		FiForncontaEntity fiForncontaEntity = new FiForncontaEntity();
		FiForncontaId forncontaId = new FiForncontaId();

		forncontaId.setSeqpessoa(seqpessoa);
		forncontaId.setSeqconta(new Short("1"));
		
		fiForncontaEntity.setId(forncontaId);

		
		if (formularioFornecedor.getBancoTitularConta()!=null) {
			fiForncontaEntity.setBanco(new Short(getBanco(formularioFornecedor.getBancoTitularConta())));
		}	
		
		if (formularioFornecedor.getAgenciaTitularConta()!=null)
			fiForncontaEntity.setAgencia(new Integer(formularioFornecedor.getAgenciaTitularConta()));

		if (formularioFornecedor.getDigitoAgenciaTitularConta()!=null)
			fiForncontaEntity.setDigagencia(formularioFornecedor.getDigitoAgenciaTitularConta());

		if (formularioFornecedor.getContaTitularConta()!=null)
			fiForncontaEntity.setNroconta(formularioFornecedor.getContaTitularConta());

		if (formularioFornecedor.getDigitoContaTitularConta()!=null)
			fiForncontaEntity.setDigconta(formularioFornecedor.getDigitoContaTitularConta());

		if (formularioFornecedor.getCpfCnpjTitularConta()!=null)
			fiForncontaEntity.setCnpjcpf(new Long(formularioFornecedor.getCpfCnpjTitularConta()));

		if (formularioFornecedor.getCpfCnpjTitularConta()!=null) {
			if (formularioFornecedor.getCpfCnpjTitularConta().length()==11) {

				fiForncontaEntity.setCnpjcpf(new Long(formularioFornecedor.getCpfCnpjTitularConta().substring(0, 9)));
				fiForncontaEntity.setDigcnpjcpf(new Integer(formularioFornecedor.getCpfCnpjTitularConta().substring(9, 11)));
				fiForncontaEntity.setTipoinscricao(new Integer(1));

			}else if (formularioFornecedor.getCpfCnpjTitularConta().length()==14) {

				fiForncontaEntity.setTipoinscricao(new Integer(2));
				fiForncontaEntity.setCnpjcpf(new Long(formularioFornecedor.getCpfCnpjTitularConta().substring(0, 12)));
				fiForncontaEntity.setDigcnpjcpf(new Integer(formularioFornecedor.getCpfCnpjTitularConta().substring(12, 14)));

			}
		}

		if (formularioFornecedor.getNomeTitularConta()!=null)
		fiForncontaEntity.setNome(formularioFornecedor.getNomeTitularConta());
		
		if (formularioFornecedor.getEnderecoTitularConta()!=null)
		fiForncontaEntity.setEndereco(formularioFornecedor.getEnderecoTitularConta());
		
		if (formularioFornecedor.getNumero()!=null)
		fiForncontaEntity.setNumero(new Integer(formularioFornecedor.getNumeroTitularConta()));
		
		if (formularioFornecedor.getBairroTitularConta()!=null)
		fiForncontaEntity.setBairro(formularioFornecedor.getBairroTitularConta());
		
		if (formularioFornecedor.getCidadeTitularConta()!=null)
		fiForncontaEntity.setCidade(formularioFornecedor.getCidadeTitularConta());
		


		if (formularioFornecedor.getCepTitularConta() != null) {
			String cep = formularioFornecedor.getCepTitularConta();
			cep = cep.replace("-", "");
			
			if(cep.length() < 8){
				cep = preencheZerosAEsquerda(cep, 8);
			}
			
			fiForncontaEntity.setCep(new Integer(cep.substring(0, 5)));
			fiForncontaEntity.setComplcep(new Short(cep.substring(5, 8)));
		}

		fiForncontaEntity.setDtaalteracao(new java.util.Date());
		fiForncontaEntity.setSituacao("A");

		if (formularioFornecedor.getUfTitularConta()!=null)
			fiForncontaEntity.setEstado(formularioFornecedor.getUfTitularConta());

		fiForncontaEntity.setUsualteracao("BPMSETA");


		if (formularioFornecedor.getObservacaoTitularConta()!=null)
			fiForncontaEntity.setObservacao(formularioFornecedor.getObservacaoTitularConta());

		if (formularioFornecedor.getTipoDaConta()!=null) {
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA PARA PAGAMENTOS VIA DEPOSITO")) {
				fiForncontaEntity.setContapadrao("S");
			}else {
				fiForncontaEntity.setContapadrao("N");
			}
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA DE TERCEIRO")) {
				fiForncontaEntity.setPropterceiro("T");
			}else {
				fiForncontaEntity.setPropterceiro("P");
			}
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA PESSOA JURIDICA")) {
				fiForncontaEntity.setContapessoajuridica("S");
			}else {
				fiForncontaEntity.setContapessoajuridica("N");
			}
			
		}else {
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA PARA PAGAMENTOS VIA DEPOSITO")) {
			}else {
				fiForncontaEntity.setContapadrao("N");
			}
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA DE TERCEIRO")) {
				
			}else {
				fiForncontaEntity.setPropterceiro("P");
			}
			
			if (formularioFornecedor.getTipoDaConta().equals("CONTA PESSOA JURIDICA")) {
			}else {
				fiForncontaEntity.setContapessoajuridica("N");
			}
			
		}

		if (formularioFornecedor.getTipoDeConta()!=null) {
			if (formularioFornecedor.getTipoDeConta().equals("CONTA CORRENTE INDIVIDUAL")) {
				fiForncontaEntity.setTipoconta("C");
			}else if (formularioFornecedor.getTipoDeConta().equals("CONTA CORRENTE CONJUNTA")) {
				fiForncontaEntity.setTipoconta("X");
			}else if (formularioFornecedor.getTipoDeConta().equals("DEPOSITO JUDICIAL INDIVIDUAL")) {
				fiForncontaEntity.setTipoconta("J");
			}else if (formularioFornecedor.getTipoDeConta().equals("DEPOSITO JUDICIAL CONJUNTA")) {
				fiForncontaEntity.setTipoconta("D");
			}else if (formularioFornecedor.getTipoDeConta().equals("POUPANCA INDIVIDUAL")) {
				fiForncontaEntity.setTipoconta("P");
			}else if (formularioFornecedor.getTipoDeConta().equals("POUPANCA CONJUNTA")) {
				fiForncontaEntity.setTipoconta("R");
			}
		}else {
		}
		
		
		if (formularioFornecedor.getCpfCnpjTitularConta().length()==11) {
			fiForncontaEntity.setTipoinscricao(1);
		}else {
			fiForncontaEntity.setTipoinscricao(2);
		}

		
		if (formularioFornecedor.getUfTitularConta()!=null) {
			fiForncontaEntity.setEstado(getUF(formularioFornecedor.getUfTitularConta()));
		}
		
		
		if (formularioFornecedor.getComplementoTitularConta()!=null) 
			fiForncontaEntity.setComplemento(formularioFornecedor.getComplementoTitularConta());
		
      
		insert(fiForncontaEntity);
		
		logger.info("Criando dados financeiros com sucesso ! ");

	}	

	private String preencheZerosAEsquerda(String nro, int tamanhoMaximo) {
		int tamanho = nro.length();
		
		while(tamanho < tamanhoMaximo){
			nro = "0" + nro;
			tamanho++;
		}
		
		return nro;
	}



	private String getUF(String estado){
		StringTokenizer tokenizer = new StringTokenizer(estado, "/");
		if(tokenizer.countTokens() == 2){
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}
		return "";
	}
	
	public String getBanco(String value) {
		if (value!=null) {
			return value.substring(0,value.indexOf(" ")).trim();
		}else {
			return "";
		}
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - DadosFinanceiroFornecedor ");
	}

}

