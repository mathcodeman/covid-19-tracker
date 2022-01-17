package io.javabrains.convic19tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Convic19TrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Convic19TrackerApplication.class, args);
	}

}
