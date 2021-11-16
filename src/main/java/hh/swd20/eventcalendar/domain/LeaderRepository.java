package hh.swd20.eventcalendar.domain;

import org.springframework.data.repository.CrudRepository;

public interface LeaderRepository extends CrudRepository<Leader, Long>{
	//do we want to add findBy functions?
}
