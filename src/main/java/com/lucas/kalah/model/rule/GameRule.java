package com.lucas.kalah.model.rule;

import com.lucas.kalah.exception.GameValidationException;
import com.lucas.kalah.model.game.Turn;

public interface GameRule {
    public Boolean validate(Turn turn);
}
