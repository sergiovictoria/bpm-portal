package br.com.seta.processo.dto;

import java.io.Serializable;

public class Actor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String displayName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", displayName=" + displayName + "]";
	}
}