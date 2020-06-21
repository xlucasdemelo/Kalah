package com.lucas.kalah.orchestrator;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;

public interface KalahOrchestrator {
    public Game performMove(Turn turn);

    public Game getGame();
}
