package com.tictactoe.design.models;

import com.tictactoe.design.strategies.botplayingstrategies.BotPlayingStrategy;
import com.tictactoe.design.strategies.botplayingstrategies.BotPlayingStrategyFactory;

public class Bot extends Player {

	private BotDifficultyLevel botDifficultyLevel;
	private BotPlayingStrategy botPlayingStrategy;

	public Bot(Long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
		super(id, name, symbol, PlayerType.BOT);
		this.botDifficultyLevel = botDifficultyLevel;
		this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficulty(botDifficultyLevel);
	}

	public BotDifficultyLevel getBotDifficultyLevel() {
		return botDifficultyLevel;
	}

	public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
		this.botDifficultyLevel = botDifficultyLevel;
	}

	@Override
	public Move makeMove(Board board) {

		Move move = botPlayingStrategy.makeMove(board);
		move.setPlayer(this);

		return move;
	}
}
