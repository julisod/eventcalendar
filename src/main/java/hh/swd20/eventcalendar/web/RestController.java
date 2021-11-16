package hh.swd20.eventcalendar.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.eventcalendar.domain.Event;
import hh.swd20.eventcalendar.domain.EventRepository;

@Controller
public class RestController {

	@Autowired
	private EventRepository erepository;
	
	
	// RESTful service to get all events
    // Java-kielinen Event-luokan oliolista muunnetaan JSON-opiskelijalistaksi (en kyl tiiä miten) ja 
    // lähetetään web-selaimelle vastauksena
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody List<Event> eventListRest() {	
        return (List<Event>) erepository.findAll();
    }    

	// RESTful service to get event by id
    @RequestMapping(value="/events/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Event> findEventRest(@PathVariable("id") Long eventId) {	
    	return erepository.findById(eventId);
    }      
    
    // RESTful service to save new event
    @RequestMapping(value="/events", method = RequestMethod.POST)
    public @ResponseBody Event saveEventRest(@RequestBody Event event) {	
    	return erepository.save(event);
    }
}
