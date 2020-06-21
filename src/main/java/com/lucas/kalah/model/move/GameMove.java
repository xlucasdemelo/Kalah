package com.lucas.kalah.model.move;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;

public interface GameMove {

    public Game move(Game game, Turn turn);
}
