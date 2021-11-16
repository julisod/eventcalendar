package hh.swd20.eventcalendar.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Leader {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String roles;
	/* will add if i have the time
	 * private String picUrl;
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "leader")
	@JsonIgnoreProperties("leader")
	private List<Event> events;
	
	public Leader() {
		super();
	}

	public Leader(String name, String email, String roles) {
		super();
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public Leader(Long id, String name, String email, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "Leader [id=" + id + ", name=" + name + ", email=" + email + ", roles=" + roles + "]";
	}
	
	
}
