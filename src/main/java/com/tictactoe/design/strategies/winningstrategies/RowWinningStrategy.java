package com.tictactoe.design.strategies.winningstrategies;

import java.util.HashMap;
import java.util.Map;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Move;
import com.tictactoe.design.models.Symbol;

public class RowWinningStrategy implements WinningStrategies {

	private Map<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

	@Override
	public boolean checkWinner(Board board, Move move) {
		int row = move.getCell().getRow();
		Symbol symbol = move.getPlayer().getSymbol();

		if (!counts.containsKey(row)) {
			counts.put(row, new HashMap<>());
		}

		Map<Symbol, Integer> rowMap = counts.get(row);

		if (!rowMap.containsKey(symbol)) {
			rowMap.put(symbol, 0);
		}

		rowMap.put(symbol, rowMap.get(symbol) + 1);

		if (rowMap.get(symbol) == board.getSize()) {
			return true;
		}

		return false;
	}

	@Override
	public void handleUndo(Board board, Move move) {
		// TODO Auto-generated method stub
		int row = move.getCell().getRow();
		Symbol symbol = move.getPlayer().getSymbol();

		Map<Symbol, Integer> rowMap = counts.get(row);

		rowMap.put(symbol, rowMap.get(symbol) - 1);

	}

}
