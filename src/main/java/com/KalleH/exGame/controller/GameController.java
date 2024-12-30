package com.KalleH.exGame.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@SpringBootApplication
@Controller
public class GameController {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
    /*@GetMapping("/")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
     */
    @GetMapping("/")
    public String home() {
        System.out.println("Homepage");
        return "home";
    }

    @GetMapping("/main")
    public String main() {
        System.out.println("Main menu");
        return "main";
    }

    @GetMapping("/stats")
    public String stats() {
        System.out.println("Stats");
        return "stats";
    }
    @GetMapping("/game")
    public String game() {
        System.out.println("Game screen");
        return "game";
    }


}
