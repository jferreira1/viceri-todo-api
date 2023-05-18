package com.viceri;

import com.viceri.app.model.User;
import com.viceri.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            log.info("Preloading " + userRepository.save(new User("Jean", "jeansouzza@gmail.com", "pass123")));
            log.info("Preloading " + userRepository.save(new User("John", "johnsmith@gmail.com", "pass123")));
        };
    };
}