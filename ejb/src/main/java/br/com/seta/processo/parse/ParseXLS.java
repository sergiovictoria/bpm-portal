package br.com.seta.processo.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;

import br.com.seta.processo.dto.Erros;
import br.com.seta.processo.dto.FormularioFornecedor;

public final class ParseXLS extends ParsePlanilha {
	
	private HSSFWorkbook workBook;
	private Sheet  sheet;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	private FormularioFornecedor formularioFornecedor = new FormularioFornecedor();
	private final static NumberFormat integerFormater = NumberFormat.getIntegerInstance();
	static{
		integerFormater.setGroupingUsed(false);
	}
	
	@Override
	public FormularioFornecedor parseBuild(File file) throws IOException {

			InputStream is = new FileInputStream(file);
			workBook    = new HSSFWorkbook(is); 
			sheet       = workBook.getSheetAt(0);

			if (getObject("J4", sheet)!=null)
				formularioFornecedor.setTipoSolicatacao((String)getObject("J4", sheet));

			if (getObject("AJ4", sheet)!=null)
				formularioFornecedor.setComprador((String)getObject("AJ4", sheet));

			if (getObject("AJ5", sheet)!=null)
				formularioFornecedor.setCategoria((String)getObject("AJ5", sheet));

			if (getObject("BD4", sheet)!=null)
				formularioFornecedor.setTipoFornecedor((String)getObject("BD4", sheet));

			if (getObject("A7", sheet)!=null)
				formularioFornecedor.setRazaoSocialFornedor((String)getObject("A7", sheet));
			
			if (getObject("A7", sheet)!=null) {
				String razaoReduzido = (String)getObject("A7", sheet);
				razaoReduzido = razaoReduzido.substring(0, 14);
				formularioFornecedor.setNomeReduzido(razaoReduzido);
			}
			
			if (getObject("X7", sheet)!=null){
				String cnpj = apenasNumeros((String)getObject("X7", sheet));
				formularioFornecedor.setCpnjJuridico(cnpj);
			}
			if (getObject("AI7", sheet)!=null)
				formularioFornecedor.setInscricaoEstadual((String)getObject("AI7", sheet));

			if (getObject("AR7", sheet)!=null);
				formularioFornecedor.setDataFundacao((Date)getObject("AR7", sheet));

			if (getObject("AY7", sheet)!=null)
				formularioFornecedor.setInscricaoMunicipal((String)getObject("AY7", sheet));

			if (getObject("BE7", sheet)!=null)
				formularioFornecedor.setSimplesNacional((String)getObject("BE7", sheet));

			if (getObject("A10", sheet)!=null)
				formularioFornecedor.setNome((String)getObject("A10", sheet));
			
			if (getObject("A10", sheet)!=null) {
				String nomeReduzido = (String)getObject("A10", sheet);
				nomeReduzido = nomeReduzido.substring(0, 14);
				formularioFornecedor.setNomeReduzido(nomeReduzido);
			}
			
			if (getObject("X10", sheet)!=null) {
				String cpfFisica = apenasNumeros((String)getObject("X10", sheet));
				formularioFornecedor.setCpfFisica(cpfFisica);
			}

			if (getObject("AI10", sheet)!=null) {
				String rg = apenasNumeros((String)getObject("AI10", sheet));
				formularioFornecedor.setRg(rg);
			}

			if (getObject("AR10", sheet)!=null);
				formularioFornecedor.setDataNascimento((Date)getObject("AR10", sheet));

			if (getObject("AY10", sheet)!=null)
				formularioFornecedor.setOrgaoExpUF((String)getObject("AY10", sheet));

			if (getObject("BE10", sheet)!=null);
				formularioFornecedor.setDataExpedicao((Date)getObject("BE10", sheet));

			if (getObject("A13", sheet)!=null) {
				String estadoUF = ParseEstados.findUF((String)getObject("A13", sheet));
				formularioFornecedor.setEstadoUf(estadoUF);
			}

			if (getObject("J13", sheet)!=null)
				formularioFornecedor.setCidade((String)getObject("J13", sheet));

			if (getObject("AI13", sheet)!=null) {
				String cep = apenasNumeros((String)getObject("AI13", sheet));
				cep = String.format("%08d", Integer.valueOf(cep)); 
				formularioFornecedor.setCep(cep);
			}

			if (getObject("AV13", sheet)!=null)
				formularioFornecedor.setBairro((String)getObject("AV13", sheet));

			if (getObject("A15", sheet)!=null) {
				String tipoLogradouro = (String)getObject("A15", sheet);
				tipoLogradouro = retiraCaracteresEspeciais(tipoLogradouro);
				formularioFornecedor.setTipoLogradouro(tipoLogradouro.trim());
			}

			if (getObject("J15", sheet)!=null)
				formularioFornecedor.setLogradouro((String)getObject("J15", sheet));

			if (getObject("AI15", sheet)!=null)
				formularioFornecedor.setNumero((String)getObject("AI15", sheet));

			if (getObject("AR15", sheet)!=null)
				formularioFornecedor.setComplemento((String)getObject("AR15", sheet));

			if (getObject("A17", sheet)!=null)
				formularioFornecedor.setEmailContato((String)getObject("A17", sheet));

			if (getObject("AI17", sheet)!=null)
				formularioFornecedor.setHomePage((String)getObject("AI17", sheet));

			if (getObject("A19", sheet)!=null)
				formularioFornecedor.setEmailParaNf((String)getObject("A19", sheet));

			if (getObject("AI19", sheet)!=null) {
				String ddd = apenasNumeros((String)getObject("AI19", sheet));
				ddd = ddd.substring(0, 2);
				formularioFornecedor.setDdFoneComercial(ddd);
			}
			
			if (getObject("AI19", sheet)!=null) {
				String telefone = apenasNumeros((String)getObject("AI19", sheet));
				telefone = telefone.substring(2);
				formularioFornecedor.setFoneComercial(telefone);
			}
			
			if (getObject("AT19", sheet)!=null) {
				String ddd = apenasNumeros((String)getObject("AT19", sheet));
				ddd = ddd.substring(0, 2);
				formularioFornecedor.setDdFoneFiscal(ddd);
			}
			
			if (getObject("AT19", sheet)!=null) {
				String telefone = apenasNumeros((String)getObject("AT19", sheet));
				telefone = telefone.substring(2);
				formularioFornecedor.setFoneFiscal(telefone);
			}
						
			if (getObject("BE19", sheet)!=null) {
				String ddd = apenasNumeros((String)getObject("BE19", sheet));
				ddd = ddd.substring(0, 2);
				formularioFornecedor.setDdFoneFinanceiro(ddd);
			}
						
			if (getObject("BE19", sheet)!=null) {
				String telefone = apenasNumeros((String)getObject("BE19", sheet));
				telefone = telefone.substring(2);
				formularioFornecedor.setFoneFinanceiro(telefone);
			}

			//- DADOS FINANCEIROS PARA PAGAMENTOS
			
			if (getObject("M21", sheet)!=null)
				formularioFornecedor.setFormaPagamento((String)getObject("M21", sheet));

			if (getObject("M22", sheet)!=null)
				formularioFornecedor.setTipoDaConta((String)getObject("M22", sheet));

			if(getObject("M23", sheet) != null)
				formularioFornecedor.setBancoTitularConta((String)getObject("M23", sheet));

			if (getObject("M24", sheet)!=null)
				formularioFornecedor.setAgenciaTitularConta((String)getObject("M24", sheet));
			
			if (getObject("Z24", sheet)!=null)
				formularioFornecedor.setDigitoAgenciaTitularConta((String)getObject("Z24", sheet));
			
			if (getObject("M25", sheet)!=null)
				formularioFornecedor.setContaTitularConta((String)getObject("M25", sheet));
			
			if (getObject("Z25", sheet)!=null)
				formularioFornecedor.setDigitoContaTitularConta((String)getObject("Z25", sheet));

			if (getObject("M26", sheet)!=null)
				formularioFornecedor.setTipoDeConta((String)getObject("M26", sheet));

			if (getObject("M27", sheet)!=null)
				formularioFornecedor.setPrazoPagamento((String)getObject("M27", sheet));

			if (getObject("AU21", sheet)!=null)
				formularioFornecedor.setNomeTitularConta((String)getObject("AU21", sheet));

			if (getObject("AU22", sheet)!=null)
				formularioFornecedor.setCpfCnpjTitularConta((String)getObject("AU22", sheet));

			if (getObject("AU23", sheet)!=null)
				formularioFornecedor.setEnderecoTitularConta((String)getObject("AU23", sheet));

			if (getObject("BN23", sheet)!=null)
				formularioFornecedor.setNumeroTitularConta((String)getObject("BN23", sheet));

			if (getObject("AU24", sheet)!=null)
				formularioFornecedor.setBairroTitularConta((String)getObject("AU24", sheet));

			if (getObject("AU25", sheet)!=null)
				formularioFornecedor.setCidadeTitularConta((String)getObject("AU25", sheet));
			
			if (getObject("BN25", sheet)!=null) {
				String estadoUF = ParseEstados.findUF((String)getObject("BN25", sheet));
				formularioFornecedor.setUfTitularConta(estadoUF);
			}
			
			if (getObject("AU26", sheet)!=null) {
				String cep = apenasNumeros((String)getObject("AU26", sheet));
				cep = String.format("%08d", Integer.valueOf(cep));
				formularioFornecedor.setCepTitularConta(cep);
			}

			if (getObject("AU27", sheet)!=null)
				formularioFornecedor.setObservacaoTitularConta((String)getObject("AU27", sheet));

			//- DADOS NEGOCIAÇÃO e ENTREGA
			
			if (getObject("M29", sheet)!=null)
				formularioFornecedor.setIndenizaTroca((String)getObject("M29", sheet));

			if (getObject("M30", sheet)!=null)
				formularioFornecedor.setRecebeNfDevolucao((String)getObject("M30", sheet));

			if (getObject("M31", sheet)!=null)
				formularioFornecedor.setNegocicacaoBoleto((String)getObject("M31", sheet));
			
			if (getObject("M32", sheet)!=null)
				formularioFornecedor.setAcordoComercial((String)getObject("M32", sheet));

			if (getObject("M33", sheet)!=null)
				formularioFornecedor.setTrocaNfBonificacao((String)getObject("M33", sheet));

			if (getObject("M34", sheet)!=null)
				formularioFornecedor.setTrocaProdutoProduto((String)getObject("M34", sheet));

			if (getObject("M35", sheet)!=null)
				formularioFornecedor.setTrocaRecolheProduto((String)getObject("M35", sheet));

			if (getObject("AU29", sheet)!=null)
				formularioFornecedor.setPrazoEntrega((String)getObject("AU29", sheet));

			if (getObject("AU30", sheet)!=null)
				formularioFornecedor.setPrazoAtrasoEntrega((String)getObject("AU30", sheet));

			if (getObject("AU31", sheet)!=null)
				formularioFornecedor.setPrazoVisita((String)getObject("AU31", sheet));

			if (getObject("AU32", sheet)!=null)
				formularioFornecedor.setTipoFrete((String)getObject("AU32", sheet));

			if (getObject("AU33", sheet)!=null)
				formularioFornecedor.setObservacaoEntrega((String)getObject("AU33", sheet));
			
			//- DADOS dos RESPONSÁVEIS para ACORDOS
					
			if (getObject("M38", sheet)!=null)
				formularioFornecedor.setNomeCompletoReponsavelAcordo((String)getObject("M38", sheet));
			
			if (getObject("M39", sheet)!=null) {
				String cpf = apenasNumeros((String)getObject("M39", sheet));
				formularioFornecedor.setCpfReponsavelAcordo(cpf);
			}

			if (getObject("M40", sheet)!=null)
				formularioFornecedor.setRgReponsavelAcordo((String)getObject("M40", sheet));

			if (getObject("M41", sheet)!=null)
				formularioFornecedor.setCargoReponsavelAcordo((String)getObject("M41", sheet));
			
			if (getObject("M42", sheet)!=null) {
				String ddd = apenasNumeros((String)getObject("M42", sheet));
				ddd = ddd.substring(0, 2);
				formularioFornecedor.setDdFoneReponsavelAcordo(ddd);
			}
			
			if (getObject("M42", sheet)!=null) {
				String telefone = apenasNumeros((String)getObject("M42", sheet));
				telefone = telefone.substring(2);
				formularioFornecedor.setFoneReponsavelAcordo(telefone);
			}
			
			if (getObject("M43", sheet)!=null)
				formularioFornecedor.setEmailReponsavelAcordo((String)getObject("M43", sheet));

			//- DADOS DO PREPOSTO / REPRESENTANTE
			
			if (getObject("AU38", sheet)!=null)
				formularioFornecedor.setNomePrepostoRepresentante((String)getObject("AU38", sheet));

			if (getObject("AU39", sheet)!=null) {
				String cpf = apenasNumeros((String)getObject("AU39", sheet));
				formularioFornecedor.setCpfPrepostoRepresentante(cpf);
			}

			if (getObject("AU40", sheet)!=null)
				formularioFornecedor.setRgPrepostoRepresentante((String)getObject("AU40", sheet));

			if (getObject("AU41", sheet)!=null)
				formularioFornecedor.setCargoPrepostoRepresentante((String)getObject("AU41", sheet));
			
			if (getObject("AU42", sheet)!=null) {
				String ddd = apenasNumeros((String)getObject("AU42", sheet));
				ddd = ddd.substring(0, 2);
				formularioFornecedor.setDdFonePrepostoRepresentante(ddd);
			}

			if (getObject("AU42", sheet)!=null) {
				String telefone = apenasNumeros((String)getObject("AU42", sheet));
				telefone = telefone.substring(2);
				formularioFornecedor.setFonePrepostoRepresentante(telefone);
			}

			if (getObject("AU43", sheet)!=null) {
				formularioFornecedor.setEmailPrepostoRepresentante((String)getObject("AU43", sheet));
			}
			
			System.out.println(formularioFornecedor);
			
			return formularioFornecedor;
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
		
		public Object getObject(String cellSgtring, Sheet sheet) {
			try {
				CellReference cellReference = new CellReference(cellSgtring);
				Row row = sheet.getRow(cellReference.getRow());
				Cell cell = row.getCell(cellReference.getCol());
				switch (cell.getCellType ()) {
				case XSSFCell.CELL_TYPE_NUMERIC : {
					if (DateUtil.isCellDateFormatted(cell)) {
						Date date = sdf.parse((String)cell.toString());
						return date;
					} else {						
						integerFormater.format(cell.getNumericCellValue());
						return integerFormater.format(cell.getNumericCellValue());
					}
				}
				case XSSFCell.CELL_TYPE_STRING : {
					return cell.getRichStringCellValue().toString().toUpperCase().trim();
				}
				case XSSFCell.CELL_TYPE_BOOLEAN : {
					return cell.getBooleanCellValue();
				}
				default :{
					return null;
				}

				}
			}catch (Exception e ) {
				Erros erro = new Erros();
				erro.setCelula(cellSgtring);
				erro.setMotivo(e.getMessage());
				formularioFornecedor.getErros().add(erro);
			}
			return null;
		}
}
