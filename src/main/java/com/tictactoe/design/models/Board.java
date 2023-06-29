package com.tictactoe.design.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int size;
	private List<List<Cell>> board;

	public Board(int dimensionsOfBoard) {
		// TODO Auto-generated constructor stub
		this.size = dimensionsOfBoard;
		this.board = new ArrayList<List<Cell>>();

		for (int i = 0; i < dimensionsOfBoard; ++i) {
			board.add(new ArrayList<Cell>());

			for (int j = 0; j < dimensionsOfBoard; ++j) {
				board.get(i).add(new Cell(i, j));
			}
		}

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<List<Cell>> getBoard() {
		return board;
	}

	public void setBoard(List<List<Cell>> board) {
		this.board = board;
	}
	
	public void printBoard() {
		
		for(List<Cell> row: board) {
			for(Cell col: row) {
				if(col.getPlayer()==null) {
					System.out.print("| - |");
				}
				else {
					System.out.print("| "+col.getPlayer().getSymbol().getaChar()+" |");
				}
			}
			System.out.println();
		}
	}
}
