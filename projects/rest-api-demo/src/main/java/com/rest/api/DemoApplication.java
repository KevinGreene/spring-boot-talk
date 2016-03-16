package com.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    CharacterRepository characters;

    @Autowired
    ShowRepository shows;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {

        Show gameOfThrones = shows.save(new Show("Game of Thrones"));

		Character arya = characters.save(new Character("Arya", "Stark", gameOfThrones));
		Character sansa = characters.save(new Character("Sansa", "Stark", gameOfThrones));
		Character jon = characters.save(new Character("Jon", "Snow", gameOfThrones));

        Show supernatural = shows.save(new Show("Supernatural"));
        Character sam = characters.save(new Character("Sam", "Winchester", supernatural));
        Character dean = characters.save(new Character("Dean", "Winchester", supernatural));
	}

}
