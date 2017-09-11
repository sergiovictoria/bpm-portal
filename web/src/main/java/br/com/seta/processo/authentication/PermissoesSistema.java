package br.com.seta.processo.authentication;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTO;

public class PermissoesSistema extends LinkedHashMap<String, List<PermissaoTagDTO>> implements Serializable {

	private static final long serialVersionUID = 1L;

	public PermissoesSistema() {
		super();
	}

	public PermissoesSistema(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	public PermissoesSistema(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public PermissoesSistema(int initialCapacity) {
		super(initialCapacity);
	}

	public PermissoesSistema(Map<? extends String, ? extends List<PermissaoTagDTO>> m) {
		super(m);
	}
	
}
