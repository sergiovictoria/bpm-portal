package br.com.seta.processo.interfaces;

import br.com.seta.processo.dto.NotaFiscal;



public interface NotaFiscals {
	public abstract NotaFiscal getVerifaIntegracaoReq(NotaFiscal notaFIscal);
	public abstract NotaFiscal getVerifaIntegracaoNroReq(NotaFiscal notaFIscal);
}

