package br.com.seta.processo.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.seta.processo.dto.OrRequisicaovencto;


public class OrRequisicaovenctoDatabase implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<OrRequisicaovencto> orRequisicaovenctos = Collections.synchronizedList(new ArrayList<OrRequisicaovencto>());


	public OrRequisicaovenctoDatabase(List<OrRequisicaovencto> orRequisicaovenctos) {
		this.orRequisicaovenctos = orRequisicaovenctos;
	}
	
	
	public OrRequisicaovenctoDatabase() {
	}
	

	public Iterator<OrRequisicaovencto> find(long first, long count)  {
		return this.orRequisicaovenctos.subList((int)first, (int)(first + count)).iterator();
	}

	public long size() {
		if (this.orRequisicaovenctos==null ) {
			return 0;
		}
		return this.orRequisicaovenctos.size();
	}


	public void delte(OrRequisicaovencto orRequisicaovencto ) {
		this.orRequisicaovenctos.remove(orRequisicaovencto);
	}
	

	public Boolean find(OrRequisicaovencto orRequisicaovencto ) {
		for (OrRequisicaovencto dto : this.orRequisicaovenctos) {
			if (dto.equals(orRequisicaovencto) ) {
				return Boolean.TRUE;
			}	
		}
		return Boolean.FALSE;
	}



	public List<OrRequisicaovencto> getOrRequisicaovenctos() {
		return orRequisicaovenctos;
	}


	public void setOrRequisicaovenctos(List<OrRequisicaovencto> orRequisicaovenctos) {
		this.orRequisicaovenctos = orRequisicaovenctos;
	}
	
	
    public void getSeqrequisicao(long seqrequisicao) {
    	for (int i = 0; i < orRequisicaovenctos.size(); i++) {
    		this.orRequisicaovenctos.get(i).setSeqrequisicao(seqrequisicao);
		}
    }
	
	

}
