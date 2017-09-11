package br.com.seta.processo.dto;

import java.io.Serializable;

public class Membership implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String assigned_date;
	private String assigned_by_user_id;
	private long role_id;
	private long group_id;
	private long user_id;

	public final String getAssigned_date() {
		return assigned_date;
	}

	public final void setAssigned_date(String assigned_date) {
		this.assigned_date = assigned_date;
	}

	public final String getAssigned_by_user_id() {
		return assigned_by_user_id;
	}

	public final void setAssigned_by_user_id(String assigned_by_user_id) {
		this.assigned_by_user_id = assigned_by_user_id;
	}

	public final long getRole_id() {
		return role_id;
	}

	public final void setRole_id(long role_id) {
		this.role_id = role_id;
	}

	public final long getGroup_id() {
		return group_id;
	}

	public final void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public final long getUser_id() {
		return user_id;
	}

	public final void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membership [");
		if (assigned_date != null) {
			builder.append("assigned_date=");
			builder.append(assigned_date);
			builder.append(", ");
		}
		if (assigned_by_user_id != null) {
			builder.append("assigned_by_user_id=");
			builder.append(assigned_by_user_id);
			builder.append(", ");
		}
		builder.append("role_id=");
		builder.append(role_id);
		builder.append(", group_id=");
		builder.append(group_id);
		builder.append(", user_id=");
		builder.append(user_id);
		builder.append("]");
		return builder.toString();
	}

}
