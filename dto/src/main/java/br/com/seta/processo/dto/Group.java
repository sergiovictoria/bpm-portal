package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date creation_date;
	private String parent_path;
	private String description;
	private String name;
	private String path;
	private String displayName;
	private Date last_update_date;
	
	private long actor_id;
	private long role_id;
	private long group_id;
	private long user_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getParent_path() {
		return parent_path;
	}
	public void setParent_path(String parent_path) {
		this.parent_path = parent_path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	
	
	/**
	 * @return the actor_id
	 */
	public final long getActor_id() {
		return actor_id;
	}
	/**
	 * @param actor_id the actor_id to set
	 */
	public final void setActor_id(long actor_id) {
		this.actor_id = actor_id;
	}
	/**
	 * @return the role_id
	 */
	public final long getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public final void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the group_id
	 */
	public final long getGroup_id() {
		return group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public final void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
	/**
	 * @return the user_id
	 */
	public final long getUser_id() {
		return user_id;
	}
	
	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public final void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Group [id=");
		builder.append(id);
		builder.append(", creation_date=");
		builder.append(creation_date);
		builder.append(", parent_path=");
		builder.append(parent_path);
		builder.append(", description=");
		builder.append(description);
		builder.append(", name=");
		builder.append(name);
		builder.append(", path=");
		builder.append(path);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", last_update_date=");
		builder.append(last_update_date);
		builder.append(", actor_id=");
		builder.append(actor_id);
		builder.append(", role_id=");
		builder.append(role_id);
		builder.append(", group_id=");
		builder.append(group_id);
		builder.append(", user_id=");
		builder.append(user_id);
		builder.append("]");
		return builder.toString();
	}

}
