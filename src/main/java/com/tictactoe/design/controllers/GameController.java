package com.tictactoe.design.controllers;

import java.util.List;

import com.tictactoe.design.exceptions.MoreThenOneBotException;
import com.tictactoe.design.exceptions.MoreThenOneSymbolException;
import com.tictactoe.design.exceptions.PlayerCountAndDimensionMissMatchException;
import com.tictactoe.design.models.Game;
import com.tictactoe.design.models.GameState;
import com.tictactoe.design.models.Player;
import com.tictactoe.design.strategies.winningstrategies.WinningStrategies;

public class GameController {

	public Game startGame(int dimensionsOfBoard, List<Player> players, List<WinningStrategies> winningStrategies)
			throws MoreThenOneSymbolException, MoreThenOneBotException, PlayerCountAndDimensionMissMatchException {
		
		return Game.getBuilder()
				.setDimensionsOfBoard(dimensionsOfBoard)
				.setPlayers(players)
				.setWinningStrategies(winningStrategies)
				.build();
	}
	
	public void makeMove(Game game) {
		game.makeMove();
	}
	
	public void undo(Game game) {
		game.undo();
	}
	
	public GameState checkStatus(Game game) {
		return game.getGameState();
	}
	
	public Player getWinner(Game game) {
		return game.getWinner();
	}
	
	public void printBoard(Game game) {
		game.printBoard();
	}
}
