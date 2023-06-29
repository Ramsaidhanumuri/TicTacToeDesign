package com.tictactoe.design.strategies.botplayingstrategies;

import java.util.List;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Cell;
import com.tictactoe.design.models.CellState;
import com.tictactoe.design.models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

	@Override
	public Move makeMove(Board board) {
		// TODO Auto-generated method stub
		for(List<Cell> row: board.getBoard()) {
			for(Cell col: row) {
				if(col.getCellState().equals(CellState.EMPTY)) {
					return new Move(col, null);
				}
			}
		}
		return null;
	}

}
