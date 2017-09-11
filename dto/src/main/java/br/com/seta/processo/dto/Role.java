package br.com.seta.processo.dto;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String displayName;
	private String description;
	private String creation_date;
	private String created_by_user_id;
	private String last_update_date;
	private String icon;

	public Role() {
	}

	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDisplayName() {
		return displayName;
	}

	public final void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getCreation_date() {
		return creation_date;
	}

	public final void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public final String getCreated_by_user_id() {
		return created_by_user_id;
	}

	public final void setCreated_by_user_id(String created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}

	public final String getLast_update_date() {
		return last_update_date;
	}

	public final void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}

	public final String getIcon() {
		return icon;
	}

	public final void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (displayName != null) {
			builder.append("displayName=");
			builder.append(displayName);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (creation_date != null) {
			builder.append("creation_date=");
			builder.append(creation_date);
			builder.append(", ");
		}
		if (created_by_user_id != null) {
			builder.append("created_by_user_id=");
			builder.append(created_by_user_id);
			builder.append(", ");
		}
		if (last_update_date != null) {
			builder.append("last_update_date=");
			builder.append(last_update_date);
			builder.append(", ");
		}
		if (icon != null) {
			builder.append("icon=");
			builder.append(icon);
		}
		builder.append("]");
		return builder.toString();
	}

}
