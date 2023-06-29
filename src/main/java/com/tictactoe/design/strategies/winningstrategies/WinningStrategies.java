package com.tictactoe.design.strategies.winningstrategies;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Move;

public interface WinningStrategies {

	boolean checkWinner(Board board, Move move);
	
	void handleUndo(Board board, Move move);
}
