package com.KalleH.exGame;

import com.KalleH.exGame.controller.GameController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExGameApplication {



	public static void main(String[] args) {


		GameController game = new GameController();




		SpringApplication.run(ExGameApplication.class, args);

	}

}
