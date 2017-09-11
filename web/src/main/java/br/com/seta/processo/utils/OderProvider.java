package br.com.seta.processo.utils;


public enum OderProvider {

	NomeRazaoAsc("NomeRazaoAsc"),
	NomeRazaoDesc("NomeRazaoDesc"),
	SeqPessoaAsc("SeqPessoaAsc"),
	SeqPessoaDesc("SeqPessoaDesc"),
	
	ModeloAscDescricao("ModeloAscDescricao"),
	ModeloDesDescricao("ModeloDesDescricao"),
	ModeloAscCodigo("ModeloAscCodigo"),
	ModeloDesCodigo("ModeloDesCodigo"),
	
	NatrurezaAscDescricao("NatrurezaAscDescricao"),
	NatrurezaDesDescricao("NatrurezaDesDescricao"),
	NatrurezaAscCodigo("NatrurezaAscCodigo"),
	NatrurezaDesCodigo("NatrurezaDesCodigo"),
		
	PessoaAscDescricao("PessoaAscDescricao"),
	PessoaDesDescricao("PessoaDesDescricao"),
	PessoaAscCodigo("PessoaAscCodigo"),
	PessoaDesCodigo("PessoaDesCodigo"),
	
	CfopCompAsc("CfopCompAsc"),
	CfopCompDes("CfopCompDes"),
	CfopCfopAsc("CfopCfopAsc"),
	CfopCfopDes("CfopCfopDes"),
	
	ProdutoDescAsc("ProdutoDescAsc"),
	ProdutoDescDes("ProdutoDescDes"),
	ProdutoSeqAsc("ProdutoSeqAsc"),
	ProdutoSeqDes("ProdutoSeqDes"),
	
	MOTIVO_ASC("MOTIVO_ASC"),
	MOTIVO_DESC("MOTIVO_DESC"),

	DATA_ASC("DATA_ASC"),
	DATA_DESC("DATA_DESC"),
	
	STATUS_ASC("STATUS_ASC"),
	STATUS_DESC("STATUS_DESC"),
	
	NOME_ASC("NOME_ASC"),
	NOME_DESC("NOME_DESC");
	
	private String value;
	
	private OderProvider(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
