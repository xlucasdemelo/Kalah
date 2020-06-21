package com.lucas.kalah.orchestrator;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;

/**
 * Orchesttrator class will be responsible to orchestrate game rules and moves.
 * this class will be responsible to orchestrate the change of state of the Game
 */
public interface KalahOrchestrator {
    public Game performMove(Turn turn);

    public Game getGame();

    public Game restartGame();
}
