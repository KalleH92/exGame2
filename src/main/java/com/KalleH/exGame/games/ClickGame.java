package com.KalleH.exGame.games;

import com.KalleH.exGame.model.Player;

public class ClickGame {

    private int points = 0;

    ClickGame game = new ClickGame();
    Player p1 = new Player();


    public void play() {
        System.out.println("Hello world");
        points += 10;
        System.out.println("current points: " + points);

    }

    public void changePoints(int newPoints) {
        points = newPoints;
        System.out.println("Points have been set to: " + points);
    }


    public static void main(String[] args) {
        System.out.println("Hejsan world");
        ClickGame game = new ClickGame();
        game.play();
        game.changePoints(50);
    }


}
