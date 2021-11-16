package hh.swd20.eventcalendar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.eventcalendar.domain.Event;
import hh.swd20.eventcalendar.domain.EventRepository;
import hh.swd20.eventcalendar.domain.LeaderRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository erepository;
	
	@Autowired
	private LeaderRepository lrepository;
	
	//main page
	@RequestMapping(value = "/eventlist", method = RequestMethod.GET)
	public String listEvents(Model model) {
		//for the list
		List<Event> events = (List<Event>) erepository.findAll();
		model.addAttribute("events", events);
		
		//for adding events
		model.addAttribute("newevent", new Event());
    	model.addAttribute("leaders", lrepository.findAll());
    	
		return "eventlist";
	}
	
	@RequestMapping(value = "/save-event", method = RequestMethod.POST)
    public String save(Event event){
        erepository.save(event);
        return "redirect:eventlist";
    } 
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit-event/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") Long eventId, Model model){
		model.addAttribute("event", erepository.findById(eventId).get());
		model.addAttribute("leaders", lrepository.findAll());
        return "editevent";
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete-event/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
    	erepository.deleteById(eventId);
        return "redirect:../eventlist";
    } 
}
