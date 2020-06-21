package com.lucas.kalah.model.rule;

import com.lucas.kalah.model.game.Turn;

/**
 * A player cannot move seeds from the endzone
 */
public class IsNotEndZoneRule implements GameRule{

    @Override
    public Boolean validate(Turn turn) {
        return turn.getHouseIndex() != turn.getPlayer().getLastPitIndex();
    }
}
