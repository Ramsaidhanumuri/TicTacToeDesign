package com.tictactoe.design.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tictactoe.design.exceptions.MoreThenOneBotException;
import com.tictactoe.design.exceptions.MoreThenOneSymbolException;
import com.tictactoe.design.exceptions.PlayerCountAndDimensionMissMatchException;
import com.tictactoe.design.strategies.winningstrategies.WinningStrategies;

public class Game {

	private List<Player> players;
	private Board board;
	private List<Move> moves;
	private GameState gameState;
	private int nextMovePlayerIndex;
	private List<WinningStrategies> winningStrategies;
	private Player winner;

	public static Builder getBuilder() {
		return new Builder();
	}

	private Game(int dimensionsOfBoard, List<Player> players, List<WinningStrategies> winningStrategies) {
		this.players = players;
		this.board = new Board(dimensionsOfBoard);
		this.moves = new ArrayList<Move>();
		this.gameState = GameState.IN_PROGRESS;
		this.nextMovePlayerIndex = 0;
		this.winningStrategies = winningStrategies;

	}

	public static class Builder {

		private int dimensionsOfBoard;
		private List<Player> players;
		private List<WinningStrategies> winningStrategies;

		public Builder() {
			// TODO Auto-generated constructor stub
			this.dimensionsOfBoard = 0;
			this.players = new ArrayList<>();
			this.winningStrategies = new ArrayList<>();
		}

		public Builder setDimensionsOfBoard(int dimensionsOfBoard) {
			this.dimensionsOfBoard = dimensionsOfBoard;
			return this;
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder addPlayer(Player player) {
			players.add(player);
			return this;
		}

		public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}

		public Builder addWinningStrategy(WinningStrategies winningStrategy) {
			this.winningStrategies.add(winningStrategy);
			return this;
		}

		private void validateBotCount() throws MoreThenOneBotException {
			int botCount = 0;

			for (Player player : players) {
				if (player.getPlayerType().equals(PlayerType.BOT)) {
					botCount += 1;
				}
			}

			if (botCount > 1) {
				throw new MoreThenOneBotException();
			}
		}

		private void validateSymbolCount() throws MoreThenOneSymbolException {
			Map<Character, Integer> symbolCount = new HashMap<>();

			for (Player player : players) {
				if (!symbolCount.containsKey(player.getSymbol().getaChar())) {
					symbolCount.put(player.getSymbol().getaChar(), 0);
				}

				symbolCount.put(player.getSymbol().getaChar(), symbolCount.get(player.getSymbol().getaChar()) + 1);

				if (symbolCount.get(player.getSymbol().getaChar()) > 1) {
					throw new MoreThenOneSymbolException();
				}
			}

		}

		private void validatePlayerCount() throws PlayerCountAndDimensionMissMatchException {
			if (players.size() != dimensionsOfBoard - 1) {
				throw new PlayerCountAndDimensionMissMatchException();
			}
		}

		private void validate()
				throws MoreThenOneSymbolException, MoreThenOneBotException, PlayerCountAndDimensionMissMatchException {

			validatePlayerCount();
			validateBotCount();
			validateSymbolCount();
		}

		public Game build()
				throws MoreThenOneSymbolException, MoreThenOneBotException, PlayerCountAndDimensionMissMatchException {

			validate();

			return new Game(dimensionsOfBoard, players, winningStrategies);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getNextMovePlayerIndex() {
		return nextMovePlayerIndex;
	}

	public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
		this.nextMovePlayerIndex = nextMovePlayerIndex;
	}

	public List<WinningStrategies> getWinningStrategies() {
		return winningStrategies;
	}

	public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
		this.winningStrategies = winningStrategies;
	}

	public void printBoard() {
		board.printBoard();
	}

	public boolean validateMove(Move move) {

		int row = move.getCell().getRow();
		int col = move.getCell().getCol();

		if (row >= board.getSize()) {
			return false;
		}

		if (col >= board.getSize()) {
			return false;
		}

		if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
			return true;
		}
		return false;
	}

	private boolean checkWinner(Board board, Move move) {
		for (WinningStrategies winningStrategies : winningStrategies) {
			if (winningStrategies.checkWinner(board, move)) {
				return true;
			}
		}
		return false;
	}

	public void makeMove() {
		Player currentPlayer = players.get(nextMovePlayerIndex);

		System.out.println("It is " + currentPlayer.getName() + "s' turn");

		Move move = currentPlayer.makeMove(board);

		if (!validateMove(move)) {
			System.out.println("Invalid Move. Please try again.");
			return;
		}

		int row = move.getCell().getRow();
		int col = move.getCell().getCol();

		Cell cellToChange = board.getBoard().get(row).get(col);
		cellToChange.setCellState(CellState.FILLED);
		cellToChange.setPlayer(currentPlayer);

		Move finalMoveObject = new Move(cellToChange, currentPlayer);
		moves.add(finalMoveObject);

		nextMovePlayerIndex += 1;
		nextMovePlayerIndex %= players.size();

		if (checkWinner(board, finalMoveObject)) {
			gameState = GameState.WIN;
			winner = currentPlayer;
		} else if (moves.size() == this.board.getSize() * this.board.getSize()) {
			gameState = GameState.DRAW;
		}

	}

	public Player getWinner() {
		return winner;
	}

	public void undo() {
		
		if(moves.size()==0) {
			System.out.println("No moves to Undo");
			return;
		}
		
		Move lastMove = moves.get(moves.size() - 1);
		moves.remove(lastMove);

		Cell cell = lastMove.getCell();
		cell.setPlayer(null);
		cell.setCellState(CellState.EMPTY);

		for(WinningStrategies winningStrategies: winningStrategies) {
			winningStrategies.handleUndo(board, lastMove);
		}
		
		nextMovePlayerIndex -= 1;
		nextMovePlayerIndex = (nextMovePlayerIndex + players.size())%players.size();
		
	}

}
