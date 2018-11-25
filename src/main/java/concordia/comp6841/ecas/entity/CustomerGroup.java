package concordia.comp6841.ecas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "user_email"))
public class CustomerGroup {

	@Id
	private String user_email;

	private Integer active_lastseen;
	private Integer inactive_lastseen;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
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
		return "CustomerGroup [user_email=" + user_email + ", active_lastseen=" + active_lastseen
				+ ", inactive_lastseen=" + inactive_lastseen + "]";
	}

}
