package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class PlayerController {
    private List<String> players = Arrays.asList("p1", "p2", "p3", "p5");
    @RequestMapping("/hello")
    @GetMapping()
    public ResponseEntity<String> getHelloMessage() {
        return new ResponseEntity<>("Greetings from Spring Boot! (GET)", HttpStatus.OK);
    }

    @GetMapping("/bau")
    public List<String> getPlayers(){
        return players;
    }

}
