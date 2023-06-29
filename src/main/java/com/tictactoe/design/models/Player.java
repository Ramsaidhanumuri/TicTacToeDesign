package com.tictactoe.design.models;

import java.util.Scanner;

public class Player {

	private Long id;
	private String name;
	private Symbol symbol;
	private PlayerType playerType;
	private Scanner scanner;

	public Player(Long id, String name, Symbol symbol, PlayerType playerType) {
		this.symbol = symbol;
		this.name = name;
		this.id = id;
		this.playerType = playerType;
		this.scanner = new Scanner(System.in);
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public Move makeMove(Board board) {
		System.out.println("Please tell row count where you want to move(starting from 0)");
		int row = scanner.nextInt();
		
		System.out.println("Please tell col count where you want to move(starting from 0)");
		int col = scanner.nextInt();
		
		return new Move(new Cell(row, col), this);
	}
}
