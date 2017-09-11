package br.com.seta.processo.dto;

import java.io.Serializable;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String last_connection;
	private String created_by_user;
	private String creation_date;
	private String id;
	private String icon;
	private String enabled;
	private String title;
	private String manager_id;
	private String job_title;
	private String userName;
	private String lastname;
	private String firstname;
	private String password;
	private String last_update_date;
	
	
	
	public String getLast_connection() {
		return last_connection;
	}
	public void setLast_connection(String last_connection) {
		this.last_connection = last_connection;
	}
	public String getCreated_by_user() {
		return created_by_user;
	}
	public void setCreated_by_user(String created_by_user) {
		this.created_by_user = created_by_user;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	
	
	
	

	
		
	

}
