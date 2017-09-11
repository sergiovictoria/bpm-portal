package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.OrvProdutoTributo;
import br.com.seta.processo.dto.Produto;

public interface Produtos {
    public abstract List<Produto> getProdutoIdPessoa(Produto produto);
    public abstract List<OrvProdutoTributo> getProdutoIdPessoaTributo(OrvProdutoTributo orvProdutoTributo);
}
