package com.KalleH.exGame.controller;

import com.KalleH.exGame.games.ClickGameService;
import com.KalleH.exGame.model.Player;
import com.KalleH.exGame.Repository.PlayerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @Autowired
    private ClickGameService clickGameService;

    @Autowired
    private PlayerRepository playerRepository;

    @ModelAttribute("player")
    public Player loadPlayer(HttpSession session) {
        Long playerId = (Long) session.getAttribute("playerId");
        if (playerId == null) {
            playerId = 1L;
            session.setAttribute("playerId", playerId);
        }
        return playerRepository.findById(playerId).orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setName("DefaultPlayer");
            newPlayer.setPts(0);
            newPlayer.setPtsPerClick(1);
            newPlayer.setFangs(0);
            newPlayer.setWorkers(0);
            return playerRepository.save(newPlayer);
        });
    }

    @GetMapping("/increasePoints")
    public String increasePoints(@ModelAttribute("player") Player player) {
        clickGameService.increasePoints(player);
        return "redirect:/game";
    }

    @GetMapping("/buyFang")
    public String buyFang(@ModelAttribute("player") Player player) {
        clickGameService.buyFang(player);
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
    @GetMapping("/setFangs")
    public String setFangs(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setFangs(value);
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

    @GetMapping("/player/points")
    @ResponseBody
    public int getPlayerPoints(HttpSession session) {
        Player player = getCurrentPlayer(session);
        return player.getPts();
    }

    @PostMapping("/createPlayer")
    public String createPlayer(@RequestParam String name, HttpSession session) {
        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setPts(0);
        newPlayer.setPtsPerClick(1);
        newPlayer.setFangs(0);
        newPlayer.setWorkers(0);
        Player saved = playerRepository.save(newPlayer);
        session.setAttribute("playerId", saved.getId());
        return "redirect:/game";
    }

    @PostMapping("/switchPlayer")
    public String switchPlayer(@RequestParam Long id, HttpSession session) {
        if (playerRepository.existsById(id)) {
            session.setAttribute("playerId", id);
            return "redirect:/game";
        }
        return "redirect:/player?error=notfound";
    }

    @GetMapping("/player")
    public String playerPage() {
        return "player";
    }

    private Player getCurrentPlayer(HttpSession session) {
        Long playerId = (Long) session.getAttribute("playerId");
        if (playerId == null) {
            playerId = 1L; // default or redirect to selection
            session.setAttribute("playerId", playerId);
        }
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " ));
    }

}
