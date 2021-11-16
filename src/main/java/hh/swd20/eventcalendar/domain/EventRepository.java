package hh.swd20.eventcalendar.domain;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
	//do we want to add findBy functions?
}
