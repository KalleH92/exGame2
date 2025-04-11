package com.KalleH.exGame.games;

import com.KalleH.exGame.Repository.PlayerRepository;
import com.KalleH.exGame.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickGameService {

    private final PlayerRepository playerRepository;

    @Autowired
    public ClickGameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void increasePoints(Player player) {
        int pointsToAdd = 1 + player.getFactories();
        player.setPts(player.getPts() + pointsToAdd);
        playerRepository.save(player);
        System.out.println("Players points after click: " + player.getPts());
    }

    public void buyFactory(Player player) {
        if (player.getPts() >= 10) {
            player.setPts(player.getPts() - 10);
            player.setFactories(player.getFactories() + 1);
            playerRepository.save(player);
            System.out.println("Factory bought. New number of factories: " + player.getFactories());
        } else {
            System.out.println("Not enough points to buy a factory.");
        }
    }

    public void hireWorker(Player player) {
        if (player.getPts() >= 100) {
            player.setPts(player.getPts() - 100);
            player.setWorkers(player.getWorkers() + 1);
            playerRepository.save(player);
            System.out.println("worker hired. new number of workers: " + player.getWorkers());
        } else {
            System.out.println("Not enough points to hire a worker.");
        }
    }
}

