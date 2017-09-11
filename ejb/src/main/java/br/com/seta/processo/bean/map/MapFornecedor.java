package br.com.seta.processo.bean.map;

import java.math.BigDecimal;
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

import br.com.seta.processo.bean.Cidades;
import br.com.seta.processo.bean.DadosFinanceiroFornecedor;
import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.bean.MafFonecedoreContatos;
import br.com.seta.processo.bean.MafFonecedores;
import br.com.seta.processo.bean.Sequences;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.entity.GeCidadeEntity;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.entity.MafForneccontatoEntity;
import br.com.seta.processo.entity.MafFornecedorEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.exceptions.ParseException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.parse.ParseNormalizacaoString;
import br.com.seta.processo.parse.ParseToUpperCase;


@Stateless(name="MapFornecedor")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapFornecedor {

	@Inject	Logger logger;
	@Inject	private Cidades cidades;
	@Inject	private GePessoas gePessoas;
	@Inject	private MafFonecedores mafFonecedores; 
	@Inject	private Sequences sequences; 
	@Inject	private MafFonecedoreContatos mafFonecedoreContatos;
	@Inject private DadosFinanceiroFornecedor dadosFinanceiroFornecedor;
	
	private static final java.util.Date _DATE = new java.util.Date();
	private ParseNormalizacaoString<FormularioFornecedor> parseNormalizacaoString = new ParseNormalizacaoString<FormularioFornecedor>();
	private ParseToUpperCase<FormularioFornecedor> parseUpperCase = new ParseToUpperCase<FormularioFornecedor>();

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Mapemento de Fornecedores");
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String create(FormularioFornecedor formularioFornecedor) throws ParseException {
		
		 MafFornecedorEntity  mafFornecedorEntity = new MafFornecedorEntity();
		 GePessoaEntity gePessoaEntity = new GePessoaEntity();
		 MafForneccontatoEntity mafForneccontatoEntity = new MafForneccontatoEntity();
		 
		
		parseNormalizacaoString.parse(formularioFornecedor);
		parseUpperCase.parse(formularioFornecedor);
		BigDecimal sequence;

		try {
			sequence = sequences.nextValue("GE_PESSOA");
		} catch (DaoException e) {
			return "Erro ao tentar criar sequencia pessoa [ "+e.getCause()+ " ] "; 
		}


		if (formularioFornecedor.getCpnjJuridico()!=null) {
			
			gePessoaEntity.setFisicajuridica("J");
			gePessoaEntity.setInscricaorg(formularioFornecedor.getInscricaoEstadual());
			gePessoaEntity.setDtanascfund(formularioFornecedor.getDataFundacao());


			if(formularioFornecedor.getRazaoSocialFornedor()!=null) {
				if (formularioFornecedor.getRazaoSocialFornedor().length() >=40) {
					gePessoaEntity.setNomerazao(formularioFornecedor.getRazaoSocialFornedor().substring(0,40));
				}else {
					gePessoaEntity.setNomerazao(formularioFornecedor.getRazaoSocialFornedor());
				}
			}

			if (formularioFornecedor.getRazaoSocialReduzido()!=null) {
				if (formularioFornecedor.getRazaoSocialReduzido().length()>=30) {
					gePessoaEntity.setFantasia(formularioFornecedor.getRazaoSocialReduzido().substring(0,30));
					gePessoaEntity.setPalavrachave(formularioFornecedor.getRazaoSocialReduzido().substring(0,30));
				}else {				
					gePessoaEntity.setFantasia(formularioFornecedor.getRazaoSocialReduzido());
					gePessoaEntity.setPalavrachave(formularioFornecedor.getRazaoSocialReduzido());
				}
			}


			if(formularioFornecedor.getCpnjJuridico()!=null) {
				if (convertLong(formularioFornecedor.getCpnjJuridico())!=null){
					Long cnpj = getCnpjSemDigito(formularioFornecedor.getCpnjJuridico());
					Long digitoCnpj = getDigitoCnpj(formularioFornecedor.getCpnjJuridico());
					gePessoaEntity.setNrocgccpf(cnpj);
					gePessoaEntity.setDigcgccpf(digitoCnpj);
					mafForneccontatoEntity.setNrocgccpf(cnpj);
					mafForneccontatoEntity.setDigcgccpf(digitoCnpj);
				}
			}

		}else {
			
			gePessoaEntity.setFisicajuridica("F");
			gePessoaEntity.setInscricaorg(formularioFornecedor.getRg());
			gePessoaEntity.setDtanascfund(formularioFornecedor.getDataNascimento());

			if (formularioFornecedor.getNome()!=null) {
				if (formularioFornecedor.getNome().length() >=40 ) {
					gePessoaEntity.setNomerazao(formularioFornecedor.getNome().substring(0,40));
				}else {
					gePessoaEntity.setNomerazao(formularioFornecedor.getNome());
				}
			}	

			if(formularioFornecedor.getNomeReduzido()!=null) {
				if (formularioFornecedor.getNomeReduzido().length() >=30) {
					gePessoaEntity.setFantasia(formularioFornecedor.getNomeReduzido().substring(0,30));
					gePessoaEntity.setPalavrachave(formularioFornecedor.getNomeReduzido().substring(0,30));
				}else {
					gePessoaEntity.setFantasia(formularioFornecedor.getNomeReduzido());
					gePessoaEntity.setPalavrachave(formularioFornecedor.getNomeReduzido());
				}
			}	


			if(formularioFornecedor.getCpfFisica()!=null) {

				if (convertLong(formularioFornecedor.getCpfFisica())!=null) {
					Long digitoCpf = getDigitoCpf(formularioFornecedor.getCpfFisica());
					Long cpf = getCpfSemDigito(formularioFornecedor.getCpfFisica());
					gePessoaEntity.setNrocgccpf(cpf);
					gePessoaEntity.setDigcgccpf(digitoCpf);
					mafForneccontatoEntity.setNrocgccpf(cpf);
					mafForneccontatoEntity.setDigcgccpf(digitoCpf);
				}	

			}
		}

		gePessoaEntity.setVersao((byte)0);
		gePessoaEntity.setDtaativacao(_DATE);
		gePessoaEntity.setStatus("A");	
		gePessoaEntity.setDtaexpedicaodoc(formularioFornecedor.getDataExpedicao());
		
		if(formularioFornecedor.getOrgaoExpUF() != null && !formularioFornecedor.getOrgaoExpUF().trim().isEmpty()){
			String[] orgaoEmissorUf = formularioFornecedor.getOrgaoExpUF().split("/");
			gePessoaEntity.setOrgexp(orgaoEmissorUf[0]);
			gePessoaEntity.setOrgexpuf(orgaoEmissorUf[1]);
		}

		if (formularioFornecedor.getCidade()!=null)
			gePessoaEntity.setCidade(formularioFornecedor.getCidade());

		if (formularioFornecedor.getBairro()!=null)
			gePessoaEntity.setBairro(formularioFornecedor.getBairro());

		if (formularioFornecedor.getEstadoUf()!=null){
			String uf = getUF(formularioFornecedor.getEstadoUf());
			gePessoaEntity.setUf(uf);
		}	
		/*** Vou procurar seqciade caso não encontre irei cadastra sem a sequencia da cidade ****/

		GeCidadeEntity  geCidadeEntity = new GeCidadeEntity();

		try {
			geCidadeEntity = cidades.getGeCidade(formularioFornecedor.getCidade(), formularioFornecedor.getEstadoUf());
		} catch (DaoException e) {
			logger.error("Cidade não será mapeada");
		}

		if (geCidadeEntity!=null) {
			gePessoaEntity.setSeqcidade(geCidadeEntity.getSeqcidade());
		}

		if(formularioFornecedor.getCep()!=null)
			gePessoaEntity.setCep(formularioFornecedor.getCep());		

		if(formularioFornecedor.getLogradouro()!=null)
			gePessoaEntity.setLogradouro(formularioFornecedor.getLogradouro());

		if(formularioFornecedor.getNumero()!=null)
			gePessoaEntity.setNrologradouro(formularioFornecedor.getNumero());

		if(formularioFornecedor.getComplemento()!=null)
			gePessoaEntity.setCmpltologradouro(formularioFornecedor.getComplemento());

		if (formularioFornecedor.getEmailContato()!=null)
			gePessoaEntity.setEmail(formularioFornecedor.getEmailContato());

		if (formularioFornecedor.getHomePage()!=null)
			gePessoaEntity.setHomepage(formularioFornecedor.getHomePage());

		if (formularioFornecedor.getEmailParaNf()!=null)
			gePessoaEntity.setEmailnfe(formularioFornecedor.getEmailParaNf());

		if (formularioFornecedor.getFoneComercial()!=null)
			gePessoaEntity.setFonenro1(formularioFornecedor.getFoneComercial());

		if (formularioFornecedor.getDdFoneComercial()!=null)
			gePessoaEntity.setFoneddd1(formularioFornecedor.getDdFoneComercial());


		if (formularioFornecedor.getFoneFiscal()!=null)
			gePessoaEntity.setFonenro2(formularioFornecedor.getFoneFiscal());

		if (formularioFornecedor.getDdFoneFiscal()!=null)
			gePessoaEntity.setFoneddd2(formularioFornecedor.getDdFoneFiscal());

		if(formularioFornecedor.getFoneFinanceiro()!=null)
			gePessoaEntity.setFonenro3(formularioFornecedor.getFoneFinanceiro());

		if(formularioFornecedor.getDdFoneFinanceiro()!=null)
			gePessoaEntity.setFoneddd3(formularioFornecedor.getDdFoneFinanceiro());


		if(formularioFornecedor.getInscricaoMunicipal()!=null)
			gePessoaEntity.setInscmunicipal(formularioFornecedor.getInscricaoMunicipal());	


		if(formularioFornecedor.isContribuinteIPI()) {
			gePessoaEntity.setIndcontribipi("S");
		}else {
			gePessoaEntity.setIndcontribipi("N");
		}

		if(formularioFornecedor.isIeProdutorRural()) {
			gePessoaEntity.setIndprodrural("S");
		}else {
			gePessoaEntity.setIndprodrural("N");
		}


		if(formularioFornecedor.isMicroEmpresa()) {
			gePessoaEntity.setIndmicroempresa("S");
		}else {
			gePessoaEntity.setIndmicroempresa("N");
		}


		/**** Agora vamos criar o fonecedor seta a maf fornecedor ****/
		mafFornecedorEntity.setNrobaseexportacao(0);

		if (formularioFornecedor.getTipoFrete()!=null) {
			if (formularioFornecedor.getTipoFrete().trim().equals("FOB")) {
				mafFornecedorEntity.setCondicaofrete("F");
			}else if (formularioFornecedor.getTipoFrete().trim().equals("CIF")) {
				mafFornecedorEntity.setCondicaofrete("C");
			}else {	
			}	
		}

		mafFornecedorEntity.setSolprorrogvencto("N");
		mafFornecedorEntity.setStatusgeral("A");
		mafForneccontatoEntity.setRg(formularioFornecedor.getRg());

		if (formularioFornecedor.isMicroEmpresa()) {
			mafFornecedorEntity.setMicroempresa("S");
		}else {
			mafFornecedorEntity.setMicroempresa("N");
		}

		if (formularioFornecedor.getTipoFornecedor()!=null) {
			
			if (formularioFornecedor.getTipoFornecedor().trim().equals("PRESTADOR DE SERVICO")) {
				mafFornecedorEntity.setTipfornecedor("P");
			}else if (formularioFornecedor.getTipoFornecedor().trim().equals("FORNECEDOR") ) {
				mafFornecedorEntity.setTipfornecedor("D");
			}
		}

		if (formularioFornecedor.getDadosAdicionais()!=null)
			mafFornecedorEntity.setObservacao(formularioFornecedor.getDadosAdicionais());

		gePessoaEntity.setSeqpessoa(sequence);
		mafFornecedorEntity.setSeqfornecedor(sequence);			

		try {
			gePessoas.create(gePessoaEntity);
		} catch (DaoException e) {
			return "Erro ao tentar criar pessoa [ "+e.getCause()+ " ] ";     
		}

		try {		

			mafFonecedores.create(mafFornecedorEntity);
			mafForneccontatoEntity.setSeqfornecedor(sequence);
			
			
			mafForneccontatoEntity.setNomerazao(formularioFornecedor.getNomeCompletoReponsavelAcordo());
			mafForneccontatoEntity.setCargo(formularioFornecedor.getCargoReponsavelAcordo());
			mafForneccontatoEntity.setFone(formularioFornecedor.getDdFoneReponsavelAcordo()+"-"+formularioFornecedor.getFoneReponsavelAcordo());
			mafForneccontatoEntity.setRg(formularioFornecedor.getRgReponsavelAcordo());
			mafForneccontatoEntity.setEmail(formularioFornecedor.getEmailReponsavelAcordo());
			mafForneccontatoEntity.setIndprincipal("S");


			if (formularioFornecedor.getCpfReponsavelAcordo()!=null) {
				if (formularioFornecedor.getCpfReponsavelAcordo().length()==11) {
					Long digitoCpf = getDigitoCpf(formularioFornecedor.getCpfReponsavelAcordo());
					Long cpf = getCpfSemDigito(formularioFornecedor.getCpfReponsavelAcordo());
					mafForneccontatoEntity.setDigcgccpf(digitoCpf);
					mafForneccontatoEntity.setNrocgccpf(cpf);
				}else if (formularioFornecedor.getCpfReponsavelAcordo().length()==14) {
					Long cnpj = getCnpjSemDigito(formularioFornecedor.getCpfReponsavelAcordo());
					Long digitoCnpj = getDigitoCnpj(formularioFornecedor.getCpfReponsavelAcordo());
					mafForneccontatoEntity.setNrocgccpf(cnpj);
					mafForneccontatoEntity.setDigcgccpf(digitoCnpj);
				}
			}



			/*** Criando contatos ***/
			try {
				mafFonecedoreContatos.create(mafForneccontatoEntity);
			}catch (Exception e){
				e.printStackTrace();
			}



			mafForneccontatoEntity.setNomerazao(formularioFornecedor.getNomePrepostoRepresentante());
			mafForneccontatoEntity.setCargo(formularioFornecedor.getCargoPrepostoRepresentante());
			mafForneccontatoEntity.setFone(formularioFornecedor.getDdFonePrepostoRepresentante()+"-"+formularioFornecedor.getFonePrepostoRepresentante());
			mafForneccontatoEntity.setRg(formularioFornecedor.getRgPrepostoRepresentante());
			mafForneccontatoEntity.setEmail(formularioFornecedor.getEmailPrepostoRepresentante());

			mafForneccontatoEntity.setIndprincipal("");
			if (formularioFornecedor.getCpfPrepostoRepresentante()!=null) {
				if (formularioFornecedor.getCpfPrepostoRepresentante().length()==11) {
					Long digitoCpf = getDigitoCpf(formularioFornecedor.getCpfPrepostoRepresentante());
					Long cpf = getCpfSemDigito(formularioFornecedor.getCpfPrepostoRepresentante());
					mafForneccontatoEntity.setDigcgccpf(digitoCpf);
					mafForneccontatoEntity.setNrocgccpf(cpf);
				}else if (formularioFornecedor.getCpfPrepostoRepresentante().length()==14) {
					Long cnpj = getCnpjSemDigito(formularioFornecedor.getCpfPrepostoRepresentante());
					Long digitoCnpj = getDigitoCnpj(formularioFornecedor.getCpfPrepostoRepresentante());
					mafForneccontatoEntity.setNrocgccpf(cnpj);
					mafForneccontatoEntity.setDigcgccpf(digitoCnpj);
				}
			}


			/*** Criando contatos ***/
			try {
				mafFonecedoreContatos.create(mafForneccontatoEntity);
			}catch (Exception e){
				e.printStackTrace();
			}


			/*** Criando dados financeiro ***/
			try {
				dadosFinanceiroFornecedor.create(formularioFornecedor, sequence.intValue());
			}catch (Exception e){
				e.printStackTrace();
			}



		} catch (DaoException e) {
			return "Erro ao tentar criar fornecedor [ "+e.getCause()+ " ] ";  
		}


		return sequence.toString();
		//return "Fornecedor criado com sucesso, número do forncedor na consinco [ "+sequence+ " ] ";
	}

	private Long convertLong(String value) {

		Long retunValue = null;

		if (value.contains("-")) {
			value = value.replace("-","");
		}
		if (value.contains(".")) {
			value = value.replace(".","");
		}
		if (value.contains("/")) {
			value = value.replace("/","");
		}
		if (value.contains("_")) {
			value = value.replace("_","");
		}
		if (value.contains(",")) {
			value = value.replace(",","");
		}	
		try {
			retunValue = Long.parseLong(value);
		}catch(Exception e ) {
		}
		return retunValue;
	}

	private Long getCpfSemDigito(String cpf){
		cpf = limpaMascaraCpfCnpj(cpf);

		if(cpf.length() < 9) Long.parseLong(cpf);

		return Long.parseLong(cpf.substring(0, 9));
	}

	private Long getDigitoCpf(String cpf){
		cpf = limpaMascaraCpfCnpj(cpf);

		if(cpf.length() < 11) return null;

		return  Long.parseLong(cpf.substring(9, 11));
	}

	private Long getCnpjSemDigito(String cnpj){
		cnpj = limpaMascaraCpfCnpj(cnpj);

		if(cnpj.length() < 12) return Long.parseLong(cnpj);

		return Long.parseLong(cnpj.substring(0, 12));
	}

	private Long getDigitoCnpj(String cnpj){
		cnpj = limpaMascaraCpfCnpj(cnpj);
		if(cnpj.length() < 14) return null;
		return Long.parseLong(cnpj.substring(12, 14));
	}

	private String limpaMascaraCpfCnpj(String valor){
		return valor.replace(".", "").replace("-", "").replace("_", "").replace("/", "");
	}

	private String getUF(String estado){
		StringTokenizer tokenizer = new StringTokenizer(estado, "/");
		if(tokenizer.countTokens() == 2){
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}

		return "";
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Mapemento de Fornecedores");
	}	
}
