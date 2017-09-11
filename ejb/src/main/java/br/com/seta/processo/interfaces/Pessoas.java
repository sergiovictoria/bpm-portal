package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.Pessoa;

public interface Pessoas {
   public abstract List<Pessoa> getPessoaS();
   public abstract List<Pessoa> getPessoasDetalhe( );
   public abstract Pessoa getPessoasDetalheIdSeq(Pessoa pessoa);
   public abstract Pessoa getPessoasDetalheComID(Pessoa pessoa);
}
