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
            newPlayer.setFangCount(0);
            newPlayer.setEgglings(0);
            newPlayer.setSharpFangs(0);
            newPlayer.setHatchSpeed(0);
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
    @GetMapping("/hireEggling")
    public String hireEggling(@ModelAttribute("player") Player player) {
        clickGameService.hireEggling(player);
        return "redirect:/game";
    }
    @GetMapping("/buySharpFang")
    public String buySharpFang(@ModelAttribute("player") Player player) {
        clickGameService.buySharpFang(player);
        return "redirect:/game";
    }

    @GetMapping("/buyHatchSpeed")
    public String buyHatchSpeed(@ModelAttribute("player") Player player) {
        clickGameService.buyHatchSpeed(player);
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
    @GetMapping("/setFangCount")
    public String setFangCount(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setFangCount(value);
        playerRepository.save(player);
        return "redirect:/game";
    }
    @GetMapping("/setEgglings")
    public String setEgglings(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setEgglings(value);
        playerRepository.save(player);
        return "redirect:/game";
    }

    @GetMapping("/setSharpFangs")
    public String setSharpFangs(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setSharpFangs(value);
        playerRepository.save(player);
        return "redirect:/game";
    }

    @GetMapping("/setHatchSpeed")
    public String setHatchSpeed(@RequestParam int value, HttpSession session) {
        Player player = getCurrentPlayer(session);
        player.setHatchSpeed(value);
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
        newPlayer.setFangCount(0);
        newPlayer.setEgglings(0);
        newPlayer.setSharpFangs(0);
        newPlayer.setHatchSpeed(0);
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
