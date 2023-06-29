package com.tictactoe.design.strategies.botplayingstrategies;

import com.tictactoe.design.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

	public static BotPlayingStrategy getBotPlayingStrategyForDifficulty(BotDifficultyLevel level) {
		return new EasyBotPlayingStrategy();
	}
}
