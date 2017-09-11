package br.com.seta.processo.dto;

import java.io.Serializable;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;

public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 1L;

	private long id;
	private long IdUser;
	private long groupID;
	private String userName;
	private String password;
	private String password_confirm;
	private String icon;
	private String firstname;
	private String lastname;
	private String title;
	private String job_title;
	private String manager_id;
	private Professional professional_data;

	private String personnal_data_address;
	private String personnal_data_city;
	private String personnal_data_country;
	private String personnal_data_zipcode;
	private String personnal_data_state;
	private String personnal_data_email;
	private String personnal_data_phone_number;
	private String personnal_data_mobile_number;
	private Long idManager;
	private String nameManager;
	private String emailManager="";
	

	private Boolean isLogin;
	private HttpClient httpClient;
	private HttpContext httpContext;
	private CookieStore cookieStore;

	private String nomeCompleto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return IdUser;
	}

	public void setIdUser(long idUser) {
		IdUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_confirm() {
		return password_confirm;
	}

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public Professional getProfessional_data() {
		return professional_data;
	}

	public void setProfessional_data(Professional professional_data) {
		this.professional_data = professional_data;
	}

	public String getPersonnal_data_address() {
		return personnal_data_address;
	}

	public void setPersonnal_data_address(String personnal_data_address) {
		this.personnal_data_address = personnal_data_address;
	}

	public String getPersonnal_data_city() {
		return personnal_data_city;
	}

	public void setPersonnal_data_city(String personnal_data_city) {
		this.personnal_data_city = personnal_data_city;
	}

	public String getPersonnal_data_country() {
		return personnal_data_country;
	}

	public void setPersonnal_data_country(String personnal_data_country) {
		this.personnal_data_country = personnal_data_country;
	}

	public String getPersonnal_data_zipcode() {
		return personnal_data_zipcode;
	}

	public void setPersonnal_data_zipcode(String personnal_data_zipcode) {
		this.personnal_data_zipcode = personnal_data_zipcode;
	}

	public String getPersonnal_data_state() {
		return personnal_data_state;
	}

	public void setPersonnal_data_state(String personnal_data_state) {
		this.personnal_data_state = personnal_data_state;
	}

	public String getPersonnal_data_email() {
		return personnal_data_email;
	}

	public void setPersonnal_data_email(String personnal_data_email) {
		this.personnal_data_email = personnal_data_email;
	}

	public String getPersonnal_data_phone_number() {
		return personnal_data_phone_number;
	}

	public void setPersonnal_data_phone_number(String personnal_data_phone_number) {
		this.personnal_data_phone_number = personnal_data_phone_number;
	}

	public String getPersonnal_data_mobile_number() {
		return personnal_data_mobile_number;
	}

	public void setPersonnal_data_mobile_number(String personnal_data_mobile_number) {
		this.personnal_data_mobile_number = personnal_data_mobile_number;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public void setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public String getNomeCompleto() {
		this.nomeCompleto = this.firstname.trim() + " " + this.lastname.trim();
		return null == this.nomeCompleto ?"" :this.nomeCompleto;
	}

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}
	
	public Long getIdManager() {
		return idManager;
	}

	public void setIdManager(Long idManager) {
		this.idManager = idManager;
	}

	public String getNameManager() {
		return nameManager;
	}

	public void setNameManager(String nameManager) {
		this.nameManager = nameManager;
	}

	public String getEmailManager() {
		return emailManager;
	}

	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", IdUser=");
		builder.append(IdUser);
		builder.append(", ");
		if (userName != null) {
			builder.append("userName=");
			builder.append(userName);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (password_confirm != null) {
			builder.append("password_confirm=");
			builder.append(password_confirm);
			builder.append(", ");
		}
		if (icon != null) {
			builder.append("icon=");
			builder.append(icon);
			builder.append(", ");
		}
		if (firstname != null) {
			builder.append("firstname=");
			builder.append(firstname);
			builder.append(", ");
		}
		if (lastname != null) {
			builder.append("lastname=");
			builder.append(lastname);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (job_title != null) {
			builder.append("job_title=");
			builder.append(job_title);
			builder.append(", ");
		}
		if (manager_id != null) {
			builder.append("manager_id=");
			builder.append(manager_id);
			builder.append(", ");
		}
		if (professional_data != null) {
			builder.append("professional_data=");
			builder.append(professional_data);
			builder.append(", ");
		}
		if (personnal_data_address != null) {
			builder.append("personnal_data_address=");
			builder.append(personnal_data_address);
			builder.append(", ");
		}
		if (personnal_data_city != null) {
			builder.append("personnal_data_city=");
			builder.append(personnal_data_city);
			builder.append(", ");
		}
		if (personnal_data_country != null) {
			builder.append("personnal_data_country=");
			builder.append(personnal_data_country);
			builder.append(", ");
		}
		if (personnal_data_zipcode != null) {
			builder.append("personnal_data_zipcode=");
			builder.append(personnal_data_zipcode);
			builder.append(", ");
		}
		if (personnal_data_state != null) {
			builder.append("personnal_data_state=");
			builder.append(personnal_data_state);
			builder.append(", ");
		}
		if (personnal_data_email != null) {
			builder.append("personnal_data_email=");
			builder.append(personnal_data_email);
			builder.append(", ");
		}
		if (personnal_data_phone_number != null) {
			builder.append("personnal_data_phone_number=");
			builder.append(personnal_data_phone_number);
			builder.append(", ");
		}
		if (personnal_data_mobile_number != null) {
			builder.append("personnal_data_mobile_number=");
			builder.append(personnal_data_mobile_number);
			builder.append(", ");
		}
		if (isLogin != null) {
			builder.append("isLogin=");
			builder.append(isLogin);
			builder.append(", ");
		}
		if (httpClient != null) {
			builder.append("httpClient=");
			builder.append(httpClient);
			builder.append(", ");
		}
		if (httpContext != null) {
			builder.append("httpContext=");
			builder.append(httpContext);
			builder.append(", ");
		}
		if (cookieStore != null) {
			builder.append("cookieStore=");
			builder.append(cookieStore);
			builder.append(", ");
		}
		if (nomeCompleto != null) {
			builder.append("nomeCompleto=");
			builder.append(nomeCompleto);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (IdUser ^ (IdUser >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (IdUser != other.IdUser)
			return false;
		if (id != other.id)
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		return true;
	}

	@Override
	public int compareTo(User user) {
		return this.getNomeCompleto().compareTo(user.getNomeCompleto());
	}
}
