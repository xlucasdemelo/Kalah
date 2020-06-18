package com.lucas.kalah.model.Move;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Turn;

public interface GameMove {

    public void move(Game game, Turn turn);

}
