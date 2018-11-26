package concordia.comp6841.ecas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class CustomerGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private Integer active_lastseen;
	private Integer inactive_lastseen;
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
	public Integer getActive_lastseen() {
		return active_lastseen;
	}
	public void setActive_lastseen(Integer active_lastseen) {
		this.active_lastseen = active_lastseen;
	}
	public Integer getInactive_lastseen() {
		return inactive_lastseen;
	}
	public void setInactive_lastseen(Integer inactive_lastseen) {
		this.inactive_lastseen = inactive_lastseen;
	}
	@Override
	public String toString() {
		return "CustomerGroup [id=" + id + ", email=" + email + ", active_lastseen=" + active_lastseen
				+ ", inactive_lastseen=" + inactive_lastseen + "]";
	}

	

}
