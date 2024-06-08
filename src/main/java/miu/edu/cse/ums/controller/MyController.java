package miu.edu.cse.ums.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello/fullname")
    public String sayName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        return "Hello, "+firstName + " " + lastName;
    }

    @GetMapping("/another-hello")
    public ResponseEntity<String> sayAnotherHello() {
        return ResponseEntity.ok("Hello World!"); //status code: 200
    }

    @GetMapping("/hello/another/{name}")
    public String sayAnotherHello(@PathVariable("name") String myName) {
        return "Hello " + myName;   //status code: 200
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
