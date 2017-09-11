package br.com.seta.processo.interfaces;

import java.math.BigDecimal;

import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.exceptions.DaoException;


public interface Fornecedores {
	public abstract GePessoaEntity create(GePessoaEntity gePessoaEntity) throws DaoException;
	public abstract GePessoaEntity getMafFornecedorEntity(BigDecimal seqpessoa) throws DaoException;
	public abstract GePessoaEntity getMafFornecedorEntity(GePessoaEntity gePessoaEntity) throws DaoException;
}
