package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;

public class UserGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String last_connection;
	private long created_by_user_id;
	private Date creation_date;
	private long id;
	private String enabled;
	private String title;
	private long manager_id;
	private String job_title;
	private String userName;
	private String lastname;
	private String firstname;
	private String password;
	private Date last_update_date;
	
	
	
	public String getLast_connection() {
		return last_connection;
	}
	public void setLast_connection(String last_connection) {
		this.last_connection = last_connection;
	}
	public long getCreated_by_user_id() {
		return created_by_user_id;
	}
	public void setCreated_by_user_id(long created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getManager_id() {
		return manager_id;
	}
	public void setManager_id(long manager_id) {
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
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	
	
	
	@Override
	public String toString() {
		return "UserGroup [last_connection=" + last_connection
				+ ", created_by_user_id=" + created_by_user_id
				+ ", creation_date=" + creation_date + ", id=" + id
				+ ", enabled=" + enabled + ", title=" + title + ", manager_id="
				+ manager_id + ", job_title=" + job_title + ", userName="
				+ userName + ", lastname=" + lastname + ", firstname="
				+ firstname + ", password=" + password + ", last_update_date="
				+ last_update_date + "]";
	}
	
	
	
	
	
	
	

	

}
