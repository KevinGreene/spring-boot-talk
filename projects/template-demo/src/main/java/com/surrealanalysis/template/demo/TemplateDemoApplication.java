package com.surrealanalysis.template.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TemplateDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(TemplateDemoApplication.class);

    @Bean
    public CommandLineRunner loadData(MeetupMemberRepository repository) {
        return (args) -> {
            repository.save(new MeetupMember("Kevin", "Greene"));
            repository.save(new MeetupMember("Carlus", "Henry"));
            repository.save(new MeetupMember("Galen", "Stevens"));
            repository.save(new MeetupMember("Thomas", "Wodarek"));
            repository.save(new MeetupMember("Christopher", "Hanes"));
            repository.save(new MeetupMember("Jeff", "Timreck"));
            repository.save(new MeetupMember("Tom", "Kasprzak"));
            repository.save(new MeetupMember("Dan", "McCracken"));
            repository.save(new MeetupMember("Dustin", "Cliffor"));

            log.info("Meetup Members found with findAll()");
            log.info("-------------------------------");
            for (MeetupMember meetupMember : repository.findAll()) {
                log.info(meetupMember.toString());
            }
            log.info("");


        };
    }


    public static void main(String[] args) {
        SpringApplication.run(TemplateDemoApplication.class, args);
    }
}
