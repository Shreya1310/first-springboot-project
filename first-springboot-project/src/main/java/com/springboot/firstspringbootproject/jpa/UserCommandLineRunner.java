package com.springboot.firstspringbootproject.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	@Autowired
	UserRepository repository;

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		repository.save(new User("Shreya", "Admin"));
		repository.save(new User("Disha", "User"));
		repository.save(new User("Ranga", "Admin"));
		repository.save(new User("Khushali", "User"));

		log.info("User inserted");

		for (User user : repository.findAll()) {

			log.info(user.toString());

		}

		log.info("All users having admin Role");

		for (User user : repository.findByRole("Admin")) {

			log.info(user.toString());

		}

	}

}
