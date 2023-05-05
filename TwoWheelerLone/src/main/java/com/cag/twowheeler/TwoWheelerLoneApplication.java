package com.cag.twowheeler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwoWheelerLoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoWheelerLoneApplication.class, args);
	}

}
