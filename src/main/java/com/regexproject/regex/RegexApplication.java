package com.regexproject.regex;

import com.regexproject.regex.service.RegexReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegexApplication implements CommandLineRunner {

	@Autowired
	RegexReader regexReader;

	public static void main(String[] args) {
		SpringApplication.run(RegexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		regexReader.readGeneratedData();
	}
}

/*
*
* https://github.com/algorythmist/aws-encryption-sdk-spring/tree/main/src/main/java/com/tecacet/awssecurity/service
*
* */