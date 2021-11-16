package hh.swd20.eventcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String type;
	private String startDate;
	private String endDate;
	private String place;
	private String targetGroup;
	
	@ManyToOne
	@JsonIgnoreProperties("events")
    @JoinColumn(name = "leaderid")
    private Leader leader;
	
	public Event() {
		super();
	}

	public Event(String name, String description, String type, String startDate, String endDate, String place,
			String targetGroup, Leader leader) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.targetGroup = targetGroup;
		this.leader = leader;
	}

	public Event(Long id, String name, String description, String type, String startDate, String endDate, String place,
			String targetGroup, Leader leader) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.targetGroup = targetGroup;
		this.leader = leader;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	static String formatDate(String date) {
		String newDate = date.substring(8) + "." + date.substring(5, 7) + "." + date.substring(0, 4);
		return newDate;
	}
	
	public String getDuration() {
		String duration;
		String starting = (formatDate(this.getStartDate()));
		String ending = (formatDate(this.getEndDate()));
		
		if (!starting.equals(ending)) {
			duration = (starting + "-" + ending);
		} else {
			duration = starting;
		}
		return duration;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", startDate="
				+ startDate + ", endDate=" + endDate + ", place=" + place + ", targetGroup=" + targetGroup + "]";
	}
	
	
}
