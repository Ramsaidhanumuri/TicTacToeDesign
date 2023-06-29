package com.tictactoe.design.strategies.winningstrategies;

import java.util.HashMap;
import java.util.Map;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Move;
import com.tictactoe.design.models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategies {

	Map<Symbol, Integer> leftDiagonal = new HashMap<Symbol, Integer>();
	Map<Symbol, Integer> rightDiagonal = new HashMap<Symbol, Integer>();

	@Override
	public boolean checkWinner(Board board, Move move) {
		// TODO Auto-generated method stub
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();

		if (row == col) {

			if (!leftDiagonal.containsKey(symbol)) {
				leftDiagonal.put(symbol, 0);
			}

			leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);

			if (leftDiagonal.get(symbol) == board.getSize()) {
				return true;
			}
		}

		if (row + col == board.getSize() - 1) {

			if (!rightDiagonal.containsKey(symbol)) {
				rightDiagonal.put(symbol, 0);
			}

			rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);

			if (rightDiagonal.get(symbol) == (board.getSize())) {
				return false;
			}

		}

		return false;
	}

	@Override
	public void handleUndo(Board board, Move move) {
		// TODO Auto-generated method stub
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();

		if (row == col) {

			leftDiagonal.put(symbol, leftDiagonal.get(symbol) - 1);

		}

		if (row + col == board.getSize() - 1) {

			rightDiagonal.put(symbol, rightDiagonal.get(symbol) - 1);

		}

	}

}
