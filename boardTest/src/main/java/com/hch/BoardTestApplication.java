package com.hch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableJpaAuditing
@SpringBootApplication
@EnableEncryptableProperties
public class BoardTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardTestApplication.class, args);
	}

}
