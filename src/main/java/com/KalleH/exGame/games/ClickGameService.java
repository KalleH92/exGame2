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
            int egglingCount = player.getEgglingCount();
            int fangCount = player.getFangCount();
            int additionalPoints = egglingCount;
            player.setPts(player.getPts() + additionalPoints);
            playerRepository.save(player);
        }
    }

    public void increasePoints(Player player) {
        int fangPower = player.getFangCount() * (int) Math.pow(2, player.getSharpFangCount());
        int pointsToAdd = 1 + fangPower;
        player.setPts(player.getPts() + pointsToAdd);
        playerRepository.save(player);
        System.out.println("Players points after click: " + player.getPts());
    }

    public void buyFang(Player player) {
        if (player.getPts() >= 10) {
            player.setPts(player.getPts() - 10);
            player.setFangCount(player.getFangCount() + 1);
            playerRepository.save(player);
            System.out.println("Fang bought. New number of fangs: " + player.getFangCount());
        } else {
            System.out.println("Not enough points to buy a fang.");
        }
    }

    public void hireEggling(Player player) {
        if (player.getPts() >= 100) {
            player.setPts(player.getPts() - 100);
            player.setEgglingCount(player.getEgglingCount() + 1);
            playerRepository.save(player);
            System.out.println("eggling born. new number of egglings: " + player.getEgglingCount());
        } else {
            System.out.println("Not enough points to hire a eggling.");
        }
    }

    public void buySharpFang(Player player) {
        if (player.getPts() >= 100 && player.getSharpFangCount() == 0) {
            player.setPts(player.getPts()-100);
            player.setSharpFangCount(player.getSharpFangCount() + 1);
            playerRepository.save(player);
            System.out.println("Sharp fang 1 upgrade bought");
        }else {
            System.out.println("Not enough points and/or evolutions to hire upgrade sharp fangs.");
        }
    }

    public void buyHatchSpeed(Player player) {
        if(player.getPts() >= 50 && player.getHatchSpeedCount() >= 3) {
            player.setPts(player.getPts()-50);
            player.setHatchSpeedCount((player.getHatchSpeedCount() + 1));
            playerRepository.save(player);
            System.out.println("Hatch speed reduced");
        } else {
            System.out.println("Not enough points to buy hatch speed reduced");
        }
    }
}

