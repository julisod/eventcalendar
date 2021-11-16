package hh.swd20.eventcalendar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.eventcalendar.domain.Leader;
import hh.swd20.eventcalendar.domain.LeaderRepository;

@Controller
public class LeaderController {	
	@Autowired
	private LeaderRepository lrepository;
	
	//main page
	@RequestMapping(value = "/leaderlist", method = RequestMethod.GET)
	public String listLeaders(Model model) {
		//for the list
		List<Leader> leaders = (List<Leader>) lrepository.findAll();
		model.addAttribute("leaders", leaders);
		
		//for adding leaders
		model.addAttribute("newleader", new Leader());
    	
		return "leaderlist";
	}
	
	@RequestMapping(value = "/save-leader", method = RequestMethod.POST)
    public String save(Leader leader){
        lrepository.save(leader);
        return "redirect:leaderlist";
    } 
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit-leader/{id}", method = RequestMethod.GET)
    public String editLeader(@PathVariable("id") Long leaderId, Model model){
		model.addAttribute("leader", lrepository.findById(leaderId).get());
        return "editleader";
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete-leader/{id}", method = RequestMethod.GET)
    public String deleteLeader(@PathVariable("id") Long leaderId, Model model) {
    	lrepository.deleteById(leaderId);
        return "redirect:../leaderlist";
    } 
}
