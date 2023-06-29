package com.tictactoe.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tictactoe.design.controllers.GameController;
import com.tictactoe.design.models.Bot;
import com.tictactoe.design.models.BotDifficultyLevel;
import com.tictactoe.design.models.Game;
import com.tictactoe.design.models.GameState;
import com.tictactoe.design.models.Player;
import com.tictactoe.design.models.PlayerType;
import com.tictactoe.design.models.Symbol;
import com.tictactoe.design.strategies.winningstrategies.ColWinningStrategy;
import com.tictactoe.design.strategies.winningstrategies.DiagonalWinningStrategy;
import com.tictactoe.design.strategies.winningstrategies.RowWinningStrategy;
import com.tictactoe.design.strategies.winningstrategies.WinningStrategies;

@SpringBootApplication
public class TictactoedesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictactoedesignApplication.class, args);

		GameController gameController = new GameController();
		Scanner scanner = new Scanner(System.in);

		try {
			int dimension = 3;
			List<Player> players = new ArrayList<Player>();
			List<WinningStrategies> winningStrategies = List.of(
					new RowWinningStrategy(),
					new ColWinningStrategy(),
					new DiagonalWinningStrategy());

			players.add(new Player(1L, "Ram", new Symbol('R'), PlayerType.HUMAN));
			players.add(new Bot(2L, "GPT", new Symbol('G'), BotDifficultyLevel.HARD));

			Game game = gameController.startGame(dimension, players, winningStrategies);

			while (gameController.checkStatus(game).equals(GameState.IN_PROGRESS)) {
				gameController.printBoard(game);
				
				System.out.println("Does anyone want to undo? (y/n)");
				String undoAns = scanner.next();
				
				if(undoAns.equalsIgnoreCase("y")) {
					gameController.undo(game);
					continue;
				}
				
				gameController.makeMove(game);
			}

			if(gameController.checkStatus(game).equals(GameState.DRAW)) {
				gameController.printBoard(game);
				System.out.println("Game is Draw");
			}
			else {
				gameController.printBoard(game);
				System.out.println("Game is Finished");
				System.out.println("Winner is: "+gameController.getWinner(game).getName());
			}
			
		} catch (Exception e) {
			System.out.println("Somthing is happend!!!" + e);
		}
	}
}
