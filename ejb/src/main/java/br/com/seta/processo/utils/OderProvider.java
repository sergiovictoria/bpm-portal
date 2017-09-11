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
	ProdutoSeqDes("ProdutoSeqDes");
		
	
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
