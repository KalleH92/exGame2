package com.KalleH.exGame.controller;

import com.KalleH.exGame.games.ClickGame;
import com.KalleH.exGame.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Scanner;

@SpringBootApplication
@Controller
public class GameController {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        ClickGame Clicker = new ClickGame();
        Clicker.play();
    }
    /*@GetMapping("/")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
     */
Player p1 = new Player();

    Scanner sc = new Scanner(System.in);

    @GetMapping("/")
    public String home() {



        p1.setName(sc.nextLine());

        //p1.getName() + 5 = new p1.setName();

        System.out.println("Test 1" + p1.getName() + "Test 2");


        System.out.println("Homepage");
        System.out.println("Test 3" + p1.getName() + "Test 2");
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
