package com.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping(path = "/helloworld")
    public String helloWorld() {
        return "Hello World";
    }
    
    @GetMapping(path = "/one")
    public String helloWorldTesting() {
        return "Hello Hi Test";
    }
    
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello");
    }
    
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldBeanInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
    
}
