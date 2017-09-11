package br.com.seta.processo.parse;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import br.com.seta.processo.dto.Erros;
import br.com.seta.processo.dto.FormularioFornecedor;

public final class ParseODS extends ParsePlanilha {

	private FormularioFornecedor formularioFornecedor = new FormularioFornecedor();
	private Sheet sheet;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public FormularioFornecedor parseBuild(File file) throws IOException {
		sheet = SpreadSheet.createFromFile(file).getSheet(0);
		

		if (hasValue("J4", sheet))
			formularioFornecedor.setTipoSolicatacao(getAsString("J4", sheet));

		if (hasValue("AJ4", sheet))
			formularioFornecedor.setComprador(getAsString("AJ4", sheet));

		if (hasValue("AJ5", sheet))
			formularioFornecedor.setCategoria(getAsString("AJ5", sheet));

		if (hasValue("BD4", sheet))
			formularioFornecedor.setTipoFornecedor(getAsString("BD4", sheet));

		if (hasValue("A7", sheet))
			formularioFornecedor.setRazaoSocialFornedor(getAsString("A7", sheet));
		
		if (hasValue("A7", sheet)) {
			String razaoReduzido = getAsString("A7", sheet);
			if(razaoReduzido.length()>14) {
				razaoReduzido = razaoReduzido.substring(0, 14);
			}
			formularioFornecedor.setNomeReduzido(razaoReduzido);
		}
				
		if (hasValue("X7", sheet))
			formularioFornecedor.setCpnjJuridico(getAsStringNumber("X7", sheet));

		if (hasValue("AI7", sheet))
			formularioFornecedor.setInscricaoEstadual(getAsString("AI7", sheet));

		if (hasValue("AR7", sheet));
			formularioFornecedor.setDataFundacao(getAsDate("AR7", sheet));

		if (hasValue("AY7", sheet))
			formularioFornecedor.setInscricaoMunicipal(getAsString("AY7", sheet));

		if (hasValue("BE7", sheet))
			formularioFornecedor.setSimplesNacional(getAsString("BE7", sheet));

		if (hasValue("A10", sheet))
			formularioFornecedor.setNome(getAsString("A10", sheet));
		
		if (hasValue("A10", sheet)) {
			String nomeReduzido = getAsString("A10", sheet);
			if(nomeReduzido.length()>14) {
				nomeReduzido = nomeReduzido.substring(0, 14);
			}
			formularioFornecedor.setNomeReduzido(nomeReduzido);
		}

		if (hasValue("X10", sheet))
			formularioFornecedor.setCpfFisica(getAsStringNumber("X10", sheet));

		if (hasValue("AI10", sheet))
			formularioFornecedor.setRg(getAsString("AI10", sheet));

		if (hasValue("AR10", sheet));
			formularioFornecedor.setDataNascimento(getAsDate("AR10", sheet));

		if (hasValue("AY10", sheet))
			formularioFornecedor.setOrgaoExpUF(getAsString("AY10", sheet));

		if (hasValue("BE10", sheet));
			formularioFornecedor.setDataExpedicao(getAsDate("BE10", sheet));

		if (hasValue("A13", sheet)) {
			String estadoUF = ParseEstados.findUF(getAsString("A13", sheet));
			formularioFornecedor.setEstadoUf(estadoUF);
		}

		if (hasValue("J13", sheet))
			formularioFornecedor.setCidade(getAsString("J13", sheet));

		if (hasValue("AI13", sheet)) {
			String cep = getAsStringNumber("AI13", sheet);
			cep = String.format("%08d", Integer.valueOf(cep));
			formularioFornecedor.setCep(cep);
		}

		if (hasValue("AV13", sheet))
			formularioFornecedor.setBairro(getAsString("AV13", sheet));

		if (hasValue("A15", sheet)) {
			String tipoLogradouro = getAsString("A15", sheet);
			tipoLogradouro = retiraCaracteresEspeciais(tipoLogradouro);
			formularioFornecedor.setTipoLogradouro(tipoLogradouro.trim());	
		}

		if (hasValue("J15", sheet))
			formularioFornecedor.setLogradouro(getAsString("J15", sheet));

		if (hasValue("AI15", sheet))
			formularioFornecedor.setNumero(getAsString("AI15", sheet));

		if (hasValue("AR15", sheet))
			formularioFornecedor.setComplemento(getAsString("AR15", sheet));

		if (hasValue("A17", sheet))
			formularioFornecedor.setEmailContato(getAsString("A17", sheet));

		if (hasValue("AI17", sheet))
			formularioFornecedor.setHomePage(getAsString("AI17", sheet));

		if (hasValue("A19", sheet))
			formularioFornecedor.setEmailParaNf(getAsString("A19", sheet));

		if (hasValue("AI19", sheet)) {
			String ddd = getAsStringNumber("AI19", sheet);
			if(ddd.length()>2) {
				ddd = ddd.substring(0, 2);
			}
			formularioFornecedor.setDdFoneComercial(ddd);
		}
		
		if (hasValue("AI19", sheet)) {
			String telefone = getAsStringNumber("AI19", sheet);
			if(telefone.length()>2) {
				telefone = telefone.substring(2);
			}
			formularioFornecedor.setFoneComercial(telefone);
		}
						
		if (hasValue("AT19", sheet)) {
			String ddd = getAsStringNumber("AT19", sheet);
			if(ddd.length()>2) {
				ddd = ddd.substring(0, 2);
			}
			formularioFornecedor.setDdFoneFiscal(ddd);
		}
		
		if (hasValue("AT19", sheet)) {
			String telefone = getAsStringNumber("AT19", sheet);
			if(telefone.length()>2) {
				telefone = telefone.substring(2);
			}
			formularioFornecedor.setFoneFiscal(telefone);
		}
		if (hasValue("BE19", sheet)) {
			String ddd = getAsStringNumber("BE19", sheet);
			if(ddd.length()>2) {
				ddd = ddd.substring(0, 2);
			}
			formularioFornecedor.setDdFoneFinanceiro(ddd);
		}

		if (hasValue("BE19", sheet)) {
			String telefone = getAsStringNumber("BE19", sheet);
			if(telefone.length()>2) {
				telefone = telefone.substring(2);
			}
			formularioFornecedor.setFoneFinanceiro(telefone);
		}
				
		if (hasValue("M21", sheet))
			formularioFornecedor.setFormaPagamento(getAsString("M21", sheet));
		
		if (hasValue("M22", sheet))
			formularioFornecedor.setTipoDaConta(getAsString("M22", sheet));

		if (hasValue("M23", sheet))
			formularioFornecedor.setBancoTitularConta(getAsString("M23", sheet));

		if (hasValue("M24", sheet))
			formularioFornecedor.setAgenciaTitularConta(getAsString("M24", sheet));
		
		if (hasValue("Z24", sheet))
			formularioFornecedor.setDigitoAgenciaTitularConta(getAsString("Z24", sheet));

		if (hasValue("M25", sheet))
			formularioFornecedor.setContaTitularConta(getAsString("M25", sheet));
		
		if (hasValue("Z25", sheet))
			formularioFornecedor.setDigitoContaTitularConta(getAsString("Z25", sheet));

		if (hasValue("M26", sheet))
			formularioFornecedor.setTipoDeConta(getAsString("M26", sheet));

		if (hasValue("M27", sheet))
			formularioFornecedor.setPrazoPagamento(getAsString("M27", sheet));

		if (hasValue("AU21", sheet))
			formularioFornecedor.setNomeTitularConta(getAsString("AU21", sheet));

		if (hasValue("AU22", sheet))
			formularioFornecedor.setCpfCnpjTitularConta(getAsStringNumber("AU22", sheet));

		if (hasValue("AU23", sheet))
			formularioFornecedor.setEnderecoTitularConta(getAsString("AU23", sheet));

		if (hasValue("BN23", sheet))
			formularioFornecedor.setNumeroTitularConta(getAsString("BN23", sheet));

		if (hasValue("AU24", sheet))
			formularioFornecedor.setBairroTitularConta(getAsString("AU24", sheet));

		if (hasValue("AU25", sheet))
			formularioFornecedor.setCidadeTitularConta(getAsString("AU25", sheet));
		
		if (hasValue("BN25", sheet)) {
			String estadoUF = ParseEstados.findUF(getAsString("BN25", sheet));
			formularioFornecedor.setUfTitularConta(estadoUF);
		}

		if (hasValue("AU26", sheet)) {
			String cep = getAsStringNumber("AU26", sheet);
			cep = String.format("%08d", Integer.valueOf(cep));
			formularioFornecedor.setCepTitularConta(cep);
		}

		if (hasValue("AU27", sheet))
			formularioFornecedor.setObservacaoTitularConta(getAsString("AU27", sheet));
		
		
		//- NEGOCIAÇÃO E ENTREGA

		if (hasValue("M29", sheet))
			formularioFornecedor.setIndenizaTroca(getAsString("M29", sheet));
		
		if (hasValue("M30", sheet))
			formularioFornecedor.setRecebeNfDevolucao(getAsString("M30", sheet));

		if (hasValue("M31", sheet))
			formularioFornecedor.setNegocicacaoBoleto(getAsString("M31", sheet));
		
		if (hasValue("M32", sheet))
			formularioFornecedor.setAcordoComercial(getAsString("M32", sheet));

		if (hasValue("M33", sheet))
			formularioFornecedor.setTrocaNfBonificacao(getAsString("M33", sheet));

		if (hasValue("M34", sheet))
			formularioFornecedor.setTrocaProdutoProduto(getAsString("M34", sheet));

		if (hasValue("M35", sheet))
			formularioFornecedor.setTrocaRecolheProduto(getAsString("M35", sheet));

		if (hasValue("AU29", sheet))
			formularioFornecedor.setPrazoEntrega(getAsString("AU29", sheet));

		if (hasValue("AU30", sheet))
			formularioFornecedor.setPrazoAtrasoEntrega(getAsString("AU30", sheet));

		if (hasValue("AU31", sheet))
			formularioFornecedor.setPrazoVisita(getAsString("AU31", sheet));

		if (hasValue("AU32", sheet))
			formularioFornecedor.setTipoFrete(getAsString("AU32", sheet));

		if (hasValue("AU33", sheet))
			formularioFornecedor.setObservacaoEntrega(getAsString("AU33", sheet));
		
		
		//- RESPONSAVEL PARA ACORDOS
				
		if (hasValue("M38", sheet))
			formularioFornecedor.setNomeCompletoReponsavelAcordo(getAsString("M38", sheet));
		
		if (hasValue("M39", sheet))
			formularioFornecedor.setCpfReponsavelAcordo(getAsStringNumber("M39", sheet));

		if (hasValue("M40", sheet))
			formularioFornecedor.setRgReponsavelAcordo(getAsStringNumber("M40", sheet));

		if (hasValue("M41", sheet))
			formularioFornecedor.setCargoReponsavelAcordo(getAsString("M41", sheet));

		if (hasValue("M42", sheet)) {
			String ddd = getAsStringNumber("M42", sheet);
			if(ddd.length()>2) {
				ddd = ddd.substring(0, 2);
			}
			formularioFornecedor.setDdFoneReponsavelAcordo(ddd);
		}
		
		if (hasValue("M42", sheet)) {
			String telefone = getAsStringNumber("M42", sheet);
			if(telefone.length()>2) {
				telefone = telefone.substring(2);
			}
			formularioFornecedor.setFoneReponsavelAcordo(telefone);
		}

		if (hasValue("M43", sheet))
			formularioFornecedor.setEmailReponsavelAcordo(getAsString("M43", sheet));

		
		//- PREPOSTO REPRESENTANTE
		
		if (hasValue("AU38", sheet))
			formularioFornecedor.setNomePrepostoRepresentante(getAsString("AU38", sheet));

		if (hasValue("AU39", sheet))
			formularioFornecedor.setCpfPrepostoRepresentante(getAsStringNumber("AU39", sheet));

		if (hasValue("AU40", sheet))
			formularioFornecedor.setRgPrepostoRepresentante(getAsStringNumber("AU40", sheet));

		if (hasValue("AU41", sheet))
			formularioFornecedor.setCargoPrepostoRepresentante(getAsString("AU41", sheet));

		if (hasValue("AU42", sheet)) {
			String ddd = getAsStringNumber("AU42", sheet);
			if(ddd.length()>2) {
				ddd = ddd.substring(0, 2);
			}
			formularioFornecedor.setDdFonePrepostoRepresentante(ddd);
		}
		
		if (hasValue("AU42", sheet)) {
			String telefone = getAsStringNumber("AU42", sheet);
			if(telefone.length()>2) {
				telefone = telefone.substring(2);
			}
			formularioFornecedor.setFonePrepostoRepresentante(telefone);
		}
		
		if (hasValue("AU43", sheet))
			formularioFornecedor.setEmailPrepostoRepresentante(getAsString("AU43", sheet));
		
		return formularioFornecedor;
	}
	
