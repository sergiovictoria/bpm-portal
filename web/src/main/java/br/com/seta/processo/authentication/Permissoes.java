package br.com.seta.processo.authentication;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTOV2;

public class Permissoes extends LinkedHashMap<String, List<PermissaoTagDTOV2>> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Permissoes() {
		super();
	}

	public Permissoes(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	public Permissoes(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public Permissoes(int initialCapacity) {
		super(initialCapacity);
	}

	public Permissoes(Map<? extends String, ? extends List<PermissaoTagDTOV2>> m) {
		super(m);
	}
	
}
