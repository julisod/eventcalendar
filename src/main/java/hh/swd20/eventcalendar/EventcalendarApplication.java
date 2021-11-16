package hh.swd20.eventcalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.eventcalendar.domain.Event;
import hh.swd20.eventcalendar.domain.EventRepository;
import hh.swd20.eventcalendar.domain.Leader;
import hh.swd20.eventcalendar.domain.LeaderRepository;
import hh.swd20.eventcalendar.domain.User;
import hh.swd20.eventcalendar.domain.UserRepository;

@SpringBootApplication
public class EventcalendarApplication {
	private static final Logger log = LoggerFactory.getLogger(EventcalendarApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EventcalendarApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner eventDemo(EventRepository erepository, LeaderRepository lrepository, UserRepository urepository) { 
		return (args) -> {
			log.info("we will add a couple of example leaders");
			Leader leader1 = new Leader("Julia Söderlund", "email@domain.com", "tarpojaluotsi");
			Leader leader2 = new Leader("Petra Partiolainen", "houppett@outlook.sbs", "ohjelmajohtaja, huivivastaava");
			Leader leader3 = new Leader("Anita Anttila", "feunb@docd.site", "lippukunnanjohtaja");
			
			lrepository.save(leader1);
			lrepository.save(leader2);
			lrepository.save(leader3);
			
			log.info("fetch all leaders");
			for (Leader leader : lrepository.findAll()) {
				log.info(leader.toString());
			}
			
			log.info("we will add a couple of example events");
			erepository.save(new Event("Lippukuntaretki", "Harry Potter -teemainen LPK-retki", "retki", "2021-10-29", "2021-10-31", "Kassu, Nuuksio", "kaikki", leader1));
			erepository.save(new Event("Kajo", "Kajo on Suomen Partiolaisten järjestämä kahdeksas kansallinen partioleiri, jolle myös kansainväliset vieraat ja EVP:t ovat tervetulleita.", "Finnjamboree", "2022-07-15", "2022-07-23", "Evo, Hämeenlinna", "tarpojista ylöspäin ja perheleiri", leader2));
			erepository.save(new Event("Suurpläjäys", "Johtajien suunnitteluviikonloppu.", "retki", "2022-01-07", "2022-01-09", "Mörrimöykky, Nuuksio", "johtajat", leader3));
			
			log.info("fetch all events");
			for (Event event : erepository.findAll()) {
				log.info(event.toString());	
			}
			
			// Create users: admin/admin user/user
			//https://www.bcryptcalculator.com/
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}
}
