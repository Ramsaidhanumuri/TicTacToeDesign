package com.tictactoe.design.strategies.winningstrategies;

import java.util.HashMap;
import java.util.Map;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Move;
import com.tictactoe.design.models.Symbol;

public class ColWinningStrategy implements WinningStrategies {

	private Map<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

	@Override
	public boolean checkWinner(Board board, Move move) {
		// TODO Auto-generated method stub
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();

		if (!counts.containsKey(col)) {
			counts.put(col, new HashMap<>());
		}

		Map<Symbol, Integer> colMap = counts.get(col);

		if (!colMap.containsKey(symbol)) {
			colMap.put(symbol, 0);
		}

		colMap.put(symbol, colMap.get(symbol) + 1);

		if (colMap.get(symbol) == board.getSize()) {
			return true;
		}

		return false;
	}

	@Override
	public void handleUndo(Board board, Move move) {
		// TODO Auto-generated method stub
		int col = move.getCell().getCol();
		Symbol symbol = move.getPlayer().getSymbol();

		Map<Symbol, Integer> colMap = counts.get(col);

		colMap.put(symbol, colMap.get(symbol) - 1);

	}

}
