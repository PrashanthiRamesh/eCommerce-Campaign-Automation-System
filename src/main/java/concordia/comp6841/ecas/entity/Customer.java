package concordia.comp6841.ecas.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "id", "email" }))
public class Customer {

	@Id
	private Long id;

	private String email;
	
	private String username;
	
	private String first_name;
	
	private String last_name;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created_at;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date last_seen;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getLast_seen() {
		return last_seen;
	}

	public void setLast_seen(Date last_seen) {
		this.last_seen = last_seen;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", username=" + username + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", created_at=" + created_at + ", last_seen=" + last_seen + "]";
	}

}
