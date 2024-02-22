package com.bootcamp.hellobiqri.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello World";
	}

	@GetMapping("/hello")
	public String hello(@RequestParam String nama){
		return "Hello " + nama;
	}


}
