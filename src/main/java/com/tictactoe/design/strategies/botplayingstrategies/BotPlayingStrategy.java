package com.tictactoe.design.strategies.botplayingstrategies;

import com.tictactoe.design.models.Board;
import com.tictactoe.design.models.Move;

public interface BotPlayingStrategy {

	Move makeMove(Board board);
}
