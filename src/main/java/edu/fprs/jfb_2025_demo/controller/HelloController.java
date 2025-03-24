package edu.fprs.jfb_2025_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Spring Boot annotation used to define a RESTful web service in a Spring application. It is a specialized version of @Controller that combines @Controller and @ResponseBody, meaning that the returned data is automatically serialized into JSON or XML and sent as an HTTP response.
public class HelloController {

    @GetMapping({"/", "/hello"})
    public String sayHello() {
        return "<h1>Hello World! \n Le serveur marche mais il n'y a rien à voir ici</h1>";
    }

}


