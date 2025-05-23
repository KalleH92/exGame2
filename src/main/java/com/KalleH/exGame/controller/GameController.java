package com.KalleH.exGame.controller;

import com.KalleH.exGame.games.ClickGameService;
import com.KalleH.exGame.model.Player;
import com.KalleH.exGame.Repository.PlayerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private ClickGameService clickGameService;

    @Autowired
    private PlayerRepository playerRepository;

    @ModelAttribute("player")
    public Player loadPlayer() {
        // Later you'll fetch by logged-in user or session ID.
        return playerRepository.findById(1L).orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setName("DefaultPlayer");
            newPlayer.setPts(0);
            newPlayer.setPtsPerClick(1);
            newPlayer.setFactories(0);
            newPlayer.setWorkers(0);
            return playerRepository.save(newPlayer); // Save new player if needed
        });
    }

    @GetMapping("/increasePoints")
    public String increasePoints(@ModelAttribute("player") Player player) {
        clickGameService.increasePoints(player);
        return "redirect:/game";
    }

    @GetMapping("/buyFactory")
    public String buyFactory(@ModelAttribute("player") Player player) {
        clickGameService.buyFactory(player);
        return "redirect:/game";
    }
    @GetMapping("/hireWorker")
    public String hireWorker(@ModelAttribute("player") Player player) {
        clickGameService.hireWorker(player);
        return "redirect:/game";
    }

    @GetMapping("/")
    public String home() {
        System.out.println("Home");
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


    @GetMapping("/setPoints")
    public String setPoints(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setPts(value);
        playerRepository.save(player);
        return "redirect:/game";
    }
    @GetMapping("/setFactories")
    public String setFactories(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setFactories(value);
        playerRepository.save(player);
        return "redirect:/game";
    }
    @GetMapping("/setWorkers")
    public String setWorkers(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setWorkers(value);
        playerRepository.save(player);
        return "redirect:/game";
    }

    private Player getCurrentPlayer(HttpSession session) {
        Long playerId = (Long) session.getAttribute("playerId");

        if (playerId == null) {
            playerId = 1L;
            session.setAttribute("playerId",playerId);
        }
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: "));
    }

}