	private String getAsString(String cellString, Sheet sheet){
		return sheet.getCellAt(cellString).getElement().getValue().toUpperCase();
	}
	
	private String getAsStringNumber(String cellString, Sheet sheet){
		String stringValue = getAsString(cellString, sheet);
		return apenasNumeros(stringValue);
	}
	
	private Date getAsDate(String cellString, Sheet sheet){
		String stringValue = getAsString(cellString, sheet);
		try {
			if(stringValue == null || stringValue.trim().isEmpty())
				return null;
			
			return dateFormatter.parse(stringValue);
		} catch (ParseException e) {
			Erros erro = new Erros();
			erro.setCelula(cellString);
			erro.setMotivo(e.getMessage());
			formularioFornecedor.getErros().add(erro);
			return null;
		}
	}
	
	private boolean hasValue(String cellString, Sheet sheet){
		if(sheet.getCellAt(cellString) == null || sheet.getCellAt(cellString).getElement().getValue() == null )
			return false;
		
		return true;
	}	
	
	private String retiraCaracteresEspeciais(String tipoLogradouro) {
		String retorno = "";
		byte[] tipo = tipoLogradouro.getBytes();
		for(int i = 0; i < tipo.length; i++) {
		    char character = (char) tipo[i];
		    int ascii = character;
		    if((ascii!=65440) && (ascii!=65474)) {
		    	retorno += (char)tipo[i];			        	
		    }
		}
		return retorno.trim();
	}
}
