package br.com.seta.processo.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.seta.processo.dto.OrReqitensdespesa;


public class OrReqitensdespesaDatabase implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<OrReqitensdespesa> orReqitensdespesas = Collections.synchronizedList(new ArrayList<OrReqitensdespesa>());


	public Iterator<OrReqitensdespesa> find(long first, long count)  {
		return this.orReqitensdespesas.subList((int)first, (int)(first + count)).iterator();
	}

	public long size() {
		if (this.orReqitensdespesas==null ) {
			return 0;
		}
		return this.orReqitensdespesas.size();
	}


	public void delte(OrReqitensdespesa orRequisicaovencto ) {
		this.orReqitensdespesas.remove(orRequisicaovencto);
	}

	public void add(OrReqitensdespesa orRequisicaovencto ) {
		this.orReqitensdespesas.add(orRequisicaovencto);
	}
	


	public Boolean find(OrReqitensdespesa orRequisicaovencto ) {
		for (OrReqitensdespesa dto : this.orReqitensdespesas) {
			if (dto.equals(orRequisicaovencto) ) {
				return Boolean.TRUE;
			}	
		}
		return Boolean.FALSE;
	}
	

	public List<OrReqitensdespesa> getOrReqitensdespesas() {
		return orReqitensdespesas;
	}


	public void setOrReqitensdespesas(List<OrReqitensdespesa> orReqitensdespesas) {
		this.orReqitensdespesas = orReqitensdespesas;
	}

	
    public void getSeqrequisicao(long seqrequisicao) {
    	for (int i = 0; i < orReqitensdespesas.size(); i++) {
    		this.orReqitensdespesas.get(i).setSeqrequisicao(seqrequisicao);
		}
    }

}
