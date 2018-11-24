package concordia.comp6841.ecas.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AbandonedCart {

	@Id
	private String email;

	private String first_name;

	private String last_name;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AbandonedCart [email=" + email + ", first_name=" + first_name + ", last_name=" + last_name + ", date="
				+ date + "]";
	}

}
