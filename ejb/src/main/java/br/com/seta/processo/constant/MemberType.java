package br.com.seta.processo.constant;

public enum MemberType {

	USER("USER"), 
	ROLE("ROLE"), 
	GROUP("GROUP"), 
	MEMBERSHIP("MEMBERSHIP");

	private String type;

	private MemberType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
