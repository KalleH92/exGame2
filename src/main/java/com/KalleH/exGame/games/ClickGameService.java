package com.KalleH.exGame.games;

import com.KalleH.exGame.Repository.PlayerRepository;
import com.KalleH.exGame.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class ClickGameService {


    private final PlayerRepository playerRepository;


    @Autowired
    public ClickGameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void generatePointsFromOffspring() {
        for (Player player : playerRepository.findAll()) {
            int workers = player.getWorkers();
            int fangs = player.getFangs();
            int additionalPoints = workers * fangs;
            player.setPts(player.getPts() + additionalPoints);
            playerRepository.save(player);
        }
    }

    public void increasePoints(Player player) {
        int pointsToAdd = 1 + player.getFangs();
        player.setPts(player.getPts() + pointsToAdd);
        playerRepository.save(player);
        System.out.println("Players points after click: " + player.getPts());
    }

    public void buyFang(Player player) {
        if (player.getPts() >= 10) {
            player.setPts(player.getPts() - 10);
            player.setFangs(player.getFangs() + 1);
            playerRepository.save(player);
            System.out.println("Fang bought. New number of fangs: " + player.getFangs());
        } else {
            System.out.println("Not enough points to buy a fang.");
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

