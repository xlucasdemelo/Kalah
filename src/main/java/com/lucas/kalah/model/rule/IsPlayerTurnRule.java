package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.Game;
import com.lucas.kalah.model.Turn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class IsPlayerTurnRule implements GameRule{

    private Game game;

    @Override
    public Boolean validate(Turn turn) {
        return game.getCurrentPLayerTurn().getName().equals(turn.getPlayer().getName());
    }

}
