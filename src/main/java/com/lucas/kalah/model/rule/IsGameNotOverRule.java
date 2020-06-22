package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Game;
import com.lucas.kalah.model.game.Turn;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IsGameNotOverRule implements GameRule{

    private Game game;

    @Override
    public Boolean validate(Turn turn) {
        return !this.game.getGameOver();
    }

}
