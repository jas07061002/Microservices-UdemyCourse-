package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/one")
	public String helloWorldTesting() {
		return "Hello Hi Test";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello");
	}
	
	
	


}
