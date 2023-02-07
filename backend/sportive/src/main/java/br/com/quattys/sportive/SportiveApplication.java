package br.com.quattys.sportive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SportiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportiveApplication.class, args);
	}

}
